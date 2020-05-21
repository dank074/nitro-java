package com.nitro.core.plugin;

import com.nitro.common.component.Component;
import com.nitro.common.events.EventDispatcher;
import com.nitro.common.events.IEventDispatcher;
import com.nitro.common.events.IEventListener;
import com.nitro.common.events.NitroEvent;
import com.nitro.core.INitroCore;

public class PluginManager extends Component implements IPluginManager {

    private final INitroCore nitroCore;
    private final IEventDispatcher eventDispatcher;

    public PluginManager(INitroCore nitroCore) {
        this.nitroCore = nitroCore;
        this.eventDispatcher = new EventDispatcher();
    }

    protected void onDispose() {
        this.eventDispatcher.dispose();
    }

    public void registerEventListener(IEventListener listener) {
        this.eventDispatcher.registerEventListener(listener);
    }

    public <T extends NitroEvent> T dispatchEvent(T event) {
        return this.eventDispatcher.dispatchEvent(event);
    }

    public INitroCore getNitroCore() {
        return this.nitroCore;
    }

    public IEventDispatcher getEventDispatcher() {
        return this.eventDispatcher;
    }
}
