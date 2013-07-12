package person;

import java.awt.Point;
import java.util.HashSet;
import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import Inventory.Backpack;
import Inventory.Gun;
import Inventory.InventoryObject;
import Inventory.Magasine;
import Inventory.Vicinity;

import server.Multiplayer;

import javagame.Bullet;
import javagame.Map;

@SuppressWarnings("unused")
public class Person {
	
	Map levelOne;
	public Gun gun = null;
	public int type = 0;
	public Image image;
	//TODO get IDs from server
	public int id = (int) Math.round(Math.random()*1000);
	
	public int packetId = 0;
	
	public double Xpos = 100;
	public double Ypos = 300;
	public double ySpeed = 0;
	public double xSpeed = 0;
	public double yForce = 0;
	public double xForce = 0;
	double currentSpeed = 0.6;
	public Float currentRotation = (float) 0;
	public Float bodyRotation = (float) 0;
	public Float aimedRotation = (float) 0;
	
	public double friction = 0.8;
	
	public int health = 100;
	
	public double width = 40;
	public double height = 40;
	
	public double offsetX = 0;
	public double offsetY = 0;
	
	public boolean directControl = false;
	
	public double[][] path = new double[3][3];
	
	public int currentPathCount = 0;
	
	long lastTimeInMillis = System.currentTimeMillis();
	
	int dirNum = 0;
	int dirCount = 0;
	
	public boolean movingUp,movingDown,movingLeft,movingRight = false;	
	
	public boolean inventoryOpen = false;
	
	public boolean isShooting = false;
	
	public String name = generateName();
	
	public boolean isDead = false;
	
	
	//Inventory containers also need adding to: handleInventoryMouseInput();
	public Vicinity vicinity;
	public Backpack backpack;
	public Backpack holster;
	public Backpack weaponSlot;
	   
    public int leftX;
    public int leftY;
    public int rightX;
    public int rightY;
	
    public boolean hasTarget = true;
    public Person myTarget;
	
	public void update() {
		
		//checkDirection();
		
		if(levelOne != null){
			//collisionCheck();
			updatePosition(levelOne.getMap());
			
			double gunTurnSpeed = 0.5;
			double bodyTurnSpeed = 0.2;
			
			
			//GUN ROTATION
			if((currentRotation - aimedRotation)<-180){
				//currentRotation = currentRotation + 360;
				System.out.println("bam 1");
			}
			
			if((currentRotation - aimedRotation)>180){
				//currentRotation = currentRotation - 360;
				System.out.println("bam 2");
			}
			
			if(currentRotation+gunTurnSpeed < aimedRotation){
				currentRotation = (float) (currentRotation + gunTurnSpeed);
			} else if(currentRotation-gunTurnSpeed > aimedRotation){
				currentRotation = (float) (currentRotation - gunTurnSpeed);
			} else {
				currentRotation = (float) (aimedRotation);
			}
			
			
			//BODY ROTATION
			if((bodyRotation - aimedRotation)<-180){
				//bodyRotation = bodyRotation + 360;
			}
			
			if((bodyRotation - aimedRotation)>180){
				//bodyRotation = bodyRotation - 360;
			}
			
			if(bodyRotation+bodyTurnSpeed < aimedRotation){
				bodyRotation = (float) (bodyRotation + bodyTurnSpeed);
			} else if(bodyRotation-bodyTurnSpeed > aimedRotation){
				bodyRotation = (float) (bodyRotation - bodyTurnSpeed);
			} else {
				bodyRotation = (float) (aimedRotation);
			}
			
			
			if(!isDead){
				System.out.println("");
				System.out.println("(currentRotation - aimedRotation):" + (currentRotation - aimedRotation));
				System.out.println("(bodyRotation - currentRotation):" + (bodyRotation - currentRotation));
			}
			
			if((bodyRotation - currentRotation)<-70){
				currentRotation = bodyRotation + 70;
			}
			
			if((bodyRotation - currentRotation)>70){
				currentRotation = bodyRotation - 70;
			}
		}
		
		if(directControl){
			offsetX = 1024/2 -Xpos;
			offsetY = 768/2 -Ypos;
		}
		
		if(health <= 0){
			isDead = true;
		}
		
		if(isShooting && gun != null && !gun.equals(null)) {
			gun.fire();
		}
		
		if(vicinity != null){
			updateVicinity();
		}
		
	}
	
	public void updateVicinity(){
		synchronized (levelOne.groundObjects.items) {  
			for (InventoryObject groundObject : levelOne.groundObjects.items) {
				Double distance = Math.sqrt((groundObject.groundPositionX-Xpos)*(groundObject.groundPositionX-Xpos) + (groundObject.groundPositionY-Ypos)*(groundObject.groundPositionY-Ypos));
				//System.out.println("distance: "+ distance);
				
				if(distance <= 60){
					vicinity.addItem(groundObject);
					//g.drawImage(groundObject.inventoryImage, (int)backpackPositionX,  (int)backpackPositionY);
				} else {
					//System.out.println("groundObject: " + groundObject.name);
					vicinity.removeItem(groundObject);
				}
				
			}
		}
			//System.out.println("person.Xpos: "+ person.Xpos + "person.Ypos: "+ person.Ypos);
		
	}
	
	public Magasine getBestMagasine(){
		HashSet<Magasine> returnCollection = new HashSet<Magasine>();
		
		Magasine bestMag = null;
		
		//returnCollection.addAll(getAllMagasines(backpack.items, "Stanag"));
		returnCollection.addAll(getAllMagasines(weaponSlot.items, "Stanag"));
		
		int HighestAmmo = 0;
		
		synchronized (returnCollection) {  
			for (Magasine item : returnCollection) {
				if(item.currBullets > HighestAmmo){
					HighestAmmo = item.currBullets;
					//returnCollection.add(item);
				}
			}
		}
		
		//System.out.println("returnCollection:" + returnCollection.size());
		
		synchronized (returnCollection) {  
			for (Magasine item : returnCollection) {
				if(item.currBullets < HighestAmmo){
					//returnCollection.remove(item);
				} else {
					bestMag=item;
				}
			}
		}
		
		//System.out.println("bestMag:" + bestMag.name);
		
		return bestMag;
		
	}
	
	public HashSet<Magasine> getAllMagasines(HashSet<InventoryObject> items, String MagType){
		
		HashSet<InventoryObject> itemCollection = new HashSet<InventoryObject>();
		HashSet<Magasine> returnCollection = new HashSet<Magasine>();
		
		synchronized (items) {  
			for (InventoryObject item : items) {
				if(item.objectType.equals("Mag")){
					returnCollection.add((Magasine) item);
				} else {
					System.out.println("objectType:" + item.objectType);
				}
			}
		}
		
		//System.out.println("items:" + items.size());
		//System.out.println("returnCollection:" + returnCollection.size());
		
		
		synchronized (returnCollection) {  
			for (Magasine mag : returnCollection) {
				if(mag.magType == null){
					returnCollection.remove(mag);
				} else {
					if(mag.magType.equals(MagType)){
						//returnCollection.remove(mag);
					} else {
						returnCollection.remove(mag);
					}
				}
				
			}
		}
		
	
		return returnCollection;
	}
	
	
	public void handleInventoryMouseInput(int xPos, int yPos, boolean mouseClick){
		this.backpack.handleMouseInput(xPos, yPos, mouseClick);
		this.holster.handleMouseInput(xPos, yPos, mouseClick);
		this.weaponSlot.handleMouseInput(xPos, yPos, mouseClick);
		
		this.vicinity.handleMouseInput(xPos, yPos, mouseClick);
	}
	
	public boolean transferInventoryItem(InventoryObject item, int xPos, int yPos){
		
		System.out.println("transferInventoryItem");
		
		if(this.backpack.handleItemTransfer(item, xPos, yPos)){
			return true;
		} else if(this.holster.handleItemTransfer(item, xPos, yPos)){
			return true;
		} else if(this.weaponSlot.handleItemTransfer(item, xPos, yPos)){
			return true;
		} else if(this.vicinity.handleItemTransfer(item, xPos, yPos)){
			return true;
		} else {
			System.out.println("transferInventoryItem false");
			return false;
		}
	}
	
	public boolean swapInventoryItem(InventoryObject item, int xPos, int yPos, int previousItemX, int previousItemY, Backpack previousContainer){
		
		System.out.println("swapInventoryItem");
		
		if(this.backpack.handleItemSwap(item, xPos, yPos, previousItemX, previousItemY, previousContainer)){
			return true;
		} else if(this.holster.handleItemSwap(item, xPos, yPos, previousItemX, previousItemY, previousContainer)){
			return true;
		} else if(this.weaponSlot.handleItemSwap(item, xPos, yPos, previousItemX, previousItemY, previousContainer)){
			return true;
		} else {
			System.out.println("swapInventoryItem false");
			return false;
		}
	}
	
	//false when needs dropping
	public boolean testDropInventoryItem(InventoryObject item, int xPos, int yPos){
		
		System.out.println("testDropInventoryItem");
		
		if(this.backpack.testDropItem(item, xPos, yPos)){
			return true;
		} else if(this.holster.testDropItem(item, xPos, yPos)){
			return true;
		} else if(this.weaponSlot.testDropItem(item, xPos, yPos)){
			return true;
		} else {
			System.out.println("testDropInventoryItem false");
			return false;
		}
	}
	
	//ugly code for testing names
	protected String generateName() {
		Random r = new Random();
		char c = (char) (r.nextInt(26) + 'a');
		char d = (char) (r.nextInt(26) + 'a');
		char e = (char) (r.nextInt(26) + 'a');
		
		StringBuilder builder = new StringBuilder();
		
		builder.append(c);
		builder.append(d);
		builder.append(e);
		
		return builder.toString();
	}
	
	public void setPath(double[][] path){
		this.path = path;
		
		//System.out.println("path set: " + path[0][1]);
	}
	
	protected void basicAI(){
		if(!hasTarget){
			followPath();
		} else {
			if(myTarget != null && !myTarget.isDead){
				followTarget();
			} else {
				hasTarget = false;
			}
		}
	}
	
	protected void followTarget(){
		
		//System.out.println("following target");
		
		int currentTargetX = (int) myTarget.Xpos;
		int currentTargetY = (int) myTarget.Ypos;

		float xDistance = (float) (Xpos - (currentTargetX));
		float yDistance = (float) (Ypos - (currentTargetY));
		
		if(xDistance < 10 && xDistance > -10 && yDistance > -10 && yDistance < 10){
			Xpos = currentTargetX;
			Ypos = currentTargetY;
		}
		
		double angleToTurn = Math.toDegrees(Math.atan2(yDistance, xDistance));
		
		currentRotation = (float) angleToTurn - 90;
		
		currentSpeed = 0.6;
		xForce = (float) (currentSpeed * Math.cos(currentRotation));
		yForce = (float) (currentSpeed * Math.sin(currentRotation));
	}
	
	protected void followPath() {
		/*
		Xpos = 100;
		Ypos = 300;
		yForce = 0;
		xForce = 0;
		currentRotation = (float) 0;
		*/
		
		//path[currentPathCount];
		
		int currentTargetX = (int) path[currentPathCount][0];
		int currentTargetY = (int) path[currentPathCount][1];
		//TODO, add time to path
		double currentTargetT = path[currentPathCount][2];
		
		//System.out.println("xpos: " + Xpos);
		
		float xDistance = (float) (Xpos - (currentTargetX));
		float yDistance = (float) (Ypos - (currentTargetY));
		
		if(xDistance < 40 && xDistance > -40 && yDistance > -40 && yDistance < 40){
			nextPathCount();
			Xpos = currentTargetX;
			Ypos = currentTargetY;
		} else {
			//System.out.println(name + "'s  -  xDistance: " + xDistance + "'   yDistance: " + yDistance);
			//System.out.println(name + "'s  -  Ypos: " + Ypos + "'   currentTargetY: " + currentTargetY);
		}
		
		
		double angleToTurn = Math.toDegrees(Math.atan2(yDistance, xDistance));
		
		currentRotation = (float) angleToTurn - 90;
		
		currentSpeed = 0.4;
		xForce = (float) (currentSpeed * Math.cos(currentRotation));
		yForce = (float) (currentSpeed * Math.sin(currentRotation));
	}
	
	protected void nextPathCount() {
		//System.out.println(name + "'s  -  currentPathCount: " + currentPathCount);
		if(currentPathCount+1 < path.length){
			currentPathCount = currentPathCount+1;
		} else {
			currentPathCount = 0;
		}
	}
	
	//TODO delete
	//Ugly code for test movement
	protected void checkDirection() {
		dirCount++;
		if(dirCount >= 1000){
			dirNum = dirNum + 1;
			dirCount = 0;
			if(dirNum >= 4)dirNum=0;
		}
		
		if(dirNum == 0){
			movingUp = true;
			movingDown = false;
			movingLeft = false;
			movingRight = false;
		}
		if(dirNum == 1){
			movingUp = false;
			movingDown = true;
			movingLeft = false;
			movingRight = false;
		}
		if(dirNum == 2){
			movingUp = false;
			movingDown = false;
			movingLeft = true;
			movingRight = false;
		}
		if(dirNum == 3){
			movingUp = false;
			movingDown = false;
			movingLeft = false;
			movingRight = true;
		}
		
	}
	
	protected void updateX(int[][] mapArray, double currentXSpeed, double updateSpeed){
		float startX = (float) Xpos;
		float startY = (float) Ypos;
		
		float endX = startX;
		float endY = startY;
		
		float testX = endX;
		
		for (int i =0; i <= updateSpeed; i++) {
			//endX   = (float) (startX + i * currentXSpeed);
			testX   = (float) (startX + i * currentXSpeed);
			
			Point iP = levelOne.getTileAtPoint(levelOne.tileSize,testX,endY);
			
			//Check if out of bounds
			if(mapArray[0].length <= iP.x || iP.x < 0){
				break;
			}
			if(mapArray.length <= iP.y || iP.y < 0){
				break;
			}
			
			//Check for collision with solid tile
			if(mapArray[iP.y][iP.x] == 1){
				break;
			}
			
			//Check for collision with exit tile
			if(mapArray[iP.y][iP.x] == 5){
				collideExit();
			}
			
			endX   = testX;
		}
		
		Xpos = endX;
		Ypos = endY;
	}
	
	protected void updateY(int[][] mapArray, double currentYSpeed, double updateSpeed){
		float startX = (float) Xpos;
		float startY = (float) Ypos;
		
		float endX = startX;
		float endY = startY;
		
		float testY = endY;
	
		for (int i =0; i <= updateSpeed; i++) {
			testY   = (float) (startY + i * currentYSpeed);
			
			Point iP = levelOne.getTileAtPoint(levelOne.tileSize,endX,testY);
			
			//Check if out of bounds
			if(mapArray[0].length <= iP.x || iP.x < 0){
				break;
			}
			if(mapArray.length <= iP.y || iP.y < 0){
				break;
			}
			//Check for collision with solid tile
			if(mapArray[iP.y][iP.x] == 1){
				break;
			}
			
			//Check for collision with exit tile
			if(mapArray[iP.y][iP.x] == 5){
				collideExit();
			}
			
			endY = testY;
		}
		
		Xpos = endX;
		Ypos = endY;
	}
	
	private void collideExit() {
		// TODO Auto-generated method stub
		isDead = true;
	}

	private void calculateFriction(){
		//System.out.println("xForce: " + xForce);
		xForce = xForce * friction;
		yForce = yForce * friction;
		//if(xForce < 0.001 && xForce > -0.001)xForce=0;
		//if(yForce < 0.001 && yForce > -0.001)yForce=0;
	}
	
	protected void updatePosition(int[][] mapArray){
		
		ySpeed = 0;
		xSpeed = 0;
		
		xSpeed = xSpeed + xForce;
		ySpeed = ySpeed + yForce;
		
		calculateFriction();
		
		if(!inventoryOpen){
			if(movingDown)ySpeed = ySpeed + currentSpeed;
			if(movingUp)ySpeed = ySpeed - currentSpeed;
			
			if(movingRight)xSpeed = xSpeed + currentSpeed;
			if(movingLeft)xSpeed = xSpeed - currentSpeed;
		}
		
		long timeInMillis = System.currentTimeMillis();
		
		double updateXspeed = currentSpeed*(timeInMillis-lastTimeInMillis);
		double updateYspeed = currentSpeed*(timeInMillis-lastTimeInMillis);
		
		if(updateYspeed >= 1 || updateXspeed >= 1 || (ySpeed == 0 && xSpeed == 0)){
			lastTimeInMillis = timeInMillis;
		}
		
		updateX(mapArray,xSpeed,updateXspeed);
		updateY(mapArray,ySpeed,updateYspeed);
		
		//Works, but essentially just spaming it. Not cool.
		Multiplayer.people.add(this);
	}

	
	protected void collisionCheck(){
		double prevY = Ypos;
		double prevX = Xpos;
		
		long timeInMillis = System.currentTimeMillis();
		
		double currentSpeed = 0.2*(timeInMillis-lastTimeInMillis);
		
		lastTimeInMillis = timeInMillis;
		
		if(movingUp){
			Ypos = Ypos - currentSpeed;
		
			if(	levelOne.isColliding(Xpos,Ypos-22)||
				levelOne.isColliding(Xpos-19,Ypos-22)||	
				levelOne.isColliding(Xpos+21,Ypos-22)
			){
				Ypos = prevY;
			}
		}
		
		if(movingDown){
			Ypos = Ypos + currentSpeed;
		
			if(	levelOne.isColliding(Xpos,Ypos+18)||
				levelOne.isColliding(Xpos-19,Ypos+18)||	
				levelOne.isColliding(Xpos+21,Ypos+18)
			){
				Ypos = prevY;
			}
		}
		
		if(movingLeft){
			Xpos = Xpos - currentSpeed;
		
			if(	levelOne.isColliding(Xpos-19,Ypos)||
				levelOne.isColliding(Xpos-19,Ypos-22)||	
				levelOne.isColliding(Xpos-19,Ypos+18)
			){
				Xpos = prevX;
			}
		}
		
		if(movingRight){
			Xpos = Xpos + currentSpeed;
			
			if(	levelOne.isColliding(Xpos+21,Ypos)||
				levelOne.isColliding(Xpos+21,Ypos-22)||	
				levelOne.isColliding(Xpos+21,Ypos+18)
			){
				Xpos = prevX;
			}
		}
	}
}
