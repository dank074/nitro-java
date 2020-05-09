package com.nitro.server.communication.messages.listeners;

import com.nitro.core.communication.messages.IMessageListener;
import com.nitro.core.communication.messages.MessageHandler;
import com.nitro.server.communication.messages.incoming.security.SecurityMachineEvent;
import com.nitro.server.communication.messages.incoming.security.SecurityTicketEvent;
import com.nitro.server.communication.messages.outgoing.security.SecurityAuthenticatedComposer;
import com.nitro.server.communication.messages.outgoing.security.SecurityMachineComposer;

public class SecurityMessagesListener implements IMessageListener {

    @MessageHandler
    public void onSecurityMachineEvent(SecurityMachineEvent event) {
        event.getConnection().processComposer(new SecurityMachineComposer(event.getParser().getMachineId()));
    }

    @MessageHandler
    public void onSecurityTicketEvent(SecurityTicketEvent event) {
        event.getConnection().processComposer(new SecurityAuthenticatedComposer());
    }
}
