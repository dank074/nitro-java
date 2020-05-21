package com.nitro.nitro.room.object.data.types;

import com.nitro.nitro.room.object.data.StuffDataBase;
import com.nitro.nitro.room.object.data.StuffDataKey;

import java.util.ArrayList;
import java.util.List;

public class StringStuffData extends StuffDataBase {

    public static int FORMAT_KEY = StuffDataKey.STRING_KEY;

    private static int STATE_INDEX = 0;

    private List<String> data;

    public StringStuffData() {
        super();

        this.data = new ArrayList<>();
    }

    public List<Object> getMessageArray() {
        List<Object> messages = new ArrayList<>();

        messages.add(this.getFlags());
        messages.add(this.data.size());
        messages.addAll(this.data);
        messages.addAll(super.getMessageArray());

        return messages;
    }

    public String getLegacyString() {
        try {
            String state = this.data.get(STATE_INDEX);

            return state;
        } catch(Exception e) {
            return "";
        }
    }

    public String getValue(int index) {
        return this.data.get(index);
    }

    public void setState(String data) {
        this.data.set(STATE_INDEX, data);
    }
}
