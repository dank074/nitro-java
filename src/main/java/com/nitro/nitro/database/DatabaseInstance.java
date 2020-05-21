package com.nitro.nitro.database;

import io.ebean.Database;

public class DatabaseInstance implements IDatabaseInstance {

    private Database database;

    public DatabaseInstance(Database database) {
        this.database = database;
    }

    public Database getDatabase() {
        return this.database;
    }
}
