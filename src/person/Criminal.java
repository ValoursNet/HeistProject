package person;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.loading.LoadingList;

import Inventory.Backpack;
import Inventory.GroundObjects;
import Inventory.Gun;
import Inventory.InventoryParser;
import Inventory.Magasine;
import Inventory.Vicinity;

import javagame.Map;
import javagame.Projectiles;

public class Criminal extends Person {

	public Criminal(Map glevelOne, Projectiles projectiles)
			throws SlickException {
		levelOne = glevelOne;
		type = 1;
		// Play play = new Play();
		
		//(name, description, inventoryImage, inGameImage, size, maxSize, layoutWidth, layoutHeight, groundObjects, owner)
			
		vicinity = new Vicinity("Vicinity", "Objects within close vicinity", null, new Image("res/VicinityGrid.png"), 0, 999, 999, 2, glevelOne.groundObjects, this);
		//vicinity.addToBackpack(new Magasine("M4 Stanag", "M4 Stanag mag", new Image("res/Stanag.png"), new Image("res/StanagGround.png"), 1, 10, 30), 0, 0);
		
		
		// image = play.player.copy();
		backpack = new Backpack("Backpack", "Alice Pack Ting", null, new Image("res/BackPackGrid.png"), 0, 40, 5, 8, glevelOne.groundObjects, this);
		backpack.addToBackpack(new Magasine("Stanag", "M4 Stanag", "M4 Stanag mag", new Image("res/Stanag.png"), new Image("res/StanagGround.png"), 1, 11, 30), 0, 0);
		backpack.addToBackpack(new Magasine("Stanag", "M4 Stanag", "M4 Stanag mag", new Image("res/Stanag.png"), new Image("res/StanagGround.png"), 1, 12, 30), 1, 0);
		backpack.addToBackpack(new Magasine("Stanag", "M4 Stanag", "M4 Stanag mag", new Image("res/Stanag.png"), new Image("res/StanagGround.png"), 1, 23, 30), 2, 0);
		backpack.addToBackpack(new Magasine("Stanag", "M4 Stanag", "M4 Stanag mag", new Image("res/Stanag.png"), new Image("res/StanagGround.png"), 1, 14, 30), 0, 1);
		
		InventoryParser inventoryParser = new InventoryParser();
		Gun wep = (Gun) inventoryParser.parseFile("wep/Glock17.json");
		backpack.addToBackpack(wep, 0, 6);
		
		holster = new Backpack("Holster 1", "Holster", null, new Image("res/HolsterGrid.png"), 0, 4, 1, 2, glevelOne.groundObjects, this);
		holster.isBackpack = false;
		holster.addToBackpack(new Magasine("Stanag", "M4 Stanag", "M4 Stanag mag", new Image("res/Stanag.png"), new Image("res/StanagGround.png"), 1, 15, 30), 0, 1);
		
		weaponSlot = new Backpack("Weapon slot 1", "Secondary weapon slot", null, new Image("res/weaponSlotGrid.png"), 0, 1, 1, 1, glevelOne.groundObjects, this);
		weaponSlot.isBackpack = false;
		weaponSlot.addToBackpack(new Magasine("Stanag", "M4 Stanag", "M4 Stanag mag", new Image("res/Stanag.png"), new Image("res/StanagGround.png"), 1, 30, 30), 0, 0);
		
		
		LoadingList.setDeferredLoading(true);
		try {
			image = new Image("res/RobberBody.png");
		} catch (SlickException e) {
		}
		LoadingList.setDeferredLoading(false);

		//directControl = true;
		gun = wep;//new Gun("Name", "Description", null, null, 1, 800, 7, 3500);
		Magasine mag = new Magasine("Stanag", "M4 Stanag", "M4 Stanag mag", new Image("res/Stanag.png"), new Image("res/StanagGround.png"), 1, 30, 30);
		gun.loadMag(mag);
		gun.setHolder(this);
		System.out.println("CRIMINAL ERE");
	}

}