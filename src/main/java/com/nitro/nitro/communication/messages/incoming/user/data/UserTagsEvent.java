package com.nitro.nitro.communication.messages.incoming.user.data;

import com.nitro.core.communication.messages.MessageEvent;
import com.nitro.nitro.communication.messages.parser.user.data.UserTagsParser;

import java.lang.reflect.Method;

public class UserTagsEvent extends MessageEvent {

    public UserTagsEvent(Method messageCallback) {
        super(messageCallback, UserTagsParser.class);
    }

    public UserTagsParser getParser() {
        return (UserTagsParser) this.parser;
    }
}
