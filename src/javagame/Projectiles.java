package javagame;

import java.util.HashSet;

import person.Person;

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
		bulletCollection.add(new Bullet(level, Xpos, Ypos, currentSpeed,currentRotation, person));
	}

	public void removeProjectile(Bullet bullet) {
		bulletCollection.remove(bullet);
	}

	public HashSet<Bullet> getBulletCollection() {
		return bulletCollection;
	}

}
