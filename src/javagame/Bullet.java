package javagame;

import java.awt.Point;
import person.Person;
import javagame.Play;

public class Bullet {
	
	Person shooter;
	Map levelOne;
	public double Xpos = 100;
	public double Ypos = 300;
	double currentSpeed = 2;
	public float currentRotation = (float) 0;
	
	public Boolean stopped = false;
	
	long lastTimeInMillis = System.currentTimeMillis();
	
	public Bullet (Map level, double Xpos, double Ypos, double currentSpeed, float currentRotation, Person shooter){		
		this.Xpos = Xpos;
		this.Ypos = Ypos;
		this.currentSpeed = currentSpeed;
		this.currentRotation = currentRotation;
		this.levelOne = level;
		this.shooter = shooter;
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
				break;
			}
			if(mapArray[0].length <= iP.x || iP.x < 0){
				break;
			}
			//Check for collision with solid tile
			if(mapArray[iP.y][iP.x] == 1){
				break;
			}
			
			/*for(int n=0; n < people.length; n++){
				//System.out.println("objName: " + people[0].name);
				if(bulletWithin(endX,endY,people[0].Xpos,people[0].Ypos,people[0].width,people[0].height)){
					System.out.println("objName: " + people[0].name);
					people[0].xForce = people[0].xForce + (Math.cos(currentRotad) * 1);
					people[0].yForce = people[0].yForce + (Math.sin(currentRotad) * 1);
					people[0].health =  people[0].health - 10;
					stopped = true;
					break;
				}
			} */
			for (Person person : Play.people) {
				if (!person.equals(shooter)) {
					if(bulletWithin(endX,endY,person.Xpos,person.Ypos,person.width,person.height)){
						System.out.println("objName: " + person.name);
						person.xForce = person.xForce + (Math.cos(currentRotad) * 1);
						person.yForce = person.yForce + (Math.sin(currentRotad) * 1);
						person.health =  person.health - 10;
						stopped = true;
						break;
					}
				}
			}
		}
		
		Xpos = endX;
		Ypos = endY;
	}

	private boolean bulletWithin(double iPx, double iPy, double xpos, double ypos, double width, double height) {
		if(iPx>=xpos-19 && iPy>=ypos-22){
			if(iPx<=xpos-19+width && iPy<=ypos-22+height){
				return true;
			}
		}
		return false;
	}
	
	/*
	void drawSightlineCollision(Person Player, Graphics g,int[][] mapArray, float gAngle){
		int lineLength = 500;
		double playerRotad = (gAngle-90) * Math.PI / 180;
		float startX = (float) Player.Xpos;
		float startY = (float) Player.Ypos;
		
		float endX   = (float) (startX+ 1 * Math.cos(playerRotad));
		float endY   = (float) (startY+ 1 * Math.sin(playerRotad));
		for (int i =0; i < lineLength; i++) {
			endX   = (float) (startX + i * Math.cos(playerRotad));
			endY   = (float) (startY + i * Math.sin(playerRotad));
			
			Point iP = levelOne.getTileAtPoint(levelOne.tileSize,endX,endY);
			
			//Check if out of bounds
			if(mapArray.length <= iP.y || iP.y < 0){
				break;
			}
			if(mapArray[0].length <= iP.x || iP.x < 0){
				break;
			}
			//Check for collision with solid tile
			if(mapArray[iP.y][iP.x] == 1){
				break;
			}
		}
		
		g.setColor(Color.red);
		g.drawLine(startX, startY, endX, endY);
	}
	*/
}
