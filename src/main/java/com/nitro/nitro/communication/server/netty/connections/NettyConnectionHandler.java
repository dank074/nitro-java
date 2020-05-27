package com.nitro.nitro.communication.server.netty.connections;

import com.nitro.core.communication.connections.Connection;
import com.nitro.core.communication.connections.IConnection;
import com.nitro.core.communication.messages.IMessageDataWrapper;
import com.nitro.core.communication.messages.IMessageEvent;
import com.nitro.core.communication.messages.IMessageParser;
import com.nitro.nitro.communication.server.netty.NettyServer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;

public class NettyConnectionHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private final NettyServer nettyServer;

    public NettyConnectionHandler(NettyServer nettyServer) {
        this.nettyServer = nettyServer;
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        String ipAddress = NettyConnection.getIpAddress(ctx.channel());

        IConnection connection = this.nettyServer.addConnection(new NettyConnection(ctx.channel(), ipAddress));

        if(connection != null) {
            ctx.channel().attr(Connection.CONNECTION_KEY).set(connection);

            return;
        }

        ctx.channel().close();
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) {
        IConnection connection = ctx.channel().attr(Connection.CONNECTION_KEY).get();

        if(connection != null) this.nettyServer.removeConnection(connection);
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf buffer) {
        int delimiter = buffer.readByte();
        buffer.resetReaderIndex();

        if(delimiter == 60) {
            String policyString = "<?xml version=\"1.0\"?>\r\n"
                    + "<!DOCTYPE cross-domain-policy SYSTEM \"/xml/dtds/cross-domain-policy.dtd\">\r\n"
                    + "<cross-domain-policy>\r\n"
                    + "<allow-access-from domain=\"*\" to-ports=\"*\" />\r\n"
                    + "</cross-domain-policy>\0)";

            ChannelFuture future = ctx.channel().writeAndFlush(Unpooled.copiedBuffer(policyString.getBytes()));
            future.addListener(ChannelFutureListener.CLOSE);

            return;
        }

        IConnection connection = ctx.channel().attr(Connection.CONNECTION_KEY).get();

        if(connection == null) {
            ctx.channel().close();

            return;
        }

        try {
            List<IMessageDataWrapper> wrappers = this.nettyServer.getCodec().decode(buffer);

            if((wrappers == null) || (wrappers.size() < 1)) return;

            for(IMessageDataWrapper wrapper : wrappers) {
                if(wrapper == null) continue;

                List<IMessageEvent> events = this.getEventsForWrapper(wrapper);

                if((events == null) || (events.size() < 1)) continue;

                for(IMessageEvent event : events) {
                    if(event == null) continue;

                    connection.handleEvent(event);
                }
            }
        } catch(Exception e) {
            this.nettyServer.getLogger().error(e.getMessage());
        }
    }

    private List<IMessageEvent> getEventsForWrapper(IMessageDataWrapper wrapper) {
        if((wrapper == null) || (this.nettyServer == null)) return null;

        List<IMessageEvent> events = this.nettyServer.getMessages().getEvents(wrapper.getHeader());

        if((events == null) || (events.size() == 0)) return null;

        IMessageParser parser = events.get(0).getParser();

        if((parser == null) || !parser.flush() || !parser.parse(wrapper)) return null;

        return events;
    }
}
