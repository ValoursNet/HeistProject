package person;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.loading.LoadingList;

import Inventory.Gun;
import Inventory.Magasine;

import javagame.Map;
import javagame.Projectiles;

public class Criminal extends Person {

	public Criminal(Map glevelOne, Projectiles projectiles)
			throws SlickException {
		levelOne = glevelOne;
		type = 1;
		// Play play = new Play();
		// image = play.player.copy();

		LoadingList.setDeferredLoading(true);
		try {
			image = new Image("res/RobberM4.png");
		} catch (SlickException e) {
		}
		LoadingList.setDeferredLoading(false);

		//directControl = true;
		gun = new Gun("Name", "Description", null, null, 1, 700, 7, 3500);
		Magasine mag = new Magasine("Magasine", "Description", null, 1, 40, 40);
		gun.loadMag(mag);
		gun.setHolder(this);
		System.out.println("CRIMINAL ERE");
	}

}