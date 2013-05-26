package Inventory;

import java.util.HashSet;

import org.newdawn.slick.Image;

public class Backpack extends InventoryObject {

	private Image inGameImage;
	public HashSet<InventoryObject> items;
	int currSize, maxSize;
	
	public Backpack(String name, String description, Image inventoryImage, Image inGameImage, int size, int maxSize) {
		super(name, description, inventoryImage, size);
		this.setInGameImage(inGameImage);
		items = new HashSet<InventoryObject>();
		currSize = 0;
		this.maxSize = maxSize;
	}
	
	
	public boolean addToBackpack(InventoryObject io) {
		if (currSize + io.size > maxSize) {
			return false;
		} else {
			items.add(io);
			currSize += io.size;
			return true;
		}
	}


	public void setInGameImage(Image inGameImage) {
		this.inGameImage = inGameImage;
	}


	public Image getInGameImage() {
		return inGameImage;
	}
}
