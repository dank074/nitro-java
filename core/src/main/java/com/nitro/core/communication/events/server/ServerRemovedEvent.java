package com.nitro.core.communication.events.server;

import com.nitro.core.communication.servers.IServer;

public class ServerRemovedEvent extends ServerEvent {

    public ServerRemovedEvent(IServer server) {
        super(server);
    }
}
