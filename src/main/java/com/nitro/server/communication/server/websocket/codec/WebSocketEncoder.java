package com.nitro.server.communication.server.websocket.codec;

import com.nitro.core.communication.messages.IMessageComposer;
import com.nitro.server.communication.server.websocket.WebSocketServer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

public class WebSocketEncoder extends MessageToMessageEncoder<IMessageComposer> {

    private WebSocketServer webSocketServer;

    public WebSocketEncoder(WebSocketServer webSocketServer) {
        this.webSocketServer = webSocketServer;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, IMessageComposer composer, List<Object> out) {
        int header = this.webSocketServer.getMessages().getComposerId(composer);

        if(header == -1) return;

        ByteBuf buffer = this.webSocketServer.getCodec().encode(header, composer.getMessageArray());

        out.add(buffer);
    }
}
