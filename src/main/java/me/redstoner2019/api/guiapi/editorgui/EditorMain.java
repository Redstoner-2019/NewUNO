package me.redstoner2019.api.guiapi.editorgui;

import me.redstoner2019.api.guiapi.GUIFrame;

public class EditorMain extends GUIFrame {
    public static void main(String[] args) {
        startGUI();

        register("menu",new Menu().init());
        switchGui("menu");
    }
}
