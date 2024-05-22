package me.redstoner2019.api.networkingapi.events;

import me.redstoner2019.api.networkingapi.odserver.ClientHandler;

public interface ClientConnectEvent {
    void connectEvent(ClientHandler handler) throws Exception;
}
