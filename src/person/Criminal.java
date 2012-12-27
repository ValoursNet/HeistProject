package person;

import gun.Gun;

import org.newdawn.slick.SlickException;

import javagame.Map;
import javagame.Play;
import javagame.Projectiles;

public class Criminal extends Person {

	public Criminal(Map glevelOne, Projectiles projectiles) throws SlickException {
		levelOne = glevelOne;
		type = 1;
		Play play = new Play();
		image = play.player.copy();
		directControl = true;
		gun = new Gun(this, projectiles);
		System.out.println("CRIMINAL ERE");
	}

}