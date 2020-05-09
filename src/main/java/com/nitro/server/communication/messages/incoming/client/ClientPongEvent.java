package com.nitro.server.communication.messages.incoming.client;

import com.nitro.core.communication.messages.MessageEvent;
import com.nitro.server.communication.messages.parser.client.ClientPongParser;

import java.lang.reflect.Method;

public class ClientPongEvent extends MessageEvent {

    public ClientPongEvent(Method messageCallback) {
        super(messageCallback, ClientPongParser.class);
    }

    public ClientPongParser getParser() {
        return (ClientPongParser) this.parser;
    }
}
