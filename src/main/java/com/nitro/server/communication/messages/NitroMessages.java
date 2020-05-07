package com.nitro.server.communication.messages;

import com.nitro.core.communication.messages.IMessageComposer;
import com.nitro.core.communication.messages.IMessageConfiguration;
import com.nitro.core.communication.messages.IMessageEvent;
import com.nitro.server.communication.messages.incoming.IncomingHeaders;
import com.nitro.server.communication.messages.incoming.client.ClientPongEvent;
import com.nitro.server.communication.messages.incoming.client.ClientReleaseVersionEvent;
import com.nitro.server.communication.messages.outgoing.OutgoingHeaders;
import com.nitro.server.communication.messages.outgoing.client.ClientPingComposer;

import java.util.HashMap;
import java.util.Map;

public class NitroMessages implements IMessageConfiguration {

    private Map<Integer, Class<? extends IMessageEvent>> events;
    private Map<Integer, Class<? extends IMessageComposer>> composers;

    public NitroMessages() {
        this.events = new HashMap<>();
        this.composers = new HashMap<>();

        this.registerEvents();
        this.registerComposers();
    }

    private void registerEvents() {
        this.events.put(IncomingHeaders.CLIENT_PONG, ClientPongEvent.class);
        this.events.put(IncomingHeaders.CLIENT_RELEASE_VERSION, ClientReleaseVersionEvent.class);
    }

    private void registerComposers() {
        this.composers.put(OutgoingHeaders.CLIENT_PING, ClientPingComposer.class);
    }

    public Map<Integer, Class<? extends IMessageEvent>> getEvents() {
        return this.events;
    }

    public Map<Integer, Class<? extends IMessageComposer>> getComposers() {
        return this.composers;
    }
}
