package com.nitro.server.communication.server.netty.connections;

import com.nitro.core.communication.connections.Connection;
import com.nitro.core.communication.connections.IConnection;
import com.nitro.core.communication.messages.IMessageDataWrapper;
import com.nitro.core.communication.messages.IMessageEvent;
import com.nitro.core.communication.messages.IMessageParser;
import com.nitro.server.communication.server.netty.NettyServer;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;

public class NettyConnectionHandler extends SimpleChannelInboundHandler<IMessageDataWrapper> {

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
        }
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) {
        IConnection connection = ctx.channel().attr(Connection.CONNECTION_KEY).get();

        if(connection != null) {
            connection.dispose();
        }
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, IMessageDataWrapper wrapper) {
        try {
            IConnection connection = ctx.channel().attr(Connection.CONNECTION_KEY).get();

            if(connection != null) {
                connection.handleEvents(this.getEventsForWrapper(wrapper));
            }
        } catch (Exception e) {

        }
    }

    private List<IMessageEvent> getEventsForWrapper(IMessageDataWrapper wrapper) {
        if(wrapper == null) return null;

        List<IMessageEvent> events = this.nettyServer.getMessages().getEvents(wrapper.getHeader());

        if((events == null) || (events.size() == 0)) return null;

        IMessageParser parser = events.get(0).getParser();

        if((parser == null) || !parser.flush() || !parser.parse(wrapper)) return null;

        return events;
    }
}
