package com.nitro.application;

import com.nitro.core.communication.messages.IMessageListener;
import com.nitro.core.communication.messages.MessageHandler;
import com.nitro.nitro.Nitro;
import com.nitro.nitro.communication.messages.incoming.security.SecurityMachineEvent;
import com.nitro.nitro.communication.messages.incoming.security.SecurityTicketEvent;
import com.nitro.nitro.communication.messages.outgoing.security.SecurityAuthenticatedComposer;
import com.nitro.nitro.communication.messages.outgoing.security.SecurityMachineComposer;
import com.nitro.nitro.communication.messages.outgoing.user.access.UserPermissionsComposer;
import com.nitro.nitro.communication.messages.outgoing.user.access.UserRightsComposer;
import com.nitro.nitro.communication.messages.outgoing.user.data.UserAchievementScoreComposer;
import com.nitro.nitro.communication.messages.outgoing.user.data.UserFirstLoginOfDayComposer;
import com.nitro.nitro.communication.messages.outgoing.user.data.UserHomeRoomComposer;
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

        user.getUserDetails().setOnlineStatus(true, true);

        event.getConnection().processComposer(new SecurityAuthenticatedComposer());
        event.getConnection().processComposer(new UserRightsComposer());
        event.getConnection().processComposer(new UserPermissionsComposer(false, 1, false));
        event.getConnection().processComposer(new UserAchievementScoreComposer(0));
        event.getConnection().processComposer(new UserHomeRoomComposer(0, false));
        event.getConnection().processComposer(new UserFirstLoginOfDayComposer(false));
    }
}
