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
		g.setColor(Color.white);
		drawMap(levelOne.getMap(),g);
		
		g.setColor(Color.red);
		g.fillRect((float)playerX-19, (float)playerY-22, (float)40, (float)40);
		
		g.drawImage(wall,0,0);
		g.drawImage(player,(int) playerX-39,(int) playerY-72);
		
		player.setCenterOfRotation(39,72);
		player.setRotation(playerR);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException {
		inputHandler(gc);
	}

	private void drawMap(int[][] mapArray,Graphics g){
		int tSize = levelOne.tileSize;
		for (int i =0; i < mapArray.length; i++) {
			for (int j = 0; j < mapArray[i].length; j++) {
				if(mapArray[i][j] == 0){
					g.fillRect(j*tSize, i*tSize, tSize, tSize);
				}
			}
		}
	}
	
	private void inputHandler(GameContainer gc){
		Input input = gc.getInput();
		
		double prevY = playerY;
		double prevX = playerX;
		
		if(input.isKeyDown(Input.KEY_W)){
			playerY = playerY - playerSpeed;
		}
		if(input.isKeyDown(Input.KEY_S)){
			playerY = playerY + playerSpeed;
		}
		
		if(levelOne.isColliding(playerX,playerY)){
			playerY = prevY;
		}
		
		if(input.isKeyDown(Input.KEY_A)){
			playerX = playerX - playerSpeed;
		}
		if(input.isKeyDown(Input.KEY_D)){
			playerX = playerX + playerSpeed;
		}	
		
		if(levelOne.isColliding(playerX,playerY)){
			playerX = prevX;
		}
		
		
		int xPos = input.getMouseX();
		int yPos = input.getMouseY();
		
		float xDistance = (float) (xPos - (playerX));
		float yDistance = (float) (yPos - (playerY));
		double angleToTurn = Math.toDegrees(Math.atan2(yDistance, xDistance));
		playerR = (float) angleToTurn+90;
	}
}
