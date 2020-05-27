package com.nitro.core.communication.servers;

import com.nitro.common.component.Component;
import com.nitro.common.events.IEventDispatcher;
import com.nitro.core.communication.codec.ICodec;
import com.nitro.core.communication.connections.IConnection;
import com.nitro.core.communication.messages.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public abstract class Server extends Component implements IServer {

    private static int SERVER_COUNTER = 0;

    private final int id;
    private final String ip;
    private final int port;

    private IServerContainer container;
    private IEventDispatcher eventDispatcher;

    private final Map<String, Map<Integer, IConnection>> connections;
    private final MessageClassManager messages;

    protected ICodec codec;

    public Server(String ip, int port) {
        this.id = SERVER_COUNTER++;
        this.ip = ip;
        this.port = port;

        this.container = null;
        this.eventDispatcher = null;

        this.connections = new HashMap<>();
        this.messages = new MessageClassManager();

        this.codec = null;
    }

    protected void onDispose() {
        this.removeAllConnections();

        this.container = null;
        this.eventDispatcher = null;

        this.connections.clear();
        this.messages.dispose();

        this.codec = null;
    }

    public IConnection addConnection(IConnection connection) {
        if(connection == null) return null;

        Map<Integer, IConnection> existing = this.connections.computeIfAbsent(connection.getIp(), k -> new HashMap<>());

        connection.setContainer(this);

        existing.put(connection.getId(), connection);

        return connection;
    }

    public void removeConnection(IConnection connection) {
        if(connection == null) return;

        String ip = connection.getIp();

        Map<Integer, IConnection> existing = this.connections.get(ip);

        if(existing != null) {
            if(existing.size() > 0) {
                IConnection existingConnection = existing.remove(connection.getId());

                if(existingConnection != null) existingConnection.dispose();
            }

            if(existing.size() == 0) this.connections.remove(ip);
        }
    }

    public void removeAllConnections() {
        if(this.connections.size() == 0) return;

        for(String ip : this.connections.keySet()) {
            Map<Integer, IConnection> connections = this.connections.get(ip);

            if((connections != null) && (connections.size() > 0)) {
                for(IConnection connection : connections.values()) {
                    if(connection != null) connections.remove(connection.getId()).dispose();
                }
            }

            this.connections.remove(ip);
        }
    }

    public void registerMessageConfiguration(IMessageConfiguration configuration) {
        if(configuration == null) return;

        this.getMessages().registerMessageConfiguration(configuration);
    }

    public void registerMessageListener(IMessageListener listener) {
        if(listener == null) return;

        Method[] methods = listener.getClass().getDeclaredMethods();

        if(methods.length == 0) return;

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
                this.getLogger().error(e.getMessage());
            }
        }
    }

    public void handleEvent(IConnection connection, IMessageEvent event) {
        if((connection == null) || (event == null)) return;

        event.setConnection(connection);

        Method messageCallback = event.getMessageCallback();

        if(messageCallback != null)
        {
            this.getLogger().log("Processing Event: " + event.getClass().getSimpleName());

            try {
                messageCallback.invoke(event.getMessageListener(), event);
            } catch(Exception e) {
                this.getLogger().error(e.getMessage());
            }
        }
    }

    public void processComposer(IConnection connection, IMessageComposer composer) {
        if((connection == null) || (composer == null)) return;

        int header = this.getMessages().getComposerId(composer);

        if(header == -1) {
            this.getLogger().error(composer.getClass().getSimpleName() + " has not been registered");

            return;
        }

        this.getLogger().log("Processing Composer: " + composer.getClass().getSimpleName() + " with header: " + header);

        connection.write(this.getCodec().encode(header, composer.getMessageArray()));
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
