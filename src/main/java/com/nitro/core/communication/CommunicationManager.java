package com.nitro.core.communication;

import com.nitro.core.communication.servers.IServer;
import com.nitro.core.communication.servers.IServerContainer;

import java.util.HashMap;
import java.util.Map;

public class CommunicationManager implements ICommunicationManager, IServerContainer {

    private Map<Integer, IServer> servers;

    private boolean isDisposed;

    public CommunicationManager() {
        this.servers = new HashMap<>();

        this.isDisposed = false;
    }

    public void dispose() {
        if(this.isDisposed()) return;

        this.isDisposed = true;

        this.removeAllServers();
    }

    public IServer addServer(IServer server) {
        if(server == null) return null;

        if(this.servers.containsValue(server)) return server;

        server.setContainer(this);

        this.servers.put(server.getId(), server);

        return server;
    }

    public void removeServer(IServer server) {
        if(server == null) return;

        IServer existing = this.servers.remove(server.getId());

        if(existing != null) existing.dispose();
    }

    public void removeAllServers() {
        if((this.servers == null) || (this.servers.size() < 1)) return;

        for(IServer server : this.servers.values()) this.removeServer(server);
    }

    public boolean isDisposed() {
        return this.isDisposed;
    }

    public Map<Integer, IServer> getServers() {
        return this.servers;
    }
}
