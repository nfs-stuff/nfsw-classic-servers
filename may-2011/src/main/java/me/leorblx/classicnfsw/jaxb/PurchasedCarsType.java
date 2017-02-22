package me.leorblx.classicnfsw.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PurchasedCarsType")
public class PurchasedCarsType
{
    @XmlElement(name = "CustomCarTrans", required = true)
    protected CustomCarType customCarTrans;

    public CustomCarType getCustomCarTrans()
    {
        return customCarTrans;
    }

    public void setCustomCarTrans(CustomCarType customCarTrans)
    {
        this.customCarTrans = customCarTrans;
    }
}