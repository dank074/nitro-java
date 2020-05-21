package com.nitro.nitro.room.object;

import com.nitro.nitro.room.utils.Point;

public interface IRoomObject {

    void dispose();

    int getId();
    String getType();
    IRoomObjectModel getModel();

    Point getLocation();
    Point getDirection();
}
