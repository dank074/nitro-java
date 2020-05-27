package com.nitro.nitro.user;

import com.nitro.common.component.Component;
import com.nitro.core.communication.connections.IConnection;
import com.nitro.nitro.database.entities.user.UserEntity;

public class User extends Component implements IUser {

    private final IUserContainer container;
    private final UserDetails userDetails;

    private IConnection connection;

    public User(IUserContainer container, UserEntity userEntity) {
        this.container = container;
        this.userDetails = new UserDetails(this, userEntity);

        this.connection = null;
    }

    protected void onInit() {

    }

    protected void onDispose() {
        this.container.removeUser(this);

        this.userDetails.setOnlineStatus(false, false);

        if(this.connection != null) {
            this.connection.dispose();

            this.connection = null;
        }

        this.userDetails.save(true);
    }

    public boolean setConnection(IConnection connection) {
        if((this.connection != null) && (this.connection != connection)) return false;

        connection.setUser(this);

        this.connection = connection;

        return true;
    }

    public IUserContainer getContainer() {
        return this.container;
    }

    public UserDetails getUserDetails() {
        return this.userDetails;
    }

    public IConnection getConnection() {
        return this.connection;
    }

    public int getId() {
        return this.userDetails.getId();
    }

    public String getUsername() {
        return this.userDetails.getUsername();
    }
}
