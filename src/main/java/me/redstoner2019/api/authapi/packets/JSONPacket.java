package me.redstoner2019.api.authapi.packets;

import me.redstoner2019.api.networkingapi.defaultpackets.Packet;
import me.redstoner2019.api.networkingapi.util.Util;

public class JSONPacket extends Packet {
    private String json;

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public JSONPacket(String json) {
        this.json = json;
    }

    @Override
    public String toString() {
        return Util.prettyJSON(json);
    }
}
