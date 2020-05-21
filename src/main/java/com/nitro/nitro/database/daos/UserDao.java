package com.nitro.nitro.database.daos;

import com.nitro.nitro.Nitro;
import com.nitro.nitro.database.IDatabaseInstance;
import com.nitro.nitro.database.entities.user.UserEntity;

public class UserDao {

    public static UserEntity getUser(int id) {
        IDatabaseInstance databaseInstance = Nitro.INSTANCE.getDatabaseInstance();

        if(databaseInstance == null) return null;

        try {
            UserEntity entity = databaseInstance.getDatabase().find(UserEntity.class)
                    .where()
                    .eq("id", id)
                    .findOne();

            if(entity != null) return entity;
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static UserEntity getUser(String username) {
        IDatabaseInstance databaseInstance = Nitro.INSTANCE.getDatabaseInstance();

        if(databaseInstance == null) return null;

        try {
            UserEntity entity = databaseInstance.getDatabase().find(UserEntity.class)
                    .where()
                    .eq("username", username)
                    .findOne();

            if(entity != null) return entity;
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
