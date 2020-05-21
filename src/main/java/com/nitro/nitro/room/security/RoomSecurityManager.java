package com.nitro.nitro.room.security;

import com.nitro.common.component.Component;
import com.nitro.nitro.room.IRoom;

import java.util.HashMap;
import java.util.Map;

public class RoomSecurityManager extends Component {

    private IRoom room;
    private Map<Integer, RoomBan> roomBanMap;
    private Map<Integer, RoomMute> roomMuteMap;
    private Map<Integer, String> roomRightsMap;

    public RoomSecurityManager(IRoom room) {
        this.room = room;

        this.roomBanMap = new HashMap<>();
        this.roomMuteMap = new HashMap<>();
        this.roomRightsMap = new HashMap<>();
    }

    protected void onInit() {

    }

    protected void onDispose() {

    }
}
