package com.nitro.nitro.database.entities.security;

import com.nitro.nitro.database.abstracts.EntityTimestamp;
import com.nitro.nitro.database.entities.user.UserEntity;
import io.ebean.annotation.ConstraintMode;
import io.ebean.annotation.DbForeignKey;
import io.ebean.annotation.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
public class SecurityRankEntity extends EntityTimestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @DbForeignKey(onDelete = ConstraintMode.SET_NULL)
    public SecurityPermissionEntity permissionEntity;

    @NotNull
    @Column(columnDefinition = "int(11) default 0")
    public int clientRank;

    @OneToMany(mappedBy = "rankEntity", fetch = FetchType.LAZY)
    public List<UserEntity> userEntities;
}
