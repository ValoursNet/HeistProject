package javagame;

import java.util.HashSet;

import person.Person;
import server.Multiplayer;

public class Projectiles {

	HashSet<Bullet> bulletCollection = new HashSet<Bullet>();
	HashSet<Casing> casingCollection = new HashSet<Casing>();
	Map level;

	public void update() {
		synchronized (bulletCollection) {  
			for (Bullet bullet : bulletCollection) {
				bullet.update();
			}
		}
		synchronized (casingCollection) {  
			for (Casing casing : casingCollection) {
				casing.update();
			}
		}
	}

	public void setMap(Map level) {
		this.level = level;
	}
	
	public void createProjectile(double Xpos, double Ypos,
			double currentSpeed, float currentRotation, Person person) {
		Bullet bullet = new Bullet(level, Xpos, Ypos, currentSpeed,currentRotation, person);
		Casing casing = new Casing(level, Xpos, Ypos, 1, currentRotation+90, person);
		synchronized (bulletCollection) {  
			bulletCollection.add(bullet);
		}
		synchronized (casingCollection) {
			casingCollection.add(casing);
		}
		Multiplayer.bullets.add(bullet);
	}
	public void createDummyProjectile(double Xpos, double Ypos, double currentSpeed, float currentRotation, int holderID) {
		Bullet bullet = new Bullet(level, Xpos, Ypos, currentSpeed,currentRotation, holderID);
		//bullet.damage = 0;
		//bullet.knockback = 0;
		synchronized (bulletCollection) {  
			bulletCollection.add(bullet);
		}
	}

	public void removeProjectile(Bullet bullet) {
		bulletCollection.remove(bullet);
	}

	public HashSet<Bullet> getBulletCollection() {
		return bulletCollection;
	}

}
