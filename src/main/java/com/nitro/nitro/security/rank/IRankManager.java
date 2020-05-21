package com.nitro.nitro.security.rank;

import com.nitro.common.component.IComponent;

import java.util.Map;

public interface IRankManager extends IComponent {

    IRank getRank(int id);
    Map<Integer, IRank> getRankMap();
}
