package com.nitro.core.communication;

import com.nitro.core.INitroCore;
import com.nitro.core.common.disposable.IDisposable;
import com.nitro.core.communication.servers.IServerContainer;

public interface ICommunicationManager extends IServerContainer, IDisposable {

    INitroCore getNitroCore();
}
