package com.nitro.application;

import com.nitro.core.communication.messages.IMessageListener;
import com.nitro.core.communication.messages.MessageHandler;
import com.nitro.nitro.communication.messages.incoming.user.data.*;
import com.nitro.nitro.communication.messages.outgoing.user.data.UserInfoComposer;
import com.nitro.nitro.user.IUser;

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
    }

    @MessageHandler
    public void onUserTagsEvent(UserTagsEvent event) {
    }
}
