package com.nitro.core.communication.messages;

import io.netty.buffer.ByteBuf;

public interface IMessageDataWrapper {

    void dispose();
    int readByte();
    ByteBuf readBytes(int length);
    boolean readBoolean();
    int readShort();
    int readInt();
    String readString();
    int getHeader();
    boolean bytesAvailable();
}
