package me.redstoner2019.uno.plugin.events;

public class Event {
    private boolean isCancelled;

    public Event() {
        this.isCancelled = false;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }
}
