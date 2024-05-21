package me.redstoner2019.uno.server.networking;

import me.redstoner2019.uno.util.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnector {
    public static Logger logger = new Logger("SERVER");
    public static void start(int port){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (!serverSocket.isClosed()){
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Socket socket = serverSocket.accept();
                        } catch (IOException e) {
                            logger.err("Error intercepting socket");
                        }
                    }
                });
                t.start();
            }
        } catch (IOException e) {
            logger.err("Unable to start Server: " + e.getLocalizedMessage());
        }
    }
}
