package com.nitro.app;

import com.nitro.core.communication.events.connection.ConnectionAddedEvent;
import com.nitro.core.communication.events.server.ServerAddedEvent;
import com.nitro.core.events.EventHandler;
import com.nitro.core.events.IEventListener;

public class EventListener implements IEventListener {

    @EventHandler
    public void onServerAddedEvent(ServerAddedEvent event) {
        System.out.println("The server will be added");
    }

    @EventHandler
    public void onConnectionAddedEvent(ConnectionAddedEvent event) {
        System.out.println("The connection will be added");
    }
}
