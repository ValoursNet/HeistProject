package javagame;

import java.util.HashSet;

import person.Person;
import server.Multiplayer;

public class Projectiles {

	HashSet<Bullet> bulletCollection = new HashSet<Bullet>();
	Map level;

	public void update() {
		for (Bullet bullet : bulletCollection) {
			bullet.update();
		}
	}

	public void setMap(Map level) {
		this.level = level;
	}
	
	public void createProjectile(double Xpos, double Ypos,
			double currentSpeed, float currentRotation, Person person) {
		Bullet bullet = new Bullet(level, Xpos, Ypos, currentSpeed,currentRotation, person);
		bulletCollection.add(bullet);
		Multiplayer.bullets.add(bullet);
	}
	public void createDummyProjectile(double Xpos, double Ypos, double currentSpeed, float currentRotation) {
		Bullet bullet = new Bullet(level, Xpos, Ypos, currentSpeed,currentRotation, null);
		bullet.damage = 0;
		bullet.knockback = 0;
		bulletCollection.add(bullet);
	}

	public void removeProjectile(Bullet bullet) {
		bulletCollection.remove(bullet);
	}

	public HashSet<Bullet> getBulletCollection() {
		return bulletCollection;
	}

}
