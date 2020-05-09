package com.nitro.core.events;

public class NitroEvent {

    private IEventListener eventListener;
    private boolean cancelled;

    public NitroEvent() {
        this.eventListener = null;
        this.cancelled = false;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public void setCancelled(boolean flag) {
        this.cancelled = flag;
    }

    public IEventListener getEventListener() {
        return this.eventListener;
    }

    public void setEventListener(IEventListener eventListener) {
        this.eventListener = eventListener;
    }
}
