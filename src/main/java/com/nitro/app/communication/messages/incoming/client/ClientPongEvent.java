package com.nitro.app.communication.messages.incoming.client;

import com.nitro.app.communication.messages.parser.client.ClientPongParser;
import com.nitro.core.communication.messages.MessageEvent;

import java.lang.reflect.Method;

public class ClientPongEvent extends MessageEvent {

    public ClientPongEvent(Method messageCallback) {
        super(messageCallback, ClientPongParser.class);
    }

    public ClientPongParser getParser() {
        return (ClientPongParser) this.parser;
    }
}
