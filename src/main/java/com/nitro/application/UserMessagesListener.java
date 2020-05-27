package com.nitro.application;

import com.nitro.core.communication.messages.IMessageListener;
import com.nitro.core.communication.messages.MessageHandler;
import com.nitro.nitro.Nitro;
import com.nitro.nitro.communication.messages.incoming.user.data.*;
import com.nitro.nitro.communication.messages.outgoing.user.data.UserInfoComposer;
import com.nitro.nitro.communication.messages.outgoing.user.data.UserProfileComposer;
import com.nitro.nitro.user.IUser;
import com.nitro.nitro.user.IUserManager;

public class UserMessagesListener implements IMessageListener {

    @MessageHandler
    public void onUserFigureEvent(UserFigureEvent event) {
    }

    @MessageHandler
    public void onUserInfoEvent(UserInfoEvent event) {
        IUser user = event.getConnection().getUser();

        if(user == null) return;

        event.getConnection().processComposer(new UserInfoComposer(user));
    }

    @MessageHandler
    public void onUserMottoEvent(UserMottoEvent event) {
    }

    @MessageHandler
    public void onUserProfileEvent(UserProfileEvent event) {
        IUserManager userManager = Nitro.getInstance().getUserManager();

        if(userManager == null) return;

        IUser profile = userManager.getOfflineUser(event.getParser().getUserId());

        if(profile == null) return;

        event.getConnection().processComposer(new UserProfileComposer(profile, false, false));
    }

    @MessageHandler
    public void onUserTagsEvent(UserTagsEvent event) {
    }
}
