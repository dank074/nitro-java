package com.nitro.core.config;

import com.nitro.common.component.Component;
import com.nitro.core.INitroCore;

import java.util.HashMap;
import java.util.Map;

public class ConfigManager extends Component implements IConfigManager {

    private final INitroCore nitroCore;
    private final Map<String, Object> configuration;

    public ConfigManager(INitroCore nitroCore) {
        this.nitroCore = nitroCore;
        this.configuration = new HashMap<>();
    }

    protected void onDispose() {
        this.configuration.clear();
    }

    public void loadYamlConfiguration(Map<String, Object> configuration) {
        if ((configuration == null) || (configuration.size() == 0)) return;

        this.configuration.putAll(configuration);
    }

    public <V> Object getOrDefault(String key, V defaultValue) {
        return this.configuration.getOrDefault(key, defaultValue);
    }

    public <V> void setValue(String key, V value) {
        if(this.configuration.get(key) != null) {
            System.out.println("Overriding configuration key: " + key);
        }

        this.configuration.put(key, value);
    }
}
