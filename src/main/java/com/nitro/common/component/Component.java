package com.nitro.common.component;

public class Component implements IComponent {

    private boolean initialized;
    private boolean disposed;

    public Component() {
        this.disposed = false;
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
}
