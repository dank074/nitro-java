package com.nitro.nitro.database.daos;

import com.nitro.common.logger.NitroLogger;
import com.nitro.nitro.Nitro;
import com.nitro.nitro.database.IDatabaseInstance;
import com.nitro.nitro.database.entities.security.SecurityPermissionEntity;
import com.nitro.nitro.database.entities.security.SecurityRankEntity;
import com.nitro.nitro.database.entities.security.SecurityTicketEntity;

import java.util.List;

public class SecurityDao {

    public static List<SecurityPermissionEntity> getSecurityPermissions() {
        IDatabaseInstance databaseInstance = Nitro.getInstance().getDatabaseInstance();

        if(databaseInstance == null) return null;

        try {
            List<SecurityPermissionEntity> entities = databaseInstance.getDatabase().find(SecurityPermissionEntity.class)
                    .findList();

            if((entities.size() >= 1)) return entities;
        } catch(Exception e) {
            NitroLogger.printError(e.getMessage());
        }

        return null;
    }

    public static List<SecurityRankEntity> getSecurityRanks() {
        IDatabaseInstance databaseInstance = Nitro.getInstance().getDatabaseInstance();

        if(databaseInstance == null) return null;

        try {
            List<SecurityRankEntity> entities = databaseInstance.getDatabase().find(SecurityRankEntity.class)
                    .findList();

            if((entities.size() >= 1)) return entities;
        } catch(Exception e) {
            NitroLogger.printError(e.getMessage());
        }

        return null;
    }

    public static SecurityTicketEntity getSecurityTicket(String ticket) {
        if(ticket == null) return null;

        IDatabaseInstance databaseInstance = Nitro.getInstance().getDatabaseInstance();

        if(databaseInstance == null) return null;

        try {
            SecurityTicketEntity entity = databaseInstance.getDatabase().find(SecurityTicketEntity.class)
                    .where()
                    .eq("ticket", ticket)
                    .findOne();

            if(entity != null) return entity;
        } catch(Exception e) {
            NitroLogger.printError(e.getMessage());
        }

        return null;
    }

    public static void removeSecurityTicket(SecurityTicketEntity entity) {
        if(entity == null) return;

        IDatabaseInstance databaseInstance = Nitro.getInstance().getDatabaseInstance();

        if(databaseInstance == null) return;

        try {
            databaseInstance.getDatabase().delete(entity);
        } catch(Exception e) {
            NitroLogger.printError(e.getMessage());
        }
    }

    public static void removeSecurityTicket(String ticket) {
        if(ticket == null) return;

        IDatabaseInstance databaseInstance = Nitro.getInstance().getDatabaseInstance();

        if(databaseInstance == null) return;

        try {
            SecurityTicketEntity entity = getSecurityTicket(ticket);

            if(entity == null) return;

            removeSecurityTicket(entity);
        } catch(Exception e) {
            NitroLogger.printError(e.getMessage());
        }
    }
}
