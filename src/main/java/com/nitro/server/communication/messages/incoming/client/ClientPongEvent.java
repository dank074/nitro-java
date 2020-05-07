package com.nitro.server.communication.messages.incoming.client;

import com.nitro.core.communication.messages.IMessageCallback;
import com.nitro.core.communication.messages.MessageEvent;
import com.nitro.server.communication.messages.parser.client.ClientPongParser;

public class ClientPongEvent extends MessageEvent {

    public ClientPongEvent(IMessageCallback messageCallback) {
        super(messageCallback, ClientPongParser.class);
    }

    public ClientPongParser getParser() {
        return (ClientPongParser) this.parser;
    }
}
