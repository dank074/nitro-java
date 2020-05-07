package com.nitro.core;

import com.nitro.core.common.disposable.IDisposable;
import com.nitro.core.communication.ICommunicationManager;

public interface INitroCore extends IDisposable {

    ICommunicationManager getCommunicationManager();
}
