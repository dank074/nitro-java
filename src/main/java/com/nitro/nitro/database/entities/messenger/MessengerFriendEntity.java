package com.nitro.nitro.database.entities.messenger;

import com.nitro.nitro.database.abstracts.EntityTimestamp;
import com.nitro.nitro.database.entities.user.UserEntity;
import io.ebean.annotation.ConstraintMode;
import io.ebean.annotation.DbForeignKey;
import io.ebean.annotation.NotNull;

import javax.persistence.*;

@Entity
@UniqueConstraint(columnNames = {"user_entity_id", "friend_entity_id"})
public class MessengerFriendEntity extends EntityTimestamp {

    @Id
    public int id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @DbForeignKey(onDelete = ConstraintMode.CASCADE)
    public UserEntity userEntity;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @DbForeignKey(onDelete = ConstraintMode.CASCADE)
    public UserEntity friendEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @DbForeignKey(onDelete = ConstraintMode.SET_NULL)
    public MessengerCategoryEntity categoryEntity;

    @NotNull
    @Column(columnDefinition = "enum('0','1','2','3') default '0'")
    private int relation;
}
