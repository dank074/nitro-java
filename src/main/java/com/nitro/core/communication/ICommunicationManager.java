package com.nitro.core.communication;

import com.nitro.common.component.IComponent;
import com.nitro.core.INitroCore;
import com.nitro.core.communication.servers.IServerContainer;

public interface ICommunicationManager extends IServerContainer, IComponent {

    INitroCore getNitroCore();
}
