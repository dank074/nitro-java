package com.nitro.nitro.communication.messages.outgoing.user.data;

import com.nitro.core.communication.messages.IMessageComposer;

import java.util.ArrayList;
import java.util.List;

public class UserHomeRoomComposer implements IMessageComposer {

    private List<Object> data;

    public UserHomeRoomComposer(int roomId, boolean update) {
        this.data = new ArrayList<>();

        this.data.add(roomId);
        this.data.add((update ? roomId : 0));
    }

    public void dispose() {

    }

    public Object[] getMessageArray() {
        return this.data.toArray();
    }
}
