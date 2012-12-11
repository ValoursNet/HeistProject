package javagame;

import java.awt.Point;

public class Bullet {
	
	Map levelOne;
	
	public double Xpos = 100;
	public double Ypos = 300;
	double currentSpeed = 2;
	public float currentRotation = (float) 0;
	
	long lastTimeInMillis = System.currentTimeMillis();
	
	public Bullet (Map levelOne, double Xpos, double Ypos, double currentSpeed, float currentRotation){
		this.levelOne = levelOne;
		
		this.Xpos = Xpos;
		this.Ypos = Ypos;
		this.currentSpeed = currentSpeed;
		this.currentRotation = currentRotation;
		
	}
	
	void update(){
		updatePosition(levelOne.mapCollisions);
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
		}
		
		Xpos = endX;
		Ypos = endY;
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
