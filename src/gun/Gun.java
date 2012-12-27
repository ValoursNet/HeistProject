package gun;

import javagame.Projectiles;
import person.Person;

public class Gun {
	
	Person holder;
	Projectiles projectiles;
	
	int magasineSize = 0;
	public int bulletCount  = 100;
	double fireRate = 0.1;
	boolean firing   = false;
	long lastTimeInMillis = System.currentTimeMillis();
	
	public Gun(Person holder, Projectiles projectiles) {
		this.holder = holder;
		this.projectiles = projectiles;
	}
	
	public boolean isFiring() {
		return firing;
	}
	
	public void fire() {
		long timeInMillis = System.currentTimeMillis();
		if (bulletCount > 0 && (fireRate * (timeInMillis - lastTimeInMillis) >= 1)) {
			firing = true;
			bulletCount--;
			projectiles.createProjectile(holder.Xpos, holder.Ypos, 2,
					holder.currentRotation, holder);
			lastTimeInMillis = timeInMillis;
		System.out.println("Slick is gay.");
		} 
		
	}
	
}
