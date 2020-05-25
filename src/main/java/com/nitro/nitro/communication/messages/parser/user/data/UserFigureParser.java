package com.nitro.nitro.communication.messages.parser.user.data;

import com.nitro.core.communication.messages.IMessageDataWrapper;
import com.nitro.core.communication.messages.IMessageParser;

public class UserFigureParser implements IMessageParser {

    private String gender;
    private String figure;

    public boolean flush() {
        this.gender = null;
        this.figure = null;

        return true;
    }

    public boolean parse(IMessageDataWrapper wrapper) {
        if(wrapper == null) return false;

        this.gender = wrapper.readString();
        this.figure = wrapper.readString();

        return true;
    }

    public String getGender() {
        return this.gender;
    }

    public String getFigure() {
        return this.figure;
    }
}
