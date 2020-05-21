package com.nitro.nitro.room.object.data;

import java.util.ArrayList;
import java.util.List;

public class StuffDataBase implements IStuffData {

    private int flags;
    private int uniqueNumber;
    private int uniqueSeries;

    public StuffDataBase() {
        this.flags = 0;
        this.uniqueNumber = 0;
        this.uniqueSeries = 0;
    }

    public List<Object> getMessageArray() {
        List<Object> messages = new ArrayList<>();

        if((this.getFlags() & StuffDataFlags.UNIQUE_SET) > 0) {
            messages.add(this.getUniqueNumber());
            messages.add(this.getUniqueSeries());
        }

        return messages;
    }

    public String getLegacyString() {
        return "";
    }

    public int getState() {
        try {
            String state = this.getLegacyString();

            return Integer.parseInt(state);
        } catch(Exception e) {
            return 0;
        }
    }

    public void setState(Object data) {
        return;
    }

    public boolean isUnique() {
        return (this.uniqueSeries > 0);
    }

    public int getUniqueNumber() {
        return this.uniqueNumber;
    }

    public int getUniqueSeries() {
        return this.uniqueSeries;
    }

    public int getFlags() {
        return this.flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }
}
