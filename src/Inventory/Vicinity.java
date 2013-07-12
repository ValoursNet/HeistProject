package Inventory;

import java.util.HashSet;

import javagame.Bullet;

import org.newdawn.slick.Image;

import person.Person;

public class Vicinity extends InventoryObject {

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
	
	//public boolean targetHighligtedItem = false;;
	//public int targetItemX;
	//public int targetItemY;
	
	public GroundObjects groundObjects;
	public Person owner;
	
	boolean processedClick = false;
	
	public int hightlightCount = 0;
	
	int currSize, maxSize;
	
	int nextAvailableSlot = 0;
	
	public Vicinity(String name, String description, Image inventoryImage, Image inGameImage, int size, int maxSize, int layoutWidth, int layoutHeight, GroundObjects groundObjects, Person owner) {
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
				if(currentHighligtedItem && itemLayout[highlightedItemX][highlightedItemY] != null){
					System.out.println("transferInventoryItem: " + itemLayout[highlightedItemX][highlightedItemY].name);
					if(!owner.transferInventoryItem(itemLayout[highlightedItemX][highlightedItemY], xPos, yPos)){
						System.out.println("oldDropFromBackpack");
						
						/*
						if(!owner.swapInventoryItem(itemLayout[highlightedItemX][highlightedItemY], xPos, yPos, highlightedItemX, highlightedItemY, this)){
							System.out.println("newDropFromBackpack");
							if(!owner.testDropInventoryItem(itemLayout[highlightedItemX][highlightedItemY], xPos, yPos)){
								dropFromBackpack(itemLayout[highlightedItemX][highlightedItemY], highlightedItemX, highlightedItemY);
							} else {
								System.out.println("!!!!!!!!!SOMETHING FUCKED UP!!!!!!!!!!!!!!!");
							}
							//testDropInventoryItem
							//test drop
						}
						*/
						
						//dropFromBackpack(itemLayout[highlightedItemX][highlightedItemY], highlightedItemX, highlightedItemY);
						currentHighligtedItem = false;
					} else {
						removeFromBackpack(itemLayout[highlightedItemX][highlightedItemY], highlightedItemX, highlightedItemY);
						//removeItem(itemLayout[highlightedItemX][highlightedItemY]);
						currentHighligtedItem = false;
						processedClick = true;
					}
				} else {
					System.out.println("currentHighligtedItem = false;: ");
					currentHighligtedItem = false;
				}
				return;
			}
			
			if(currentHighligtedItem == false){
				if(itemLayout[backpackMouseX][backpackMouseY] != null){
					currentHighligtedItem = true;
					highlightedItemX = backpackMouseX;
					highlightedItemY = backpackMouseY;
					System.out.println("currentHighligtedItem: " + itemLayout[backpackMouseX][backpackMouseY].name);
				}
				
			} 
			/*
			else {
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
			*/
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
		
		//removeItem(io);
		
		if(groundObjects.items.contains(io)){
			System.out.println("removeFromGround");
			groundObjects.removeFromGround(io);
		}
		
		if(items.contains(io)){
			nextAvailableSlot = nextAvailableSlot - io.layoutWidth;
			items.remove(io);
			updateItemLayout();
		} 
		
		
		if(itemLayout[layoutX][layoutY] == null){
			return null;
		}else{
			itemLayout[layoutX][layoutY] = null;
			currSize -= io.size;
			return io;
		}
	}
	
	
	/*
	public void dropFromBackpack(InventoryObject io, int layoutX, int layoutY) {
		if(itemLayout[layoutX][layoutY] == null){
			return;
		}else{
			itemLayout[layoutX][layoutY] = null;
			currSize -= io.size;
			groundObjects.addToGround(io, (int)owner.Xpos, (int)owner.Ypos);
		}
	}
	*/
	
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

	/*
	public boolean swapToBackpack(InventoryObject io, int layoutX, int layoutY, int previousItemX, int previousItemY, Vicinity previousContainer){
		boolean attempt = addToBackpack(io, layoutX, layoutY);
		if(attempt){
			return true;
		} else {
			
			InventoryObject outgoingItem = removeFromBackpack(itemLayout[layoutX][layoutY], layoutX, layoutY);
			InventoryObject incomingItem = previousContainer.removeFromBackpack( previousContainer.itemLayout[previousItemX][previousItemY], previousItemX, previousItemY);
			
			if(addToBackpack(incomingItem ,layoutX, layoutY)){
				if(previousContainer.addToBackpack(outgoingItem ,previousItemX, previousItemY)){
					return true;
				} else {
					removeFromBackpack(itemLayout[layoutX][layoutY], layoutX, layoutY);
				}
			} else {
				
				addToBackpack(outgoingItem, layoutX, layoutY);
				previousContainer.addToBackpack(incomingItem, previousItemX, previousItemY);
				
				return false;
			}
			return false;
		}
	}
	 */
	
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
	
	public InventoryObject removeItem(InventoryObject io) {
		
		//System.out.println("trying to remove");
		
		if(items.contains(io)){
			System.out.println("actually removing item");
			nextAvailableSlot = nextAvailableSlot - io.layoutWidth;
			items.remove(io);
			updateItemLayout();
			return io;
		} else {
			return null;
		}
	}
	
	public void updateItemLayout(){
		int itemSlotCounter = 0;
		
		for(int i=0; i<itemLayout.length; i++){
			itemLayout[i][0] = null;
		}
		
		synchronized (items) {  
			for (InventoryObject io : items) {
				itemLayout[itemSlotCounter][0] = io;
				itemSlotCounter = itemSlotCounter + io.layoutWidth;
			}
		}
	}

	public void addItem(InventoryObject io) {
		//public boolean addToBackpack(InventoryObject io, int layoutX, int layoutY) {
		if(io != null){
			if(!items.contains(io)){
				if(itemLayout[nextAvailableSlot][0] == null){
					itemLayout[nextAvailableSlot][0] = io;
					nextAvailableSlot = nextAvailableSlot + io.layoutWidth;
					items.add(io);
					currSize += io.size;
					System.out.println(name + " addItem true");
				}else{
					System.out.println(name + " SOMETHING WENT HORRIBLY WRONG !!!!!!!");
				}
			}
		}
	}
	
	/*
	//return false when outside backpack
	public boolean testDropItem(InventoryObject item, int xPos, int yPos) {
		backpackMouseX = Math.round((xPos - backpackPositionX)/50);
		backpackMouseY = Math.round((yPos - backpackPositionY)/50);
		
		//outside backpack
		if(backpackMouseX >= itemLayout.length || backpackMouseX < 0 || backpackMouseY >= itemLayout[0].length || backpackMouseY < 0){
			System.out.println(name + " handleItemTransfer false1");
			return false;
		}

		return true;
	}
	*/
	/*
	public boolean handleItemSwap(InventoryObject item, int xPos, int yPos, int previousItemX, int previousItemY, Vicinity previousContainer) {
		backpackMouseX = Math.round((xPos - backpackPositionX)/50);
		backpackMouseY = Math.round((yPos - backpackPositionY)/50);
		
		//outside backpack
		if(backpackMouseX >= itemLayout.length || backpackMouseX < 0 || backpackMouseY >= itemLayout[0].length || backpackMouseY < 0){
			System.out.println(name + " handleItemSwap false1");
			return false;
		}
		
		if(swapToBackpack(item ,backpackMouseX, backpackMouseY, previousItemX, previousItemY, previousContainer)){
			processedClick = true;
			return true;
		} else {
			System.out.println(name + " handleItemTransfer false2");
			return false;
		}

	}
	*/
}
