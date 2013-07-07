package Inventory;

import java.util.HashSet;

import org.newdawn.slick.Image;

import person.Criminal;
import person.Person;

public class Backpack extends InventoryObject {

	private Image inGameImage;
	public HashSet<InventoryObject> items;
	public InventoryObject[][] itemLayout;
	public int backpackPositionX;
	public int backpackPositionY;
	public int backpackMouseX;
	public int backpackMouseY;
	
	public int hoverMouseX;
	public int hoverMouseY;
	
	public boolean backpackMouseDown;
	
	public boolean currentHighligtedItem = false;;
	public int highlightedItemX;
	public int highlightedItemY;
	
	public boolean targetHighligtedItem = false;;
	public int targetItemX;
	public int targetItemY;
	
	public GroundObjects groundObjects;
	public Person owner;
	
	boolean processedClick = false;
	
	public int hightlightCount = 0;
	
	int currSize, maxSize;
	
	public Backpack(String name, String description, Image inventoryImage, Image inGameImage, int size, int maxSize, int layoutWidth, int layoutHeight, GroundObjects groundObjects, Person owner) {
		super(name, description, inventoryImage, size);
		this.setInGameImage(inGameImage);
		this.groundObjects = groundObjects;
		this.owner = owner;
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
		
		if(mouseClick && !processedClick){
			backpackMouseX = (int) Math.floor((xPos - backpackPositionX)/50);
			backpackMouseY = (int) Math.floor((yPos - backpackPositionY)/50);
			
			//System.out.println("bpkMs: " + (xPos - backpackPositionX)/50 + " backpackMouseX:" + backpackMouseX);
			
			//outside backpack
			if(backpackMouseX >= itemLayout.length || backpackMouseX < 0 || backpackMouseY >= itemLayout[0].length || backpackMouseY < 0){
				if(currentHighligtedItem && itemLayout[targetItemX][targetItemY] != null){
					System.out.println("transferInventoryItem: " + itemLayout[targetItemX][targetItemY].name);
					if(!owner.transferInventoryItem(itemLayout[targetItemX][targetItemY], xPos, yPos)){
						System.out.println("dropFromBackpack");
						dropFromBackpack(itemLayout[targetItemX][targetItemY], targetItemX, targetItemY);
						currentHighligtedItem = false;
					} else {
						removeFromBackpack(itemLayout[targetItemX][targetItemY], targetItemX, targetItemY);
						currentHighligtedItem = false;
						processedClick = true;
					}
				}
				return;
			}
			
			if(currentHighligtedItem == false){
				if(itemLayout[backpackMouseX][backpackMouseY] != null){
					currentHighligtedItem = true;
					highlightedItemX = backpackMouseX;
					highlightedItemY = backpackMouseY;
				}
				
			} else {
				//System.out.println("highlightedItemX: " + highlightedItemX + "highlightedItemY: " + highlightedItemY);
				//System.out.println("targetItemX: " + backpackMouseX + "targetItemY: " + backpackMouseY);
				if(highlightedItemX != backpackMouseX || highlightedItemY != backpackMouseY){
					targetHighligtedItem = true;
					targetItemX = backpackMouseX;
					targetItemY = backpackMouseY;
				}
			}
			
			if(targetHighligtedItem && currentHighligtedItem){
				InventoryObject targetItem = removeFromBackpack(itemLayout[targetItemX][targetItemY], targetItemX, targetItemY);
				addToBackpack(removeFromBackpack(itemLayout[highlightedItemX][highlightedItemY], highlightedItemX, highlightedItemY), targetItemX, targetItemY);
				if(targetItem != null){
					addToBackpack(targetItem ,highlightedItemX, highlightedItemY);
				}
				currentHighligtedItem = false;
				targetHighligtedItem = false;
			}
		} else {
			if(hoverMouseX == Math.round((xPos - backpackPositionX)/50) && hoverMouseY == Math.round((yPos - backpackPositionY)/50)){
				hightlightCount++;
			} else {
				hightlightCount = 0;
			}
			hoverMouseX = Math.round((xPos - backpackPositionX)/50);
			hoverMouseY = Math.round((yPos - backpackPositionY)/50);
			//System.out.println(hightlightCount);
		}
		
		
		if(mouseClick){
			hightlightCount = 0;
			processedClick = true;
		} else {
			processedClick = false;
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
	
	public void dropFromBackpack(InventoryObject io, int layoutX, int layoutY) {
		if(itemLayout[layoutX][layoutY] == null){
			return;
		}else{
			itemLayout[layoutX][layoutY] = null;
			currSize -= io.size;
			groundObjects.addToGround(io, (int)owner.Xpos, (int)owner.Ypos);
		}
	}
	
	public boolean addToBackpack(InventoryObject io, int layoutX, int layoutY) {
		if(io != null){
			if (currSize + io.size > maxSize) {
				System.out.println(name + " addToBackpack false1 currSize:" + currSize + " io.size:" + io.size + " maxSize:" + maxSize);
				return false;
			} else if(layoutX < itemLayout.length && layoutY < itemLayout[0].length) {
				System.out.println("layoutX: " + layoutX + "layoutY: " + layoutY);
				System.out.println("itemLayout[0].length: " + itemLayout[0].length + "itemLayout.length: " + itemLayout.length);
				if(itemLayout[layoutX][layoutY] == null){
					itemLayout[layoutX][layoutY] = io;
					items.add(io);
					currSize += io.size;
					System.out.println(name + " addToBackpack true");
					return true;
				}else{
					System.out.println(name + " addToBackpack false2");
					return false;
				}
			} else {
				System.out.println(name + " addToBackpack false3");
				return false;
			}
		} else {
			System.out.println(name + " addToBackpack false4");
			return false;
		}
	}


	public void setInGameImage(Image inGameImage) {
		this.inGameImage = inGameImage;
	}


	public Image getInGameImage() {
		return inGameImage;
	}

	public boolean handleItemTransfer(InventoryObject item, int xPos, int yPos) {
		backpackMouseX = Math.round((xPos - backpackPositionX)/50);
		backpackMouseY = Math.round((yPos - backpackPositionY)/50);
		
		//outside backpack
		if(backpackMouseX >= itemLayout.length || backpackMouseX < 0 || backpackMouseY >= itemLayout[0].length || backpackMouseY < 0){
			System.out.println(name + " handleItemTransfer false1");
			return false;
		}
		
		if(addToBackpack(item ,backpackMouseX, backpackMouseY)){
			processedClick = true;
			return true;
		} else {
			System.out.println(name + " handleItemTransfer false2");
			return false;
		}

	}
}
