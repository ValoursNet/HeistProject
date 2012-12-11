package javagame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import person.Criminal;

public class UserInput {
	
	//Input - Should be moved to own class.
	void inputHandler(Criminal Player, GameContainer gc){
		Input input = gc.getInput();
		
		if(input.isKeyDown(Input.KEY_W)){
			Player.movingUp = true;
		} else {
			Player.movingUp = false;
		}
		if(input.isKeyDown(Input.KEY_S)){
			Player.movingDown = true;
		} else {
			Player.movingDown = false;
		}
		if(input.isKeyDown(Input.KEY_A)){
			Player.movingLeft = true;
		} else {
			Player.movingLeft = false;
		}
		if(input.isKeyDown(Input.KEY_D)){
			Player.movingRight = true;
		} else {
			Player.movingRight = false;
		}
		
		int xPos = input.getMouseX();
		int yPos = input.getMouseY();
		
		boolean mouseClick = input.isMouseButtonDown(0);
		if(mouseClick){
			Player.isShooting = true;
		} else {
			Player.isShooting = false;
		}
		
		float xDistance = (float) (xPos - (Player.Xpos));
		float yDistance = (float) (yPos - (Player.Ypos));
		double angleToTurn = Math.toDegrees(Math.atan2(yDistance, xDistance));
		Player.currentRotation = (float) angleToTurn+90;
	}
}
