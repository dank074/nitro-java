package com.nitro.nitro.communication.messages.outgoing.desktop;

import com.nitro.core.communication.messages.IMessageComposer;

import java.util.ArrayList;
import java.util.List;

public class DesktopCampaignsComposer implements IMessageComposer {

    private List<Object> data;

    public DesktopCampaignsComposer() {
        this.data = new ArrayList<>();

        this.data.add("");
        this.data.add("");
    }

    public void dispose() {

    }

    public Object[] getMessageArray() {
        return this.data.toArray();
    }
}
