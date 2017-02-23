package me.leorblx.classicnfsw;

import me.leorblx.classicnfsw.core.Configuration;
import me.leorblx.classicnfsw.core.ConfigurationManager;
import me.leorblx.classicnfsw.http.HttpSrv;
import me.leorblx.classicnfsw.http.controller.Session;
import me.leorblx.classicnfsw.xmpp.XmppSrv;
import org.eclipse.jetty.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NfswServer
{
    private static Logger logger = LoggerFactory.getLogger(NfswServer.class);
    
    public static void main(String[] args)
    {
        System.setProperty("jsse.enableCBCProtection", "false");

        Configuration configuration = ConfigurationManager.getInstance().getConfiguration();

        Session.setXmppIp(configuration.getXmppIp());
        Session.setXmppServerType(configuration.getXmppServerType());
        
        Server server = new Server(configuration.getHttpPort());
        server.setHandler(new HttpSrv());
        
        try {
            server.start();
            logger.info("HTTP server started!");
            
            new XmppSrv();
            
            logger.info("XMPP server started!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
