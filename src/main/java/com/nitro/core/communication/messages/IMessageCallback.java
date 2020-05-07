package com.nitro.core.communication.messages;

public interface IMessageCallback {

    void handle(IMessageEvent messageEvent);
}
