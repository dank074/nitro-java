package com.nitro.nitro.security.permission;

import com.nitro.nitro.database.entities.security.SecurityPermissionEntity;

public class Permission implements IPermission {

    private IPermissionContainer container;
    private SecurityPermissionEntity entity;

    private boolean disposed;

    public Permission(IPermissionContainer container, SecurityPermissionEntity entity) {
        this.container = container;
        this.entity = entity;

        this.disposed = false;
    }

    public void dispose() {
        if(this.isDisposed()) return;

        this.disposed = true;

        if(this.container != null) {
            this.container.removePermission(this.getId());

            this.container = null;
        }

        this.entity = null;
    }

    public boolean hasPermission(String permission) {
        if(permission == null) return true;

        if(this.hasAllPermissions()) return true;

        return false;
    }

    public boolean isDisposed() {
        return this.disposed;
    }

    public IPermissionContainer getContainer() {
        return this.container;
    }

    public int getId() {
        return this.entity.id;
    }

    public boolean hasAllPermissions() {
        return this.entity.allPermissions.equals("1");
    }
}
