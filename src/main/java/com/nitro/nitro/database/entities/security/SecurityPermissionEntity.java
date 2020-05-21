package com.nitro.nitro.database.entities.security;

import com.nitro.nitro.database.abstracts.EntityTimestamp;
import io.ebean.annotation.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
public class SecurityPermissionEntity extends EntityTimestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @NotNull
    public String permissionName;

    @NotNull
    @Column(columnDefinition = "enum('0','1') default '0'")
    public String allPermissions;

    @OneToMany(mappedBy = "securityPermissionEntity", fetch = FetchType.LAZY)
    public List<SecurityRankEntity> securityRankEntities;
}
