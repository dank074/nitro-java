package com.nitro.common.component;

import com.nitro.common.disposable.IDisposable;
import com.nitro.common.logger.INitroLogger;

public interface IComponent extends IDisposable {

    void init();
    boolean isInitialized();
    INitroLogger getLogger();
}
