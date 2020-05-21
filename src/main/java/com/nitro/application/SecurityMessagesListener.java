package com.nitro.application;

import com.nitro.core.communication.messages.IMessageListener;
import com.nitro.core.communication.messages.MessageHandler;
import com.nitro.nitro.Nitro;
import com.nitro.nitro.communication.messages.incoming.security.SecurityMachineEvent;
import com.nitro.nitro.communication.messages.incoming.security.SecurityTicketEvent;
import com.nitro.nitro.communication.messages.outgoing.security.SecurityAuthenticatedComposer;
import com.nitro.nitro.communication.messages.outgoing.security.SecurityMachineComposer;
import com.nitro.nitro.security.ISecurityManager;
import com.nitro.nitro.user.IUser;
import com.nitro.nitro.user.IUserManager;

public class SecurityMessagesListener implements IMessageListener {

    @MessageHandler
    public void onSecurityMachineEvent(SecurityMachineEvent event) {
        event.getConnection().processComposer(new SecurityMachineComposer(event.getParser().getMachineId()));
    }

    @MessageHandler
    public void onSecurityTicketEvent(SecurityTicketEvent event) {
        ISecurityManager securityManager = Nitro.INSTANCE.getSecurityManager();
        IUserManager userManager = Nitro.INSTANCE.getUserManager();

        if((securityManager == null) || (userManager == null)) {
            event.getConnection().dispose();

            return;
        }

        int userId = securityManager.getUserIdFromTicket(event.getParser().getTicket());

        if(userId == 0) {
            event.getConnection().dispose();

            return;
        }

        IUser user = userManager.createUser(userId, event.getConnection());

        if(user == null) {
            event.getConnection().dispose();

            return;
        }

        event.getConnection().processComposer(new SecurityAuthenticatedComposer());
    }
}
