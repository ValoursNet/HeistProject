package Inventory;

import javagame.Play;

import org.newdawn.slick.Image;

import person.Person;

public class Gun extends InventoryObject {
	
	Image inGameImage;
	Magasine mag;
	double fireRate;
	double spread;
	double reloadTime;
	double reloadCount;
	long lastTime;
	long startReloadTime;
	long timeReloading;
	Person holder;
	
	public Gun(String name, String description, Image inventoryImage, Image inGameImage, 
			   int size, double fireRate, double spread, double reloadTime) {
		super(name, description, inventoryImage, size);
		this.inGameImage = inGameImage;
		mag = null;
		this.fireRate = fireRate;
		this.spread = spread;
		this.reloadTime = reloadTime;
		reloadCount = 0;
		lastTime = System.currentTimeMillis();
		holder = null;	
	}

	public boolean isLoaded() {
		return mag != null;
	}
	
	public void loadMag(Magasine mag) {
		this.mag = mag;
	}
	
	public boolean isEmptyMag() {
		return (mag != null && mag.isEmpty());
	}
	
	public Magasine removeMag() {
		Magasine magasine = this.mag;
		this.mag = null;
		return magasine;
	}
	
	public void setHolder(Person holder) {
		this.holder = holder;
	}
	
	public boolean fire() {
		if (mag != null && holder != null && !mag.isEmpty()) {
			long timeInMillis = System.currentTimeMillis();
			if (fireRate * (timeInMillis - lastTime) >= 1) {
				double shotAngle = spread*Math.random() + holder.currentRotation - spread/2;
				Play.projectiles.createProjectile(holder.Xpos, holder.Ypos, 2, (float) shotAngle, holder);
				return mag.fire();
			}
		}
		return false;
	}
	
}
