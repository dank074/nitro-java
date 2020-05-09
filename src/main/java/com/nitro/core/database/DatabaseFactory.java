package com.nitro.core.database;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class DatabaseFactory {

    public static SessionFactory createMySQLDatabase(String host, int port, String database, String username, String password, String params) {
        Configuration configuration = createHibernateConfiguration();

        applyMySQLDialect(configuration);
        applyMySQLConnectionDetails(configuration, host, port, database, username, password, params);

        StandardServiceRegistryBuilder databaseBuilder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());

        return configuration.buildSessionFactory(databaseBuilder.build());
    }

    public static SessionFactory createMySQLDatabase(Configuration configuration) {
        if(configuration == null) return null;

        StandardServiceRegistryBuilder databaseBuilder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());

        return configuration.buildSessionFactory(databaseBuilder.build());
    }

    public static Configuration applyMySQLDialect(Configuration configuration) {
        if(configuration == null) return null;

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");

        return configuration;
    }

    public static Configuration applyMySQLConnectionDetails(Configuration configuration, String host, int port, String database, String username, String password) {
        return applyMySQLConnectionDetails(configuration, host, port, database, username, password, null);
    }

    public static Configuration applyMySQLConnectionDetails(Configuration configuration, String host, int port, String database, String username, String password, String params) {
        if(configuration == null) return null;

        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://" + host + ":" + port + "/" + database + params);
        configuration.setProperty("hibernate.connection.username", username);
        configuration.setProperty("hibernate.connection.password", password);

        return configuration;
    }

    public static Configuration createHibernateConfiguration() {
        return new Configuration();
    }
}
