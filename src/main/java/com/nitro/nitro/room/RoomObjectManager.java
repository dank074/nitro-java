package com.nitro.nitro.room;

import com.nitro.nitro.room.object.IRoomObject;
import com.nitro.nitro.room.object.RoomObject;

import java.util.HashMap;
import java.util.Map;

public class RoomObjectManager implements IRoomObjectManager {

    private Map<Integer, IRoomObject> objects;

    public RoomObjectManager() {
        this.objects = new HashMap<>();
    }

    public void dispose() {
        this.removeAllObjects();
    }

    public IRoomObject getObject(int id) {
        try {
            IRoomObject object = this.objects.get(id);

            if(object == null) return null;

            return object;
        } catch(Exception e) {
            return null;
        }
    }

    private IRoomObject addObject(int id, IRoomObject object) {
        IRoomObject existing = this.getObject(id);

        if(existing != null) {
            object.dispose();

            return null;
        }

        this.objects.put(id, object);

        return object;
    }

    public IRoomObject createObject(int id, String type) {
        return this.addObject(id, new RoomObject(id, type));
    }

    public void removeObject(int id) {
        try {
            IRoomObject object = this.objects.remove(id);

            if(object != null) object.dispose();
        } catch(Exception e) {

        }
    }

    public void removeAllObjects() {
        for(int id : this.objects.keySet()) {
            this.removeObject(id);
        }
    }

    public Map<Integer, IRoomObject> getObjects() {
        return this.objects;
    }
}
