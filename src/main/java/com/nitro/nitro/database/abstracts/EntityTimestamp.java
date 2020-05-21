package com.nitro.nitro.database.abstracts;

import io.ebean.annotation.WhenCreated;
import io.ebean.annotation.WhenModified;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public abstract class EntityTimestamp
{
    @WhenCreated
    @Column(columnDefinition = "DATETIME default CURRENT_TIMESTAMP")
    protected Date timestampCreated;

    @WhenModified
    @Column(columnDefinition = "DATETIME default CURRENT_TIMESTAMP")
    protected Date timestampUpdated;
}
