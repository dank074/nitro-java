package com.nitro.nitro.communication.server.netty;

import com.nitro.core.communication.servers.Server;
import com.nitro.nitro.communication.codec.evawire.EvaWireFormat;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.FixedRecvByteBufAllocator;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.net.InetSocketAddress;

public class NettyServer extends Server {

    final private static int BACK_LOG = 20;
    final private static int BUFFER_SIZE = 2048;

    private final DefaultChannelGroup channels;
    private final ServerBootstrap bootstrap;

    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;

    public NettyServer(String ip, int port) {
        super(ip, port);

        this.channels = new DefaultChannelGroup((GlobalEventExecutor.INSTANCE));
        this.bootstrap = new ServerBootstrap();

        this.bossGroup = null;
        this.workerGroup = null;

        this.codec = new EvaWireFormat();
    }

    protected void onInit() {
        this.createSocket();

        this.bootstrap.bind(new InetSocketAddress(this.getIp(), this.getPort()))
                .addListener(future -> {
                    if(!future.isSuccess()) {
                        System.out.println("NettyServer failed to start");
                    } else {
                        System.out.println("NettyServer is listening on {}:{}");
                    }
                });
    }

    protected void onDispose() {
        try {
            this.workerGroup.shutdownGracefully().sync();
            this.bossGroup.shutdownGracefully().sync();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        super.onDispose();
    }

    private void createSocket() {
        int totalThreads = Runtime.getRuntime().availableProcessors();

        this.bossGroup = (Epoll.isAvailable()) ? new EpollEventLoopGroup(totalThreads) : new NioEventLoopGroup(totalThreads);
        this.workerGroup = (Epoll.isAvailable()) ? new EpollEventLoopGroup(totalThreads) : new NioEventLoopGroup(totalThreads);

        this.bootstrap.group(bossGroup, workerGroup)
                .channel((Epoll.isAvailable()) ? EpollServerSocketChannel.class : NioServerSocketChannel.class)
                .childHandler(new NettyChannelInitializer(this))
                .option(ChannelOption.SO_BACKLOG, BACK_LOG)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.SO_RCVBUF, BUFFER_SIZE)
                .childOption(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(BUFFER_SIZE))
                .childOption(ChannelOption.ALLOCATOR, new PooledByteBufAllocator(true));
    }
}
