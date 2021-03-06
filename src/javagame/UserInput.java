package javagame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import person.Criminal;

public class UserInput {
	
	//Input - Should be moved to own class.
	void inputHandler(Criminal Player, GameContainer gc, float offsetX, float offsetY){
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
		
		if(input.isKeyDown(Input.KEY_E)){
			Player.inventoryOpen = true;
		} else {
			Player.inventoryOpen = false;
		}
		
		if(input.isKeyDown(Input.KEY_R)){
			//if(Player.getBestMagasine() != null){
			Player.gun.loadMag(Player.getBestMagasine());
			//}
		}
		
		int xPos = input.getMouseX();
		int yPos = input.getMouseY();
		boolean mouseClick = input.isMouseButtonDown(0);
		
		if(mouseClick){ 
			if(!Player.inventoryOpen){
				Player.isShooting = true;
			}
		} else {
			Player.isShooting = false;
		}
		
		if(Player.inventoryOpen){
			Player.handleInventoryMouseInput(xPos, yPos, mouseClick);
		}
		
		if(!Player.inventoryOpen){
			float xDistance = (float) (xPos - offsetX - (Player.Xpos));
			float yDistance = (float) (yPos - offsetY - (Player.Ypos));
			double angleToTurn = Math.toDegrees(Math.atan2(yDistance, xDistance));
			Player.aimedRotation = (float) angleToTurn+90;
		}
	}
}
