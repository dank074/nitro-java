package com.nitro.server.communication.messages.incoming.security;

import com.nitro.core.communication.messages.IMessageCallback;
import com.nitro.core.communication.messages.MessageEvent;
import com.nitro.server.communication.messages.parser.security.SecurityTicketParser;

public class SecurityTicketEvent extends MessageEvent {

    public SecurityTicketEvent(IMessageCallback messageCallback) {
        super(messageCallback, SecurityTicketParser.class);
    }

    public SecurityTicketParser getParser() {
        return (SecurityTicketParser) this.parser;
    }
}
