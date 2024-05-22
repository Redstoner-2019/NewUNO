package me.redstoner2019.api.authapi.client;

import me.redstoner2019.api.authapi.packets.JSONPacket;
import me.redstoner2019.api.networkingapi.events.PacketListener;
import me.redstoner2019.api.networkingapi.odclient.ODClient;
import me.redstoner2019.api.networkingapi.util.ConnectionProtocol;
import org.json.JSONObject;

public class AuthenticatorServer extends ODClient {

    public String authenticationServerIp = "localhost";
    public int authenticationServerPort = 8009;
    private JSONObject result;
    private final Object REF = new Object();

    public void main(String[] args) {
        setup();
    }

    public void setup(){
        setPacketListener(new PacketListener() {
            @Override
            public void packetRecievedEvent(Object o) {
                if(o instanceof JSONPacket p){
                    result = new JSONObject(p.getJson());
                    synchronized (REF){
                        REF.notify();
                    }
                }
            }
        });
        connect(authenticationServerIp, authenticationServerPort, ConnectionProtocol.UDP);
        startSender();
    }

    public JSONObject getTokenInfo(String token){
        JSONObject object = new JSONObject();
        object.put("header","server");
        object.put("request","token-info");
        object.put("token",token);
        sendObject(new JSONPacket(object.toString()));
        try {
            synchronized (REF){
                REF.wait();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    public JSONObject getAccountInfo(String username){
        JSONObject object = new JSONObject();
        object.put("header","server");
        object.put("request","token-info");
        object.put("username",username);
        sendObject(new JSONPacket(object.toString()));
        try {
            synchronized (REF){
                REF.wait();

            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
