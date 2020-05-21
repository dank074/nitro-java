package com.nitro.nitro.room.object.data.types;

import com.nitro.nitro.room.object.data.StuffDataBase;
import com.nitro.nitro.room.object.data.StuffDataKey;

import java.util.ArrayList;
import java.util.List;

public class VoteStuffData extends StuffDataBase {

    public static int FORMAT_KEY = StuffDataKey.VOTE_KEY;

    private String state;
    private int result;

    public VoteStuffData() {
        super();

        this.state = "";
        this.result = 0;
    }

    public List<Object> getMessageArray() {
        List<Object> messages = new ArrayList<>();

        messages.add(this.getFlags());
        messages.add(this.state);
        messages.add(this.result);
        messages.addAll(super.getMessageArray());

        return messages;
    }

    public String getLegacyString() {
        return this.state;
    }

    public void setState(String data) {
        this.state = data;
    }

    public int getResult() {
        return this.result;
    }
}
