package Inventory;

import org.newdawn.slick.Image;

public class InventoryObject {
	public String name;
	String description;
	public Image inventoryImage;
	public int size;
	public String objectType;
	
	public InventoryObject(String name, String description, Image image, int size) {
		this.name = name;
		this.description = description;
		this.inventoryImage = image;
		this.size = size;
	}
	
	public String overlayText(){
		return "";
	}
}
