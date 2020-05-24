package com.nitro.nitro.communication.messages.parser.desktop;

import com.nitro.core.communication.messages.IMessageDataWrapper;
import com.nitro.core.communication.messages.IMessageParser;

public class DesktopCampaignsParser implements IMessageParser {

    private String someString;

    public boolean flush() {
        this.someString = null;

        return true;
    }

    public boolean parse(IMessageDataWrapper wrapper) {
        if(wrapper == null) return false;

        this.someString = wrapper.readString();

        return true;
    }

    public String getSomeString() {
        return this.someString;
    }
}
