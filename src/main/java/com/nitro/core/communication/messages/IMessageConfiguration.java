package com.nitro.core.communication.messages;

import java.util.Map;

public interface IMessageConfiguration {

    Map<Integer, Class<? extends IMessageEvent>> getEvents();
    Map<Integer, Class<? extends IMessageComposer>> getComposers();
}
