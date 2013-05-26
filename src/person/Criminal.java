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
		backpack = new Backpack("Alice", "Alice Pack Ting", null, new Image("res/BackPackGrid.png"), 0, 10);
		backpack.addToBackpack(new Magasine("M4 Stanag", "M4 Stanag mag", new Image("res/Stanag.png"), 1, 30, 30));
		backpack.addToBackpack(new Magasine("M4 Stanag", "M4 Stanag mag", new Image("res/Stanag.png"), 1, 30, 30));
		backpack.addToBackpack(new Magasine("M4 Stanag", "M4 Stanag mag", new Image("res/Stanag.png"), 1, 30, 30));
		backpack.addToBackpack(new Magasine("M4 Stanag", "M4 Stanag mag", new Image("res/Stanag.png"), 1, 30, 30));

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