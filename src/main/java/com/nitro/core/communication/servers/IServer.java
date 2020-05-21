package com.nitro.core.communication.servers;

import com.nitro.common.disposable.IDisposable;
import com.nitro.common.events.IEventDispatcher;
import com.nitro.core.communication.codec.ICodec;
import com.nitro.core.communication.connections.IConnectionContainer;
import com.nitro.core.communication.messages.IMessageConfiguration;
import com.nitro.core.communication.messages.IMessageListener;
import com.nitro.core.communication.messages.MessageClassManager;

public interface IServer extends IConnectionContainer, IDisposable {

    void init();
    void registerMessageConfiguration(IMessageConfiguration configuration);
    void registerMessageListener(IMessageListener listener);
    int getId();
    String getIp();
    int getPort();
    IServerContainer getContainer();
    void setContainer(IServerContainer container);
    MessageClassManager getMessages();
    IEventDispatcher getEventDispatcher();
    void setEventDispatcher(IEventDispatcher eventDispatcher);
    ICodec getCodec();
}
