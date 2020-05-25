package com.nitro.nitro.communication.messages.parser.user.data;

import com.nitro.core.communication.messages.IMessageDataWrapper;
import com.nitro.core.communication.messages.IMessageParser;

public class UserProfileParser implements IMessageParser {

    private int userId;

    public boolean flush() {
        this.userId = 0;

        return true;
    }

    public boolean parse(IMessageDataWrapper wrapper) {
        if(wrapper == null) return false;

        this.userId = wrapper.readInt();

        return true;
    }

    public int getUserId() {
        return this.userId;
    }
}
