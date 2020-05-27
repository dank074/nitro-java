package com.nitro.common.component;

import com.nitro.common.logger.INitroLogger;
import com.nitro.common.logger.NitroLogger;

public class Component implements IComponent {

    private boolean initialized;
    private boolean disposed;

    private INitroLogger logger;

    public Component(NitroLogger logger) {
        this.initialized = false;
        this.disposed = false;

        this.logger = logger;
    }

    public Component() {
        this(new NitroLogger());

        this.logger.setName(getClass().getSimpleName());
    }

    public void init() {
        if(this.isInitialized()) return;

        this.initialized = true;

        this.onInit();
    }

    public void dispose() {
        if(this.isDisposed()) return;

        this.disposed = true;

        this.onDispose();
    }

    protected void onInit() {

    }

    protected void onDispose() {

    }

    public boolean isInitialized() {
        return this.initialized;
    }

    public boolean isDisposed() {
        return this.disposed;
    }

    public INitroLogger getLogger() {
        return this.logger;
    }
}
