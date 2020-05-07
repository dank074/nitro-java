package com.nitro.server.communication.messages.handlers;

import com.nitro.core.communication.messages.IMessageEvent;
import com.nitro.core.communication.messages.MessageHandler;
import com.nitro.server.communication.messages.incoming.security.SecurityMachineEvent;
import com.nitro.server.communication.messages.incoming.security.SecurityTicketEvent;
import com.nitro.server.communication.messages.outgoing.security.SecurityAuthenticatedComposer;
import com.nitro.server.communication.messages.outgoing.security.SecurityMachineComposer;
import com.nitro.server.communication.messages.parser.security.SecurityMachineParser;
import com.nitro.server.communication.messages.parser.security.SecurityTicketParser;

public class SecurityMessagesHandler extends MessageHandler {

    public void onInit() {
        this.registerEvent(new SecurityMachineEvent(SecurityMessagesHandler::onSecurityMachineEvent));
        this.registerEvent(new SecurityTicketEvent(SecurityMessagesHandler::onSecurityTicketEvent));
    }

    private static void onSecurityMachineEvent(IMessageEvent event) {
        SecurityMachineParser parser = (SecurityMachineParser) event.getParser();

        if(parser == null) return;

        event.getConnection().processComposer(new SecurityMachineComposer(parser.getMachineId()));
    }

    private static void onSecurityTicketEvent(IMessageEvent event) {
        SecurityTicketParser parser = (SecurityTicketParser) event.getParser();

        if(parser == null) return;

        event.getConnection().processComposer(new SecurityAuthenticatedComposer());
    }
}
