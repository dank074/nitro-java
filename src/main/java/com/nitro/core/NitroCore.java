package com.nitro.core;

import com.nitro.core.communication.CommunicationManager;
import com.nitro.core.communication.ICommunicationManager;

public class NitroCore implements INitroCore {

    private ICommunicationManager communicationManager;

    public NitroCore() {
        this.communicationManager = new CommunicationManager();
    }

    public ICommunicationManager getCommunicationManager() {
        return this.communicationManager;
    }
}
