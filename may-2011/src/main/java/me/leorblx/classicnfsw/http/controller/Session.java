package me.leorblx.classicnfsw.http.controller;

import me.leorblx.classicnfsw.core.Router;

public class Session extends Router
{
	private static String xmppIp = "127.0.0.1";

	private static String xmppServerType = "Offline";

	private static long currentMpSessionId = 10000L;

	public String getChatInfo() {
        return "fileref:Session/GetChatInfo.xml";
	}

	public static String getXmppIp() {
		return xmppIp;
	}

	public static void setXmppIp(String xmppIp) {
		Session.xmppIp = xmppIp;
	}

	public static String getXmppServerType() {
		return xmppServerType;
	}

	public static void setXmppServerType(String xmppServerType) {
		Session.xmppServerType = xmppServerType;
	}
}