package com.nitro.core.database;

import org.hibernate.SessionFactory;

public class DatabaseInstance implements IDatabaseInstance {

    private SessionFactory sessionFactory;

    public DatabaseInstance(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }
}
