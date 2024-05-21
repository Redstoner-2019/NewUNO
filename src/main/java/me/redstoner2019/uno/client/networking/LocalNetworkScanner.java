package me.redstoner2019.uno.client.networking;

import me.redstoner2019.uno.util.Logger;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

public class LocalNetworkScanner {
    private static final int TIMEOUT = 100;
    private static Logger logger = new Logger("NETWORK-SEARCH");

    public static void scan(List<String> serverList, int port) throws IOException {
        serverList.clear();
        logger.log(InetAddress.getLocalHost().getHostAddress());
        String[] ipArr = InetAddress.getLocalHost().getHostAddress().replace(".", "-").split("-");

        final int[] ipsScanned = {0};
        final int[] threadsStarted = {0};

        for (int i = 0; i <= 255; i++) {
            int finalI = i;
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    threadsStarted[0]++;
                    String ip = ipArr[0] + "." + ipArr[1] + "." + ipArr[2] + "." + finalI;
                    if (isReachable(ip) && isOpen(ip, port)) {
                        if(!serverList.contains(ip)) serverList.add(ip);
                    }
                    ipsScanned[0]++;
                }
            });
            t.start();
        }
        logger.log(serverList.size());
    }
    private static boolean isReachable(String ip) {
        try {
            return InetAddress.getByName(ip).isReachable(1000);
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isOpen(String ip, int port) {
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(ip, port), TIMEOUT);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            try {
                socket.close();
            } catch (IOException e){
                // Ignore Exception
            }
        }
    }
}