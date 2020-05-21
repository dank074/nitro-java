package com.nitro.core.plugin;

import com.nitro.common.component.IComponent;
import com.nitro.common.events.IEventDispatcher;
import com.nitro.common.events.IEventListener;
import com.nitro.common.events.NitroEvent;
import com.nitro.core.INitroCore;

public interface IPluginManager extends IComponent {

    void registerEventListener(IEventListener listener);
    <T extends NitroEvent> T dispatchEvent(T event);
    INitroCore getNitroCore();
    IEventDispatcher getEventDispatcher();
}
