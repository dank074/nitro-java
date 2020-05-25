package com.nitro.nitro.communication.messages.incoming.user.data;

import com.nitro.core.communication.messages.MessageEvent;
import com.nitro.nitro.communication.messages.parser.user.data.UserInfoParser;

import java.lang.reflect.Method;

public class UserInfoEvent extends MessageEvent {

    public UserInfoEvent(Method messageCallback) {
        super(messageCallback, UserInfoParser.class);
    }

    public UserInfoParser getParser() {
        return (UserInfoParser) this.parser;
    }
}
