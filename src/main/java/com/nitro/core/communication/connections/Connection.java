package com.nitro.core.communication.connections;

import com.nitro.core.common.disposable.IDisposable;
import io.netty.util.AttributeKey;

public abstract class Connection implements IConnection, IDisposable {

    public static AttributeKey<IConnection> CONNECTION_KEY = AttributeKey.valueOf("Connection");

    private static int CONNECTION_COUNTER = 0;

    private IConnectionContainer container;

    private final int id;
    private final String ip;

    private boolean disposed;

    public Connection(String ip) {
        this.id = CONNECTION_COUNTER++;
        this.ip = ip;

        this.disposed = false;
    }

    public abstract void init();

    public void dispose() {
        if(this.isDisposed()) return;

        this.disposed = true;

        if(this.container != null) {
            this.container.removeConnection(this);
        }
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

    public int getId() {
        return this.id;
    }

    public String getIp() {
        return this.ip;
    }
}
