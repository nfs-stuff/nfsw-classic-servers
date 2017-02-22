package me.leorblx.classicnfsw.jaxb;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CommerceResultTransType", propOrder = {"commerceItems", "invalidBasket", "inventoryItems",
        "purchasedCars", "status"})
@XmlRootElement(name = "CommerceResultTrans")
public class CommerceResultTransType
{
    @XmlElement(name = "CommerceItems", required = true)
    protected String commerceItems;
    @XmlElement(name = "InvalidBasket", required = true)
    protected String invalidBasket;
    @XmlElement(name = "InventoryItems", required = true)
    protected InventoryItemsType inventoryItems;
    @XmlElement(name = "PurchasedCars", required = true)
    protected PurchasedCarsType purchasedCars;
    @XmlElement(name = "Status", required = true)
    protected String status;

    public String getCommerceItems()
    {
        return commerceItems;
    }

    public void setCommerceItems(String commerceItems)
    {
        this.commerceItems = commerceItems;
    }

    public String getInvalidBasket()
    {
        return invalidBasket;
    }

    public void setInvalidBasket(String value)
    {
        this.invalidBasket = value;
    }

    public InventoryItemsType getInventoryItems()
    {
        return inventoryItems;
    }

    public void setInventoryItems(InventoryItemsType value)
    {
        this.inventoryItems = value;
    }

    public PurchasedCarsType getPurchasedCars()
    {
        return purchasedCars;
    }

    public void setPurchasedCars(PurchasedCarsType value)
    {
        this.purchasedCars = value;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String value)
    {
        this.status = value;
    }
}