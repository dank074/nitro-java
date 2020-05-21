package com.nitro.nitro.security.rank;

import com.nitro.nitro.database.entities.security.SecurityRankEntity;
import com.nitro.nitro.security.permission.IPermission;

public class Rank implements IRank {

    private IRankContainer container;
    private SecurityRankEntity entity;
    private IPermission permission;

    private boolean disposed;

    public Rank(IRankContainer container, SecurityRankEntity entity) {
        this.container = container;
        this.entity = entity;
        this.permission = null;

        this.disposed = false;
    }

    public void dispose() {
        if(this.isDisposed()) return;

        this.disposed = true;

        if(this.container != null) {
            this.container.removeRank(this.getId());

            this.container = null;
        }

        this.entity = null;
    }

    public boolean hasPermission(String permission) {
        if(permission == null) return true;

        if(this.permission != null) return this.permission.hasPermission(permission);

        return false;
    }

    public boolean isDisposed() {
        return this.disposed;
    }

    public IRankContainer getContainer() {
        return this.container;
    }

    public int getId() {
        return this.entity.id;
    }

    public int getPermissionId() {
        return this.entity.securityPermissionEntity.id;
    }

    public IPermission getPermission() {
        return this.permission;
    }

    public void setPermission(IPermission permission) {
        this.permission = permission;
    }
}
