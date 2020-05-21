package com.nitro.nitro.database.entities.security;

import com.nitro.nitro.database.abstracts.EntityTimestamp;
import io.ebean.annotation.ConstraintMode;
import io.ebean.annotation.DbForeignKey;
import io.ebean.annotation.NotNull;

import javax.persistence.*;

@Entity
public class SecurityRankEntity extends EntityTimestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @ManyToOne
    @DbForeignKey(onDelete = ConstraintMode.SET_NULL)
    public SecurityPermissionEntity securityPermissionEntity;

    @NotNull
    @Column(columnDefinition = "int(11) default 0")
    public int clientRank;
}
