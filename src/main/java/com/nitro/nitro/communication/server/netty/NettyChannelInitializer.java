package com.nitro.nitro.communication.server.netty;

import com.nitro.nitro.communication.server.netty.connections.NettyConnectionHandler;
import com.nitro.nitro.communication.server.netty.handler.MessageInterceptorHandler;
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

        pipeline.addLast("messageInterceptor", new MessageInterceptorHandler());
        pipeline.addLast(new NettyConnectionHandler(this.nettyServer));
    }
}
