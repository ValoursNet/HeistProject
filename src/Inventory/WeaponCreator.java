package Inventory;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.loading.LoadingList;

import person.Person;

public class WeaponCreator {
	
	public Image stanag = null;
	public Image stanagGround = null;
	
	public WeaponCreator(){
		LoadingList.setDeferredLoading(true);
		try {
			stanagGround = new Image("res/StanagGround.png");
			stanag = new Image("res/Stanag.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//LoadingList.setDeferredLoading(false);
	}
	
	public Gun newGlock(Person newHolder){
		//directControl = true;
		InventoryParser inventoryParser = new InventoryParser();
		Gun wep = (Gun) inventoryParser.parseFile("wep/Glock17.json");
		//backpack.addToBackpack(wep, 0, 6);
		Gun gun = wep;//new Gun("Name", "Description", null, null, 1, 800, 7, 3500);
		Magasine mag = new Magasine("Stanag", "M4 Stanag", "M4 Stanag mag", stanag, stanagGround, 1, 30, 30);
		gun.setHolder(newHolder);
		gun.loadMag(mag);
		
		return gun;
	}
			
}
