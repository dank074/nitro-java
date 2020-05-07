package com.nitro.core.communication;

import com.nitro.core.communication.servers.IServer;

public interface ICommunicationManager {

    IServer addServer(IServer server);
    void removeServer(IServer server) throws InterruptedException;
    void removeAllServers() throws InterruptedException;
}
