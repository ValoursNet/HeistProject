package Inventory;

import org.newdawn.slick.Image;

public class InventoryObject {
	public String name;
	public String description;
	public Image inventoryImage;
	public Image groundImage;
	public int size;
	public String objectType;
	
	public int groundPositionX = 0;
	public int groundPositionY = 0;
	public boolean onGround = false;
	
	public InventoryObject(String name, String description, Image image, int size) {
		this.name = name;
		this.description = description;
		this.inventoryImage = image;
		this.size = size;
	}
	
	public InventoryObject(String name, String description, Image invImage, Image groundImage, int size) {
		this.name = name;
		this.description = description;
		this.inventoryImage = invImage;
		this.groundImage = groundImage;
		this.size = size;
	}
	
	public String overlayText(){
		return "";
	}
}
