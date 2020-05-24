package com.nitro.nitro.database;

import io.ebean.config.DatabaseConfig;
import io.ebean.datasource.DataSourceConfig;

import java.util.Properties;

public class DatabaseFactory {

    public static DataSourceConfig createDataSource(String host, int port, String database, String username, String password, String params) {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();

        dataSourceConfig.setDriver("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUrl("jdbc:mysql://" + host + ":" + port + "/" + database + params);
        dataSourceConfig.setUsername(username);
        dataSourceConfig.setPassword(password);

        return dataSourceConfig;
    }

    public static DatabaseConfig createDatabaseConfig(DataSourceConfig dataSourceConfig, boolean generateDatabase) {
        if(dataSourceConfig == null) return null;

        DatabaseConfig databaseConfig = new DatabaseConfig();

        databaseConfig.setDataSourceConfig(dataSourceConfig);

        Properties properties = new Properties();

        properties.setProperty("ebean.search.packages", "com.nitro.nitro.database.entities");

        if(generateDatabase) {
            properties.setProperty("ebean.ddl.generate", "true");
            properties.setProperty("ebean.ddl.run", "true");
        }

        databaseConfig.loadFromProperties(properties);

        return databaseConfig;
    }

    public static IDatabaseInstance createDatabaseInstance(DatabaseConfig databaseConfig) {
        if(databaseConfig == null) return null;

        io.ebean.

        Database database = io.ebean.DatabaseFactory.create(databaseConfig);

        return new DatabaseInstance(database);
    }
}
