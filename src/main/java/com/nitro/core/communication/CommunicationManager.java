package com.nitro.core.communication;

import com.nitro.core.communication.servers.IServer;
import com.nitro.core.communication.servers.IServerContainer;

import java.util.HashMap;
import java.util.Map;

public class CommunicationManager implements ICommunicationManager, IServerContainer {

    private Map<Integer, IServer> servers;

    public CommunicationManager() {
        this.servers = new HashMap<>();
    }

    public IServer addServer(IServer server) {
        if(server == null) return null;

        if(this.servers.containsValue(server)) return server;

        server.setContainer(this);

        this.servers.put(server.getId(), server);

        return server;
    }

    public void removeServer(IServer server) throws InterruptedException {
        if(server == null) return;

        IServer existing = this.servers.remove(server.getId());

        if(existing != null) existing.dispose();
    }

    public void removeAllServers() throws InterruptedException {
        if((this.servers == null) || (this.servers.size() < 1)) return;

        for(int id : this.servers.keySet()) {
            IServer existing = this.servers.remove(id);

            if(existing != null) existing.dispose();
        }
    }
}
