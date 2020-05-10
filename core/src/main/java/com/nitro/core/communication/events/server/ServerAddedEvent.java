package com.nitro.core.communication.events.server;

import com.nitro.core.communication.servers.IServer;

public class ServerAddedEvent extends ServerEvent {

    public ServerAddedEvent(IServer server) {
        super(server);
    }
}
