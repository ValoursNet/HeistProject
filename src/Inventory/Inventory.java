package Inventory;

import java.util.HashSet;

public class Inventory {

	HashSet<InventoryObject> objects;
	
	public Inventory() {
		objects = new HashSet<InventoryObject>();
	}

	public void addToInventory(InventoryObject io) {
		objects.add(io);
	}
	
	public void removeInventoryObject(InventoryObject io) {
		objects.remove(io);
	}
	
}
