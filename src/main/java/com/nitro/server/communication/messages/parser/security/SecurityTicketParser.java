package com.nitro.server.communication.messages.parser.security;

import com.nitro.core.communication.messages.IMessageDataWrapper;
import com.nitro.core.communication.messages.IMessageParser;

public class SecurityTicketParser implements IMessageParser {

    private String ticket;

    public boolean flush() {
        this.ticket = null;

        return true;
    }

    public boolean parse(IMessageDataWrapper wrapper) {
        if(wrapper == null) return false;

        this.ticket = wrapper.readString();

        return true;
    }

    public String getTicket() {
        return this.ticket;
    }
}
