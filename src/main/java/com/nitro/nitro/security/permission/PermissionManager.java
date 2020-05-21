package com.nitro.nitro.security.permission;

import com.nitro.common.component.Component;
import com.nitro.nitro.database.entities.security.SecurityPermissionEntity;
import com.nitro.nitro.security.ISecurityManager;

import java.util.HashMap;
import java.util.Map;

public class PermissionManager extends Component implements IPermissionManager, IPermissionContainer {

    private final ISecurityManager securityManager;
    private final Map<Integer, IPermission> permissionMap;

    public PermissionManager(ISecurityManager securityManager) {
        this.securityManager = securityManager;
        this.permissionMap = new HashMap<>();
    }

    protected void onDispose() {
        this.removeAllPermissions();
    }

    public IPermission getPermission(int id) {
        return this.permissionMap.get(id);
    }

    public IPermission addPermission(SecurityPermissionEntity entity) {
        if(entity == null) return null;

        IPermission existing = this.permissionMap.get(entity.id);

        if(existing == null) {
            existing = new Permission(this, entity);

            this.permissionMap.put(existing.getId(), existing);
        }

        return existing;
    }

    public void removePermission(int id) {
        IPermission permission = this.permissionMap.remove(id);

        if(permission != null) {
            permission.dispose();

            this.securityManager.setPermissionsForRanks();
        }
    }

    public void removePermission(IPermission permission) {
        if(permission == null) return;

        this.removePermission(permission.getId());
    }

    public void removeAllPermissions() {
        if(this.permissionMap.size() == 0) return;

        for(int id : this.permissionMap.keySet()) this.removePermission(id);
    }

    public Map<Integer, IPermission> getPermissionMap() {
        return this.permissionMap;
    }
}
