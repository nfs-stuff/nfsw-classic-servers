package me.leorblx.classicnfsw.http.controller;

import me.leorblx.classicnfsw.core.HttpState;
import me.leorblx.classicnfsw.core.Router;
import me.leorblx.classicnfsw.core.XmlUtils;
import me.leorblx.classicnfsw.jaxb.SessionInfoType;
import me.leorblx.classicnfsw.xmpp.XmppSrv;

public class Matchmaking extends Router
{
    public String launchevent() {
        Long eventId = Long.parseLong(getTarget().split("/")[6]);
        SessionInfoType sessionInfoType = new SessionInfoType();
        sessionInfoType.setSessionId(1337);

        HttpState.setEventId(eventId);

        XmppSrv.sendMsg(getLoggedPersonaId(), XmlUtils.marshal(sessionInfoType));
        
        return "";
    }
}
