package com.nitro.nitro.room.object.data;

import com.nitro.nitro.room.object.data.types.*;

public class StuffDataFactory {

    public static IStuffData createStuffData(int flags) {
        IStuffData stuffData = null;

        switch(flags & 0xFF) {
            case StuffDataKey.CRACKABLE_KEY:
                stuffData = new CrackableStuffData();
                break;
            case StuffDataKey.EMPTY_KEY:
                stuffData = new EmptyStuffData();
                break;
            case StuffDataKey.LEGACY_KEY:
                stuffData = new LegacyStuffData();
                break;
            case StuffDataKey.MAP_KEY:
                stuffData = null;
                break;
            case StuffDataKey.NUMBER_KEY:
                stuffData = new NumberStuffData();
                break;
            case StuffDataKey.STRING_KEY:
                stuffData = new StringStuffData();
                break;
            case StuffDataKey.VOTE_KEY:
                stuffData = new VoteStuffData();
                break;
        }

        if(stuffData == null) return null;

        stuffData.setFlags(flags);

        return stuffData;
    }
}
