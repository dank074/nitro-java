package com.nitro.nitro.room.object.data;

import java.util.List;

public interface IStuffData {

    List<Object> getMessageArray();
    String getLegacyString();
    int getState();
    void setState(Object data);
    boolean isUnique();
    int getUniqueNumber();
    int getUniqueSeries();
    int getFlags();
    void setFlags(int flags);
}
