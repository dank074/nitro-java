package com.nitro.core.communication.servers;

import com.nitro.core.communication.connections.IConnection;

import java.util.Map;

public interface IServerContainer {

    IServer addServer(IServer server);
    void removeServer(IServer server);
    void removeAllServers();
    IConnection addConnection(IServer server, IConnection connection);
    void removeConnection(IServer server, IConnection connection);
    Map<Integer, IServer> getServers();
}
