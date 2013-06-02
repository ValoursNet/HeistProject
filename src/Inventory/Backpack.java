package Inventory;

import java.util.HashSet;

import org.newdawn.slick.Image;

public class Backpack extends InventoryObject {

	private Image inGameImage;
	public HashSet<InventoryObject> items;
	public InventoryObject[][] itemLayout;
	public int backpackPositionX;
	public int backpackPositionY;
	public int backpackMouseX;
	public int backpackMouseY;
	public boolean backpackMouseDown;
	
	public boolean currentHighligtedItem = false;;
	public int highlightedItemX;
	public int highlightedItemY;
	
	public boolean targetHighligtedItem = false;;
	public int targetItemX;
	public int targetItemY;
	
	int currSize, maxSize;
	
	public Backpack(String name, String description, Image inventoryImage, Image inGameImage, int size, int maxSize, int layoutWidth, int layoutHeight) {
		super(name, description, inventoryImage, size);
		this.setInGameImage(inGameImage);
		items = new HashSet<InventoryObject>();
		itemLayout = new InventoryObject[layoutWidth][layoutHeight];
		
		 for (int row = 0; row < itemLayout[0].length; row ++){
	            for (int col = 0; col < itemLayout.length; col++){
	            	if(itemLayout[col][row] != null){
	            		itemLayout[col][row] = new InventoryObject("Blank", "", null, 0);
	            	}
	            }
		 }
		
		currSize = 0;
		this.maxSize = maxSize;
	}
	
	public void handleMouseInput(int xPos, int yPos, boolean mouseClick){
	//	System.out.println(Math.round((xPos - backpackPositionX)/50));
		backpackMouseDown = mouseClick;
		if(mouseClick){
			backpackMouseX = Math.round((xPos - backpackPositionX)/50);
			backpackMouseY = Math.round((yPos - backpackPositionY)/50);
			
			if(currentHighligtedItem == false){
				currentHighligtedItem = true;
				highlightedItemX = backpackMouseX;
				highlightedItemY = backpackMouseY;
			} else {
				System.out.println("highlightedItemX: " + highlightedItemX + "highlightedItemY: " + highlightedItemY);
				System.out.println("targetItemX: " + backpackMouseX + "targetItemY: " + backpackMouseY);
				if(highlightedItemX != backpackMouseX || highlightedItemY != backpackMouseY){
					targetHighligtedItem = true;
					targetItemX = backpackMouseX;
					targetItemY = backpackMouseY;
				}
			}
			
			if(targetHighligtedItem && currentHighligtedItem){
				InventoryObject targetItem = removeFromBackpack(itemLayout[targetItemX][targetItemY], targetItemX, targetItemY);
				addToBackpack(removeFromBackpack(itemLayout[highlightedItemX][highlightedItemY], highlightedItemX, highlightedItemY), targetItemX, targetItemY);
				addToBackpack(targetItem ,highlightedItemX, highlightedItemY);
				currentHighligtedItem = false;
				targetHighligtedItem = false;
			}
			/*
			if(currentHighligtedItem == false){
				currentHighligtedItem = true;
				highlightedItemX = backpackMouseX;
				highlightedItemY = backpackMouseY;
			} else {
				System.out.println("itemLayout[0].length: " + itemLayout[0].length + "itemLayout.length: " + itemLayout.length);
				if(highlightedItemX < itemLayout.length && highlightedItemY < itemLayout[0].length
					&& backpackMouseX < itemLayout.length && backpackMouseY < itemLayout[0].length	) {
					addToBackpack(removeFromBackpack(itemLayout[highlightedItemX][highlightedItemY], highlightedItemX, highlightedItemY), backpackMouseX, backpackMouseY);
					currentHighligtedItem = false;
				}
			}
			*/
		}
	}
	
	public InventoryObject removeFromBackpack(InventoryObject io, int layoutX, int layoutY) {
		if(itemLayout[layoutX][layoutY] == null){
			return null;
		}else{
			itemLayout[layoutX][layoutY] = null;
			currSize -= io.size;
			return io;
		}
	}
	
	
	public boolean forceToBackpack(InventoryObject io, int layoutX, int layoutY) {
		removeFromBackpack(itemLayout[layoutX][layoutY], layoutX, layoutY);
		
		if(io != null){
			System.out.println("itemLayout[0].length: " + itemLayout[0].length + "itemLayout.length: " + itemLayout.length);
			if (currSize + io.size > maxSize) {
				return false;
			} else if(layoutX < itemLayout.length && layoutY < itemLayout[0].length) {
				System.out.println("layoutX: " + layoutX + "layoutY: " + layoutY);
				if(itemLayout[layoutX][layoutY] == null){
					itemLayout[layoutX][layoutY] = io;
					items.add(io);
					currSize += io.size;
					return true;
				}else{
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public boolean addToBackpack(InventoryObject io, int layoutX, int layoutY) {
		if(io != null){
			if (currSize + io.size > maxSize) {
				return false;
			} else if(layoutX < itemLayout.length && layoutY < itemLayout[0].length) {
				System.out.println("layoutX: " + layoutX + "layoutY: " + layoutY);
				System.out.println("itemLayout[0].length: " + itemLayout[0].length + "itemLayout.length: " + itemLayout.length);
				if(itemLayout[layoutX][layoutY] == null){
					itemLayout[layoutX][layoutY] = io;
					items.add(io);
					currSize += io.size;
					return true;
				}else{
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}


	public void setInGameImage(Image inGameImage) {
		this.inGameImage = inGameImage;
	}


	public Image getInGameImage() {
		return inGameImage;
	}
}
