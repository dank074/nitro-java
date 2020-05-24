package com.nitro.nitro.communication.messages.outgoing.user.data;

import com.nitro.core.communication.messages.IMessageComposer;

import java.util.ArrayList;
import java.util.List;

public class UserFirstLoginOfDayComposer implements IMessageComposer {

    private List<Object> data;

    public UserFirstLoginOfDayComposer(boolean flag) {
        this.data = new ArrayList<>();

        this.data.add(flag);
    }

    public void dispose() {

    }

    public Object[] getMessageArray() {
        return this.data.toArray();
    }
}
