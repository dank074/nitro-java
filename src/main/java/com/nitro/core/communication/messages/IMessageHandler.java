package com.nitro.core.communication.messages;

import com.nitro.core.communication.servers.IServer;

import java.util.List;

public interface IMessageHandler {

    void onInit();
    void registerEvent(IMessageEvent event);
    void registerEvents(List<IMessageEvent> events);
    void unregisterEvent(IMessageEvent event);
    void unregisterAllEvents();
    IServer getServer();
    void setServer(IServer server);
}
