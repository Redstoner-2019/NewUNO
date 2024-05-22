package me.redstoner2019.uno.server.networking;

import me.redstoner2019.api.networkingapi.events.ClientConnectEvent;
import me.redstoner2019.api.networkingapi.events.PacketListener;
import me.redstoner2019.api.networkingapi.odserver.ClientHandler;
import me.redstoner2019.api.networkingapi.odserver.ODServer;
import me.redstoner2019.api.networkingapi.util.ConnectionProtocol;
import me.redstoner2019.uno.util.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnector extends ODServer {
    public static Logger logger = new Logger("SERVER");
    public static void start(int port){
        setup(port, ConnectionProtocol.UDP);

        setClientConnectEvent(new ClientConnectEvent() {
            @Override
            public void connectEvent(ClientHandler handler) throws Exception {
                handler.startPacketSender();
                handler.startPacketListener(new PacketListener() {
                    @Override
                    public void packetRecievedEvent(Object packet) {
                        logger.log(packet);
                    }
                });
            }
        });

        start();
    }
}
