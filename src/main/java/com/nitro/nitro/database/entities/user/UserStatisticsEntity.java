package com.nitro.nitro.database.entities.user;

import com.nitro.nitro.database.abstracts.EntityTimestamp;
import io.ebean.annotation.ConstraintMode;
import io.ebean.annotation.DbForeignKey;
import io.ebean.annotation.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
public class UserStatisticsEntity extends EntityTimestamp {

    @Id
    public int id;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @DbForeignKey(onDelete = ConstraintMode.CASCADE)
    public UserEntity userEntity;

    @NotNull
    @Column(columnDefinition = "int(11) default 0")
    public int loginStreak;

    public Date loginStreakLast;

    @NotNull
    @Column(columnDefinition = "int(11) default 0")
    public int loginStreakLifetime;

    @NotNull
    @Column(columnDefinition = "int(11) default 0")
    public int totalSecondsOnline;

    @NotNull
    @Column(columnDefinition = "int(11) default 0")
    public int totalLogins;
}
