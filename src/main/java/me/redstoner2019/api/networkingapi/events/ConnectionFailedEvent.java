package me.redstoner2019.api.networkingapi.events;

public interface ConnectionFailedEvent {
    void onConnectionFailedEvent(Exception reason);
}
