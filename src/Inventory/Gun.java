package Inventory;

import javagame.Play;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

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
	
	public Animation idled = null;// = new Animation();
	public int idledRotationX;
	public int idledRotationY;
	
	public Animation shot = null;// = new Animation();
	public int shotRotationX;
	public int shotRotationY;
	
	public Animation reload = null;// = new Animation();
	public int reloadRotationX;
	public int reloadRotationY;
	
	public Animation aim = null;// = new Animation();
	public int aimRotationX;
	public int aimRotationY;
	
	public Animation idle = null;// = new Animation();
	public int idleRotationX;
	public int idleRotationY;
	
	public Animation aimed = null;// = new Animation();
	public int aimedRotationX;
	public int aimedRotationY;
	
	public Animation empty = null;// = new Animation();
	public int emptyRotationX;
	public int emptyRotationY;
	
	public Animation current = null;// = new Animation();
	public int currentRotationX;
	public int currentRotationY;
	public boolean triggerDown = false;
	
	public Gun(String name, String description, Image inventoryImage, Image inGameImage, int size, double fireRate, double spread, double reloadTime) {
		super(name, description, inventoryImage,inGameImage, size);
		this.inGameImage = inGameImage;
		mag = null;
		this.fireRate = fireRate;
		this.spread = spread;
		this.reloadTime = reloadTime;
		reloadCount = 0;
		lastTime = System.currentTimeMillis();
		holder = null;	
	}
	
	public void changeAnimation(String animationName){
		if(animationName == "Idled"){
			current = idled;
			currentRotationX = idledRotationX;
			currentRotationY = idledRotationY;
		} else if(animationName == "Shot"){
			current = shot;
			currentRotationX = shotRotationX;
			currentRotationY = shotRotationY;
		} else if(animationName == "Reload"){
			current = reload;
			currentRotationX = reloadRotationX;
			currentRotationY = reloadRotationY;
		} else if(animationName == "Aim"){
			current = aim;
			currentRotationX = aimRotationX;
			currentRotationY = aimRotationY;
		} else if(animationName == "Idle"){
			current = idle;
			currentRotationX = idleRotationX;
			currentRotationY = idleRotationY;
		} else if(animationName == "Aimed"){
			current = aimed;
			currentRotationX = aimedRotationX;
			currentRotationY = aimedRotationY;
		} else if(animationName == "Empty"){
			current = empty;
			currentRotationX = emptyRotationX;
			currentRotationY = emptyRotationY;
		}
	}
	public void addAnimation(String animationName ,Image gImage, int frameWidth, int frameHeight, int numberOfFrames, int framesPerSecond, int idledRotationX, int idledRotationY ){
		SpriteSheet sheet = new SpriteSheet(gImage,frameWidth,frameHeight);
		
		if(animationName == "Idled"){
			idled = new Animation();
			idled.setAutoUpdate(true);
	
			for(int col=0;col<numberOfFrames;col++){
				idled.addFrame(sheet.getSprite(col,0), (1000/framesPerSecond));
			}
			
			this.idledRotationX = idledRotationX;
			this.idledRotationY = idledRotationY;
		} else if(animationName == "Shot"){
			shot = new Animation();
			shot.setAutoUpdate(true);
			shot.setLooping(false);
	
			for(int col=0;col<numberOfFrames;col++){
				shot.addFrame(sheet.getSprite(col,0), (1000/framesPerSecond));
			}
			
			this.shotRotationX = idledRotationX;
			this.shotRotationY = idledRotationY;
		} else if(animationName == "Reload"){
			reload = new Animation();
			reload.setAutoUpdate(true);
			reload.setLooping(false);
	
			for(int col=0;col<numberOfFrames;col++){
				reload.addFrame(sheet.getSprite(col,0), (1000/framesPerSecond));
			}
			
			this.reloadRotationX = idledRotationX;
			this.reloadRotationY = idledRotationY;
		} else if(animationName == "Aim"){
			aim = new Animation();
			aim.setAutoUpdate(true);
	
			for(int col=0;col<numberOfFrames;col++){
				aim.addFrame(sheet.getSprite(col,0), (1000/framesPerSecond));
			}
			
			this.aimRotationX = idledRotationX;
			this.aimRotationY = idledRotationY;
		} else if(animationName == "Idle"){
			idle = new Animation();
			idle.setAutoUpdate(true);
	
			for(int col=0;col<numberOfFrames;col++){
				idle.addFrame(sheet.getSprite(col,0), (1000/framesPerSecond));
			}
			
			this.idleRotationX = idledRotationX;
			this.idleRotationY = idledRotationY;
		} else if(animationName == "Aimed"){
			aimed = new Animation();
			aimed.setAutoUpdate(true);
	
			for(int col=0;col<numberOfFrames;col++){
				aimed.addFrame(sheet.getSprite(col,0), (1000/framesPerSecond));
			}
			
			this.aimedRotationX = idledRotationX;
			this.aimedRotationY = idledRotationY;
		} else if(animationName == "Empty"){
			empty = new Animation();
			empty.setAutoUpdate(true);
	
			for(int col=0;col<numberOfFrames;col++){
				empty.addFrame(sheet.getSprite(col,0), (1000/framesPerSecond));
			}
			
			this.emptyRotationX = idledRotationX;
			this.emptyRotationY = idledRotationY;
		}
	}

	public boolean isLoaded() {
		return mag != null;
	}
	
	public void loadMag(Magasine mag) {
		if(current!=reload || (current==reload && current.isStopped())){
			if(this.mag != null && mag != null){
				if(this.mag.currBullets <= 0){
					holder.weaponSlot.dropFromBackpack(this.mag, 0, 0);
					if(holder.holster.items.contains(mag)){
						holder.weaponSlot.addToBackpack(holder.holster.removeFromBackpack(mag), 0, 0);
					}
				} else {
					if(holder.holster.items.contains(mag)){
						InventoryObject tempInvObj = holder.weaponSlot.removeFromBackpack(this.mag);
						holder.weaponSlot.addToBackpack(holder.holster.swapToBackpack(mag,this.mag), 0, 0);
						System.out.println("swapToBackpack");
					}
				}
				
				
				this.mag = mag;
			} else if(this.mag == null && mag != null) {
				this.mag = mag;
				System.out.println("null mag thing ---------------------------------");
				holder.weaponSlot.addToBackpack(this.mag, 0, 0);
			}
			
			changeAnimation("Reload");
			current.restart();
		}
	}
	
	public boolean isEmptyMag() {
		if(mag != null && mag.isEmpty()){
			changeAnimation("Empty");
		}
		
		return (mag != null && mag.isEmpty());
	}
	
	public Magasine removeMag() {
		Magasine magasine = this.mag;
		this.mag = null;
		changeAnimation("Reload");
		current.restart();
		return magasine;
	}
	
	public void setHolder(Person holder) {
		this.holder = holder;
	}
	
	public boolean fire() {
		//System.out.println("mag.isEmpty()" +mag.isEmpty());
		if (mag != null && holder != null && !mag.isEmpty()) {
			//check for reloading
			if(current!=reload || (current==reload && current.isStopped())){
			//long timeInMillis = System.currentTimeMillis();
			//System.out.println("timeInMillis: " + timeInMillis);
			//System.out.println("lastTime: " + lastTime);
			//System.out.println("(((timeInMillis - lastTime)/1000)): " + (fireRate *(((timeInMillis - lastTime)/1000))));
			//System.out.println("");
			//if (((fireRate * (timeInMillis - lastTime))/1000)/60 >= 1) {
				//lastTime = timeInMillis;
				double shotAngle = spread*Math.random() + holder.currentRotation - spread/2;
				Play.projectiles.createProjectile(holder.Xpos, holder.Ypos, 4, (float) shotAngle, holder);
				changeAnimation("Shot");
				current.restart();
				return mag.fire();
			//}
			}
		} else {
			//System.out.println("mag: + " + mag);
		//	System.out.println("holder: + " + holder);
		}
		return false;
	}
	
}
