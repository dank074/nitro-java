package com.nitro.nitro.room;

import com.nitro.common.disposable.IDisposable;

public interface IRoom extends IDisposable {

    void cancelDispose();
    IRoomContainer getContainer();
    int getId();
}
