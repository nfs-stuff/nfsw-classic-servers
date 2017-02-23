package me.leorblx.classicnfsw.http;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.leorblx.classicnfsw.core.HttpState;
import me.leorblx.classicnfsw.core.Router;
import me.leorblx.classicnfsw.core.XmlUtils;
import me.leorblx.classicnfsw.http.controller.Session;
import org.eclipse.jetty.server.HttpOutput;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.gzip.GzipHandler;
import org.eclipse.jetty.server.handler.gzip.GzipHttpOutputInterceptor;

public class HttpSrv extends GzipHandler
{

    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
    {
        if ("/favicon.ico".equals(target))
            return;
        System.out.println(baseRequest.toString());
        String[] targetSplitted = target.split("/");
        String className = "Default";
        String methodName = "";
        String content = null;

        if (targetSplitted.length > 4) {
            className = targetSplitted[3];
            className = String.valueOf(Character.toUpperCase(className.charAt(0))).concat(className.substring(1));
            methodName = targetSplitted[4];
            if (methodName.matches(".*\\d+.*")) {
                if (targetSplitted.length >= 6) {
                    methodName = targetSplitted[5];
                } else {
                    methodName = "handle";
                }
            }
            methodName = String.valueOf(Character.toLowerCase(methodName.charAt(0))).concat(methodName.substring(1));
        } else {
            methodName = targetSplitted[3];
            methodName = String.valueOf(Character.toLowerCase(methodName.charAt(0))).concat(methodName.substring(1));
        }

        try {
            Class.forName("me.leorblx.classicnfsw.http.controller.".concat(className));
        } catch (ClassNotFoundException e) {
            methodName = className.substring(0, 1).toLowerCase() + className.substring(1);
            className = "Default";
        }

        try {
            Class<?> dynamicObj = Class.forName("me.leorblx.classicnfsw.http.controller.".concat(className));
            Router newInstance = (Router) dynamicObj.newInstance();
            newInstance.setBaseRequest(baseRequest);
            newInstance.setRequest(request);
            newInstance.setTarget(target);
            Method declaredMethod;
            declaredMethod = dynamicObj.getDeclaredMethod(methodName);
            content = (String) declaredMethod.invoke(newInstance);

            if (content.startsWith("fileref:")) {
                String filename = content.substring(content.indexOf(':') + 1);

                content = XmlUtils.getFromFile("www/nfsw/Engine.svc/" + filename);
            }

            response.setStatus(content == null ? 404 : 200);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | SecurityException e) {
            e.printStackTrace();
            System.out.println("class or method error");
        } catch (NoSuchMethodException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("class not found");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            response.setStatus(500);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("generic error");
        }
        try {
            setCompressionLevel(9);
            HttpOutput out = baseRequest.getResponse().getHttpOutput();
            out.setInterceptor(new GzipHttpOutputInterceptor(this, GzipHttpOutputInterceptor.VARY_ACCEPT_ENCODING,
                    baseRequest.getHttpChannel(), out.getInterceptor()));
            response.setContentType("application/xml;charset=utf-8");
            if (target.contains("accept")) {
                response.addHeader("Keep-Alive", "timeout=70");
            } else {
                response.setHeader("connection", "close");
            }
            baseRequest.setHandled(true);
            if (content == null) {
                content = "Not found";
            }
            
            content = content.replace("RELAYPERSONA", HttpState.getPersonaId().toString())
                    .replace("{xmppIp}", Session.getXmppIp());

            response.getOutputStream().write(content.getBytes(StandardCharsets.UTF_8));
            response.getOutputStream().flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        System.setProperty("jsse.enableCBCProtection", "false");

        try {
            Server server = new Server(1337);
            server.setHandler(new HttpSrv());
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}