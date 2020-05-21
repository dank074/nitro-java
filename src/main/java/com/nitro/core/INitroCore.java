package com.nitro.core;

import com.nitro.common.component.IComponent;
import com.nitro.core.communication.ICommunicationManager;
import com.nitro.core.config.IConfigManager;
import com.nitro.core.plugin.IPluginManager;

public interface INitroCore extends IComponent {

    IConfigManager getConfigManager();
    IPluginManager getPluginManager();
    ICommunicationManager getCommunicationManager();
}
