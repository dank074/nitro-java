package com.nitro.core.communication.events.connection;

import com.nitro.core.communication.connections.IConnection;

public class ConnectionAddedEvent extends ConnectionEvent {

    public ConnectionAddedEvent(IConnection connection) {
        super(connection);
    }
}
