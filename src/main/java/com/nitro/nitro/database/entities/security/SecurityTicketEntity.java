package com.nitro.nitro.database.entities.security;

import com.nitro.nitro.database.abstracts.EntityTimestamp;
import com.nitro.nitro.database.entities.user.UserEntity;
import io.ebean.annotation.ConstraintMode;
import io.ebean.annotation.DbForeignKey;
import io.ebean.annotation.NotNull;

import javax.persistence.*;

@Entity
public class SecurityTicketEntity extends EntityTimestamp {

    @Id
    public int id;

    public int userEntityId;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @DbForeignKey(onDelete = ConstraintMode.CASCADE)
    public UserEntity userEntity;

    @NotNull
    public String ticket;

    @NotNull
    public String ipAddress;

    @NotNull
    @Column(columnDefinition = "enum('0','1') default '0'")
    public int isLocked;
}
