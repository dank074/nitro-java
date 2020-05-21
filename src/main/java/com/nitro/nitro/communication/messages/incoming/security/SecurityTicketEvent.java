package com.nitro.nitro.communication.messages.incoming.security;

import com.nitro.core.communication.messages.MessageEvent;
import com.nitro.nitro.communication.messages.parser.security.SecurityTicketParser;

import java.lang.reflect.Method;

public class SecurityTicketEvent extends MessageEvent {

    public SecurityTicketEvent(Method messageCallback) {
        super(messageCallback, SecurityTicketParser.class);
    }

    public SecurityTicketParser getParser() {
        return (SecurityTicketParser) this.parser;
    }
}
