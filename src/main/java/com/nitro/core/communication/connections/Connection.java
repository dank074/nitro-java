package com.nitro.core.communication.connections;

import com.nitro.core.communication.messages.IMessageComposer;
import com.nitro.core.communication.messages.IMessageEvent;
import com.nitro.nitro.user.IUser;
import io.netty.buffer.ByteBuf;
import io.netty.util.AttributeKey;

public abstract class Connection implements IConnection {

    public static AttributeKey<IConnection> CONNECTION_KEY = AttributeKey.valueOf("Connection");

    private static int CONNECTION_COUNTER = 0;

    private final int id;
    private final String ip;

    private IConnectionContainer container;
    private IUser user;

    private boolean disposed;

    public Connection(String ip) {
        this.id = CONNECTION_COUNTER++;
        this.ip = ip;

        this.container = null;
        this.user = null;

        this.disposed = false;
    }

    public void dispose() {
        if(this.isDisposed()) return;

        this.disposed = true;

        if(this.user != null) this.user.dispose();

        if(this.container != null) {
            this.container.removeConnection(this);

            this.container = null;
        }
    }

    public abstract void write(ByteBuf buffer);

    public void handleEvent(IMessageEvent event) {
        if(this.container != null) this.container.handleEvent(this, event);
    }

    public void processComposer(IMessageComposer composer) {
        if(this.container != null) this.container.processComposer(this, composer);
    }

    public boolean setUser(IUser user) {
        if((this.user != null) && (this.user != user)) return false;

        this.user = user;

        return true;
    }

    public boolean isDisposed() {
        return this.disposed;
    }

    public IConnectionContainer getContainer() {
        return this.container;
    }

    public void setContainer(IConnectionContainer container) {
        this.container = container;
    }

    public IUser getUser() {
        return this.user;
    }

    public int getId() {
        return this.id;
    }

    public String getIp() {
        return this.ip;
    }
}
