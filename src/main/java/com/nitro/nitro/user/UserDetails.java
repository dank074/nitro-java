package com.nitro.nitro.user;

import com.nitro.nitro.database.entities.user.UserEntity;

public class UserDetails {

    private final IUser user;
    private final UserEntity entity;

    public UserDetails(IUser user, UserEntity entity) {
        this.user = user;
        this.entity = entity;
    }

    public int getId() {
        return this.entity.id;
    }

    public String getUsername() {
        return this.entity.username;
    }
}
