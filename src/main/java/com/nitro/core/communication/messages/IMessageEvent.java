package com.nitro.core.communication.messages;

import com.nitro.core.communication.connections.IConnection;

public interface IMessageEvent {

    void dispose();
    IMessageCallback getMessageCallback();
    Class<? extends IMessageParser> getParserClass();
    IMessageParser getParser();
    void setParser(IMessageParser parser);
    IConnection getConnection();
    void setConnection(IConnection connection);
}
