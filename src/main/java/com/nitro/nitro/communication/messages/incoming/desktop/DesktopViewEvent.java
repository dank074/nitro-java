package com.nitro.nitro.communication.messages.incoming.desktop;

import com.nitro.core.communication.messages.MessageEvent;
import com.nitro.nitro.communication.messages.parser.desktop.DesktopViewParser;

import java.lang.reflect.Method;

public class DesktopViewEvent extends MessageEvent {

    public DesktopViewEvent(Method messageCallback) {
        super(messageCallback, DesktopViewParser.class);
    }

    public DesktopViewParser getParser() {
        return (DesktopViewParser) this.parser;
    }
}
