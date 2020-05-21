package com.nitro.nitro.room;

import com.nitro.common.component.Component;
import com.nitro.nitro.room.mapping.RoomModel;

import java.util.HashMap;
import java.util.Map;

public class RoomManager extends Component implements IRoomManager, IRoomContainer {

    private Map<Integer, IRoom> rooms;
    private Map<Integer, RoomModel> models;

    public RoomManager() {
        this.rooms = new HashMap<>();
        this.models = new HashMap<>();
    }

    protected void onInit() {
        this.loadModels();
    }

    protected void onDispose() {
        this.removeAllRooms();
    }

    public IRoom getRoom(int id) {
        IRoom existing = this.getActiveRoom(id);

        if(existing != null) return existing;

        return this.getOfflineRoom(id);
    }

    public IRoom getActiveRoom(int id) {
        try {
            IRoom existing = this.rooms.get(id);

            if(existing == null) return null;

            existing.cancelDispose();

            return existing;
        } catch(Exception e) {
            return null;
        }
    }

    private IRoom getOfflineRoom(int id) {
        IRoom room = new Room(this);

        return this.addRoom(room);
    }

    private IRoom addRoom(IRoom room) {
        if(room == null) return null;

        IRoom existing = this.getActiveRoom(room.getId());

        if(existing != null) {
            if(room != existing) room.dispose();

            return existing;
        }

        return this.rooms.put(room.getId(), room);
    }

    public void removeRoom(int id) {
        try {
            IRoom existing = this.rooms.remove(id);

            if(existing == null) return;

            existing.dispose();
        } catch(Exception e) {
        }
    }

    public void removeRoom(IRoom room) {
        if(room == null) return;

        this.removeRoom(room.getId());
    }

    public void removeAllRooms() {
        if((this.rooms != null) && (this.rooms.size() > 0)) {
            for(IRoom room : this.rooms.values()) {
                if(room == null) continue;
            }
        }
    }

    private void loadModels() {

    }
}
