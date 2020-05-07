package com.nitro.server.communication.messages.incoming.client;

import com.nitro.core.communication.messages.IMessageCallback;
import com.nitro.core.communication.messages.IMessageEvent;
import com.nitro.core.communication.messages.MessageEvent;
import com.nitro.server.communication.messages.parser.client.ClientReleaseVersionParser;

public class ClientReleaseVersionEvent extends MessageEvent implements IMessageEvent {

    public ClientReleaseVersionEvent(IMessageCallback messageCallback) {
        super(messageCallback, ClientReleaseVersionParser.class);
    }

    public ClientReleaseVersionParser getParser() {
        return (ClientReleaseVersionParser) this.parser;
    }
}
