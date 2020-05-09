package com.nitro.core.communication;

import com.nitro.core.INitroCore;
import com.nitro.core.communication.connections.IConnection;
import com.nitro.core.communication.events.connection.ConnectionAddedEvent;
import com.nitro.core.communication.events.connection.ConnectionRemovedEvent;
import com.nitro.core.communication.events.server.ServerAddedEvent;
import com.nitro.core.communication.events.server.ServerRemovedEvent;
import com.nitro.core.communication.servers.IServer;
import com.nitro.core.communication.servers.IServerContainer;

import java.util.HashMap;
import java.util.Map;

public class CommunicationManager implements ICommunicationManager, IServerContainer {

    private INitroCore nitroCore;
    private Map<Integer, IServer> servers;

    private boolean isDisposed;

    public CommunicationManager(INitroCore nitroCore) {
        this.nitroCore = nitroCore;
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

        if(this.nitroCore != null) {

            ServerAddedEvent event = this.nitroCore.getPluginManager().dispatchEvent(new ServerAddedEvent(server));

            if(event.isCancelled()) return null;
        }

        this.servers.put(server.getId(), server);

        return server;
    }

    public void removeServer(IServer server) {
        if(server == null) return;

        if(this.nitroCore != null) {
            ServerRemovedEvent event = this.nitroCore.getPluginManager().dispatchEvent(new ServerRemovedEvent(server));

            if(event.isCancelled()) return;
        }

        IServer existing = this.servers.remove(server.getId());

        if(existing != null) existing.dispose();
    }

    public void removeAllServers() {
        if((this.servers == null) || (this.servers.size() < 1)) return;

        for(IServer server : this.servers.values()) this.removeServer(server);
    }

    public IConnection addConnection(IServer container, IConnection connection) {
        if((container == null) || (connection == null)) return null;

        if(this.nitroCore != null) {
            ConnectionAddedEvent event = this.nitroCore.getPluginManager().dispatchEvent(new ConnectionAddedEvent());

            if(event.isCancelled()) return null;
        }

        return container.addConnection(connection);
    }

    public void removeConnection(IServer container, IConnection connection) {
        if((container == null) || (connection == null)) return;

        if(this.nitroCore != null) {
            ConnectionRemovedEvent event = this.nitroCore.getPluginManager().dispatchEvent(new ConnectionRemovedEvent());

            if(event.isCancelled()) return;
        }

        container.removeConnection(connection);
    }

    public boolean isDisposed() {
        return this.isDisposed;
    }

    public INitroCore getNitroCore() {
        return this.nitroCore;
    }

    public Map<Integer, IServer> getServers() {
        return this.servers;
    }
}
