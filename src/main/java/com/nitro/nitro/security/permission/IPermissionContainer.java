package com.nitro.nitro.security.permission;

import com.nitro.nitro.database.entities.security.SecurityPermissionEntity;

public interface IPermissionContainer {

    IPermission getPermission(int id);
    IPermission addPermission(SecurityPermissionEntity entity);
    void removePermission(int id);
    void removePermission(IPermission permission);
}
