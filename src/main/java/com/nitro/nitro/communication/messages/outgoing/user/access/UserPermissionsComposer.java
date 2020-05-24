package com.nitro.nitro.communication.messages.outgoing.user.access;

import com.nitro.core.communication.messages.IMessageComposer;

import java.util.ArrayList;
import java.util.List;

public class UserPermissionsComposer implements IMessageComposer {

    private List<Object> data;

    public UserPermissionsComposer(boolean hasClub, int clientRank, boolean isAmbassador) {
        this.data = new ArrayList<>();

        this.data.add((hasClub) ? 2 : 0);
        this.data.add(clientRank);
        this.data.add(isAmbassador);
    }

    public void dispose() {

    }

    public Object[] getMessageArray() {
        return this.data.toArray();
    }
}
