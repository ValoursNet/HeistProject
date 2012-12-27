package javagame;

import java.util.HashSet;

import person.Person;

public class Projectiles {
	
	HashSet<Bullet> bulletCollection = new HashSet<Bullet>();
	
	public Projectiles(){
		
	}
	
	public void update(){
		for (Bullet bullet : bulletCollection) {
			bullet.update();
		}
	}
	
	public void createProjectile(Map levelOne, double Xpos, double Ypos, double currentSpeed, float currentRotation, Person[] people){
		bulletCollection.add(new Bullet(levelOne, Xpos, Ypos, currentSpeed, currentRotation, people));
	}
	
	public void removeProjectile(Bullet bullet){
		bulletCollection.remove(bullet);
	}
	
	public HashSet<Bullet> getBulletCollection(){
		return bulletCollection;
	}
	
}
