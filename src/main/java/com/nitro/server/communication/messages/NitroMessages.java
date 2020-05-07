package com.nitro.server.communication.messages;

import com.nitro.core.communication.messages.IMessageComposer;
import com.nitro.core.communication.messages.IMessageConfiguration;
import com.nitro.core.communication.messages.IMessageEvent;
import com.nitro.server.communication.messages.incoming.IncomingHeaders;
import com.nitro.server.communication.messages.incoming.client.ClientPongEvent;
import com.nitro.server.communication.messages.incoming.client.ClientReleaseVersionEvent;
import com.nitro.server.communication.messages.incoming.security.SecurityMachineEvent;
import com.nitro.server.communication.messages.incoming.security.SecurityTicketEvent;
import com.nitro.server.communication.messages.outgoing.OutgoingHeaders;
import com.nitro.server.communication.messages.outgoing.client.ClientPingComposer;
import com.nitro.server.communication.messages.outgoing.security.SecurityAuthenticatedComposer;
import com.nitro.server.communication.messages.outgoing.security.SecurityMachineComposer;

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

        this.events.put(IncomingHeaders.SECURITY_MACHINE, SecurityMachineEvent.class);
        this.events.put(IncomingHeaders.SECURITY_TICKET, SecurityTicketEvent.class);
    }

    private void registerComposers() {
        this.composers.put(OutgoingHeaders.CLIENT_PING, ClientPingComposer.class);

        this.composers.put(OutgoingHeaders.AUTHENTICATED, SecurityAuthenticatedComposer.class);
        this.composers.put(OutgoingHeaders.SECURITY_MACHINE, SecurityMachineComposer.class);
    }

    public Map<Integer, Class<? extends IMessageEvent>> getEvents() {
        return this.events;
    }

    public Map<Integer, Class<? extends IMessageComposer>> getComposers() {
        return this.composers;
    }
}
