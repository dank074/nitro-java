package com.nitro.application.communication.messages.outgoing.security;

import com.nitro.core.communication.messages.IMessageComposer;

import java.util.ArrayList;
import java.util.List;

public class SecurityAuthenticatedComposer implements IMessageComposer {

    private List<Object> data;

    public SecurityAuthenticatedComposer() {
        this.data = new ArrayList<>();
    }

    public void dispose() {

    }

    public Object[] getMessageArray() {
        return this.data.toArray();
    }
}
