package com.nitro.nitro.communication.messages.outgoing.user.data;

import com.nitro.core.communication.messages.IMessageComposer;
import com.nitro.nitro.user.IUser;
import com.nitro.nitro.user.UserDetails;

import java.util.ArrayList;
import java.util.List;

public class UserInfoComposer implements IMessageComposer {

    private List<Object> data;

    public UserInfoComposer(IUser user) {
        this.data = new ArrayList<>();

        UserDetails userDetails = user.getUserDetails();

        this.data.add(userDetails.getId());
        this.data.add(userDetails.getUsername());
        this.data.add(userDetails.getFigure());
        this.data.add(userDetails.getGender());
        this.data.add(userDetails.getMotto());
        this.data.add(userDetails.getUsername());
        this.data.add(false);
        this.data.add(0); // respects
        this.data.add(0); // remaining
        this.data.add(0); // pet
        this.data.add(false);
        this.data.add("01-01-1970 00:00:00");
        this.data.add(false); // change name
        this.data.add(false);
    }

    public void dispose() {

    }

    public Object[] getMessageArray() {
        return this.data.toArray();
    }
}
