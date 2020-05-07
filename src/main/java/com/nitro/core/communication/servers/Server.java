package com.nitro.core.communication.servers;

import com.nitro.core.communication.codec.ICodec;
import com.nitro.core.communication.connections.IConnection;
import com.nitro.core.communication.connections.IConnectionContainer;
import com.nitro.core.communication.messages.IMessageConfiguration;
import com.nitro.core.communication.messages.MessageClassManager;

import java.util.HashMap;
import java.util.Map;

public abstract class Server implements IServer, IConnectionContainer {

    private static int SERVER_COUNTER = 0;

    private final int id;
    private final String ip;
    private final int port;

    private IServerContainer container;
    private Map<String, Map<Integer, IConnection>> connections;
    private MessageClassManager messages;

    protected ICodec codec;

    public Server(String ip, int port) {
        this.id = SERVER_COUNTER++;
        this.ip = ip;
        this.port = port;

        this.container = null;
        this.connections = new HashMap<>();
        this.messages = new MessageClassManager();

        this.codec = null;
    }

    public IConnection addConnection(IConnection connection) {
        if(connection == null) return null;

        Map<Integer, IConnection> existing = this.connections.get(connection.getIp());

        if(existing == null) {
            existing = new HashMap<>();

            this.connections.put(connection.getIp(), existing);
        }

        connection.setContainer(this);

        existing.put(connection.getId(), connection);

        return connection;
    }

    public void removeConnection(IConnection connection) {
        if(connection == null) return;

        String ip = connection.getIp();

        Map<Integer, IConnection> existing = this.connections.get(ip);

        if((existing != null) && (existing.size() > 0)) existing.remove(connection.getId()).dispose();

        if(existing.size() == 0) this.connections.remove(ip);
    }

    public void removeAllConnections() {
        if((this.connections == null) || (this.connections.size() == 0)) return;

        for(String ip : this.connections.keySet()) {
            Map<Integer, IConnection> connections = this.connections.get(ip);

            if((connections != null) || (connections.size() > 0)) {
                for(IConnection connection : connections.values()) {
                    if(connection != null) connections.remove(connection.getId()).dispose();
                }
            }

            this.connections.remove(ip);
        }
    }

    public void registerMessages(IMessageConfiguration configuration) {
        if(configuration == null) return;

        this.getMessages().registerMessages(configuration);
    }

    public int getId() {
        return this.id;
    }

    public String getIp() {
        return this.ip;
    }

    public int getPort() {
        return this.port;
    }

    public IServerContainer getContainer() {
        return this.container;
    }

    public void setContainer(IServerContainer container) {
        this.container = container;
    }

    public Map<String, Map<Integer, IConnection>> getConnections() {
        return this.connections;
    }

    public MessageClassManager getMessages() {
        return this.messages;
    }

    public ICodec getCodec() {
        return this.codec;
    }
}
