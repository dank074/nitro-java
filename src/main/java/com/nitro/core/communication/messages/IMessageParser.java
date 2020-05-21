package com.nitro.core.communication.messages;

public interface IMessageParser {

    boolean flush();
    boolean parse(IMessageDataWrapper wrapper);
}
