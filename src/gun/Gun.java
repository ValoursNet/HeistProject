package gun;

import javagame.Projectiles;
import person.Person;

public class Gun {
	
	Person holder;
	Projectiles projectiles;
	
	int magasineSize = 0;
	
	public int bulletMagCount  = 30;
	public int bulletCount  = bulletMagCount;
	double fireRate = 0.01;
	
	double spread = 7;
	double shotAngle = 0;
	
	double reloadTime = 3500;
	double reloadCount = 0;

	boolean firing   = false;
	long lastTimeInMillis = System.currentTimeMillis();
	
	long startReloadTime = 0;
	long timeReloading = 0;
	
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
			
			shotAngle = spread*Math.random() + holder.currentRotation - spread/2;
			projectiles.createProjectile(holder.Xpos, holder.Ypos, 2, (float) shotAngle, holder);
			
			lastTimeInMillis = timeInMillis;
		} else if (bulletCount == 1) {
			startReloadTime = timeInMillis;
		} else if (bulletCount <= 0) {
			timeReloading = timeInMillis - startReloadTime;
			
			if(reloadTime < timeReloading){
				bulletCount = bulletMagCount;
				startReloadTime = 0;
			}
		}
		
	}
	
}
