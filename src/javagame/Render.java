package javagame;

import java.awt.Point;
import java.util.HashSet;

import map.Building;
import map.Room;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import Inventory.Backpack;
import Inventory.InventoryObject;
import Inventory.Vicinity;

import person.Person;

public class Render {

	Map levelOne;
	Building buildingOne;
	boolean drawDebug = false;

	Image player;
	Image wall;
	Image cop;
	Image glockShoot;
	Image glockAimed;
	Image G9mmCasing;
	Image blood;

	public float offsetX = 0;
	public float offsetY = 0;
	
	Projectiles projObj;
	
	Color[] colours;

	long lastTimeInMillis = System.currentTimeMillis();
	
	SpriteSheet sheet;
	Animation glockShootAnim;

	public Render(){
		createColours();
	}
	
	public void setMap(Map levelOne) {
		this.levelOne = levelOne;
	}

	public void setProjectiles(Projectiles projObj) {
		this.projObj = projObj;
	}

	public void setImages(Image player, Image wall, Image cop, Image glockShoot, Image glockAimed, Image G9mmCasing, Image blood) {
		this.player = player;
		this.cop = cop;
		this.wall = wall;
		this.glockShoot = glockShoot;
		this.glockAimed = glockAimed;
		this.G9mmCasing = G9mmCasing;
		this.blood = blood;
		
		System.out.println("glockShoot: " + glockShoot);
		
		createAnimation();
	} 
	
	public void createAnimation(){
		sheet = new SpriteSheet(glockShoot,54,87);
		glockShootAnim = new Animation();
		glockShootAnim.setAutoUpdate(true);
		
		
		//int tX = 0;
		//int tY = 0;
		for(int col=0;col<3;col++){
			//tX = tX + 36;
			//glockShoot.addFrame(frame, duration)
			glockShootAnim.addFrame(sheet.getSprite(col,0), (1000/24));
			//System.out.println("Tx: "+tX+", Ty: "+tY);
		}
	}
	
	private void createColours(){
		/*
		 COLORS
		 Color(143, 147, 120);
		 Color(133, 133, 133);
		 Color(204, 204, 204);
		 Color(60, 60, 60);
		 Color(80, 209, 56);
		 Color(220, 46, 46);
		*/
		
		colours = new Color[6];
		colours[0] = new  Color(143, 147, 120);
		colours[1] = new  Color(133, 133, 133);
		colours[2] = new  Color(204, 204, 204);
		colours[3] = new  Color(60, 60, 60);
		colours[4] = new  Color(80, 209, 56);
		colours[5] = new  Color(220, 46, 46);;
	}
	
	/*
	private void showGroundObjects(Person person, Graphics g, int invContainerX, int invContainerY){
		double backpackPositionX = person.Xpos + invContainerX;
		double backpackPositionY = person.Ypos + invContainerY;
		
		synchronized (levelOne.groundObjects.items) {  
			for (InventoryObject groundObject : levelOne.groundObjects.items) {
				Double distance = Math.sqrt((groundObject.groundPositionX-person.Xpos)*(groundObject.groundPositionX-person.Xpos) + (groundObject.groundPositionY-person.Ypos)*(groundObject.groundPositionY-person.Ypos));
				System.out.println("distance: "+ distance);
				
				if(distance <= 60){
					g.drawImage(groundObject.inventoryImage, (int)backpackPositionX,  (int)backpackPositionY);
				}
				
			}
		}
		//System.out.println("person.Xpos: "+ person.Xpos + "person.Ypos: "+ person.Ypos);
	}
	*/
	
	private void drawVicinityContainer(Vicinity invContainer, Person person, Graphics g, int invContainerX, int invContainerY){
		if(invContainer != null){
			double backpackPositionX = person.Xpos + invContainerX - invContainer.getInGameImage().getWidth()/2;
			double backpackPositionY = person.Ypos + invContainerY - invContainer.getInGameImage().getHeight()/2;
			double itemOffsetX = 130;
			invContainer.backpackPositionX = (int) (backpackPositionX + itemOffsetX + offsetX);
			invContainer.backpackPositionY = (int) (backpackPositionY + offsetY);
			
			
			g.setColor(new Color(133, 133, 133, 200) );
			g.fillRect((float)backpackPositionX,(float) backpackPositionY, (float)invContainer.getInGameImage().getWidth(), (float)invContainer.getInGameImage().getHeight());
			g.setColor(new Color(255, 255, 255, 255) );
			g.drawString("VICINITY", (int)backpackPositionX, (int)backpackPositionY-20);
			g.drawImage(invContainer.getInGameImage(), (int)backpackPositionX,  (int)backpackPositionY);
			
			 for (int row = 0; row < invContainer.itemLayout[0].length; row ++){
		            for (int col = 0; col < invContainer.itemLayout.length; col++){
		            	InventoryObject item = invContainer.itemLayout[col][row];
		            	if(item != null && item.inventoryImage != null){
		            		g.drawImage(item.inventoryImage, (float) ((int) backpackPositionX + itemOffsetX + 5 + col*50), (int) backpackPositionY + 5 + row*50);
		            		
		            		if(!item.overlayText().equals("")){
								g.setColor(Color.white);
								g.drawString(item.overlayText(), (float) ((int) backpackPositionX + itemOffsetX + 30 + col*50),(int) backpackPositionY + 35 + row*50);
							}
		            	}
		            }
			 }
			 
			 ///System.out.println("invContainer.currentHighligtedItem:" + invContainer.currentHighligtedItem);
			
			 g.setColor(new Color(220, 220, 220, 133) );
			 int hightlightCol = (int) (invContainer.highlightedItemX*50 +  backpackPositionX + itemOffsetX + 2);
			 int hightlightRow = (int) (invContainer.highlightedItemY*50 +  backpackPositionY + 2);
			 if( hightlightCol >= 0 && hightlightRow >= 0 && invContainer.currentHighligtedItem){
				// System.out.println(invContainer.name);
				 g.fillRect(hightlightCol, hightlightRow, 50, 50);
			 }
			 
			
			
			 
			 //Hover information
			 for (int row = 0; row < invContainer.itemLayout[0].length; row ++){
		            for (int col = 0; col < invContainer.itemLayout.length; col++){
		            	InventoryObject item = invContainer.itemLayout[col][row];
		            	if(item != null && item.inventoryImage != null){
		            		if(!item.name.equals("") && col == invContainer.hoverMouseX && row == invContainer.hoverMouseY){
			            		 g.setColor(Color.black);
								 g.fillRect((float) ((int) backpackPositionX + itemOffsetX  + col*50), (int) backpackPositionY + 5 + row*50, 90, 20);
								 g.setColor(Color.white);
								 g.drawString(item.name, (float) ((int) backpackPositionX + itemOffsetX + 2 + col*50),(int) (int) backpackPositionY + 5 + row*50);
		            		}
		            		
		            		/*
		            		if(!item.description.equals("") && invContainer.hightlightCount > 50 && col == invContainer.hoverMouseX && row == invContainer.hoverMouseY){
			            		 g.setColor(Color.black);
								 g.fillRect((float) ((int) backpackPositionX + itemOffsetX  + col*50), (int) backpackPositionY + 25 + row*50, 120, 20);
								 g.setColor(Color.white);
								 g.drawString(item.description, (float) ((int) backpackPositionX +itemOffsetX + 2 + col*50),(int) (int) backpackPositionY + 25 + row*50);
		            		}
		            		*/
		            	}
		            }
			 }
			 
		}
	}
	
	private void drawInventoryContainer(Backpack invContainer, Person person, Graphics g, int invContainerX, int invContainerY, int itemOffsetX, int itemOffsetY){
		if(invContainer != null){
			double backpackPositionX = person.Xpos + invContainerX - invContainer.getInGameImage().getWidth()/2;
			double backpackPositionY = person.Ypos + invContainerY - invContainer.getInGameImage().getHeight()/2;;
			invContainer.backpackPositionX = (int) (backpackPositionX + itemOffsetX + offsetX);
			invContainer.backpackPositionY = (int) (backpackPositionY + itemOffsetY + offsetY);
			
			g.setColor(new Color(133, 133, 133, 200) );
			g.fillRect((float)backpackPositionX,(float) backpackPositionY, (float)invContainer.getInGameImage().getWidth(), (float)invContainer.getInGameImage().getHeight());
			g.setColor(new Color(255, 255, 255, 255) );
			g.drawString(invContainer.name.toUpperCase(), (int)backpackPositionX, (int)backpackPositionY-20);
			g.drawImage(invContainer.getInGameImage(), (int)backpackPositionX,  (int)backpackPositionY);
			
			 for (int row = 0; row < invContainer.itemLayout[0].length; row ++){
		            for (int col = 0; col < invContainer.itemLayout.length; col++){
		            	InventoryObject item = invContainer.itemLayout[col][row];
		            	if(item != null && item.inventoryImage != null){
		            		g.drawImage(item.inventoryImage, (int) backpackPositionX + itemOffsetX + 5 + col*50, (int) backpackPositionY + itemOffsetY + 5 + row*50);
		            		
		            		if(!item.overlayText().equals("")){
								g.setColor(Color.white);
								g.drawString(item.overlayText(), (int) backpackPositionX + itemOffsetX + 30 + col*50,(int) backpackPositionY + itemOffsetY + 35 + row*50);
							}
		            	}
		            }
			 }
			 
			 
			 g.setColor(new Color(220, 220, 220, 133) );
			 int hightlightCol = (int) (invContainer.highlightedItemX*50 +  backpackPositionX + itemOffsetX + 2);
			 int hightlightRow = (int) (invContainer.highlightedItemY*50 +  backpackPositionY + itemOffsetY + 2);
			 if( hightlightCol >= 0 && hightlightRow >= 0 && invContainer.currentHighligtedItem){
				// System.out.println(invContainer.name);
				 g.fillRect(hightlightCol, hightlightRow, 50, 50);
			 }
			 
			 //Hover information
			 for (int row = 0; row < invContainer.itemLayout[0].length; row ++){
		            for (int col = 0; col < invContainer.itemLayout.length; col++){
		            	InventoryObject item = invContainer.itemLayout[col][row];
		            	if(item != null && item.inventoryImage != null){
		            		if(!item.name.equals("") && col == invContainer.hoverMouseX && row == invContainer.hoverMouseY){
			            		 g.setColor(Color.black);
								 g.fillRect((int) backpackPositionX + itemOffsetX  + col*50, (int) backpackPositionY + itemOffsetY + 5 + row*50, 90, 20);
								 g.setColor(Color.white);
								 g.drawString(item.name, (int) backpackPositionX + itemOffsetX + 2 + col*50,(int) (int) backpackPositionY + itemOffsetY + 5 + row*50);
		            		}
		            		
		            		/*
		            		if(!item.description.equals("") && invContainer.hightlightCount > 50 && col == invContainer.hoverMouseX && row == invContainer.hoverMouseY){
			            		 g.setColor(Color.black);
								 g.fillRect((int) backpackPositionX + itemOffsetX + col*50, (int) backpackPositionY + itemOffsetY + 25 + row*50, 120, 20);
								 g.setColor(Color.white);
								 g.drawString(item.description, (int) backpackPositionX + itemOffsetX + 2 + col*50,(int) (int) backpackPositionY + itemOffsetY + 25 + row*50);
		            		}
		            		*/
		            	}
		            }
			 }
			 
		}
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, Graphics g,
			float offsetX, float offsetY, HashSet<Person> people)
			throws SlickException {
		// float gX = (float) (1024/2 -Player.Xpos);
		// float gY = (float) (768/2 -Player.Ypos);
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		g.translate(offsetX, offsetY);
		
		//OLD COLORS
		/*
		Color cZero = new Color(33, 65, 104);
		Color cOne = new Color(27, 47, 72);
		Color cTwo = new Color(27, 107, 72);
		 */
		
		//g.setColor(colours[0]);
		//g.fillRect(0, 0, 1024, 768);

		if((System.currentTimeMillis()-lastTimeInMillis)*1000 < 60){
			System.out.println(projObj.bulletCollection.size());
		}
		
		//g.setColor(cTwo);
		//g.fillRect(0, 0, 1024, 768);

		g.setColor(colours[0]);
		
		drawMap(levelOne.getMap(), g, 0);

		g.setColor(colours[1]);
		drawMap(levelOne.getMap(), g, 1);

		g.setColor(colours[2]);
		drawMap(levelOne.getMap(), g, 2);
		
		g.setColor(colours[3]);
		drawMap(levelOne.getMap(), g, 3);
		
		g.setColor(colours[4]);
		drawMap(levelOne.getMap(), g, 4);
		
		g.setColor(colours[5]);
		drawMap(levelOne.getMap(), g, 5);

		/*if (!Player.isDead) {
			g.drawImage(player, (int) Player.Xpos - 39, (int) Player.Ypos - 72);
			player.setCenterOfRotation(39, 72);
			player.setRotation(Player.currentRotation);
		}

		if (!policeUnit.isDead) {
			g.drawImage(cop, (int) policeUnit.Xpos - 39,
					(int) policeUnit.Ypos - 72);
			cop.setCenterOfRotation(39, 72);
			cop.setRotation(policeUnit.currentRotation);
		}*/
		
		
		synchronized (projObj.casingCollection) {  
			for (Casing casing : projObj.casingCollection) {
				//Color bColor = new Color(255,253,149,230);
				//g.setColor(bColor);
				//g.setColor(new  Color(142, 142, 71));
				//if (!bullet.stopped) {
					g.pushTransform();
					g.rotate((int) casing.Xpos, (int) casing.Ypos, casing.randomRotation);
					g.drawImage(G9mmCasing, (float) casing.Xpos, (float) casing.Ypos);
					//g.fillRect((float) bullet.Xpos, (float) bullet.Ypos, (float) 30, (float) 1);
					g.popTransform();
				//}
			}
			projObj.update();
		}
		
		synchronized (levelOne.effectCollection) {  
			for (BloodSplat bloodSplat : levelOne.effectCollection.bloodCollection) {
					g.pushTransform();
					g.rotate((int) bloodSplat.Xpos, (int) bloodSplat.Ypos, bloodSplat.currentRotation);
					g.drawImage(blood, (float) bloodSplat.Xpos, (float) bloodSplat.Ypos);
					g.popTransform();
				//}
			}
			projObj.update();
		}
		
		//CURENTLY UNUSED
		//long timeInMillis = System.currentTimeMillis();
		//System.out.println("GroundObjs: " + levelOne.groundObjects.items.size());
		
		for( InventoryObject item : levelOne.groundObjects.items){
			g.drawImage(item.groundImage, item.groundPositionX,  item.groundPositionY);
			item.groundImage.setCenterOfRotation(0, 0);
			item.groundImage.setRotation(item.groundRotation);
		}
		
		for (Person person : people) {
			if (!person.isDead) {
				/* Draw the person. */
				//g.drawImage(person.image, (int) person.Xpos - 27, (int) person.Ypos - 45);
				
				//if(glockShoot != null){
				double personX = person.Xpos;
				double personY = person.Ypos;
				
					//g.drawImage(glockAimed, (int) personX - 3, (int) personY - 46);
					//g.drawImage(person.image, (int) personX - 3, (int) personY - 13);
				//	}
				//glockShoot
				
				//g.drawImage(glockShootAnim.getCurrentFrame(), (int) person.Xpos - 27, (int) person.Ypos - 70);
				
				
				g.pushTransform();
				g.rotate((int) personX, (int) personY, person.currentRotation);
				//g.drawImage(person.gun.idled, (int) personX - 3, (int) personY - 46);
				//g.drawImage(person.image, (int) personX - 27, (int) personY - 13);
				if(person.gun != null && person.gun.current != null){
					g.drawAnimation(person.gun.current, (int) personX - person.gun.currentRotationX, (int) personY - person.gun.currentRotationY);
				}
				g.popTransform();
				
				g.pushTransform();
				g.rotate((int) personX, (int) personY, person.bodyRotation);
				//g.drawImage(glockAimed, (int) personX - 3, (int) personY - 46);
				g.drawImage(person.image, (int) personX - 27, (int) personY - 13);
				//g.drawAnimation(glockShootAnim, (int) personX - 27, (int) personY - 70);
				g.popTransform();
				
				
				//g.drawAnimation(glockShootAnim, (int) person.Xpos - 27, (int) person.Ypos - 45);
				//glockShootAnim.getCurrentFrame().setCenterOfRotation(27, 47);
				//glockShootAnim.getCurrentFrame().setRotation(person.currentRotation);
				//glockShoot.
				//glockShoot.draw((int) person.Xpos - 27, (int) person.Ypos - 45);
				
				//glockShoot.getCurrentFrame().setCenterOfRotation(27, 72);
				//glockShoot.getCurrentFrame().setRotation(person.currentRotation);
				//glockAimed.setCenterOfRotation(3, 46);
				//glockAimed.setRotation(person.currentRotation);
				//person.image.setCenterOfRotation(27, 13);
				//person.image.setRotation(person.currentRotation);
				
				g.setColor(Color.blue);
				g.drawString(person.name,(float) person.Xpos-10,(float) person.Ypos-60);
				g.setColor(Color.red);
				g.drawLine((float) person.Xpos-20,(float) person.Ypos-70, (float) person.Xpos-20 +(person.health/2),(float) person.Ypos-70);
				
				//very temp reload "symbol"
				if(person.gun != null && person.gun.isLoaded() && person.gun.isEmptyMag()){
					g.drawLine((float) person.Xpos-5,(float) person.Ypos-5, (float) person.Xpos+5,(float) person.Ypos+5);
					g.drawLine((float) person.Xpos+5,(float) person.Ypos-5, (float) person.Xpos-5,(float) person.Ypos+5);
				}
				
				if (drawDebug) {
					if(person.type == 2){
						g.drawLine((float) person.Xpos,(float) person.Ypos, (float) person.leftX,(float) person.leftY);
						g.drawLine((float) person.Xpos,(float) person.Ypos, (float) person.rightX,(float) person.rightY);
						g.drawLine((float) person.leftX,(float) person.leftY, (float) person.rightX,(float) person.rightY);
					  
					}
				}
				
				if (person.inventoryOpen) {
					
					g.setColor(new Color(0, 0, 0, 200) );
					g.fillRect((float)0,(float) 0, (float)2000, (float)2000);
					
					
					drawInventoryContainer(person.backpack, person, g, 300, 0, 0, 0);
					drawInventoryContainer(person.holster, person, g, 100, 150, 0, 0);
					drawInventoryContainer(person.weaponSlot, person, g, -300, 150, 190, 13);
					
					drawVicinityContainer(person.vicinity, person, g, 0, -300);
					//showGroundObjects(person, g, -300, -300);
				}
				
				/* Render hit boxes if in debug mode. */
				if (drawDebug) {
					g.setColor(Color.red);
					g.fillRect((float) person.Xpos - 19, (float) person.Ypos - 22,
							(float) 40, (float) 40);
				}	
				/* Render lines of sight. */
				//if (drawDebug) {
					/*
					for (int i = 0; i < 30; i++) {
						drawSightlineCollision(person, g, levelOne.getMap(),
								person.currentRotation - 60 + (i * 4));
					}
					*/
					//drawSightline(person, g, person.currentRotation, Color.blue);
					//drawSightline(person, g, person.aimedRotation, Color.green);
				//}
			}
		}

		
		
		drawBuildingWalls(g, buildingOne);

		// drawSightlineCollision(Player,
		// g,levelOne.getMap(),Player.currentRotation);
		/*if (Player.isShooting
				&& (0.02 * (timeInMillis - lastTimeInMillis) >= 1)) {
			Person[] people = new Person[1];// = [policeUnit];
			people[0] = policeUnit;
			projObj.createProjectile(levelOne, Player.Xpos, Player.Ypos, 2,
					Player.currentRotation, people);
			Player.isShooting = false;
			lastTimeInMillis = timeInMillis;
		}*/
		
		synchronized (projObj.bulletCollection) {  
			for (Bullet bullet : projObj.bulletCollection) {
				Color bColor = new Color(255,253,149,230);
				g.setColor(bColor);
				//g.setColor(new  Color(142, 142, 71));
				if (!bullet.stopped) {
					g.pushTransform();
					g.rotate((int) bullet.Xpos, (int) bullet.Ypos, bullet.currentRotation-90);
					g.fillRect((float) bullet.Xpos, (float) bullet.Ypos, (float) 30, (float) 1);
					g.popTransform();
				}
			}
			projObj.update();
		}
		
	
	}
	
	private void drawBuildingWalls(Graphics g, Building buildingOne) {
		synchronized (buildingOne.wallCollection) {
			for (Rectangle wallRect : buildingOne.wallCollection) {
				//System.out.println("asd");
				g.fillRect(wallRect.getX(),wallRect.getY(),wallRect.getWidth(),wallRect.getHeight());
			}
		}
		drawHorizontalWalls(g,buildingOne);
		drawVerticalWalls(g,buildingOne);
	}
	
	private void drawHorizontalWalls(Graphics g, Building buildingOne){
		for (int i = 0; i < buildingOne.horizontalWallCollection.length; i++) {
			int[] currentWallLine = buildingOne.horizontalWallCollection[i];
			for (int j = 0; j < currentWallLine.length; j++) {
				int currentWall = currentWallLine[j];
				if(currentWall == 1){
					g.drawImage(buildingOne.interriorWallImage, j*100, i*100);
				}
			}
		}
	}
	
	private void drawVerticalWalls(Graphics g, Building buildingOne){
		Image rotatedWall = buildingOne.interriorWallImage.copy();
		rotatedWall.setCenterOfRotation(0, 0);
		rotatedWall.setRotation(90);
		for (int i = 0; i < buildingOne.verticalWallCollection.length; i++) {
			int[] currentWallLine = buildingOne.verticalWallCollection[i];
			for (int j = 0; j < currentWallLine.length; j++) {
				int currentWall = currentWallLine[j];
				if(currentWall == 1){
					g.drawImage(rotatedWall, i*100+13, j*100);
				}
			}
		}
	}
	
	private void drawRoom(Graphics g, Room roomOne) {
		/*
		g.fillRect(roomOne.positionX, roomOne.positionY, roomOne.width*roomOne.unitSize, roomOne.height*roomOne.unitSize);
		
		
		
		for (int i = 0; i < roomOne.width; i++) {
			int wallPositionX = roomOne.positionX + i*roomOne.unitSize;
			int wallPositionY = roomOne.positionY + roomOne.height*roomOne.unitSize;
			g.drawImage(roomOne.interriorWallImage, wallPositionX, roomOne.positionY);
			g.drawImage(roomOne.interriorWallImage, wallPositionX, wallPositionY);
			
		}
		
		Image rotatedWall = roomOne.interriorWallImage.copy();
		rotatedWall.setCenterOfRotation(0, 0);
		rotatedWall.setRotation(90);
		for (int j = 0; j < roomOne.height; j++) {
			int wallPositionX = roomOne.positionX + roomOne.width*roomOne.unitSize;
			int wallPositionY = roomOne.positionY + j*roomOne.unitSize;
			g.drawImage(rotatedWall, roomOne.positionX, wallPositionY);
			g.drawImage(rotatedWall, wallPositionX, wallPositionY);
		}
		*/
	}

	// Should be based on paramater map, not class defined levelOne
	void drawMap(int[][] mapArray, Graphics g, int drawNum) {
		
		try {
			g.drawImage(new Image("res/Mapv1.png"), (int) 540, (int) 420);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//new Image("res/Mapv1.png")
		//Mapv1.png
		
		int tSize = levelOne.tileSize;
		for (int i = 0; i < mapArray.length; i++) {
			for (int j = 0; j < mapArray[i].length; j++) {
				if (mapArray[i][j] == drawNum) {
					g.fillRect(j * tSize, i * tSize, tSize, tSize);
				}
			}
		}
		
	}

	// Should probably be combined with drawSightlineCollision
	void drawSightline(Person Player, Graphics g, double rotateAngle, Color gColor) {
		int lineLength = 500;
		double playerRotad = (rotateAngle - 90) * Math.PI / 180;
		float startX = (float) Player.Xpos;
		float startY = (float) Player.Ypos;
		float endX = (float) (startX + lineLength * Math.cos(playerRotad));
		float endY = (float) (startY + lineLength * Math.sin(playerRotad));

		g.setColor(gColor);
		g.drawLine(startX, startY, endX, endY);
	}

	// Should probably be combined with drawSightline
	void drawSightlineCollision(Person Player, Graphics g, int[][] mapArray,
			float gAngle) {
		int lineLength = 500;
		double playerRotad = (gAngle - 90) * Math.PI / 180;
		float startX = (float) Player.Xpos;
		float startY = (float) Player.Ypos;

		float endX = (float) (startX + 1 * Math.cos(playerRotad));
		float endY = (float) (startY + 1 * Math.sin(playerRotad));
		for (int i = 0; i < lineLength; i++) {
			endX = (float) (startX + i * Math.cos(playerRotad));
			endY = (float) (startY + i * Math.sin(playerRotad));

			Point iP = levelOne.getTileAtPoint(levelOne.tileSize, endX, endY);

			// Check if out of bounds
			if (mapArray.length <= iP.y || iP.y < 0) {
				break;
			}
			if (mapArray[0].length <= iP.x || iP.x < 0) {
				break;
			}
			// Check for collision with solid tile
			if (mapArray[iP.y][iP.x] == 1) {
				break;
			}
		}

		g.setColor(Color.red);
		g.drawLine(startX, startY, endX, endY);
	}

	public void setBuilding(Building buildingOne) {
		this.buildingOne = buildingOne;
	}
}
