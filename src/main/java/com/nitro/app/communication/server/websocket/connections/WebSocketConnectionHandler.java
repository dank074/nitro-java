package com.nitro.app.communication.server.websocket.connections;

import com.nitro.app.communication.server.websocket.WebSocketServer;
import com.nitro.core.communication.connections.Connection;
import com.nitro.core.communication.connections.IConnection;
import com.nitro.core.communication.messages.IMessageDataWrapper;
import com.nitro.core.communication.messages.IMessageEvent;
import com.nitro.core.communication.messages.IMessageParser;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;

public class WebSocketConnectionHandler extends SimpleChannelInboundHandler<IMessageDataWrapper> {

    private final WebSocketServer webSocketServer;

    public WebSocketConnectionHandler(WebSocketServer webSocketServer) {
        this.webSocketServer = webSocketServer;
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        String ipAddress = WebSocketConnection.getIpAddress(ctx.channel());

        IConnection connection = this.webSocketServer.addConnection(new WebSocketConnection(ctx.channel(), ipAddress));

        if(connection != null) {
            ctx.channel().attr(Connection.CONNECTION_KEY).set(connection);

            System.out.println("Websocket Connection Added");
        }
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) {
        IConnection connection = ctx.channel().attr(Connection.CONNECTION_KEY).get();

        if(connection != null) {
            connection.dispose();

            System.out.println("Websocket Connection Removed");
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

        List<IMessageEvent> events = this.webSocketServer.getMessages().getEvents(wrapper.getHeader());

        if((events == null) || (events.size() == 0)) return null;

        IMessageParser parser = events.get(0).getParser();

        if((parser == null) || !parser.flush() || !parser.parse(wrapper)) return null;

        return events;
    }
}
