package javagame;

import java.awt.Point;
import person.Person;
import javagame.Play;

public class Casing {
	
	public Person shooter;
	public int holderID;
	Map levelOne;
	public double Xpos = 100;
	public double Ypos = 300;
	public double currentSpeed = 2;
	public float currentRotation = (float) 0;
	public float randomRotation = (float) (Math.random()*180);
	public double damage = 10;
	public double knockback = 1;
	
	private double slowRate = (Math.random()*0.08);
	
	public Boolean stopped = false;
	
	long lastTimeInMillis = System.currentTimeMillis();
	
	public Casing (Map level, double Xpos, double Ypos, double currentSpeed, float currentRotation, Person shooter){		
		this.Xpos = Xpos;
		this.Ypos = Ypos;
		this.currentSpeed = currentSpeed;
		this.currentRotation = (float) (currentRotation-25 + (Math.random()*50));
		this.levelOne = level;
		this.shooter = shooter;
		this.holderID = shooter.id;
	}
	
	public Casing (Map level, double Xpos, double Ypos, double currentSpeed, float currentRotation, int holderID){		
		this.Xpos = Xpos;
		this.Ypos = Ypos;
		this.currentSpeed = currentSpeed;
		this.currentRotation = (float) (currentRotation-45 + (Math.random()*90));
		this.levelOne = level;
		this.shooter = null;
		this.holderID = holderID;
	}
	
	void update(){
		if(!stopped){
			updatePosition(levelOne.mapCollisions);
		}
	}
	
	private void updatePosition(int[][] mapArray) {
		double currentRotad = (currentRotation-90) * Math.PI / 180;
		float startX = (float) Xpos;
		float startY = (float) Ypos;
		
		float endX = startX;
		float endY = startY;
		
		long timeInMillis = System.currentTimeMillis();
		
		currentSpeed = currentSpeed/(1.05 + slowRate);
		if(currentSpeed<0.01){
			stopped = true;
		}
		
		double updateSpeed = currentSpeed*(timeInMillis-lastTimeInMillis);
		
		if(updateSpeed >= 1){
			//System.out.println("currentSpeed:" + currentSpeed);
			lastTimeInMillis = timeInMillis;
		}
		
		for (int i =0; i <= updateSpeed; i++) {
			endX   = (float) (startX + i * Math.cos(currentRotad));
			endY   = (float) (startY + i * Math.sin(currentRotad));
			
			Point iP = levelOne.getTileAtPoint(levelOne.tileSize,endX,endY);
			
			//Check if out of bounds
			if(mapArray.length <= iP.y || iP.y < 0){
				stopped = true;
				break;
			}
			if(mapArray[0].length <= iP.x || iP.x < 0){
				stopped = true;
				break;
			}
			//Check for collision with solid tile
			if(mapArray[iP.y][iP.x] == 1){
				currentRotation = currentRotation - 180;
				currentRotad = (currentRotation-90) * Math.PI / 180;
				
				//stopped = true;
				//break;
			}
		}
		
		Xpos = endX;
		Ypos = endY;
	}
}
