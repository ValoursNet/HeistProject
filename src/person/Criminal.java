package person;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.loading.LoadingList;

import Inventory.Backpack;
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
		backpack = new Backpack("Alice", "Alice Pack Ting", null, new Image("res/BackPackGrid.png"), 0, 10, 5, 8);
		backpack.forceToBackpack(new Magasine("M4 Stanag", "M4 Stanag mag", new Image("res/Stanag.png"), 1, 10, 30), 0, 0);
		backpack.forceToBackpack(new Magasine("M4 Stanag", "M4 Stanag mag", new Image("res/Stanag.png"), 1, 30, 30), 1, 0);
		backpack.forceToBackpack(new Magasine("M4 Stanag", "M4 Stanag mag", new Image("res/Stanag.png"), 1, 26, 30), 2, 0);
		backpack.forceToBackpack(new Magasine("M4 Stanag", "M4 Stanag mag", new Image("res/Stanag.png"), 1, 13, 30), 0, 1);

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