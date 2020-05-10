package com.nitro.core.database;

import org.hibernate.SessionFactory;

public interface IDatabaseInstance {

    SessionFactory getSessionFactory();
}
