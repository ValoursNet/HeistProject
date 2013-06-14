package Inventory;

import java.util.HashSet;

public class GroundObjects {
	
	public  HashSet<InventoryObject> items;
	
	public GroundObjects() {
		items = new HashSet<InventoryObject>();
	}
	
	public boolean addToGround(InventoryObject io, int positionX, int positionY) {
		if(io != null){
			io.onGround = true;
			io.groundPositionX = positionX;
			io.groundPositionY = positionY;
			items.add(io);
			return true;
		} else {
			return false;
		}
	}
	
	
	public InventoryObject removeFromGround(InventoryObject io) {
		io.onGround = false;
		io.groundPositionX = 0;
		io.groundPositionY = 0;
		items.remove(io);
		
		return io;
	}
	
}
