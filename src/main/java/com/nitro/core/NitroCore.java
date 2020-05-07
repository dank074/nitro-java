package com.nitro.core;

import com.nitro.core.communication.CommunicationManager;
import com.nitro.core.communication.ICommunicationManager;

public class NitroCore implements INitroCore {

    private ICommunicationManager communicationManager;

    private boolean isDisposed;

    public NitroCore() {
        this.communicationManager = new CommunicationManager();

        this.isDisposed = false;
    }

    public void dispose() {
        if(this.isDisposed()) return;

        this.isDisposed = true;

        if(this.communicationManager != null) {
            this.communicationManager.dispose();

            this.communicationManager = null;
        }
    }

    public boolean isDisposed() {
        return this.isDisposed;
    }

    public ICommunicationManager getCommunicationManager() {
        return this.communicationManager;
    }
}
