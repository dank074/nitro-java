package com.nitro.nitro.database.abstracts;

import io.ebean.Model;
import io.ebean.annotation.WhenCreated;
import io.ebean.annotation.WhenModified;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.util.Date;

@MappedSuperclass
public abstract class EntityTimestamp extends Model
{
    @WhenCreated
    @NotNull
    @Column(columnDefinition = "DATETIME default CURRENT_TIMESTAMP()")
    protected Date timestampCreated;

    @WhenModified
    @NotNull
    @Column(columnDefinition = "DATETIME default CURRENT_TIMESTAMP() ON UPDATE CURRENT_TIMESTAMP()")
    protected Date timestampUpdated;
}
