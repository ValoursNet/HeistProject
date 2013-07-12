package Inventory;

import org.newdawn.slick.Image;

public class Magasine extends InventoryObject {
	Image inGameImage;
	public int currBullets, maxBullets;
	public String magType;
	
	public Magasine(String magType, String name, String description, Image inventoryImage, int size, int currBullets, int maxBullets) {
		super(name, description, inventoryImage, size);
		this.objectType = "Mag";
		this.magType = magType;
		this.currBullets = currBullets;
		this.maxBullets = maxBullets;
	}
	
	public Magasine(String magType,String name, String description, Image inventoryImage, Image groundImage, int size, int currBullets, int maxBullets) {
		super(name, description, inventoryImage, groundImage, size);
		this.objectType = "Mag";
		this.magType = magType;
		this.currBullets = currBullets;
		this.maxBullets = maxBullets;
	}
	
	public String overlayText(){
		return Integer.toString(currBullets);
	}
	
	public boolean isEmpty() {
		return currBullets == 0;
	}
	
	public boolean fire() {
		if (currBullets > 0) {
			currBullets--;
			return true;
		} else {
			return false;
		}
	}
	
}
