package com.nitro.server.communication.server.websocket.codec;

import com.nitro.core.communication.messages.IMessageDataWrapper;
import com.nitro.server.communication.server.websocket.WebSocketServer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class WebSocketDecoder extends ByteToMessageDecoder {

    private WebSocketServer webSocketServer;

    public WebSocketDecoder(WebSocketServer webSocketServer) {
        this.webSocketServer = webSocketServer;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) {
        List<IMessageDataWrapper> wrappers = this.webSocketServer.getCodec().decode(buffer);

        if((wrappers == null) || (wrappers.size() < 1)) return;

        out.addAll(wrappers);
    }
}
