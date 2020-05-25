package com.nitro.nitro.communication.messages.parser.user.data;

import com.nitro.core.communication.messages.IMessageDataWrapper;
import com.nitro.core.communication.messages.IMessageParser;

public class UserMottoParser implements IMessageParser {

    private String motto;

    public boolean flush() {
        this.motto = null;

        return true;
    }

    public boolean parse(IMessageDataWrapper wrapper) {
        if(wrapper == null) return false;

        this.motto = wrapper.readString();

        return true;
    }

    public String getMotto() {
        return this.motto;
    }
}
