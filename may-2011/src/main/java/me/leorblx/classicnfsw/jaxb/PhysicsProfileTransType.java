package me.leorblx.classicnfsw.jaxb;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "PhysicsProfileTrans")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"attribHash", "tier"})
public class PhysicsProfileTransType
{
    @XmlElement(name = "AttribHash")
    private long attribHash;
    
    @XmlElement(name = "Tier")
    private int tier;

    public long getAttribHash()
    {
        return attribHash;
    }

    public void setAttribHash(long attribHash)
    {
        this.attribHash = attribHash;
    }

    public int getTier()
    {
        return tier;
    }

    public void setTier(int tier)
    {
        this.tier = tier;
    }
}
