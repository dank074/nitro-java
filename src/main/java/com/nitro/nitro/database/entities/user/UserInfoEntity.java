package com.nitro.nitro.database.entities.user;

import com.nitro.nitro.database.abstracts.EntityTimestamp;
import io.ebean.annotation.ConstraintMode;
import io.ebean.annotation.DbForeignKey;
import io.ebean.annotation.NotNull;

import javax.persistence.*;

@Entity
public class UserInfoEntity extends EntityTimestamp {

    public UserInfoEntity(UserEntity userEntity) {
        this.userEntity = userEntity;

        this.friendRequestsDisabled = "0";
        this.toolbarToggles = "1";
        this.oldChat = "0";
        this.ignoreInvites = "0";
        this.cameraFocus = "0";
        this.navigatorSearchOpen = "0";
    }

    @Id
    public int id;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @DbForeignKey(onDelete = ConstraintMode.CASCADE)
    public UserEntity userEntity;

    @Column(columnDefinition = "int(11) default 0")
    public int homeRoomId;

    @NotNull
    @Column(columnDefinition = "int(11) default 0")
    public int respectsReceived;

    @NotNull
    @Column(columnDefinition = "int(11) default 0")
    public int respectsRemaining;

    @NotNull
    @Column(columnDefinition = "int(11) default 0")
    public int respectsPetRemaining;

    @NotNull
    @Column(columnDefinition = "int(11) default 0")
    public int achievementScore;

    @NotNull
    @Column(columnDefinition = "enum('0','1') default '0'")
    public String friendRequestsDisabled;

    @NotNull
    @Column(columnDefinition = "enum('0','1') default '1'")
    public String toolbarToggles;

    @NotNull
    @Column(columnDefinition = "int(11) default 0")
    public int volumeFurni;

    @NotNull
    @Column(columnDefinition = "int(11) default 0")
    public int volumeSystem;

    @NotNull
    @Column(columnDefinition = "int(11) default 0")
    public int volumeTrax;

    @NotNull
    @Column(columnDefinition = "enum('0','1') default '0'")
    public String oldChat;

    @NotNull
    @Column(columnDefinition = "enum('0','1') default '0'")
    public String ignoreInvites;

    @NotNull
    @Column(columnDefinition = "enum('0','1') default '0'")
    public String cameraFocus;

    @NotNull
    @Column(columnDefinition = "int(11) default 0")
    public int chatStyle;

    @NotNull
    @Column(columnDefinition = "int(11) default 100")
    public int navigatorX;

    @NotNull
    @Column(columnDefinition = "int(11) default 100")
    public int navigatorY;

    @NotNull
    @Column(columnDefinition = "int(11) default 435")
    public int navigatorWidth;

    @NotNull
    @Column(columnDefinition = "int(11) default 535")
    public int navigatorHeight;

    @NotNull
    @Column(columnDefinition = "enum('0','1') default '0'")
    public String navigatorSearchOpen;
}
