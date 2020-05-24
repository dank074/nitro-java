package com.nitro.nitro.communication.messages;

import com.nitro.core.communication.messages.MessageConfiguration;
import com.nitro.nitro.communication.messages.incoming.IncomingHeaders;
import com.nitro.nitro.communication.messages.incoming.client.ClientPongEvent;
import com.nitro.nitro.communication.messages.incoming.client.ClientReleaseVersionEvent;
import com.nitro.nitro.communication.messages.incoming.desktop.DesktopCampaignsEvent;
import com.nitro.nitro.communication.messages.incoming.desktop.DesktopNewsEvent;
import com.nitro.nitro.communication.messages.incoming.desktop.DesktopViewEvent;
import com.nitro.nitro.communication.messages.incoming.security.SecurityMachineEvent;
import com.nitro.nitro.communication.messages.incoming.security.SecurityTicketEvent;
import com.nitro.nitro.communication.messages.outgoing.OutgoingHeaders;
import com.nitro.nitro.communication.messages.outgoing.client.ClientPingComposer;
import com.nitro.nitro.communication.messages.outgoing.desktop.DesktopCampaignsComposer;
import com.nitro.nitro.communication.messages.outgoing.desktop.DesktopViewComposer;
import com.nitro.nitro.communication.messages.outgoing.security.SecurityAuthenticatedComposer;
import com.nitro.nitro.communication.messages.outgoing.security.SecurityMachineComposer;
import com.nitro.nitro.communication.messages.outgoing.user.access.UserPermissionsComposer;
import com.nitro.nitro.communication.messages.outgoing.user.access.UserRightsComposer;
import com.nitro.nitro.communication.messages.outgoing.user.data.UserAchievementScoreComposer;
import com.nitro.nitro.communication.messages.outgoing.user.data.UserFirstLoginOfDayComposer;
import com.nitro.nitro.communication.messages.outgoing.user.data.UserHomeRoomComposer;

public class NitroMessages extends MessageConfiguration {

    protected void registerEvents() {
        this.events.put(IncomingHeaders.CLIENT_PONG, ClientPongEvent.class);
        this.events.put(IncomingHeaders.CLIENT_RELEASE_VERSION, ClientReleaseVersionEvent.class);

        this.events.put(IncomingHeaders.DESKTOP_CAMPAIGNS, DesktopCampaignsEvent.class);
        this.events.put(IncomingHeaders.DESKTOP_NEWS, DesktopNewsEvent.class);
        this.events.put(IncomingHeaders.DESKTOP_VIEW, DesktopViewEvent.class);

        this.events.put(IncomingHeaders.SECURITY_MACHINE, SecurityMachineEvent.class);
        this.events.put(IncomingHeaders.SECURITY_TICKET, SecurityTicketEvent.class);
    }

    protected void registerComposers() {
        this.composers.put(OutgoingHeaders.CLIENT_PING, ClientPingComposer.class);

        this.composers.put(OutgoingHeaders.DESKTOP_CAMPAIGN, DesktopCampaignsComposer.class);
        this.composers.put(OutgoingHeaders.DESKTOP_VIEW, DesktopViewComposer.class);

        this.composers.put(OutgoingHeaders.AUTHENTICATED, SecurityAuthenticatedComposer.class);
        this.composers.put(OutgoingHeaders.SECURITY_MACHINE, SecurityMachineComposer.class);

        this.composers.put(OutgoingHeaders.USER_PERMISSIONS, UserPermissionsComposer.class);
        this.composers.put(OutgoingHeaders.USER_RIGHTS, UserRightsComposer.class);

        this.composers.put(OutgoingHeaders.USER_ACHIEVEMENT_SCORE, UserAchievementScoreComposer.class);
        this.composers.put(OutgoingHeaders.FIRST_LOGIN_OF_DAY, UserFirstLoginOfDayComposer.class);
        this.composers.put(OutgoingHeaders.USER_HOME_ROOM, UserHomeRoomComposer.class);
    }
}
