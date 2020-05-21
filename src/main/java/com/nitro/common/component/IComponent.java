package com.nitro.common.component;

import com.nitro.common.disposable.IDisposable;

public interface IComponent extends IDisposable {

    void init();
    boolean isInitialized();
}
