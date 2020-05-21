package com.nitro.nitro.communication.messages.parser.client;

import com.nitro.core.communication.messages.IMessageDataWrapper;
import com.nitro.core.communication.messages.IMessageParser;

public class ClientPongParser implements IMessageParser {

    private String version;

    public boolean flush() {
        return true;
    }

    public boolean parse(IMessageDataWrapper wrapper) {
        if(wrapper == null) return false;

        return true;
    }
}
