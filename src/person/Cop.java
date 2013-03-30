package person;

import java.awt.Polygon;

import gun.Gun;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.loading.LoadingList;

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
	        try { image = new Image("res/RobberM4.png"); }
	        catch (SlickException e) {}
	        LoadingList.setDeferredLoading(false);
		
		//gun = new Gun(this, projectiles);
		System.out.println("COP ERE");
	}
	
	@Override
	public void update() {
		super.update();
		//checkDirection();
		
		basicAI();
		if(shootOnSight())isShooting=true;
		else isShooting=false;
		//isShooting = true;
		//gun.bulletCount = 100;
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
