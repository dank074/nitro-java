package com.nitro.nitro.database.entities.user;

import com.nitro.nitro.database.abstracts.EntityTimestamp;
import com.nitro.nitro.database.entities.security.SecurityRankEntity;
import com.nitro.nitro.database.entities.security.SecurityTicketEntity;
import io.ebean.annotation.ConstraintMode;
import io.ebean.annotation.DbForeignKey;
import io.ebean.annotation.NotNull;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class UserEntity extends EntityTimestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String email;

    private String motto;

    @NotNull
    @Column(columnDefinition = "enum('M', 'F') default 'M'")
    private String gender;

    @NotNull
    private String figure;

    private Date lastOnline;

    @NotNull
    @Column(columnDefinition = "enum('0', '1') default '0'")
    private String online;

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
    private List<UserCurrencyEntity> userCurrencyEntities;

    @OneToOne(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private UserInfoEntity userInfoEntity;

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
    private List<UserOutfitEntity> userOutfitEntities;

    @OneToOne(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private UserStatisticsEntity userStatisticsEntity;

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
    private List<UserSubscriptionEntity> userSubscriptionEntities;

    @ManyToOne(fetch = FetchType.LAZY)
    @DbForeignKey(onDelete = ConstraintMode.SET_NULL)
    private SecurityRankEntity rankEntity;

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
    private List<SecurityTicketEntity> securityTicketEntities;

    public int getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public String getMotto() {
        return this.motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFigure() {
        return this.figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public Date getLastOnline() {
        return this.lastOnline;
    }

    public void setLastOnline(Date date) {
        this.lastOnline = date;
    }

    public boolean getOnline() {
        return this.online.equals("1");
    }

    public void setOnline(boolean flag) {
        this.online = ((flag) ? "1" : "0");
    }

    public UserInfoEntity getUserInfoEntity() {
        return this.userInfoEntity;
    }

    public void setUserInfoEntity(UserInfoEntity entity) {
        if(entity == null) return;

        entity.userEntity = this;

        this.userInfoEntity = entity;
    }

    public UserStatisticsEntity getUserStatisticsEntity() {
        return this.userStatisticsEntity;
    }

    public void setUserStatisticsEntity(UserStatisticsEntity entity) {
        if(entity == null) return;

        entity.userEntity = this;

        this.userStatisticsEntity = entity;
    }
}
