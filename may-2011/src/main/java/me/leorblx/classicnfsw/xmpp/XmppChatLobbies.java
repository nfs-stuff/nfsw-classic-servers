package me.leorblx.classicnfsw.xmpp;

import java.util.HashMap;

public class XmppChatLobbies {

    private static HashMap<Long, XmppFreeroamLobby> xmppFreeroamLobbies = new HashMap<>();
	private static XmppSystemLobby xmppSystemLobby = new XmppSystemLobby();

	public static XmppFreeroamLobby getFreeroamLobby(String channelName, Integer channelNumber) {
		Long channelHash = (long) String.format("%d_%d", channelName.hashCode(), channelNumber).hashCode();

		if (xmppFreeroamLobbies.containsKey(channelHash))
			return xmppFreeroamLobbies.get(channelHash);

		XmppFreeroamLobby xmppFreeroamChat = new XmppFreeroamLobby(channelName, channelNumber);
		xmppFreeroamLobbies.put(channelHash, xmppFreeroamChat);
		return xmppFreeroamChat;
	}

	public static XmppSystemLobby getSystemLobby() {
		return xmppSystemLobby;
	}

	public static void signOut(XmppTalk xmppTalk) {
		Long personaId = xmppTalk.getPersonaId();
		getFreeroamLobby(xmppTalk.getCurrentChannelName(), xmppTalk.getCurrentChannelNumber())
				.removeXmppTalk(personaId);
		getSystemLobby().removeXmppTalk(personaId);
		XmppSrv.removeXmppClient(personaId);
	}
}