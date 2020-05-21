package com.nitro.core.communication.connections;

import com.nitro.common.disposable.IDisposable;
import com.nitro.core.communication.messages.IMessageComposer;
import com.nitro.core.communication.messages.IMessageEvent;
import com.nitro.nitro.user.IUser;
import io.netty.buffer.ByteBuf;

public interface IConnection extends IDisposable {

    void write(ByteBuf buffer);
    void handleEvent(IMessageEvent event);
    void processComposer(IMessageComposer composer);
    boolean setUser(IUser user);
    IConnectionContainer getContainer();
    void setContainer(IConnectionContainer container);
    IUser getUser();
    int getId();
    String getIp();
}
