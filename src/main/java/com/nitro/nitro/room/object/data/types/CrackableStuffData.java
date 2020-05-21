package com.nitro.nitro.room.object.data.types;

import com.nitro.nitro.room.object.data.StuffDataBase;
import com.nitro.nitro.room.object.data.StuffDataKey;

import java.util.ArrayList;
import java.util.List;

public class CrackableStuffData extends StuffDataBase {

    public static final int FORMAT_KEY = StuffDataKey.CRACKABLE_KEY;

    private String state;
    private int hits;
    private int target;

    public CrackableStuffData() {
        super();

        this.state = "";
        this.hits = 0;
        this.target = 0;
    }

    public List<Object> getMessageArray() {
        List<Object> messages = new ArrayList<>();

        messages.add(this.getFlags());
        messages.add(this.state);
        messages.add(this.hits);
        messages.add(this.target);
        messages.addAll(super.getMessageArray());

        return messages;
    }

    public String getLegacyString() {
        return this.state;
    }

    public void setState(String data) {
        this.state = data;
    }

    public int getHits() {
        return this.hits;
    }

    public int getTarget() {
        return this.target;
    }
}
