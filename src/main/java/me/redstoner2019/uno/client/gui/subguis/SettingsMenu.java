package me.redstoner2019.uno.client.gui.subguis;

import me.redstoner2019.api.guiapi.GUI;
import me.redstoner2019.api.guiapi.design.Design;
import me.redstoner2019.api.guiapi.design.Setting;
import me.redstoner2019.uno.Main;
import me.redstoner2019.uno.client.ClientMain;
import me.redstoner2019.uno.client.gui.Application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsMenu extends GUI {
    public static JLabel title = new JLabel("UNO");
    public static JLabel subtitle = new JLabel("Version " + Main.getVersion());
    public static JButton createAccount = new JButton("CREATE ACCOUNT");
    public static JButton loginAccount = new JButton("LOGIN");
    public static JButton manageAccount = new JButton("MANAGE ACCOUNT");
    public static JLabel currentAuthServer = new JLabel("localhost");
    public static JTextField newAuthServerIp = new JTextField("localhost");
    public static JButton setNewAuthServer = new JButton("Set Auth Server");
    public static JButton connectToAuthServer = new JButton("Connect To Auth Server");
    public static JButton mainMenu = new JButton("Main Menu");
    @Override
    public String getGUIName() {
        return "settings-menu";
    }

    @Override
    public GUI init() {
        register(title,new Setting(0, .1, 1, .1));
        register(subtitle,new Setting(0, .225, 1, .05));

        register(createAccount,new Setting(.1, .5, .2, .05));
        register(loginAccount,new Setting(.1, .6, .2, .05));
        register(manageAccount,new Setting(.1, .7, .2, .05));

        registerNoDesign(currentAuthServer,new Setting(.1, .4, .5, .05));
        register(newAuthServerIp,new Setting(.4, .5, .2, .05));
        register(setNewAuthServer,new Setting(.4, .6, .2, .05));
        register(connectToAuthServer,new Setting(.4, .7, .2, .05));

        register(mainMenu,new Setting(.1, .85, .8, .1));

        Design.register(this);

        Design.setFontSize(title,50);
        Design.setFontSize(subtitle,30);
        Design.setFontSize(currentAuthServer,20);

        Design.centerText(title);
        Design.setFontStyle(title, Font.BOLD);

        Design.centerText(subtitle);
        Design.setFontStyle(subtitle, Font.PLAIN);

        Design.centerText(currentAuthServer);
        Design.setFontStyle(currentAuthServer, Font.PLAIN);

        currentAuthServer.setForeground(Color.RED);

        setNewAuthServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentAuthServer.setText(newAuthServerIp.getText());
                ClientMain.authenticatorClient.setAddress(newAuthServerIp.getText());
                if(ClientMain.authenticatorClient.isConnected()){
                    ClientMain.authenticatorClient.disconnect();
                    currentAuthServer.setForeground(Color.RED);
                }
            }
        });

        connectToAuthServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        connectToAuthServer.setEnabled(false);
                        connectToAuthServer.setText("Connecting...");
                        if(ClientMain.authenticatorClient.isConnected()){
                            ClientMain.authenticatorClient.disconnect();
                        }
                        ClientMain.authenticatorClient.setup();
                        if(ClientMain.authenticatorClient.isConnected()){
                            currentAuthServer.setForeground(Color.GREEN);
                        } else {
                            currentAuthServer.setForeground(Color.RED);
                        }
                        connectToAuthServer.setEnabled(true);
                        connectToAuthServer.setText("Connect to Auth Server");
                    }
                });
                t.start();

            }
        });

        mainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Application.switchGui("main-menu");
            }
        });

        createAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Application.switchGui("create-account-gui");
            }
        });

        loginAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Application.switchGui("login-account-gui");
            }
        });

        manageAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Application.switchGui("manage-account-gui");
            }
        });
        return this;
    }
}
