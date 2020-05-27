package com.nitro.nitro.database.entities.user;

import com.nitro.nitro.database.abstracts.EntityTimestamp;
import io.ebean.annotation.ConstraintMode;
import io.ebean.annotation.DbForeignKey;
import io.ebean.annotation.NotNull;

import javax.persistence.*;

@Entity
public class UserInfoEntity extends EntityTimestamp {

    public UserInfoEntity() {
        this.friendRequestsDisabled = "0";
        this.toolbarToggles = "1";
        this.oldChat = "0";
        this.ignoreInvites = "0";
        this.cameraFocus = "0";
        this.navigatorSearchOpen = "0";
    }

    @Id
    private int id;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @DbForeignKey(onDelete = ConstraintMode.CASCADE)
    private UserEntity userEntity;

    @Column(columnDefinition = "int(11) default 0")
    private int homeRoomId;

    @NotNull
    @Column(columnDefinition = "int(11) default 0")
    private int respectsReceived;

    @NotNull
    @Column(columnDefinition = "int(11) default 0")
    private int respectsRemaining;

    @NotNull
    @Column(columnDefinition = "int(11) default 0")
    private int respectsPetRemaining;

    @NotNull
    @Column(columnDefinition = "int(11) default 0")
    private int achievementScore;

    @NotNull
    @Column(columnDefinition = "enum('0','1') default '0'")
    private String friendRequestsDisabled;

    @NotNull
    @Column(columnDefinition = "enum('0','1') default '1'")
    private String toolbarToggles;

    @NotNull
    @Column(columnDefinition = "int(11) default 0")
    private int volumeFurni;

    @NotNull
    @Column(columnDefinition = "int(11) default 0")
    private int volumeSystem;

    @NotNull
    @Column(columnDefinition = "int(11) default 0")
    private int volumeTrax;

    @NotNull
    @Column(columnDefinition = "enum('0','1') default '0'")
    private String oldChat;

    @NotNull
    @Column(columnDefinition = "enum('0','1') default '0'")
    private String ignoreInvites;

    @NotNull
    @Column(columnDefinition = "enum('0','1') default '0'")
    private String cameraFocus;

    @NotNull
    @Column(columnDefinition = "int(11) default 0")
    private int chatStyle;

    @NotNull
    @Column(columnDefinition = "int(11) default 100")
    private int navigatorX;

    @NotNull
    @Column(columnDefinition = "int(11) default 100")
    private int navigatorY;

    @NotNull
    @Column(columnDefinition = "int(11) default 435")
    private int navigatorWidth;

    @NotNull
    @Column(columnDefinition = "int(11) default 535")
    private int navigatorHeight;

    @NotNull
    @Column(columnDefinition = "enum('0','1') default '0'")
    private String navigatorSearchOpen;

    public UserEntity getUserEntity() {
        return this.userEntity;
    }

    public void setUserEntity(UserEntity entity) {
        this.userEntity = entity;
    }

    public int getHomeRoomId() {
        return this.homeRoomId;
    }

    public void setHomeRoomId(int roomId) {
        this.homeRoomId = roomId;
    }

    public int getRespectsReceived() {
        return this.respectsReceived;
    }

    public void setRespectsReceived(int amount) {
        this.respectsReceived = amount;
    }

    public int getRespectsRemaining() {
        return this.respectsRemaining;
    }

    public void setRespectsRemaining(int amount) {
        this.respectsRemaining = amount;
    }

    public int getRespectsPetRemaining() {
        return this.respectsPetRemaining;
    }

    public void setRespectsPetRemaining(int amount) {
        this.respectsPetRemaining = amount;
    }

    public int getAchievementScore() {
        return this.achievementScore;
    }

    public void setAchievementScore(int amount) {
        this.achievementScore = amount;
    }
}
