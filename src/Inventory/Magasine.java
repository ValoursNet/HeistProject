package Inventory;

import org.newdawn.slick.Image;

public class Magasine extends InventoryObject {
	Image inGameImage;
	int currBullets, maxBullets;
	
	public Magasine(String name, String description, Image inventoryImage, int size, int currBullets, int maxBullets) {
		super(name, description, inventoryImage, size);
		this.currBullets = currBullets;
		this.maxBullets = maxBullets;
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
