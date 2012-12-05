package person;

import javagame.Map;

public class Cop extends Person {
	
	public Cop(Map glevelOne) {
		levelOne = glevelOne;
		System.out.println("COP ERE");
	}
	
	@Override
	public void updateSelf() {
		
		checkDirection();
		
		if(levelOne != null){
			collisionCheck();
			currentRotation = (float) (currentRotation + 0.1);
		}
	}
	
}
