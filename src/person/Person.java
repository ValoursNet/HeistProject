package person;

import java.awt.Point;
import java.util.Random;

import org.newdawn.slick.Image;

import Inventory.Backpack;
import Inventory.Gun;
import Inventory.InventoryObject;

import server.Multiplayer;

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
	public Backpack backpack;
	public Backpack holster;
	   
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
			currentRotation = (float) (currentRotation + 0.1);
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
		
	}
	
	public void handleInventoryMouseInput(int xPos, int yPos, boolean mouseClick){
		this.backpack.handleMouseInput(xPos, yPos, mouseClick);
		this.holster.handleMouseInput(xPos, yPos, mouseClick);
	}
	
	public boolean transferInventoryItem(InventoryObject item, int xPos, int yPos){
		if(!this.backpack.handleItemTransfer(item, xPos, yPos)){
			return true;
		} else {
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
		
		System.out.println("following target");
		
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
		
		if(movingDown)ySpeed = ySpeed + currentSpeed;
		if(movingUp)ySpeed = ySpeed - currentSpeed;
		
		if(movingRight)xSpeed = xSpeed + currentSpeed;
		if(movingLeft)xSpeed = xSpeed - currentSpeed;
		
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
