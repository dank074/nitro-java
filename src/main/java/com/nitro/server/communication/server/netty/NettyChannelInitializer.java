package com.nitro.server.communication.server.netty;

import com.nitro.server.communication.server.netty.codec.NettyDecoder;
import com.nitro.server.communication.server.netty.connections.NettyConnectionHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class NettyChannelInitializer extends ChannelInitializer<SocketChannel> {

    private final NettyServer nettyServer;

    public NettyChannelInitializer(NettyServer nettyServer) {
        this.nettyServer = nettyServer;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline
                .addLast(new NettyDecoder(this.nettyServer))
                .addLast(new NettyConnectionHandler(this.nettyServer));
    }
}
