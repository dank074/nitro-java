package com.nitro.nitro.communication.messages.incoming.desktop;

import com.nitro.core.communication.messages.MessageEvent;
import com.nitro.nitro.communication.messages.parser.desktop.DesktopCampaignsParser;

import java.lang.reflect.Method;

public class DesktopCampaignsEvent extends MessageEvent {

    public DesktopCampaignsEvent(Method messageCallback) {
        super(messageCallback, DesktopCampaignsParser.class);
    }

    public DesktopCampaignsParser getParser() {
        return (DesktopCampaignsParser) this.parser;
    }
}
