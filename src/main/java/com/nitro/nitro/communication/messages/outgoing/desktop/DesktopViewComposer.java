package com.nitro.nitro.communication.messages.outgoing.desktop;

import com.nitro.core.communication.messages.IMessageComposer;

import java.util.ArrayList;
import java.util.List;

public class DesktopViewComposer implements IMessageComposer {

    private List<Object> data;

    public DesktopViewComposer() {
        this.data = new ArrayList<>();
    }

    public void dispose() {

    }

    public Object[] getMessageArray() {
        return this.data.toArray();
    }
}
