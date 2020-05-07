package com.nitro.server.communication.messages.outgoing.security;

import com.nitro.core.communication.messages.IMessageComposer;

import java.util.ArrayList;
import java.util.List;

public class SecurityMachineComposer implements IMessageComposer {

    private List<Object> data;

    public SecurityMachineComposer(String machineId) {
        this.data = new ArrayList<>();

        data.add(machineId);
    }

    public void dispose() {

    }

    public Object[] getMessageArray() {
        return this.data.toArray();
    }
}
