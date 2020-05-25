package com.nitro.nitro;

import com.nitro.common.component.Component;
import com.nitro.core.INitroCore;
import com.nitro.nitro.database.IDatabaseInstance;
import com.nitro.nitro.room.IRoomManager;
import com.nitro.nitro.room.RoomManager;
import com.nitro.nitro.security.ISecurityManager;
import com.nitro.nitro.security.SecurityManager;
import com.nitro.nitro.user.IUserManager;
import com.nitro.nitro.user.UserManager;

public class Nitro extends Component {

    public static Nitro INSTANCE = null;

    private final INitroCore nitroCore;
    private final IRoomManager roomManager;
    private final ISecurityManager securityManager;
    private final IUserManager userManager;

    private IDatabaseInstance databaseInstance;

    public Nitro(INitroCore nitroCore) {
        this.nitroCore = nitroCore;
        this.roomManager = new RoomManager();
        this.securityManager = new SecurityManager();
        this.userManager = new UserManager();

        this.databaseInstance = null;

        INSTANCE = this;
    }

    protected void onInit() {
        try {
            if(this.databaseInstance == null) {
                throw new Exception("A database instance was not specified");
            }

            this.databaseInstance.init();
            this.securityManager.init();
            this.roomManager.init();
            this.userManager.init();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void onDispose() {
        this.userManager.dispose();
        this.roomManager.dispose();
        this.securityManager.dispose();
        this.databaseInstance.dispose();
    }

    public void setDatabaseInstance(IDatabaseInstance databaseInstance) {
        if(this.databaseInstance != null) {
            this.databaseInstance.dispose();

            this.databaseInstance = null;
        }

        this.databaseInstance = databaseInstance;
    }

    public INitroCore getNitroCore() {
        return this.nitroCore;
    }

    public IDatabaseInstance getDatabaseInstance() {
        return this.databaseInstance;
    }

    public IRoomManager getRoomManager() {
        return this.roomManager;
    }

    public ISecurityManager getSecurityManager() {
        return this.securityManager;
    }

    public IUserManager getUserManager() {
        return this.userManager;
    }
}
