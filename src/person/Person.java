package person;

import java.util.Random;

import javagame.Map;

public class Person {
	
	Map levelOne;
	public double Xpos = 100;
	public double Ypos = 300;
	double currentSpeed = 0.2;
	public Float currentRotation = (float) 0;
	
	long lastTimeInMillis = System.currentTimeMillis();
	
	int dirNum = 0;
	int dirCount = 0;
	
	public boolean movingUp,movingDown,movingLeft,movingRight = false;	
	public boolean isShooting = false;
	
	public String name = generateName();
	
	public void update() {
		
		//checkDirection();
		
		if(levelOne != null){
			collisionCheck();
			currentRotation = (float) (currentRotation + 0.1);
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
