package person;

import javagame.Map;

public class Cop extends Person {
	
	public Cop(Map glevelOne) {
		levelOne = glevelOne;
		System.out.println("COP ERE");
	}
	
	//@Override
	//Overriding for checkDirection. For testing.
	/*
	public void update() {
		
	//	checkDirection();
		
		if(levelOne != null){
			updatePosition(levelOne.getMap());
			currentRotation = (float) (currentRotation + 0.1);
		}
	}
	*/
	
}
