package me.leorblx.classicnfsw.jaxb;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomCarType", propOrder = {"baseCarId", "durability", "heat", "isPreset", "level", "name", "apiId",
        "paints", "performanceParts", "physicsProfile", "rating", "resalePrice", "vinyls",
        "visualParts"})
@XmlRootElement(name = "CustomCarTrans")
public class CustomCarType
{
    @XmlElement(name = "BaseCar", required = true)
    private long baseCarId;
    @XmlElement(name = "IsPreset")
    private boolean isPreset;
    @XmlElement(name = "Level")
    protected int level;
    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "Id", required = true)
    private long apiId;
    @XmlElement(name = "Paints")
    protected PaintsType paints;
    @XmlElement(name = "PerformanceParts")
    private PerformancePartsType performanceParts;
    @XmlElement(name = "PhysicsProfile")
    private PhysicsProfileTransType physicsProfile;
    @XmlElement(name = "Rating")
    protected int rating;
    @XmlElement(name = "ResalePrice")
    private int resalePrice;
    @XmlElement(name = "Vinyls")
    protected VinylsType vinyls;
    @XmlElement(name = "VisualParts")
    private VisualPartsType visualParts;

    @XmlElement(name = "Heat")
    private float heat;

    @XmlElement(name = "Durability")
    private int durability;

    public void setBaseCarId(long value)
    {
        this.baseCarId = value;
    }

    public long getBaseCarId()
    {
        return this.baseCarId;
    }

    public boolean getIsPreset()
    {
        return isPreset;
    }

    public void setPreset(boolean value)
    {
        this.isPreset = value;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int value)
    {
        this.level = value;
    }

    public void setApiId(long value)
    {
        this.apiId = value;
    }

    public long getApiId()
    {
        return this.apiId;
    }

    public void setPaints(PaintsType value)
    {
        this.paints = value;
    }

    public PaintsType getPaints()
    {
        return this.paints;
    }

    public void setPerformanceParts(PerformancePartsType value)
    {
        this.performanceParts = value;
    }

    public PerformancePartsType getPerformanceParts()
    {
        return this.performanceParts;
    }

    public PhysicsProfileTransType getPhysicsProfile()
    {
        return physicsProfile;
    }

    public void setPhysicsProfile(PhysicsProfileTransType physicsProfile)
    {
        this.physicsProfile = physicsProfile;
    }

    public void setRating(int value)
    {
        this.rating = value;
    }

    public int getRating()
    {
        return this.rating;
    }

    public void setResalePrice(int value)
    {
        this.resalePrice = value;
    }

    public int getResalePrice()
    {
        return this.resalePrice;
    }

    public void setVinyls(VinylsType value)
    {
        this.vinyls = value;
    }

    public VinylsType getVinyls()
    {
        return this.vinyls;
    }

    public void setVisualParts(VisualPartsType value)
    {
        this.visualParts = value;
    }

    public VisualPartsType getVisualParts()
    {
        return this.visualParts;
    }

    public float getHeat()
    {
        return heat;
    }

    public void setDurability(int durability)
    {
        this.durability = durability;
    }

    public int getDurability()
    {
        return durability;
    }

    public void setHeat(float heat)
    {
        this.heat = heat;
    }
}