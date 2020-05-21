package com.nitro.nitro.security.rank;

import com.nitro.common.component.Component;
import com.nitro.nitro.database.entities.security.SecurityRankEntity;
import com.nitro.nitro.security.ISecurityManager;

import java.util.HashMap;
import java.util.Map;

public class RankManager extends Component implements IRankManager, IRankContainer {

    private final ISecurityManager securityManager;
    private final Map<Integer, IRank> rankMap;

    public RankManager(ISecurityManager securityManager) {
        this.securityManager = securityManager;
        this.rankMap = new HashMap<>();
    }

    protected void onDispose() {
        this.removeAllRanks();
    }

    public IRank getRank(int id) {
        return this.rankMap.get(id);
    }

    public IRank addRank(SecurityRankEntity entity) {
        if(entity == null) return null;

        IRank existing = this.rankMap.get(entity.id);

        if(existing == null) {
            existing = new Rank(this, entity);

            this.rankMap.put(existing.getId(), existing);
        }

        return existing;
    }

    public void removeRank(int id) {
        IRank rank = this.rankMap.remove(id);

        if(rank != null) rank.dispose();
    }

    public void removeRank(IRank rank) {
        if(rank == null) return;

        this.removeRank(rank.getId());
    }

    public void removeAllRanks() {
        if(this.rankMap.size() == 0) return;

        for(int id : this.rankMap.keySet()) this.removeRank(id);
    }

    public Map<Integer, IRank> getRankMap() {
        return this.rankMap;
    }
}
