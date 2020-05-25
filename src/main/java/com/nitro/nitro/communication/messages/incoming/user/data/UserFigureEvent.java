package com.nitro.nitro.communication.messages.incoming.user.data;

import com.nitro.core.communication.messages.MessageEvent;
import com.nitro.nitro.communication.messages.parser.user.data.UserFigureParser;

import java.lang.reflect.Method;

public class UserFigureEvent extends MessageEvent {

    public UserFigureEvent(Method messageCallback) {
        super(messageCallback, UserFigureParser.class);
    }

    public UserFigureParser getParser() {
        return (UserFigureParser) this.parser;
    }
}
