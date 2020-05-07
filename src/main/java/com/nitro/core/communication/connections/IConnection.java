package com.nitro.core.communication.connections;

public interface IConnection {

    void init();
    void dispose();
    IConnectionContainer getContainer();
    void setContainer(IConnectionContainer container);
    int getId();
    String getIp();
}
