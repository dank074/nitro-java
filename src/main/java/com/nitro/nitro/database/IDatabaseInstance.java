package com.nitro.nitro.database;

import io.ebean.Database;

public interface IDatabaseInstance {

    Database getDatabase();
}
