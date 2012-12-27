package person;

import gun.Gun;

import org.newdawn.slick.SlickException;

import javagame.Map;
import javagame.Play;
import javagame.Projectiles;

public class Cop extends Person {
	
	public Cop(Map glevelOne, Projectiles projectiles) throws SlickException {
		levelOne = glevelOne;
		type = 2;
		Play play = new Play();
		image = play.cop.copy();
		gun = new Gun(this, projectiles);
		System.out.println("COP ERE");
	}
	
	@Override
	public void update() {
		super.update();
		//checkDirection();
		
		followPath();
		isShooting = true;
		gun.bulletCount = 1000;
	}
	
	
}
