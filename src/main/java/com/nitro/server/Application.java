package com.nitro.server;

import com.nitro.core.INitroCore;
import com.nitro.core.NitroCore;
import com.nitro.core.communication.messages.IMessageEvent;
import com.nitro.core.communication.servers.IServer;
import com.nitro.server.communication.messages.NitroMessages;
import com.nitro.server.communication.messages.incoming.client.ClientReleaseVersionEvent;
import com.nitro.server.communication.messages.parser.client.ClientReleaseVersionParser;
import com.nitro.server.communication.server.netty.NettyServer;
import com.nitro.server.room.IRoomEngine;
import com.nitro.server.room.RoomEngine;

import java.lang.reflect.InvocationTargetException;

public class Application {

    private static INitroCore core;
    private static IRoomEngine roomEngine;

    public static void main(String[] args) {
        try {
            core = new NitroCore();
            roomEngine = new RoomEngine();

            setupServer();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void setupServer() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String ip = "0.0.0.0";
        int port = 1242;

        IServer server = core.getCommunicationManager().addServer(new NettyServer(ip, port));

        server.registerMessages(new NitroMessages());

        server.getMessages().registerMessageEvent(new ClientReleaseVersionEvent(Application::onClientReleaseVersionEvent));

        server.init();
    }

    private static void onClientReleaseVersionEvent(IMessageEvent event) {
        ClientReleaseVersionParser parser = (ClientReleaseVersionParser) event.getParser();

        if(parser == null) return;

        System.out.println(parser.getVersion());
    }

    public static INitroCore getCore() {
        return core;
    }

    public static IRoomEngine getRoomEngine() {
        return roomEngine;
    }
}
