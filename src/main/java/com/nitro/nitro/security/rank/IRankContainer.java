package com.nitro.nitro.security.rank;

import com.nitro.nitro.database.entities.security.SecurityRankEntity;

public interface IRankContainer {

    IRank getRank(int id);
    IRank addRank(SecurityRankEntity entity);
    void removeRank(int id);
    void removeRank(IRank rank);
}
