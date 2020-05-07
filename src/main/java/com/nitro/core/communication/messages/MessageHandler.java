package com.nitro.core.communication.messages;

import com.nitro.core.communication.servers.IServer;

import java.util.ArrayList;
import java.util.List;

public abstract class MessageHandler implements IMessageHandler {

    private IServer server;
    private List<IMessageEvent> events;

    public MessageHandler() {
        this.events = new ArrayList<>();
    }

    public void registerEvent(IMessageEvent event) {
        if((event == null) || (this.server == null)) return;

        this.events.add(event);
        this.server.getMessages().registerMessageEvent(event);
    }

    public void registerEvents(List<IMessageEvent> events) {
        if((events == null) || (events.size() == 0)) return;

        for(IMessageEvent event : events) this.registerEvent(event);
    }

    public void unregisterEvent(IMessageEvent event) {
        if((event == null) || (this.server == null)) return;

        this.events.remove(event);
        this.server.getMessages().removeMessageEvent(event);
    }

    public void unregisterAllEvents() {
        if((this.events == null) || (this.events.size() == 0)) return;

        for(IMessageEvent event : this.events) this.unregisterEvent(event);
    }

    public IServer getServer() {
        return this.server;
    }

    public void setServer(IServer server) {
        this.server = server;
    }
}
