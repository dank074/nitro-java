package com.nitro.core.communication.connections;

import com.nitro.core.communication.messages.IMessageComposer;
import com.nitro.core.communication.messages.IMessageEvent;

import java.util.Map;

public interface IConnectionContainer {

    IConnection addConnection(IConnection connection);
    void removeConnection(IConnection connection);
    void removeAllConnections();
    void handleEvent(IConnection connection, IMessageEvent event);
    void processComposer(IConnection connection, IMessageComposer composer);
    Map<String, Map<Integer, IConnection>> getConnections();
}
