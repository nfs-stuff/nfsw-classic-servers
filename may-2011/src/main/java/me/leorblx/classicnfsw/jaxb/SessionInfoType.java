package me.leorblx.classicnfsw.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SessionInfo")
@XmlAccessorType(XmlAccessType.FIELD)
public class SessionInfoType
{
    @XmlElement(name = "SessionId")
    private long sessionId;

    public long getSessionId()
    {
        return sessionId;
    }

    public void setSessionId(long sessionId)
    {
        this.sessionId = sessionId;
    }
}
