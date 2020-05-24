package com.nitro.nitro.database.entities.messenger;

import com.nitro.nitro.database.abstracts.EntityTimestamp;
import com.nitro.nitro.database.entities.user.UserEntity;
import io.ebean.annotation.ConstraintMode;
import io.ebean.annotation.DbForeignKey;
import io.ebean.annotation.NotNull;

import javax.persistence.*;

@Entity
@UniqueConstraint(columnNames = {"user_entity_id", "name"})
public class MessengerCategoryEntity extends EntityTimestamp {

    @Id
    public int id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @DbForeignKey(onDelete = ConstraintMode.CASCADE)
    public UserEntity userEntity;

    @NotNull
    private String name;
}
