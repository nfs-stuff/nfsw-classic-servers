package me.leorblx.classicnfsw.jaxb;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class CarsOwnedByPersonaList
{
    @XmlElement(name = "CustomCarTrans", required = true)
    protected List<CustomCarType> customCarList;

    public List<CustomCarType> getCustomCarList()
    {
        return customCarList;
    }

    public void setCustomCarList(List<CustomCarType> customCarList)
    {
        this.customCarList = customCarList;
    }

    public boolean add(CustomCarType e)
    {
        if (customCarList == null) {
            customCarList = new ArrayList<>();
        }

        return customCarList.add(e);
    }
}