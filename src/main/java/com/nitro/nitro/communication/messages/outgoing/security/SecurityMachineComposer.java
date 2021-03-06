package com.nitro.nitro.communication.messages.outgoing.security;

import com.nitro.core.communication.messages.IMessageComposer;

import java.util.ArrayList;
import java.util.List;

public class SecurityMachineComposer implements IMessageComposer {

    private List<Object> data;

    public SecurityMachineComposer(String machineId) {
        this.data = new ArrayList<>();

        this.data.add(machineId);
    }

    public void dispose() {

    }

    public Object[] getMessageArray() {
        return this.data.toArray();
    }
}
