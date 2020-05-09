package com.nitro.app;

import com.nitro.app.communication.messages.NitroMessages;
import com.nitro.app.communication.messages.listeners.ClientMessagesListener;
import com.nitro.app.communication.messages.listeners.SecurityMessagesListener;
import com.nitro.app.communication.server.netty.NettyServer;
import com.nitro.app.room.IRoomEngine;
import com.nitro.app.room.RoomEngine;
import com.nitro.core.INitroCore;
import com.nitro.core.NitroCore;
import com.nitro.core.communication.servers.IServer;

public class Application {

    private static INitroCore core;
    private static IRoomEngine roomEngine;

    public static void main(String[] args) {
        try {
            core = new NitroCore();
            roomEngine = new RoomEngine();

            core.getPluginManager().registerEventListener(new EventListener());

            setupServer();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void setupServer() {
        String ip = "0.0.0.0";
        int port = 1242;

        IServer server = core.getCommunicationManager().addServer(new NettyServer(ip, port));

        if(server == null) return;

        server.registerMessageConfiguration(new NitroMessages());
        server.registerMessageListener(new ClientMessagesListener());
        server.registerMessageListener(new SecurityMessagesListener());

        server.init();
    }

    public static INitroCore getCore() {
        return core;
    }

    public static IRoomEngine getRoomEngine() {
        return roomEngine;
    }
}
