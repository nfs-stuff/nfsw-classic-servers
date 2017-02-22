package me.leorblx.classicnfsw.http.controller;

import me.leorblx.classicnfsw.core.HttpState;
import me.leorblx.classicnfsw.core.Router;

public class Event extends Router
{
    public String arbitration()
    {
        return HttpState.getEventId() == 2 ? "fileref:event/arbitration_pursuit.xml" : "fileref:event/arbitration.xml";
    }
}
