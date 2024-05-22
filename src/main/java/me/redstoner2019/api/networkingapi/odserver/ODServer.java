package me.redstoner2019.api.networkingapi.odserver;

import me.redstoner2019.api.networkingapi.defaultpackets.ACK;
import me.redstoner2019.api.networkingapi.defaultpackets.ConnectSuccessPacket;
import me.redstoner2019.api.networkingapi.defaultpackets.ConnectionRejectedPacket;
import me.redstoner2019.api.networkingapi.events.ClientConnectEvent;
import me.redstoner2019.api.networkingapi.events.PacketReadFailedEvent;
import me.redstoner2019.api.networkingapi.util.ConnectionProtocol;
import me.redstoner2019.api.networkingapi.util.Util;
import org.json.JSONObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import static me.redstoner2019.api.networkingapi.util.ConnectionProtocol.TCP;

public class ODServer {
    private static int PORT = 3030;
    private static ClientConnectEvent clientConnectEvent = handler -> {};
    private static PacketReadFailedEvent packetReadFailedEvent = (error, handler) -> {};
    private static final List<ClientHandler> clients = new ArrayList<>();
    private static ConnectionProtocol protocol;
    public static ConnectionProtocol getProtocol(){
        return protocol;
    }
    private static boolean debugMode = false;

    public static java.util.List<ClientHandler> getClients() {
        return clients;
    }

    public static void setup(int port, ConnectionProtocol connProtocol){
        PORT = port;
        protocol = connProtocol;
    }
    public static void setDebugMode(boolean debug){
        debugMode = debug;
    }
    public static void start(){
        try {
            Util.log("Starting Server on Port " + InetAddress.getLocalHost().getHostAddress() + ":" +  PORT);
            Util.log("Using protocol: " + protocol);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            Util.log("Couldn't start Server: " + e.getLocalizedMessage());
            return;
        }
        while (serverSocket.isBound()){
            try{
                final Socket socket = serverSocket.accept();
                final ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                final ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            ClientHandler handler = new ClientHandler(in,out,socket);
                            Object o = in.readObject();

                            if(o instanceof String){
                                JSONObject packet = new JSONObject((String) o);
                                if(!packet.has("header")) {
                                    Util.log("Invalid login Request");
                                    JSONObject object = new JSONObject();
                                    object.put("header","invalid-login-request");
                                    object.put("message","Invalid login request");
                                    handler.sendObject(object.toString());
                                    return;
                                }
                                switch (packet.getString("header")){
                                    case "connect-request": {
                                        ConnectionProtocol prot = ConnectionProtocol.valueOf(packet.getString("protocol"));
                                        if(prot.equals(protocol)){
                                            Util.log("Login validation complete");
                                            clients.add(handler);
                                            //if(ODServer.getProtocol().equals(TCP)) handler.sendObject(new ACK(p.uuid,0)); TODO: reimplement
                                            handler.sendObject(new ConnectSuccessPacket());
                                            clientConnectEvent.connectEvent(handler);
                                            return;
                                        } else {
                                            Util.log("Incorrect Protocol " + prot.name());
                                            handler.sendObject(new ConnectionRejectedPacket("Incorrect Protocol"));
                                        }
                                        break;
                                    }
                                    case "ACK": {
                                        break;
                                    }
                                }
                            }
                            handler.disconnect();
                        } catch (Exception e) {
                            if(debugMode) Util.log(e.getLocalizedMessage());
                        }
                    }
                });
                t.start();
            }catch (Exception ignored){
                if(debugMode) Util.log(ignored.getLocalizedMessage() + "");
            }
        }
    }

    public static void setClientConnectEvent(ClientConnectEvent e){
        clientConnectEvent = e;
    }
    public static void setPacketReadFailedEvent(PacketReadFailedEvent e){
        packetReadFailedEvent = e;
    }
    public static void sendObject(Object packet, ObjectOutputStream out, Socket socket){
        if(!socket.isConnected()){
            Util.log("Not Connected, can't send Object.");
            return;
        }
        try {
            out.writeObject(packet);
            out.flush();
        } catch (IOException e) {
            Util.log("Error sending Object.");
        }
    }
    public static void broadcastMessage(Object packet){
        for(ClientHandler c : clients){
            c.sendObject(packet);
        }
    }
    public static void broadcastMessage(Object packet, List<ClientHandler> exceptions){
        Util.log(clients.size() + " Broadcasting " + packet);
        for(ClientHandler c : clients){
            if(exceptions.contains(c)) continue;
            c.sendObject(packet);
        }
    }
}