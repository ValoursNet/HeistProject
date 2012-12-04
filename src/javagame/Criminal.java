package javagame;

public class Criminal {
	
	Map levelOne;
	double Xpos = 100;
	double Ypos = 300;
	double currentSpeed = 0.2;
	Float currentRotation = (float) 0;
	
	int dirNum = 0;
	int dirCount = 0;
	
	boolean movingUp,movingDown,movingLeft,movingRight = false;
	boolean isShooting = false;
	
	public Criminal(Map glevelOne) {
		levelOne = glevelOne;
		System.out.println("CRIMINAL ERE");
		//movingUp = true;
	}
	
	public void updateSelf() {
			collisionCheck();
	}

	private void collisionCheck(){
		double prevY = Ypos;
		double prevX = Xpos;
		
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