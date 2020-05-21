package com.nitro.common.events;

public class NitroEvent {

    private boolean cancelled;

    public NitroEvent() {
        this.cancelled = false;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public void setCancelled(boolean flag) {
        this.cancelled = flag;
    }
}
