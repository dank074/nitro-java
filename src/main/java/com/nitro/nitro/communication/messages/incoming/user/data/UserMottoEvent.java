package com.nitro.nitro.communication.messages.incoming.user.data;

import com.nitro.core.communication.messages.MessageEvent;
import com.nitro.nitro.communication.messages.parser.user.data.UserMottoParser;

import java.lang.reflect.Method;

public class UserMottoEvent extends MessageEvent {

    public UserMottoEvent(Method messageCallback) {
        super(messageCallback, UserMottoParser.class);
    }

    public UserMottoParser getParser() {
        return (UserMottoParser) this.parser;
    }
}
