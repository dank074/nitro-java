package com.nitro.nitro.room;

import com.nitro.nitro.room.object.IRoomObject;

import java.util.Map;

public interface IRoomObjectManager {

    void dispose();
    IRoomObject getObject(int id);
    IRoomObject createObject(int id, String type);
    void removeObject(int id);
    void removeAllObjects();
    Map<Integer, IRoomObject> getObjects();
}
