package com.nitro.core.communication;

import com.nitro.common.component.Component;
import com.nitro.core.INitroCore;
import com.nitro.core.communication.servers.IServer;

import java.util.HashMap;
import java.util.Map;

public class CommunicationManager extends Component implements ICommunicationManager {

    private final INitroCore nitroCore;
    private final Map<Integer, IServer> servers;

    public CommunicationManager(INitroCore nitroCore) {
        this.nitroCore = nitroCore;
        this.servers = new HashMap<>();
    }

    protected void onDispose() {
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
        if(this.servers.size() < 1) return;

        for(IServer server : this.servers.values()) this.removeServer(server);
    }

    public INitroCore getNitroCore() {
        return this.nitroCore;
    }

    public Map<Integer, IServer> getServers() {
        return this.servers;
    }
}
