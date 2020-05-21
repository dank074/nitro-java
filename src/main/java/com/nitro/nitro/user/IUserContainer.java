package com.nitro.nitro.user;

import com.nitro.core.communication.connections.IConnection;

public interface IUserContainer {

    IUser getActiveUser(int id);
    IUser getActiveUser(String username);
    IUser getOfflineUser(int id, boolean remove);
    IUser getOfflineUser(String username, boolean remove);
    IUser getOfflineUser(int id);
    IUser getOfflineUser(String username);
    IUser createUser(int id, IConnection connection);
    void removeUser(int id);
    void removeUser(IUser user);
    void removeAllUsers();
}
