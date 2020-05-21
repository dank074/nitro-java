package com.nitro.application;

import com.nitro.core.communication.messages.IMessageListener;
import com.nitro.core.communication.messages.MessageHandler;
import com.nitro.nitro.communication.messages.incoming.client.ClientPongEvent;
import com.nitro.nitro.communication.messages.incoming.client.ClientReleaseVersionEvent;

public class ClientMessagesListener implements IMessageListener {

    @MessageHandler
    public void onClientPongEvent(ClientPongEvent event) {
    }

    @MessageHandler
    public void onClientReleaseVersionEvent(ClientReleaseVersionEvent event) {
    }
}
