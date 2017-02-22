package me.leorblx.classicnfsw.http.controller;

import me.leorblx.classicnfsw.core.HttpState;
import me.leorblx.classicnfsw.core.Router;

public class User extends Router
{
    public String getPermanentSession()
    {
        return "fileref:User/GetPermanentSession.xml";
    }


    public String secureLogout()
    {
        HttpState.setPersonaId(-1L);
        return "";
    }

    public String secureLoginPersona()
    {
        HttpState.setPersonaId(Long.valueOf(getParam("personaId")));

        return "";
    }

    public String secureLogoutPersona()
    {
        HttpState.setPersonaId(0L);
        return "";
    }
    
    public String setDefaultPersona() {
        return "";
    }
}
