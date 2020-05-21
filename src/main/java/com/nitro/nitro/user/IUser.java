package com.nitro.nitro.user;

import com.nitro.common.component.IComponent;
import com.nitro.core.communication.connections.IConnection;

public interface IUser extends IComponent {

    boolean setConnection(IConnection connection);
    IUserContainer getContainer();
    UserDetails getUserDetails();
    IConnection getConnection();
    int getId();
    String getUsername();
}
