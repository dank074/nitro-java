package com.nitro.nitro.communication.messages.incoming.client;

import com.nitro.core.communication.messages.MessageEvent;
import com.nitro.nitro.communication.messages.parser.client.ClientLatencyParser;

import java.lang.reflect.Method;

public class ClientLatencyEvent extends MessageEvent {

    public ClientLatencyEvent(Method messageCallback) {
        super(messageCallback, ClientLatencyParser.class);
    }

    public ClientLatencyParser getParser() {
        return (ClientLatencyParser) this.parser;
    }
}
