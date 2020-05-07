package com.nitro.core.communication.messages;

import com.nitro.core.communication.connections.IConnection;

public class MessageEvent implements IMessageEvent {

    protected IMessageCallback messageCallback;
    protected Class<? extends IMessageParser> parserClass;
    protected IMessageParser parser;
    protected IConnection connection;

    public MessageEvent(IMessageCallback messageCallback, Class<? extends IMessageParser> parserClass) {
        this.messageCallback = messageCallback;
        this.parserClass = parserClass;
    }

    public void dispose() {
        this.parserClass = null;
        this.parser = null;
        this.connection = null;
    }

    public IMessageCallback getMessageCallback() {
        return this.messageCallback;
    }

    public Class<? extends IMessageParser> getParserClass() {
        return this.parserClass;
    }

    public IMessageParser getParser() {
        return this.parser;
    }

    public void setParser(IMessageParser parser) {
        this.parser = parser;
    }

    public IConnection getConnection() {
        return this.connection;
    }

    public void setConnection(IConnection connection) {
        this.connection = connection;
    }
}
