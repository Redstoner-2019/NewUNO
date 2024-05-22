package me.redstoner2019.api.networkingapi.events;

import me.redstoner2019.api.networkingapi.odserver.ClientHandler;

public interface PacketReadFailedEvent {
    void onPacketReadFailed(String error, ClientHandler handler);
}
