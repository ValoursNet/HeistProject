package javagame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Play extends BasicGameState{

	Image player;
	Image wall;
	double playerX = 100;
	double playerY = 100;
	double playerSpeed = 0.2;
	Float playerR = (float) 0;
	
	Map levelOne = new Map();
	
	public Play(int state){
	
	}
	
	@Override
	public int getID() {
		return 1;
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		player = new Image("res/PlayerAKDrawn.png");		
		wall = new Image("res/wall.png");	
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)throws SlickException {
		g.drawImage(wall,0,0);
		g.drawImage(player,(int) playerX,(int) playerY);
		player.setCenterOfRotation(39,72);
		player.setRotation(playerR);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException {
		inputHandler(gc);
	}

	private void inputHandler(GameContainer gc){
		Input input = gc.getInput();
		
		double prevY = playerY;
		double prevX = playerX;
		
		if(input.isKeyDown(Input.KEY_UP)){
			playerY = playerY - playerSpeed;
		}
		if(input.isKeyDown(Input.KEY_DOWN)){
			playerY = playerY + playerSpeed;
		}
		if(input.isKeyDown(Input.KEY_LEFT)){
			playerX = playerX - playerSpeed;
		}
		if(input.isKeyDown(Input.KEY_RIGHT)){
			playerX = playerX + playerSpeed;
		}	
		
		levelOne.showPos(playerX, playerY);
		
		
		if(levelOne.isColliding(playerX,playerY)){
			playerY = prevY;
			playerX = prevX;
			//System.out.println("collision");
		}
		
		
		int xPos = input.getMouseX();
		int yPos = input.getMouseY();
		
		float xDistance = (float) (xPos - (playerX+39));
		float yDistance = (float) (yPos - (playerY+72));
		double angleToTurn = Math.toDegrees(Math.atan2(yDistance, xDistance));
		playerR = (float) angleToTurn+90;
	}
}
