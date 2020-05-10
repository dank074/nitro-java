package com.nitro.core.communication.connections;

import java.util.Map;

public interface IConnectionContainer {

    IConnection addConnection(IConnection connection);
    void removeConnection(IConnection connection);
    void removeAllConnections();
    Map<String, Map<Integer, IConnection>> getConnections();
}
