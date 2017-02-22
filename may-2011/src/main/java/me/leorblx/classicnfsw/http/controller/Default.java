package me.leorblx.classicnfsw.http.controller;

import me.leorblx.classicnfsw.core.Router;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Default extends Router
{
    public String getfriendlistfromuserid()
    {
        return "<PersonaFriendsList/>";
    }

    public String systeminfo()
    {
        String timeString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSSS+00:00").format(new Date());
        timeString = timeString.replace(" ", "T");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<SystemInfo>\n");
        stringBuilder.append("  <Branch>debug</Branch>\n");
        stringBuilder.append("  <ChangeList>620384</ChangeList>\n");
        stringBuilder.append("  <ClientVersion>637</ClientVersion>\n");
        stringBuilder.append("  <ClientVersionCheck>true</ClientVersionCheck>\n");
        stringBuilder.append("  <Deployed>08/20/2013 11:24:40</Deployed>\n");
        stringBuilder.append("  <EntitlementsToDownload>true</EntitlementsToDownload>\n");
        stringBuilder.append("  <ForcePermanentSession>true</ForcePermanentSession>\n");
        stringBuilder.append("  <JidPrepender>nfsw</JidPrepender>\n");
        stringBuilder.append("  <LauncherServiceUrl>http://10.100.15.202/LauncherService/onlineconfig.aspx</LauncherServiceUrl>\n");
        stringBuilder.append("  <NucleusNamespace>nfsw-live</NucleusNamespace>\n");
        stringBuilder.append("  <NucleusNamespaceWeb>nfs_web</NucleusNamespaceWeb>\n");
        stringBuilder.append("  <PersonaCacheTimeout>900</PersonaCacheTimeout>\n");
        stringBuilder.append("  <PortalDomain/>\n");
        stringBuilder.append("  <PortalSecureDomain/>\n");
        stringBuilder.append("  <PortalStoreFailurePage/>\n");
        stringBuilder.append("  <PortalTimeOut>60000</PortalTimeOut>\n");
        stringBuilder.append("  <ShardName>US</ShardName>\n");
        stringBuilder.append("  <Time>");
        // stringBuilder.append("2010-01-01T12:00:00.0000000+00:00");
        stringBuilder.append(timeString);
        stringBuilder.append("</Time>\n");
        stringBuilder.append("  <Version>1599</Version>\n");
        stringBuilder.append("</SystemInfo>\n");
        stringBuilder.append("");

        return stringBuilder.toString();
    }

    public String getsocialsettings()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<SocialSettings>\n");
        stringBuilder.append("  <AppearOffline>false</AppearOffline>\n");
        stringBuilder.append("  <DeclineGroupInvite>0</DeclineGroupInvite>\n");
        stringBuilder.append("  <DeclineIncommingFriendRequests>false</DeclineIncommingFriendRequests>\n");
        stringBuilder.append("  <DeclinePrivateInvite>0</DeclinePrivateInvite>\n");
        stringBuilder.append("  <HideOfflineFriends>false</HideOfflineFriends>\n");
        stringBuilder.append("  <ShowNewsOnSignIn>false</ShowNewsOnSignIn>\n");
        stringBuilder.append("  <ShowOnlyPlayersInSameChatChannel>false</ShowOnlyPlayersInSameChatChannel>\n");
        stringBuilder.append("</SocialSettings>");
        return stringBuilder.toString();
    }

    public String getusersettings()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<User_Settings>\n");
        stringBuilder.append("  <CarCacheAgeLimit>600</CarCacheAgeLimit>\n");
        stringBuilder.append("  <IsRaceNowEnabled>true</IsRaceNowEnabled>\n");
        stringBuilder.append("  <MaxCarCacheSize>250</MaxCarCacheSize>\n");
        stringBuilder.append("  <MinRaceNowLevel>2</MinRaceNowLevel>\n");
        stringBuilder.append("  <VoipAvailable>false</VoipAvailable>\n");
        stringBuilder.append("  <activatedHolidaySceneryGroups>\n");
        stringBuilder.append("    <string>SCENERY_GROUP_CHRISTMAS</string>\n");
        stringBuilder.append("  </activatedHolidaySceneryGroups>\n");
        stringBuilder.append("  <activeHolidayIds>\n");
        stringBuilder.append("    <long>0</long>\n");
        stringBuilder.append("  </activeHolidayIds>\n");
        stringBuilder.append("  <disactivatedHolidaySceneryGroups>\n");
        stringBuilder.append("    <string>SCENERY_GROUP_CHRISTMAS_DISABLE</string>\n");
        stringBuilder.append("  </disactivatedHolidaySceneryGroups>\n");
        stringBuilder.append("  <firstTimeLogin>false</firstTimeLogin>\n");
        stringBuilder.append("  <maxLevel>60</maxLevel>\n");
        stringBuilder.append("  <starterPackApplied>false</starterPackApplied>\n");
        stringBuilder.append("  <userId>" + getUserId() + "</userId>\n");
        stringBuilder.append("</User_Settings>");

        return stringBuilder.toString();
    }

    public String getblockeduserlist()
    {
        return "<ArrayOflong/>";
    }

    public String getblockersbyusers()
    {
        return "<ArrayOflong/>";
    }

    public String heartbeat()
    {
        return "";
    }

    public String newsArticles()
    {
        return "<ArrayOfNewsArticleTrans />";
    }

    public String getsocialnetworkinfo()
    {
        return "<SocialNetworkInfo />";
    }

    public String setsocialsettings()
    {
        return "";
    }

    public String addfriendrequest()
    {
        return "";
    }

    public String onlinesetting()
    {
        return "<OnlineSettingTrans xmlns=\"http://schemas.datacontract.org/2004/07/EA.NFSWO.ENGINE.DataLayer.Serialization\"\n" +
                "                    xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "    <CountdownProposal>3</CountdownProposal>\n" +
                "    <DirectConnectTimeout>1000</DirectConnectTimeout>\n" +
                "    <DropOutTime>15</DropOutTime>\n" +
                "    <HandshakeTimeout>22</HandshakeTimeout>\n" +
                "    <HeartbeatInterval>1</HeartbeatInterval>\n" +
                "    <Id>1</Id>\n" +
                "    <RaceLoadingTimeout>30</RaceLoadingTimeout>\n" +
                "    <UDPRelayRelayBandwidthBps>9600</UDPRelayRelayBandwidthBps>\n" +
                "    <UDPRelayTimeout>60000</UDPRelayTimeout>\n" +
                "</OnlineSettingTrans>";
    }

    public String serverconfigs()
    {
        return "<ArrayOfServerConfigTrans xmlns=\"http://schemas.datacontract.org/2004/07/EA.NFSWO.ENGINE.DataLayer.Serialization\"\n" +
                "                          xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                "    <ServerConfigTrans>\n" +
                "        <Id>3</Id>\n" +
                "        <RebroadcasterHost>localhost</RebroadcasterHost>\n" +
                "        <RebroadcasterPort>5223</RebroadcasterPort>\n" +
                "    </ServerConfigTrans>\n" +
                "</ArrayOfServerConfigTrans>";
    }

    public String skills()
    {
        return "<ArrayOfSkillDef/>";
    }

    public String setusersettings()
    {
        return "";
    }
    
    public String statistics() {
        return "<ArrayOfPersonaStats/>";
    }
    
    public String getrecentplayerlist() {
        return "";
    }
    
    public String suggestiveSales() {
        return "";
    }
}
