package com.nitro.app.communication.messages.incoming.security;

import com.nitro.app.communication.messages.parser.security.SecurityTicketParser;
import com.nitro.core.communication.messages.MessageEvent;

import java.lang.reflect.Method;

public class SecurityTicketEvent extends MessageEvent {

    public SecurityTicketEvent(Method messageCallback) {
        super(messageCallback, SecurityTicketParser.class);
    }

    public SecurityTicketParser getParser() {
        return (SecurityTicketParser) this.parser;
    }
}
