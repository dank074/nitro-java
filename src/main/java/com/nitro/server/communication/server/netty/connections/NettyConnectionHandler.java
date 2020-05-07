package com.nitro.server.communication.server.netty.connections;

import com.nitro.core.communication.connections.Connection;
import com.nitro.core.communication.connections.IConnection;
import com.nitro.core.communication.messages.IMessageCallback;
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
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        String ipAddress = NettyConnection.getIpAddress(ctx.channel());

        IConnection connection = this.nettyServer.addConnection(new NettyConnection(ctx.channel(), ipAddress));

        if(connection != null) {
            ctx.channel().attr(Connection.CONNECTION_KEY).set(connection);

            System.out.println("Connection Added");
        }
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        IConnection connection = ctx.channel().attr(Connection.CONNECTION_KEY).get();

        if(connection != null) {
            connection.dispose();

            System.out.println("Connection Removed");
        }
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, IMessageDataWrapper wrapper) {
        try {
            IConnection connection = ctx.channel().attr(Connection.CONNECTION_KEY).get();

            if(connection != null) {
                List<IMessageEvent> events = this.getMessagesForWrapper(wrapper);

                if(events != null) this.handleEvents(connection, events);
            }
        } catch (Exception e) {

        }
    }

    private List<IMessageEvent> getMessagesForWrapper(IMessageDataWrapper wrapper) {
        if(wrapper == null) return null;

        List<IMessageEvent> events = this.nettyServer.getMessages().getEvents(wrapper.getHeader());

        if((events == null) || (events.size() == 0)) return null;

        IMessageParser parser = events.get(0).getParser();

        if((parser == null) || !parser.flush() || !parser.parse(wrapper)) return null;

        return events;
    }

    private void handleEvents(IConnection connection, List<IMessageEvent> events) {
        if((events == null) || (events.size() == 0)) return;

        for(IMessageEvent event : events) {
            if(event == null) continue;

            event.setConnection(connection);

            IMessageCallback messageCallback = event.getMessageCallback();

            if(messageCallback != null) messageCallback.handle(event);

            event.dispose();
        }
    }
}
