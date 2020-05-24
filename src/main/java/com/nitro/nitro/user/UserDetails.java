package com.nitro.nitro.user;

import com.nitro.nitro.database.entities.user.UserEntity;
import com.nitro.nitro.database.entities.user.UserInfoEntity;
import com.nitro.nitro.database.entities.user.UserStatisticsEntity;

public class UserDetails {

    private final IUser user;
    private final UserEntity entity;

    public UserDetails(IUser user, UserEntity entity) {
        this.user = user;
        this.entity = entity;

        this.setExtendedData();
    }

    private void setExtendedData() {
        if(this.entity == null) return;

        if(this.entity.userInfoEntity == null) {
            UserInfoEntity entity = new UserInfoEntity(this.entity);

            this.entity.userInfoEntity = entity;

            try {
                entity.save();
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }
        }

        if(this.entity.userStatisticsEntity == null) {
            UserStatisticsEntity entity = new UserStatisticsEntity(this.entity);

            this.entity.userStatisticsEntity = entity;

            try {
                entity.save();
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }
        }
    }

    public int getId() {
        return this.entity.id;
    }

    public String getUsername() {
        return this.entity.username;
    }
}
