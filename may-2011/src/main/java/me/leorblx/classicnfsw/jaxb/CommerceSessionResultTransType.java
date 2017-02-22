package me.leorblx.classicnfsw.jaxb;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CommerceSessionResultTransType", propOrder = { "invalidBasket", "inventoryItems", "status",
        "updatedCar"})
@XmlRootElement(name = "CommerceSessionResultTrans")
public class CommerceSessionResultTransType {
	@XmlElement(name = "InvalidBasket", required = true)
	protected String invalidBasket;
	@XmlElement(name = "InventoryItems", required = true)
	protected InventoryItemsType inventoryItems;
	@XmlElement(name = "UpdatedCar", required = true)
	protected CustomCarType updatedCar;
	@XmlElement(name = "Status", required = true)
	protected String status;

	public String getInvalidBasket() {
		return invalidBasket;
	}

	public void setInvalidBasket(String value) {
		this.invalidBasket = value;
	}

	public InventoryItemsType getInventoryItems() {
		return inventoryItems;
	}

	public void setInventoryItems(InventoryItemsType value) {
		this.inventoryItems = value;
	}

	public CustomCarType getUpdatedCar() {
		return updatedCar;
	}

	public void setUpdatedCar(CustomCarType value) {
		this.updatedCar = value;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String value) {
		this.status = value;
	}
}