package com.nitro.server.communication.server.netty.codec;

import com.nitro.core.communication.messages.IMessageComposer;
import com.nitro.server.communication.server.netty.NettyServer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

public class NettyEncoder extends MessageToMessageEncoder<IMessageComposer> {

    private NettyServer nettyServer;

    public NettyEncoder(NettyServer nettyServer) {
        this.nettyServer = nettyServer;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, IMessageComposer composer, List<Object> out) throws Exception {
        int header = this.nettyServer.getMessages().getComposerId(composer);

        if(header == -1) return;

        ByteBuf buffer = this.nettyServer.getCodec().encode(header, composer.getMessageArray());

        out.add(buffer);
    }
}
