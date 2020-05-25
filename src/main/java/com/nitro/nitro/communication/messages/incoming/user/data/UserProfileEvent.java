package com.nitro.nitro.communication.messages.incoming.user.data;

import com.nitro.core.communication.messages.MessageEvent;
import com.nitro.nitro.communication.messages.parser.user.data.UserProfileParser;

import java.lang.reflect.Method;

public class UserProfileEvent extends MessageEvent {

    public UserProfileEvent(Method messageCallback) {
        super(messageCallback, UserProfileParser.class);
    }

    public UserProfileParser getParser() {
        return (UserProfileParser) this.parser;
    }
}
