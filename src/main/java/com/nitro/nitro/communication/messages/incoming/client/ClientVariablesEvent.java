package com.nitro.nitro.communication.messages.incoming.client;

import com.nitro.core.communication.messages.MessageEvent;
import com.nitro.nitro.communication.messages.parser.client.ClientVariablesParser;

import java.lang.reflect.Method;

public class ClientVariablesEvent extends MessageEvent {

    public ClientVariablesEvent(Method messageCallback) {
        super(messageCallback, ClientVariablesParser.class);
    }

    public ClientVariablesParser getParser() {
        return (ClientVariablesParser) this.parser;
    }
}
