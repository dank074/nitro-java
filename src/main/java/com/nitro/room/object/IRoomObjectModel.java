package com.nitro.room.object;

public interface IRoomObjectModel {

    String getString(String key);
    void setString(String key, String value);

    int getNumber(String key);
    void setNumber(String key, int value);
}
