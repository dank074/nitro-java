package com.nitro.nitro.communication.messages.outgoing.user.data;

import com.nitro.core.communication.messages.IMessageComposer;

import java.util.ArrayList;
import java.util.List;

public class UserAchievementScoreComposer implements IMessageComposer {

    private List<Object> data;

    public UserAchievementScoreComposer(int score) {
        this.data = new ArrayList<>();

        this.data.add(score);
    }

    public void dispose() {

    }

    public Object[] getMessageArray() {
        return this.data.toArray();
    }
}
