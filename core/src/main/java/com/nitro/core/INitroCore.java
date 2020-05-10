package com.nitro.core;

import com.nitro.common.disposable.IDisposable;
import com.nitro.core.communication.ICommunicationManager;
import com.nitro.core.plugin.IPluginManager;

public interface INitroCore extends IDisposable {

    IPluginManager getPluginManager();
    ICommunicationManager getCommunicationManager();
}
