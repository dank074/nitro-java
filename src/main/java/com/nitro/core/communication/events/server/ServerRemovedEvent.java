package com.nitro.core.communication.events.server;

import com.nitro.core.communication.servers.IServer;

public class ServerRemovedEvent extends ServerEvent {

    private final IServer server;

    public ServerRemovedEvent(IServer server) {
        this.server = server;
    }

    public IServer getServer() {
        return this.server;
    }
}
