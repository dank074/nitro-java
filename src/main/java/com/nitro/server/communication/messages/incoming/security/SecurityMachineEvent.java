package com.nitro.server.communication.messages.incoming.security;

import com.nitro.core.communication.messages.IMessageCallback;
import com.nitro.core.communication.messages.MessageEvent;
import com.nitro.server.communication.messages.parser.security.SecurityMachineParser;

public class SecurityMachineEvent extends MessageEvent {

    public SecurityMachineEvent(IMessageCallback messageCallback) {
        super(messageCallback, SecurityMachineParser.class);
    }

    public SecurityMachineParser getParser() {
        return (SecurityMachineParser) this.parser;
    }
}
