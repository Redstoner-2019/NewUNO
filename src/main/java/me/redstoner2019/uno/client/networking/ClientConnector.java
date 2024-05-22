package me.redstoner2019.uno.client.networking;

import me.redstoner2019.api.networkingapi.events.ConnectionFailedEvent;
import me.redstoner2019.api.networkingapi.events.ConnectionLostEvent;
import me.redstoner2019.api.networkingapi.events.ConnectionSuccessEvent;
import me.redstoner2019.api.networkingapi.events.PacketListener;
import me.redstoner2019.api.networkingapi.odclient.ODClient;
import me.redstoner2019.api.networkingapi.util.ConnectionProtocol;
import me.redstoner2019.uno.client.gui.Application;
import me.redstoner2019.uno.client.gui.subguis.ServerMenu;

import java.awt.*;

public class ClientConnector extends ODClient {
    public void connect(String address, int port){
        startSender();
        setPacketListener(new PacketListener() {
            @Override
            public void packetRecievedEvent(Object packet) {

            }
        });
        setConnectionFailedEvent(new ConnectionFailedEvent() {
            @Override
            public void onConnectionFailedEvent(Exception reason) {
                Application.switchGui("server-menu");
                ServerMenu.serverInfo.setText("Unable to connect");
                ServerMenu.serverInfo.setForeground(Color.RED);
            }
        });
        setConnectionLostEvent(new ConnectionLostEvent() {
            @Override
            public void onConnectionLostEvent(String reason) {
                Application.switchGui("server-menu");
                ServerMenu.serverInfo.setText("Lost Connection");
                ServerMenu.serverInfo.setForeground(Color.RED);
            }
        });
        setOnConnectionSuccessEvent(new ConnectionSuccessEvent() {
            @Override
            public void onConnectionSuccess() {
                if(Application.getGUI().equals("server-menu")){
                    Application.switchGui("lobby-selector");
                }
            }
        });
        connect(address,port, ConnectionProtocol.UDP);
    }
}
