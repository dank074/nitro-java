package com.nitro.application;

import com.nitro.core.communication.messages.IMessageListener;
import com.nitro.core.communication.messages.MessageHandler;
import com.nitro.nitro.communication.messages.incoming.desktop.DesktopCampaignsEvent;
import com.nitro.nitro.communication.messages.incoming.desktop.DesktopNewsEvent;
import com.nitro.nitro.communication.messages.incoming.desktop.DesktopViewEvent;
import com.nitro.nitro.communication.messages.outgoing.desktop.DesktopCampaignsComposer;

public class DesktopMessagesListener implements IMessageListener {

    @MessageHandler
    public void onDesktopCampaignsEvent(DesktopCampaignsEvent event) {
        event.getConnection().processComposer(new DesktopCampaignsComposer());
    }

    @MessageHandler
    public void onDesktopNewsEvent(DesktopNewsEvent event) {
    }

    @MessageHandler
    public void onDesktopViewEvent(DesktopViewEvent event) {
    }
}
