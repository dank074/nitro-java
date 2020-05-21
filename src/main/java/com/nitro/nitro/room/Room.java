package com.nitro.nitro.room;

public class Room implements IRoom {

    private IRoomContainer container;

    private int id;
    private RoomDetails details;

    private boolean disposed;

    public Room(IRoomContainer container) {
        this.container = container;

        this.disposed = false;
    }

    public void init() {

    }

    public void dispose() {
        if(this.isDisposed()) return;

        this.disposed = true;
    }

    public void cancelDispose() {

    }

    public boolean isDisposed() {
        return this.disposed;
    }

    public IRoomContainer getContainer() {
        return this.container;
    }

    public int getId() {
        return this.id;
    }
}
