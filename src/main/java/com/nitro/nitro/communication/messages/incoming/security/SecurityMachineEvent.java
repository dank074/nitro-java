package com.nitro.nitro.communication.messages.incoming.security;

import com.nitro.core.communication.messages.MessageEvent;
import com.nitro.nitro.communication.messages.parser.security.SecurityMachineParser;

import java.lang.reflect.Method;

public class SecurityMachineEvent extends MessageEvent {

    public SecurityMachineEvent(Method messageCallback) {
        super(messageCallback, SecurityMachineParser.class);
    }

    public SecurityMachineParser getParser() {
        return (SecurityMachineParser) this.parser;
    }
}
