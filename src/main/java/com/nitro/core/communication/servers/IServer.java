package com.nitro.core.communication.servers;

import com.nitro.core.communication.codec.ICodec;
import com.nitro.core.communication.messages.IMessageConfiguration;
import com.nitro.core.communication.messages.MessageClassManager;

public interface IServer {

    void init();
    void dispose() throws InterruptedException;
    void registerMessages(IMessageConfiguration configuration);
    int getId();
    String getIp();
    int getPort();
    IServerContainer getContainer();
    void setContainer(IServerContainer container);
    MessageClassManager getMessages();
    ICodec getCodec();
}
