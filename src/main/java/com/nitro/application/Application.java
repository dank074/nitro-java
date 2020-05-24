package com.nitro.application;

import com.nitro.core.NitroCore;
import com.nitro.core.communication.servers.IServer;
import com.nitro.core.config.IConfigManager;
import com.nitro.nitro.Nitro;
import com.nitro.nitro.communication.messages.NitroMessages;
import com.nitro.nitro.communication.server.netty.NettyServer;
import com.nitro.nitro.database.DatabaseFactory;
import com.nitro.nitro.database.IDatabaseInstance;
import io.ebean.config.DatabaseConfig;
import io.ebean.datasource.DataSourceConfig;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;

public class Application {

    private final Nitro nitro;

    public static void bootstrap(String[] args) {
        try {
            new Application();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Application() {
        this.nitro = new Nitro(new NitroCore());

        this.setupConfig();
        this.setupDatabase();
        this.setupServer();

        this.nitro.init();

        this.nitro.getNitroCore().getPluginManager().registerEventListener(new EventListener());
    }

    private void setupConfig() {
        if(this.nitro == null) return;

        IConfigManager config = this.nitro.getNitroCore().getConfigManager();

        if(config == null) return;

        String configPath = System.getProperty("user.dir") + "/config.yml";

        try {
            File configFile = new File(configPath);

            if(!configFile.exists()) {
                System.out.println("The configuration file could not be found, config.yml");

                return;
            }

            Yaml yaml = new Yaml();

            config.loadYamlConfiguration(yaml.load(new FileInputStream(configFile)));
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void setupDatabase() {
        if(this.nitro == null) return;

        IConfigManager config = this.nitro.getNitroCore().getConfigManager();

        if(config == null) return;

        String host = (String) config.getOrDefault("database.host", "localhost");
        int port = (int) config.getOrDefault("database.port", 3306);
        String name = (String) config.getOrDefault("database.name", "nitro");
        String user = (String) config.getOrDefault("database.user", "root");
        String pass = (String) config.getOrDefault("database.pass", "");
        String params = (String) config.getOrDefault("database.params", "");
        boolean databaseGeneration = (boolean) config.getOrDefault("database.generate", false);

        DataSourceConfig dataSourceConfig = DatabaseFactory.createDataSource(host, port, name, user, pass, params);
        DatabaseConfig databaseConfig = DatabaseFactory.createDatabaseConfig(dataSourceConfig, databaseGeneration);

        IDatabaseInstance databaseInstance = DatabaseFactory.createDatabaseInstance(databaseConfig);

        this.nitro.setDatabaseInstance(databaseInstance);
    }

    private void setupServer() {
        if(this.nitro == null) return;

        IConfigManager config = this.nitro.getNitroCore().getConfigManager();

        if(config == null) return;

        String host = (String) config.getOrDefault("server.host", "0.0.0.0");
        int port = (int) config.getOrDefault("server.port", 1242);

        IServer server = this.nitro.getNitroCore().getCommunicationManager().addServer(new NettyServer(host, port));

        if(server == null) return;

        server.registerMessageConfiguration(new NitroMessages());
        server.registerMessageListener(new ClientMessagesListener());
        server.registerMessageListener(new DesktopMessagesListener());
        server.registerMessageListener(new SecurityMessagesListener());

        server.init();
    }
}
