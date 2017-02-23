package me.leorblx.classicnfsw.core;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class Configuration
{
    private String xmppIp;

    @SerializedName("debug")
    private boolean debugEnabled;

    private int httpPort;

    private String openfireToken;

    private String xmppServerType = "offline";

    public void setXmppIp(String xmppIp)
    {
        this.xmppIp = xmppIp;
    }

    public String getXmppIp()
    {
        return xmppIp;
    }

    public boolean isDebugEnabled()
    {
        return debugEnabled;
    }

    public void setDebugEnabled(boolean debugEnabled)
    {
        this.debugEnabled = debugEnabled;
    }

    public int getHttpPort()
    {
        return httpPort;
    }

    public void setHttpPort(int httpPort)
    {
        this.httpPort = httpPort;
    }

    public String getOpenfireToken()
    {
        return openfireToken;
    }

    public void setOpenfireToken(String openfireToken)
    {
        this.openfireToken = openfireToken;
    }

    public String getXmppServerType()
    {
        return xmppServerType;
    }

    public void setXmppServerType(String xmppServerType)
    {
        this.xmppServerType = xmppServerType;
    }

    public Map<String, Object> getAsMap()
    {
        Map<String, Object> map = new HashMap<>();

        // generic XMPP
        map.put("xmppIp", getXmppIp());
        map.put("xmppServerType", getXmppServerType());

//        map.put("openFireRestPort", getOpenFireRestPort());
        map.put("openFireToken", getOpenfireToken());

        // etc
        map.put("debug", isDebugEnabled());
        map.put("httpPort", getHttpPort());

        return map;
    }
}
