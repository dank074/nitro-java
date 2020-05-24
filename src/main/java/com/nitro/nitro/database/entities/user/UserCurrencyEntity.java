package com.nitro.nitro.database.entities.user;

import com.nitro.nitro.database.abstracts.EntityTimestamp;
import io.ebean.annotation.ConstraintMode;
import io.ebean.annotation.DbForeignKey;
import io.ebean.annotation.NotNull;

import javax.persistence.*;

@Entity
@UniqueConstraint(columnNames = {"user_entity_id", "type"})
public class UserCurrencyEntity extends EntityTimestamp {

    @Id
    public int id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @DbForeignKey(onDelete = ConstraintMode.CASCADE)
    public UserEntity userEntity;

    @NotNull
    private int type;

    @NotNull
    private int amount;
}
