package com.nitro.core.events;

public interface IEventDispatcher {

    void dispose();
    void registerEventListener(IEventListener listener);
    <T extends NitroEvent> T dispatchEvent(T event);
}
