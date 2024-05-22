package me.redstoner2019.api.networkingapi.events;

public interface ConnectionLostEvent {
    void onConnectionLostEvent(String reason);
}
