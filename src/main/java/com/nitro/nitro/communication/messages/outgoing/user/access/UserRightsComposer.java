package com.nitro.nitro.communication.messages.outgoing.user.access;

import com.nitro.core.communication.messages.IMessageComposer;

import java.util.ArrayList;
import java.util.List;

public class UserRightsComposer implements IMessageComposer {

    private List<Object> data;

    public UserRightsComposer() {
        this.data = new ArrayList<>();

        this.data.add(true);
        this.data.add(true);
        this.data.add(true);
    }

    public void dispose() {

    }

    public Object[] getMessageArray() {
        return this.data.toArray();
    }
}
