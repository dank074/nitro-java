package com.nitro.core.config;

import com.nitro.common.component.IComponent;

import java.util.Map;

public interface IConfigManager extends IComponent {

    void loadYamlConfiguration(Map<String, Object> configuration);
    <V> Object getOrDefault(String key, V defaultValue);
    <V> void setValue(String key, V value);
}
