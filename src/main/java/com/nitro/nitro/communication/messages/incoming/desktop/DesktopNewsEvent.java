package com.nitro.nitro.communication.messages.incoming.desktop;

import com.nitro.core.communication.messages.MessageEvent;
import com.nitro.nitro.communication.messages.parser.desktop.DesktopNewsParser;

import java.lang.reflect.Method;

public class DesktopNewsEvent extends MessageEvent {

    public DesktopNewsEvent(Method messageCallback) {
        super(messageCallback, DesktopNewsParser.class);
    }

    public DesktopNewsParser getParser() {
        return (DesktopNewsParser) this.parser;
    }
}
