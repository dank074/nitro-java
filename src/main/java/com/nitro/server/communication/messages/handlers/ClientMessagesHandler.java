package com.nitro.server.communication.messages.handlers;

import com.nitro.core.communication.messages.IMessageEvent;
import com.nitro.core.communication.messages.MessageHandler;
import com.nitro.server.communication.messages.incoming.client.ClientPongEvent;
import com.nitro.server.communication.messages.incoming.client.ClientReleaseVersionEvent;
import com.nitro.server.communication.messages.parser.client.ClientPongParser;
import com.nitro.server.communication.messages.parser.client.ClientReleaseVersionParser;

public class ClientMessagesHandler extends MessageHandler {

    public void onInit() {
        this.registerEvent(new ClientPongEvent(ClientMessagesHandler::onClientPongEvent));
        this.registerEvent(new ClientReleaseVersionEvent(ClientMessagesHandler::onClientReleaseVersionEvent));
    }

    private static void onClientPongEvent(IMessageEvent event) {
        ClientPongParser parser = (ClientPongParser) event.getParser();

        if(parser == null) return;
    }

    private static void onClientReleaseVersionEvent(IMessageEvent event) {
        ClientReleaseVersionParser parser = (ClientReleaseVersionParser) event.getParser();

        if(parser == null) return;
    }
}
