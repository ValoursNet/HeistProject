package javagame;

import java.awt.Point;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import person.Cop;
import person.Criminal;

public class Play extends BasicGameState{

	Image player;
	Image wall;
	Image cop;
	
	Render renderObj = new Render();
	
	Map levelOne = new Map();
	Cop policeUnit = new Cop(levelOne);
	Criminal Player = new Criminal(levelOne);
	
	boolean drawDebug = false;
	
	public Play(int state){
	
	}

	@Override
	public int getID() {
		return 1;
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		player = new Image("res/PlayerAKDrawn.png");
		cop = new Image("res/PlayerAKDrawn.png");
		
		renderObj.setImages(player,wall,cop);
		renderObj.setMap(levelOne);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)throws SlickException {
		renderObj.update(gc, sbg, g, Player, policeUnit);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException {
		inputHandler(gc);
		policeUnit.update();
		Player.update();
	}
	
	//Input - Should be moved to own class.
	private void inputHandler(GameContainer gc){
		Input input = gc.getInput();
		
		/*
		 * g.fillRect((float)Player.Xpos-19, (float)Player.Ypos-22, (float)40, (float)40);
		 */
		
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
