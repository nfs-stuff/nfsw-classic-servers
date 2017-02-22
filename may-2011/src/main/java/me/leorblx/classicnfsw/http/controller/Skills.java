package me.leorblx.classicnfsw.http.controller;

import me.leorblx.classicnfsw.core.Router;

public class Skills extends Router
{
    public String handle()
    {
        Long personaId = Long.valueOf(getTarget().split("/")[4]);
        
        return "fileref:Skills/" + personaId + ".xml";
    }
}
