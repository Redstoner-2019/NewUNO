package me.redstoner2019.uno.client.gui.subguis;

import me.redstoner2019.api.guiapi.GUI;
import me.redstoner2019.api.guiapi.design.Design;
import me.redstoner2019.api.guiapi.design.Setting;
import me.redstoner2019.uno.client.ClientMain;
import me.redstoner2019.uno.client.gui.Application;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends GUI {
    public static JLabel title = new JLabel("Login Account");
    public static JTextField username = new JTextField();
    public static JPasswordField password = new JPasswordField();
    public static JLabel usernameLabel = new JLabel("Username:");
    public static JLabel passwordLabel = new JLabel("Password:");
    public static JButton loginButton = new JButton("LOGIN");
    public static JButton backButton = new JButton("BACK");
    public static JLabel createStatus = new JLabel();
    @Override
    public String getGUIName() {
        return "login-account-gui";
    }

    @Override
    public GUI init() {
        register(title,new Setting(0,0,1,.1));

        register(username,new Setting(.5,.2,.2,.05));
        register(password,new Setting(.5,.3,.2,.05));

        register(usernameLabel,new Setting(.3,.2,.2,.05));
        register(passwordLabel,new Setting(.3,.3,.2,.05));

        register(loginButton,new Setting(.3,.4,.4,.05));
        register(backButton,new Setting(.3,.475,.4,.05));

        registerNoDesign(createStatus,new Setting(0,.55,1,.45));

        Design.centerText(createStatus);

        Design.centerText(title);
        Design.setFontSize(title,50);

        Design.setFontSize(usernameLabel,20);
        Design.setFontSize(passwordLabel,20);

        Design.setFontSize(username,20);
        Design.setFontSize(password,20);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!ClientMain.authenticatorClient.isConnected()){
                    createStatus.setForeground(Color.RED);
                    createStatus.setText("Not connected to an authentication server!");
                    return;
                }
                JSONObject result = ClientMain.authenticatorClient.loginAccount(username.getText(),password.getText());
                if(result.getString("data").equals("login-success")){
                    createStatus.setForeground(Color.GREEN);
                    createStatus.setText("You are now logged in!");
                    ClientMain.TOKEN = result.getString("token");
                }
                if(result.getString("data").equals("incorrect-password")){
                    createStatus.setForeground(Color.RED);
                    createStatus.setText("You entered a wrong password.");
                }
                if(result.getString("data").equals("account-doesnt-exist")){
                    createStatus.setForeground(Color.RED);
                    createStatus.setText("This account does not exist.");
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Application.switchGui("settings-menu");
            }
        });

        Design.register(this);
        return this;
    }
}
