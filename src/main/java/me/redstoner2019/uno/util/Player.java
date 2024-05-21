package me.redstoner2019.uno.util;

public class Player {
    private String username;
    private String displayname;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public Player(String username, String displayname) {
        this.username = username;
        this.displayname = displayname;
    }

    @Override
    public String toString() {
        return "Player{" +
                "username='" + username + '\'' +
                ", displayname='" + displayname + '\'' +
                '}';
    }
}
