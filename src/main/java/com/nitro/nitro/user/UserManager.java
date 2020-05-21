package com.nitro.nitro.user;

import com.nitro.common.component.Component;
import com.nitro.core.communication.connections.IConnection;
import com.nitro.nitro.database.daos.UserDao;
import com.nitro.nitro.database.entities.user.UserEntity;

import java.util.HashMap;
import java.util.Map;

public class UserManager extends Component implements IUserManager {

    private final Map<Integer, IUser> users;

    public UserManager() {
        this.users = new HashMap<>();
    }

    protected void onInit() {
    }

    protected void onDispose() {
        this.removeAllUsers();
    }

    public IUser getActiveUser(int id) {
        return this.users.get(id);
    }

    public IUser getActiveUser(String username) {
        if(this.users.size() == 0) return null;

        username = username.toLowerCase();

        for(IUser user : this.users.values()) {
            if(user == null) continue;

            if(user.getUserDetails().getUsername().toLowerCase().equals(username)) return user;
        }

        return null;
    }

    public IUser getOfflineUser(int id, boolean remove) {
        IUser existing = this.getActiveUser(id);

        if(existing != null) {
            if(remove) existing.dispose();
            else return existing;
        }

        UserEntity entity = UserDao.getUser(id);

        if(entity != null) return new User(this, entity);

        return null;
    }

    public IUser getOfflineUser(String username, boolean remove) {
        IUser existing = this.getActiveUser(username);

        if(existing != null) {
            if(remove) existing.dispose();
            else return existing;
        }

        UserEntity entity = UserDao.getUser(username);

        if(entity != null) return new User(this, entity);

        return null;
    }

    public IUser getOfflineUser(int id) {
        return this.getOfflineUser(id, false);
    }

    public IUser getOfflineUser(String username) {
        return this.getOfflineUser(username, false);
    }

    public IUser createUser(int id, IConnection connection) {
        if((id == 0) || (connection == null)) return null;

        IUser user = this.getOfflineUser(id, true);

        if(user == null) return null;

        if(!user.setConnection(connection)) {
            user.dispose();
            connection.dispose();
        }

        this.users.put(user.getId(), user);

        user.init();

        return user;
    }

    public void removeUser(int id) {
        IUser existing = this.users.remove(id);

        if(existing == null) return;

        existing.dispose();
    }

    public void removeUser(IUser user) {
        this.removeUser(user.getId());
    }

    public void removeAllUsers() {
        if(this.users.size() == 0) return;

        for(int id : this.users.keySet()) this.removeUser(id);
    }
}
