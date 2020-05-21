package com.nitro.nitro.communication.messages.incoming.client;

import com.nitro.core.communication.messages.IMessageEvent;
import com.nitro.core.communication.messages.MessageEvent;
import com.nitro.nitro.communication.messages.parser.client.ClientReleaseVersionParser;

import java.lang.reflect.Method;

public class ClientReleaseVersionEvent extends MessageEvent implements IMessageEvent {

    public ClientReleaseVersionEvent(Method messageCallback) {
        super(messageCallback, ClientReleaseVersionParser.class);
    }

    public ClientReleaseVersionParser getParser() {
        return (ClientReleaseVersionParser) this.parser;
    }
}
