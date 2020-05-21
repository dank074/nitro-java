package com.nitro.nitro.room.object.data.types;

import com.nitro.nitro.room.object.data.StuffDataBase;
import com.nitro.nitro.room.object.data.StuffDataKey;

import java.util.ArrayList;
import java.util.List;

public class LegacyStuffData extends StuffDataBase {

    public static int FORMAT_KEY = StuffDataKey.LEGACY_KEY;

    private String data;

    public LegacyStuffData() {
        super();

        this.data = "";
    }

    public List<Object> getMessageArray() {
        List<Object> messages = new ArrayList<>();

        messages.add(this.getFlags());
        messages.add(this.data);
        messages.addAll(super.getMessageArray());

        return messages;
    }

    public String getLegacyString() {
        return this.data;
    }

    public void setState(String data) {
        this.data = data;
    }
}
