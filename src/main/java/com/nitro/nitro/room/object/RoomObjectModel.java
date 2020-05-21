package com.nitro.nitro.room.object;

import java.util.HashMap;
import java.util.Map;

public class RoomObjectModel implements IRoomObjectModel {

    private Map<String, String> stringHashMap;
    private Map<String, Integer> numberHashMap;

    public RoomObjectModel() {
        this.stringHashMap = new HashMap<>();
        this.numberHashMap = new HashMap<>();
    }

    public String getString(String key) {
        return this.stringHashMap.get(key);
    }

    public void setString(String key, String value) {
        this.stringHashMap.put(key, value);
    }

    public int getNumber(String key) {
        return this.numberHashMap.get(key);
    }

    public void setNumber(String key, int value) {
        this.numberHashMap.put(key, value);
    }
}
