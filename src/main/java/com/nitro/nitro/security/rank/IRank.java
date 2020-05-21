package com.nitro.nitro.security.rank;

import com.nitro.common.disposable.IDisposable;
import com.nitro.nitro.security.permission.IPermission;

public interface IRank extends IDisposable {

    boolean hasPermission(String permission);
    IRankContainer getContainer();
    int getId();
    int getPermissionId();
    IPermission getPermission();
    void setPermission(IPermission permission);
}
