package com.nitro.room;

public interface IRoomManager {

    IRoomInstance getInstance(int id);
    IRoomInstance getActiveInstance(int id);
    void removeInstance(int id);
    void removeAllInstances();
}
