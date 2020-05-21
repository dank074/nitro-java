package com.nitro.nitro.security;

import com.nitro.common.component.Component;
import com.nitro.nitro.database.daos.SecurityDao;
import com.nitro.nitro.database.entities.security.SecurityPermissionEntity;
import com.nitro.nitro.database.entities.security.SecurityRankEntity;
import com.nitro.nitro.database.entities.security.SecurityTicketEntity;
import com.nitro.nitro.security.permission.IPermission;
import com.nitro.nitro.security.permission.IPermissionContainer;
import com.nitro.nitro.security.permission.IPermissionManager;
import com.nitro.nitro.security.permission.PermissionManager;
import com.nitro.nitro.security.rank.IRank;
import com.nitro.nitro.security.rank.IRankContainer;
import com.nitro.nitro.security.rank.IRankManager;
import com.nitro.nitro.security.rank.RankManager;

import java.util.List;
import java.util.Map;

public class SecurityManager extends Component implements ISecurityManager {

    private final IPermissionManager permissionManager;
    private final IRankManager rankManager;

    public SecurityManager() {
        this.permissionManager = new PermissionManager(this);
        this.rankManager = new RankManager(this);
    }

    protected void onInit() {
        this.loadRanks();
        this.loadPermissions();
        this.setPermissionsForRanks();
    }

    protected void onDispose() {
        this.permissionManager.dispose();
        this.rankManager.dispose();
    }

    private void loadRanks() {
        List<SecurityRankEntity> securityRankEntities = SecurityDao.getSecurityRanks();

        if(securityRankEntities == null) return;

        IRankContainer rankManager = (IRankContainer) this.rankManager;

        for(SecurityRankEntity entity : securityRankEntities) rankManager.addRank(entity);
    }

    private void loadPermissions() {
        List<SecurityPermissionEntity> securityPermissionEntities = SecurityDao.getSecurityPermissions();

        if(securityPermissionEntities == null) return;

        IPermissionContainer permissionManager = (IPermissionContainer) this.permissionManager;

        for(SecurityPermissionEntity entity : securityPermissionEntities) permissionManager.addPermission(entity);
    }

    public void setPermissionsForRanks() {
        Map<Integer, IRank> rankMap = this.rankManager.getRankMap();

        if((rankMap == null) || (rankMap.size() == 0)) return;

        for(IRank rank : rankMap.values()) {
            if(rank == null) continue;

            IPermission permission = this.getPermissionManager().getPermission(rank.getPermissionId());

            rank.setPermission(permission);
        }
    }

    public int getUserIdFromTicket(String ticket) {
        if(ticket == null) return 0;

        SecurityTicketEntity entity = SecurityDao.getSecurityTicket(ticket);

        if(entity == null) return 0;

        if(entity.isLocked == 0) SecurityDao.removeSecurityTicket(entity);

        return entity.userEntity.id;
    }

    public IPermissionManager getPermissionManager() {
        return this.permissionManager;
    }

    public IRankManager getRankManager() {
        return this.rankManager;
    }
}
