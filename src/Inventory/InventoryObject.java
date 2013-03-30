package Inventory;

import org.newdawn.slick.Image;

public class InventoryObject {
	String name;
	String description;
	Image inventoryImage;
	int size;
	
	public InventoryObject(String name, String description, Image image, int size) {
		this.name = name;
		this.description = description;
		this.inventoryImage = image;
		this.size = size;
	}
}
