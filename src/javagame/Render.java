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
	
	public float offsetX = 0;
	public float offsetY = 0;
	
	Projectiles projObj = new Projectiles();
	
	long lastTimeInMillis = System.currentTimeMillis();
	
	public void setMap(Map levelOne){
		this.levelOne = levelOne;
	}
	
	public void setProjectiles(Projectiles projObj){
		this.projObj = projObj;
	}
	
	public void setImages(Image player,	Image wall,	Image cop){
		this.player = player;
		this.cop = cop;
		this.wall = wall;
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, Graphics g, float offsetX, float offsetY, Person Player, Person policeUnit)throws SlickException {
		//float gX = (float) (1024/2 -Player.Xpos);
		//float gY = (float) (768/2 -Player.Ypos);
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		g.translate(offsetX,offsetY);
		
		Color cZero = new Color(33, 65, 104);
		Color cOne = new Color(27, 47, 72);
		Color cTwo = new Color(27, 107, 72);
		
		g.setColor(cTwo);
		g.fillRect(0, 0, 1024, 768);
		
		g.setColor(cZero);
		drawMap(levelOne.getMap(),g,0);
		
		g.setColor(cOne);
		drawMap(levelOne.getMap(),g,1);
		
		g.setColor(cTwo);
		drawMap(levelOne.getMap(),g,2);
		
		if(drawDebug){
			g.setColor(Color.red);
			g.fillRect((float)Player.Xpos-19, (float)Player.Ypos-22, (float)40, (float)40);
		}
		
		if(!Player.isDead){
			g.drawImage(player,(int) Player.Xpos-39,(int) Player.Ypos-72);
			player.setCenterOfRotation(39,72);
			player.setRotation(Player.currentRotation);
		}
		
		if(!policeUnit.isDead){
			g.drawImage(cop,(int) policeUnit.Xpos-39,(int) policeUnit.Ypos-72);
			cop.setCenterOfRotation(39,72);
			cop.setRotation(policeUnit.currentRotation);
		}
		
		long timeInMillis = System.currentTimeMillis();
		
		if(Player.isShooting && projObj.bulletCount < 50){
		//	drawSightlineCollision(Player, g,levelOne.getMap(),Player.currentRotation);
			if(0.02*(timeInMillis-lastTimeInMillis)>= 1){
				Person[] people = new Person[1];// = [policeUnit];
				people[0] = policeUnit;
				projObj.createProjectile(levelOne,Player.Xpos,Player.Ypos, 2,Player.currentRotation, people);
				Player.isShooting = false;
				lastTimeInMillis=timeInMillis;
			}
		}
		
		for(int i=0; i < projObj.bulletCount; i++){
			Bullet b1 = projObj.getBulletCollection()[i];
			g.setColor(Color.red);
			if(!b1.stopped){
				g.fillRect((float)b1.Xpos, (float)b1.Ypos, (float)4, (float)4);
			}
		}
		projObj.update();
			
		if(drawDebug){
			drawSightline(Player,g);
			for (int i =0; i < 10; i++) {
				drawSightlineCollision(Player,g,levelOne.getMap(),Player.currentRotation-20+(i*4));
			}
		}
	}
	
	//Should be based on paramater map, not class defined levelOne
	void drawMap(int[][] mapArray,Graphics g, int drawNum){
		int tSize = levelOne.tileSize;
		for (int i =0; i < mapArray.length; i++) {
			for (int j = 0; j < mapArray[i].length; j++) {
				if(mapArray[i][j] == drawNum){
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
			
			Point iP = levelOne.getTileAtPoint(levelOne.tileSize,endX,endY);
			
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
}
