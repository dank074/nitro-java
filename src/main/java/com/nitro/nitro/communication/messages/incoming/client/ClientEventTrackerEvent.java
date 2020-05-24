package com.nitro.nitro.communication.messages.incoming.client;

import com.nitro.core.communication.messages.MessageEvent;
import com.nitro.nitro.communication.messages.parser.client.ClientEventTrackerParser;

import java.lang.reflect.Method;

public class ClientEventTrackerEvent extends MessageEvent {

    public ClientEventTrackerEvent(Method messageCallback) {
        super(messageCallback, ClientEventTrackerParser.class);
    }

    public ClientEventTrackerParser getParser() {
        return (ClientEventTrackerParser) this.parser;
    }
}
