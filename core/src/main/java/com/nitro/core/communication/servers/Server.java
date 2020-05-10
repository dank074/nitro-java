package com.nitro.core.communication.servers;

import com.nitro.core.communication.codec.ICodec;
import com.nitro.core.communication.connections.IConnection;
import com.nitro.core.communication.events.connection.ConnectionAddedEvent;
import com.nitro.core.communication.events.connection.ConnectionRemovedEvent;
import com.nitro.core.communication.events.messages.MessageConfigurationEvent;
import com.nitro.core.communication.events.messages.MessageListenerEvent;
import com.nitro.core.communication.messages.*;
import com.nitro.core.events.IEventDispatcher;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public abstract class Server implements IServer {

    private static int SERVER_COUNTER = 0;

    private final int id;
    private final String ip;
    private final int port;

    private IServerContainer container;
    private Map<String, Map<Integer, IConnection>> connections;
    private MessageClassManager messages;
    private IEventDispatcher eventDispatcher;

    protected ICodec codec;
    protected boolean disposed;

    public Server(String ip, int port) {
        this.id = SERVER_COUNTER++;
        this.ip = ip;
        this.port = port;

        this.container = null;
        this.connections = new HashMap<>();
        this.messages = new MessageClassManager();
        this.eventDispatcher = null;

        this.codec = null;
        this.disposed = false;
    }

    public void dispose() {
        if(this.isDisposed()) return;

        this.disposed = true;

        this.removeAllConnections();

        this.container = null;
        this.connections = null;
        this.messages = null;
        this.eventDispatcher = null;
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
        connection.setServer(this);

        if(this.eventDispatcher != null) {
            ConnectionAddedEvent event = this.eventDispatcher.dispatchEvent(new ConnectionAddedEvent(connection));

            if(event.isCancelled()) return null;
        }

        existing.put(connection.getId(), connection);

        return connection;
    }

    public void removeConnection(IConnection connection) {
        if(connection == null) return;

        String ip = connection.getIp();

        Map<Integer, IConnection> existing = this.connections.get(ip);

        if((existing != null) && (existing.size() > 0)) {

            if(this.eventDispatcher != null) {
                ConnectionRemovedEvent event = this.eventDispatcher.dispatchEvent(new ConnectionRemovedEvent(connection));

                if(event.isCancelled()) return;
            }

            existing.remove(connection.getId()).dispose();
        }

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

    public void registerMessageConfiguration(IMessageConfiguration configuration) {
        if(configuration == null) return;

        if(this.eventDispatcher != null) {
            MessageConfigurationEvent event = this.eventDispatcher.dispatchEvent(new MessageConfigurationEvent(configuration));

            if(event.isCancelled()) return;
        }

        this.getMessages().registerMessageConfiguration(configuration);
    }

    public void registerMessageListener(IMessageListener listener) {
        if(listener == null) return;

        Method[] methods = listener.getClass().getDeclaredMethods();

        if((methods == null) || (methods.length == 0)) return;

        if(this.eventDispatcher != null) {
            MessageListenerEvent event = this.eventDispatcher.dispatchEvent(new MessageListenerEvent(listener));

            if(event.isCancelled()) return;
        }

        for(Method method : methods) {
            if(!method.isAnnotationPresent(MessageHandler.class)) continue;

            Class<?>[] parameterTypes = method.getParameterTypes();

            if(parameterTypes.length == 0) continue;

            Class<?> eventClass = parameterTypes[0];

            if(eventClass == null) continue;

            try {
                Constructor<?> eventClassConstructor = eventClass.getConstructor(Method.class);
                IMessageEvent event = (IMessageEvent) eventClassConstructor.newInstance(method);

                event.setMessageListener(listener);

                this.getMessages().registerMessageEvent(event);
            } catch(Exception e) {
                System.out.println("Could not register event");
            }
        }
    }

    public boolean isDisposed() {
        return this.disposed;
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

    public IEventDispatcher getEventDispatcher() {
        return this.eventDispatcher;
    }

    public void setEventDispatcher(IEventDispatcher eventDispatcher) {
        this.eventDispatcher = eventDispatcher;
    }

    public ICodec getCodec() {
        return this.codec;
    }
}
