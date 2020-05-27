package com.nitro.nitro.user;

import com.nitro.nitro.Nitro;
import com.nitro.nitro.database.entities.user.UserEntity;
import com.nitro.nitro.database.entities.user.UserInfoEntity;
import com.nitro.nitro.database.entities.user.UserStatisticsEntity;
import io.ebean.bean.EntityBean;

import java.util.Date;

public class UserDetails {

    private final IUser user;
    private final UserEntity entity;

    public UserDetails(IUser user, UserEntity entity) {
        this.user = user;
        this.entity = entity;

        this.setExtendedData();
    }

    public void save(boolean skipQueue) {
        Nitro.getInstance().getDatabaseInstance().saveEntity((EntityBean) this.entity, skipQueue);
    }

    public void save() {
        this.save(false);
    }

    private void setExtendedData() {
        if(this.entity == null) return;

        boolean needsSave = false;

        if(this.entity.getUserInfoEntity() == null) {
            this.entity.setUserInfoEntity(new UserInfoEntity());

            needsSave = true;
        }

        if(this.entity.getUserStatisticsEntity() == null) {
            this.entity.setUserStatisticsEntity(new UserStatisticsEntity());

            needsSave = true;
        }

        if(needsSave) this.save(true);
    }

    public void setOnlineStatus(boolean flag, boolean updateStreak) {
        if(flag) {
            this.entity.setOnline(true);
            this.entity.setLastOnline(new Date());

            if(updateStreak) this.updateLoginStreak();
        } else {
            this.entity.setOnline(false);
        }

        this.save();
    }

    private void updateLoginStreak() {

    }

    public int getId() {
        return this.entity.getId();
    }

    public String getUsername() {
        return this.entity.getUsername();
    }

    public String getMotto() {
        return this.entity.getMotto();
    }

    public String getGender() {
        return this.entity.getGender().toUpperCase();
    }

    public String getFigure() {
        return this.entity.getFigure();
    }

    public boolean isOnline() {
        return this.entity.isOnline();
    }

    public Date getTimestampCreated() {
        return this.entity.getTimestampCreated();
    }

    public int getAchievementScore() {
        return this.entity.getUserInfoEntity().getAchievementScore();
    }
}
