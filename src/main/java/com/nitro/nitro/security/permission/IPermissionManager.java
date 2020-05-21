package com.nitro.nitro.security.permission;

import com.nitro.common.component.IComponent;

import java.util.Map;

public interface IPermissionManager extends IComponent {

    IPermission getPermission(int id);
    Map<Integer, IPermission> getPermissionMap();
}
