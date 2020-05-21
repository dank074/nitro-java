package com.nitro.nitro.security;

import com.nitro.common.component.IComponent;
import com.nitro.nitro.security.permission.IPermissionManager;
import com.nitro.nitro.security.rank.IRankManager;

public interface ISecurityManager extends IComponent {

    void setPermissionsForRanks();
    int getUserIdFromTicket(String ticket);
    IPermissionManager getPermissionManager();
    IRankManager getRankManager();
}
