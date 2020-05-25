package com.nitro.nitro.database;

import com.nitro.common.component.IComponent;
import io.ebean.Database;
import io.ebean.bean.EntityBean;

public interface IDatabaseInstance extends IComponent {

    void saveEntity(EntityBean entityBean, boolean skipQueue);
    void saveEntity(EntityBean entityBean);
    Database getDatabase();
}
