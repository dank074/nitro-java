package com.nitro.nitro.communication.messages.parser.desktop;

import com.nitro.core.communication.messages.IMessageDataWrapper;
import com.nitro.core.communication.messages.IMessageParser;

public class DesktopNewsParser implements IMessageParser {

    public boolean flush() {
        return true;
    }

    public boolean parse(IMessageDataWrapper wrapper) {
        if(wrapper == null) return false;

        return true;
    }
}
