package com.nitro.server;

import com.nitro.core.INitroCore;
import com.nitro.core.NitroCore;
import com.nitro.core.communication.servers.IServer;
import com.nitro.server.communication.messages.NitroMessages;
import com.nitro.server.communication.messages.listeners.ClientMessagesListener;
import com.nitro.server.communication.messages.listeners.SecurityMessagesListener;
import com.nitro.server.communication.server.netty.NettyServer;
import com.nitro.server.room.IRoomEngine;
import com.nitro.server.room.RoomEngine;

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

    private static void setupServer() {
        String ip = "0.0.0.0";
        int port = 1242;

        IServer server = core.getCommunicationManager().addServer(new NettyServer(ip, port));

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
