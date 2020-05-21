package com.nitro.nitro.database.entities.user;

import com.nitro.nitro.database.abstracts.EntityTimestamp;
import com.nitro.nitro.database.entities.security.SecurityTicketEntity;
import io.ebean.annotation.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
public class UserEntity extends EntityTimestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @NotNull
    @Column(unique = true)
    public String username;

    @NotNull
    public String password;

    @NotNull
    public String email;

    public String motto;

    @NotNull
    @Column(columnDefinition = "enum('M', 'F') default 'M'")
    public String gender;

    @NotNull
    public String figure;

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY)
    public List<SecurityTicketEntity> securityTicketEntities;
}
