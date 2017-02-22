package me.leorblx.classicnfsw.core;

import org.eclipse.jetty.server.Request;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.SecureRandom;

public class Router
{

    public static SecureRandom random = new SecureRandom();

    private String target;
    private HttpServletRequest request;
    private Request baseRequest;

    protected String getHeader(String param)
    {
        return request.getHeader(param);
    }

    protected Long getLoggedPersonaId()
    {
        return HttpState.getPersonaId();
    }

    protected String getParam(String param)
    {
        return baseRequest.getParameter(param);
    }

    protected HttpServletRequest getRequest()
    {
        return request;
    }

    protected String getSecureRandomText()
    {
        return new BigInteger(130, Router.random).toString(32);
    }

    protected String getSecurityToken()
    {
        if (getHeader("securityToken") != null)
            return (String) getHeader("securityToken");
        return null;
    }

    protected String getTarget()
    {
        return target;
    }

    protected Long getUserId()
    {
        if (getHeader("userId") != null)
            return Long.valueOf(getHeader("userId"));
        return -1L;
    }

    protected String readInputStream()
    {
        StringBuilder buffer = new StringBuilder();
        try {
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null)
                buffer.append(line);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

    public void setBaseRequest(Request baseRequest)
    {
        this.baseRequest = baseRequest;
    }

    public void setRequest(HttpServletRequest request)
    {
        this.request = request;
    }

    public void setTarget(String target)
    {
        this.target = target;
    }

    protected String shuffleString(String input)
    {
        StringBuilder sb = new StringBuilder(input.length());
        double mathRandom;
        for (char c : input.toCharArray()) {
            mathRandom = Math.random();
            if (mathRandom < 0.34)
                sb.append(c);
            else if (mathRandom < 0.67)
                sb.insert(sb.length() / 2, c);
            else
                sb.insert(0, c);
        }
        return sb.toString();
    }
}