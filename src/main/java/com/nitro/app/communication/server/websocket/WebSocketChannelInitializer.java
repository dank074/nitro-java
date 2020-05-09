package com.nitro.app.communication.server.websocket;

import com.nitro.app.communication.server.websocket.connections.WebSocketConnectionHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;

public class WebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {

    private final WebSocketServer webSocketServer;

    public WebSocketChannelInitializer(WebSocketServer webSocketServer) {
        this.webSocketServer = webSocketServer;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline
                .addLast(new HttpServerCodec())
                .addLast(new HttpObjectAggregator(65536))
                .addLast(new WebSocketServerCompressionHandler())
                //.addLast("encoder", new WebSocketEncoder(this.webSocketServer))
                //.addLast("decoder", new WebSocketDecoder(this.webSocketServer))
                .addLast(new WebSocketConnectionHandler(this.webSocketServer));
    }
}
