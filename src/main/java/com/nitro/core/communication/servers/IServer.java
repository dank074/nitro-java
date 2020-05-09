package com.nitro.core.communication.servers;

import com.nitro.core.communication.codec.ICodec;
import com.nitro.core.communication.messages.IMessageConfiguration;
import com.nitro.core.communication.messages.IMessageListener;
import com.nitro.core.communication.messages.MessageClassManager;

public interface IServer {

    void init();
    void dispose();
    void registerMessageConfiguration(IMessageConfiguration configuration);
    void registerMessageListener(IMessageListener listener);
    int getId();
    String getIp();
    int getPort();
    IServerContainer getContainer();
    void setContainer(IServerContainer container);
    MessageClassManager getMessages();
    ICodec getCodec();
}
