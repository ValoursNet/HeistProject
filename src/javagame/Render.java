package javagame;

import java.awt.Point;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import person.Person;

public class Render {
	
	Map levelOne;
	boolean drawDebug = false;
	
	Image player;
	Image wall;
	Image cop;
	
	public void setMap(Map levelOne){
		this.levelOne = levelOne;
	}
	
	public void setImages(Image player,	Image wall,	Image cop){
		this.player = player;
		this.cop = cop;
		this.wall = wall;
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, Graphics g, Person Player, Person policeUnit)throws SlickException {
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
			drawSightlineCollision(Player, g,levelOne.getMap(),Player.currentRotation);
		}
		
		if(drawDebug){
			drawSightline(Player,g);
			for (int i =0; i < 10; i++) {
				drawSightlineCollision(Player,g,levelOne.getMap(),Player.currentRotation-20+(i*4));
			}
		}
	}
	
	//Should be based on paramater map, not class defined levelOne
	void drawMap(int[][] mapArray,Graphics g){
		int tSize = levelOne.tileSize;
		for (int i =0; i < mapArray.length; i++) {
			for (int j = 0; j < mapArray[i].length; j++) {
				if(mapArray[i][j] == 0){
					g.fillRect(j*tSize, i*tSize, tSize, tSize);
				}
			}
		}
	}
	
	//Should probably be combined with drawSightlineCollision
	void drawSightline(Person Player, Graphics g){
		int lineLength = 500;
		double playerRotad = (Player.currentRotation-90) * Math.PI / 180;
		float startX = (float) Player.Xpos;
		float startY = (float) Player.Ypos;
		float endX   = (float) (startX + lineLength * Math.cos(playerRotad));
		float endY   = (float) (startY + lineLength * Math.sin(playerRotad));
		
		g.setColor(Color.blue);
		g.drawLine(startX, startY, endX, endY);
	}
	
	//Should probably be combined with drawSightline
	void drawSightlineCollision(Person Player, Graphics g,int[][] mapArray, float gAngle){
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
	
	//Util - Should be moved to util class/package/object
	private Point getTileAtPoint(int tileSize, double gX, double gY){
		int rX = (int) (gX/tileSize);
		int rY = (int) (gY/tileSize);;

		Point rP = new Point(rX,rY);
		return rP;
	}
	
}
