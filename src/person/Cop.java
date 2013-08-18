package person;

import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.util.pathfinding.navmesh.NavPath;

import Inventory.Gun;
import Inventory.InventoryParser;
import Inventory.Magasine;
import Inventory.WeaponCreator;

import javagame.Map;
import javagame.Play;
import javagame.Projectiles;

public class Cop extends Person {
	
	int viewDistance = 400;
	
	public Cop(Map glevelOne, Projectiles projectiles) throws SlickException {
		levelOne = glevelOne;
		type = 2;
		//Play play = new Play();
		//image =  new Image("res/RobberM4.png");
		
		 LoadingList.setDeferredLoading(true);
        try { image = new Image("res/RobberBody.png");}
        catch (SlickException e) {}
	        //LoadingList.setDeferredLoading(false);
		
        
        gun = levelOne.weaponCreator.newGlock(this);
        
        /*
        Image gunMag = null;
		Image gunMagGround = null;
	        
		try {
			gunMag = new Image("res/Stanag.png");
			gunMagGround = new Image("res/StanagGround.png");
		}
		catch (SlickException e) {}
		
		 //Add gun;
		InventoryParser inventoryParser = new InventoryParser();   
	    Gun wep = (Gun) inventoryParser.parseFile("wep/Glock17.json");
		
		
		
		//backpack.addToBackpack(wep, 0, 6);
		gun = wep;//new Gun("Name", "Description", null, null, 1, 800, 7, 3500);
		Magasine mag = new Magasine("Stanag", "M4 Stanag", "M4 Stanag mag", gunMag, gunMagGround, 1, 30, 30);
		gun.setHolder(this);
		gun.loadMag(mag);
		
	   */
		
		LoadingList.setDeferredLoading(false);
		//gun = new Gun(this, projectiles);
		System.out.println("COP ERE");
	}
	
	@Override
	public void update() {
		super.update();
		//checkDirection();
		
		//System.out.println("targetUnit: " + (targetUnit == null));
		
		if(targetUnit != null){
			currentPath = levelOne.findPath((int)Math.round((Xpos-50)/levelOne.tileSize), (int)Math.round((Ypos-50)/levelOne.tileSize), (int)Math.round((targetUnit.Xpos-50)/levelOne.tileSize), (int)Math.round((targetUnit.Ypos-50)/levelOne.tileSize));
			//System.out.println("currentPath: " + (currentPath == null));
			
			
			int travelTime = 1;
			double desiredSpeed = 0.5;
			
			if(currentPath != null && currentPath.length() > 2){
				if((int) currentPath.getX(0) == (int) currentPath.getX(1) && (int) currentPath.getY(0) == (int) currentPath.getY(1)){
					currentPath.remove(0);
				}
				//pcx*100 + 50
				//xSpeed = (float) (((currentPath.getX(1)*100) + 50 - Xpos) / travelTime);
				xSpeed = (float) (((((int)currentPath.getX(1))*100) + 50 - Xpos) / travelTime);
				ySpeed = (float) (((((int)currentPath.getY(1))*100) + 50 - Ypos) / travelTime);
				
				float factor = (float) (desiredSpeed / Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed));
				
				xSpeed = xSpeed*factor;
				ySpeed = ySpeed*factor;
				
				
				float xDistance = (float) ((((int)currentPath.getX(1)*100)) + 50 - offsetX - (Xpos));
				float yDistance = (float) ((((int)currentPath.getY(1)*100)) + 50 - offsetY - (Ypos));
				double angleToTurn = Math.toDegrees(Math.atan2(yDistance, xDistance));
				aimedRotation = (float) angleToTurn+90;
				
				
				leftReactive = new Rectangle((float) (Xpos-20),(float)Ypos-40,(float)20,(float)40);
				leftReactive = leftReactive.transform(Transform.createRotateTransform((float) Math.toRadians(aimedRotation),(float) Xpos,(float)Ypos));
				
				rightReactive = new Rectangle((float) (Xpos),(float)Ypos-40,(float)20,(float)40);
				rightReactive = rightReactive.transform(Transform.createRotateTransform((float) Math.toRadians(aimedRotation),(float) Xpos,(float)Ypos));
				
			}
			
			//void drawSightlineCollision(Person Player, Graphics g, int[][] mapArray,
					//float gAngle) {
				//int lineLength = 500;
				//double playerRotad = (gAngle - 90) * Math.PI / 180;
				//float startX = (float) Player.Xpos;
				//float startY = (float) Player.Ypos;

				//float endX = (float) (startX + 1 * Math.cos(playerRotad));
				//float endY = (float) (startY + 1 * Math.sin(playerRotad));
				//boolean hadCollision = false;
				//float xDistance = (float) (targetUnit.Xpos - (Xpos));
				//float yDistance = (float) (targetUnit.Ypos - (Ypos));
				//double angleToTurn = Math.toDegrees(Math.atan2(yDistance, xDistance));
				//double thisRotation = (float) angleToTurn+90;
				//int distance = (int) Math.sqrt((xDistance)*(xDistance) + (yDistance)*(yDistance));
				
				//float endX = (float) Xpos;
				//float endY = (float) Ypos;
				
				//for (int i = 0; i < distance; i++) {
					//endX = (float) (Xpos + i * Math.cos(thisRotation));
					//endY = (float) (Ypos + i * Math.sin(thisRotation));
					Boolean breakNow = false;
					Line asdaasdf = new Line( (float) Xpos, (float) Ypos, (float) targetUnit.Xpos, (float) targetUnit.Ypos);
					
					synchronized (levelOne.buildingOne.wallCollection) {  
						for (Rectangle wallRect : levelOne.buildingOne.wallCollection) {
							if(wallRect.intersects(asdaasdf)){
								//System.out.println("collision");
								breakNow = true;
								break;
							}
						}
					}
					//if(breakNow){
					//	hadCollision = true;
					//	break;
					//}	
				//}
				
				if(breakNow){
					hasTargetSight = false;
				} else {
					hasTargetSight = true;
				}
				
				
				
				
				if(hasTargetSight){
					float xDistance = (float) (targetUnit.Xpos - offsetX - (Xpos));
					float yDistance = (float) (targetUnit.Ypos - offsetY - (Ypos));
					double angleToTurn = Math.toDegrees(Math.atan2(yDistance, xDistance));
					aimedRotation = (float) angleToTurn+90;
					
					if(round(aimedRotation,10) == round(currentRotation,10)){
						isShooting = true;
					}
				} else {
					isShooting = false;
				}
				//g.setColor(Color.red);
				//g.drawLine(startX, startY, endX, endY);
			//}
		}
		
		
		if(gun != null){
			
			if(gun.shotCooldown != 0 || isShooting){
				//System.out.println("shotcooldownUp");
				gun.shotCooldown++;
			}
			if(gun.shotCooldown > 30){
				System.out.println("shotcooldownReset");
				isShooting = false;
				gun.shotCooldown = 0;
			}
		}
		
		//basicAI();
		//if(shootOnSight())isShooting=true;
		//else isShooting=false;
		//isShooting = true;
		//gun.bulletCount = 100;
	}
	
	int round(double i, int v){
	    return (int) (Math.round(i/v) * v);
	} 
	
	private boolean shootOnSight(){
	    boolean seen = false;
	    Polygon p = new Polygon();
	    double actualRotation = currentRotation;
	    //1 degrees = 0.0174532925
	    double halfSightAngleDegrees = 22;
	    double halfSightAngle = halfSightAngleDegrees*0.0174;
	    
	    Person target = Play.playerUnit;
	    
	    p.addPoint((int) Xpos,(int) Ypos);
	    
	    leftX   = (int) (Xpos + viewDistance * Math.cos(actualRotation-halfSightAngle));
		leftY   = (int) (Ypos + viewDistance * Math.sin(actualRotation-halfSightAngle));
		rightX   = (int) (Xpos + viewDistance * Math.cos(actualRotation+halfSightAngle));
		rightY   = (int) (Ypos + viewDistance * Math.sin(actualRotation+halfSightAngle));
		
	    p.addPoint(leftX, leftY);
	    p.addPoint(rightX, rightY);

	    if(p.contains(target.Xpos,target.Ypos)){
	    	seen = true;
	    	hasTarget = true;
	    	myTarget = target;
	    }
	    //System.out.print(c);
		return seen;
	}
	
	
}
