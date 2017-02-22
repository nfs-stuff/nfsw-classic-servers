package me.leorblx.classicnfsw.http.controller;

import me.leorblx.classicnfsw.core.HttpState;
import me.leorblx.classicnfsw.core.Router;

import java.nio.ByteBuffer;
import java.util.Base64;

public class Crypto extends Router
{
	public String cryptoticket() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<ClientServerCryptoTicket>\n");
		stringBuilder.append("<CryptoTicket>");
		stringBuilder.append("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA==");
		stringBuilder.append("</CryptoTicket>\n");
		stringBuilder.append("<SessionKey>AAAAAAAAAAAAAAAAAAAAAA==</SessionKey>\n");
		stringBuilder.append("<TicketIv>AAAAAAAAAAAAAAAAAAAAAA==</TicketIv>\n");
		stringBuilder.append("</ClientServerCryptoTicket>");
		String cryptoTicket = stringBuilder.toString();
		return cryptoTicket;
	}

	public String relaycryptoticket() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<UdpRelayCryptoTicket>");
		stringBuilder.append("<CryptoTicket>");
		stringBuilder.append("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA==");
		stringBuilder.append("</CryptoTicket>\n");
		stringBuilder.append("<SessionKey>AAAAAAAAAAAAAAAAAAAAAA==</SessionKey>\n");
		stringBuilder.append("<TicketIv>AAAAAAAAAAAAAAAAAAAAAA==</TicketIv>\n");
		stringBuilder.append("</UdpRelayCryptoTicket>");
		String relayCrypto = stringBuilder.toString();
		return relayCrypto;
	}
}