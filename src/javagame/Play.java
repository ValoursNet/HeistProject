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
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)throws SlickException {
		g.setColor(Color.white);
		drawMap(levelOne.getMap(),g);
		
		if(drawDebug){
			g.setColor(Color.red);
			g.fillRect((float)Player.Xpos-19, (float)Player.Ypos-22, (float)40, (float)40);
		}
		
		g.drawImage(player,(int) Player.Xpos-39,(int) Player.Ypos-72);
		player.setCenterOfRotation(39,72);
		player.setRotation(Player.currentRotation);
		
		g.drawImage(cop,(int) policeUnit.Xpos-39,(int) policeUnit.Ypos-72);
		cop.setCenterOfRotation(39,72);
		cop.setRotation(policeUnit.currentRotation);
		
		
		if(Player.isShooting){
			drawSightlineCollision(g,levelOne.getMap(),Player.currentRotation);
		}
		
		if(drawDebug){
			drawSightline(g);
			for (int i =0; i < 10; i++) {
				drawSightlineCollision(g,levelOne.getMap(),Player.currentRotation-20+(i*4));
			}
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException {
		inputHandler(gc);
		policeUnit.updateSelf();
		Player.updateSelf();
	}
	
	
	private void drawSightline(Graphics g){
		int lineLength = 500;
		double playerRotad = (Player.currentRotation-90) * Math.PI / 180;
		float startX = (float) Player.Xpos;
		float startY = (float) Player.Ypos;
		float endX   = (float) (startX + lineLength * Math.cos(playerRotad));
		float endY   = (float) (startY + lineLength * Math.sin(playerRotad));
		
		g.setColor(Color.blue);
		g.drawLine(startX, startY, endX, endY);
	}
	
	private void drawSightlineCollision(Graphics g,int[][] mapArray, float gAngle){
		int lineLength = 500;
		double playerRotad = (gAngle-90) * Math.PI / 180;
		float startX = (float) Player.Xpos;
		float startY = (float) Player.Ypos;
		
		float endX   = (float) (startX+ 1 * Math.cos(playerRotad));
		float endY   = (float) (startY+ 1 * Math.sin(playerRotad));
		for (int i =0; i < lineLength; i++) {
			endX   = (float) (startX + i * Math.cos(playerRotad));
			endY   = (float) (startY + i * Math.sin(playerRotad));
			
			Point iP = getTileAtPoint(levelOne.tileSize,endX,endY);
			
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
	
	private Point getTileAtPoint(int tileSize, double gX, double gY){
		int rX = (int) (gX/tileSize);
		int rY = (int) (gY/tileSize);;

		Point rP = new Point(rX,rY);
		return rP;
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
