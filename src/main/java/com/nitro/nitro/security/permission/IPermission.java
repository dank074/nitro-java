package com.nitro.nitro.security.permission;

import com.nitro.common.disposable.IDisposable;

public interface IPermission extends IDisposable {

    boolean hasPermission(String permission);
    IPermissionContainer getContainer();
    int getId();
    boolean hasAllPermissions();
}
