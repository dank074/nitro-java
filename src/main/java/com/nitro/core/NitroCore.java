package com.nitro.core;

import com.nitro.common.component.Component;
import com.nitro.core.communication.CommunicationManager;
import com.nitro.core.communication.ICommunicationManager;
import com.nitro.core.config.ConfigManager;
import com.nitro.core.config.IConfigManager;
import com.nitro.core.plugin.IPluginManager;
import com.nitro.core.plugin.PluginManager;

public class NitroCore extends Component implements INitroCore {

    private final IConfigManager configManager;
    private final IPluginManager pluginManager;
    private final ICommunicationManager communicationManager;

    public NitroCore() {
        this.configManager = new ConfigManager(this);
        this.pluginManager = new PluginManager(this);
        this.communicationManager = new CommunicationManager(this);
    }

    protected void onDispose() {
        this.communicationManager.dispose();
        this.pluginManager.dispose();
        this.configManager.dispose();
    }

    public IConfigManager getConfigManager() {
        return this.configManager;
    }

    public IPluginManager getPluginManager() {
        return this.pluginManager;
    }

    public ICommunicationManager getCommunicationManager() {
        return this.communicationManager;
    }
}
