package com.nitro.nitro.communication.messages.outgoing.user.data;

import com.nitro.core.communication.messages.IMessageComposer;
import com.nitro.nitro.user.IUser;
import com.nitro.nitro.user.UserDetails;

import java.util.ArrayList;
import java.util.List;

public class UserProfileComposer implements IMessageComposer {

    private List<Object> data;

    public UserProfileComposer(IUser user, boolean isFriend, boolean isFriendRequested) {
        this.data = new ArrayList<>();

        UserDetails userDetails = user.getUserDetails();

        this.data.add(userDetails.getId());
        this.data.add(userDetails.getUsername());
        this.data.add(userDetails.getFigure());
        this.data.add(userDetails.getMotto());
        this.data.add(userDetails.getTimestampCreated().toString());
        this.data.add(userDetails.getAchievementScore());
        this.data.add(0); // total friends
        this.data.add(isFriend);
        this.data.add((!isFriend && isFriendRequested));
        this.data.add(userDetails.isOnline());
        this.data.add(0); // iterate groups
        this.data.add(0); // last online
        this.data.add(true);
    }

    public void dispose() {

    }

    public Object[] getMessageArray() {
        return this.data.toArray();
    }
}
