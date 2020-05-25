package com.nitro.nitro.communication.codec.evawire;

import com.nitro.core.communication.codec.Byte;
import com.nitro.core.communication.codec.ICodec;
import com.nitro.core.communication.codec.Short;
import com.nitro.core.communication.messages.IMessageDataWrapper;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.ArrayList;
import java.util.List;

public class EvaWireFormat implements ICodec {

    public ByteBuf encode(int header, Object[] messages) {
        ByteBuf buffer = Unpooled.buffer();

        buffer.writeInt(0).writeShort(header);

        for(Object message : messages) {
            if(message instanceof Byte) {
                buffer.writeByte(((Byte) message).getValue());

                continue;
            }

            if(message instanceof Short) {
                buffer.writeShort(((Short) message).getValue());

                continue;
            }

            if(message instanceof Integer) {
                buffer.writeInt((Integer) message);

                continue;
            }

            if(message instanceof Boolean) {
                buffer.writeInt(((Boolean) message ? 1 : 0));

                continue;
            }

            if(message instanceof String) {
                String messageString = message.toString();

                if(messageString.length() == 0) {
                    buffer.writeShort(0);
                } else {
                    buffer.writeShort(messageString.length());
                    buffer.writeBytes(messageString.getBytes());
                }
            }
        }

        buffer.setInt(0, (buffer.writerIndex() - 4));

        return buffer;
    }

    public List<IMessageDataWrapper> decode(ByteBuf buffer) {
        List<IMessageDataWrapper> wrappers = new ArrayList<>();

        while(true) {
            if(buffer.readableBytes() < 6) return wrappers;

            int length = buffer.readInt();

            if(length < 2) return wrappers;

            ByteBuf extracted = buffer.readBytes(length);

            wrappers.add(new EvaWireDataWrapper(extracted.readShort(), extracted));
        }
    }
}
