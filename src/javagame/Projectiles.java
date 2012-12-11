package javagame;

public class Projectiles {
	
	Bullet[] bulletCollection = new Bullet[50];
	int bulletCount = 0;
	
	public Projectiles(){
		
	}
	
	public void update(){
		for (int i =0; i < bulletCount; i++) {
			bulletCollection[i].update();
		}
	}
	
	public void createProjectile(Map levelOne, double Xpos, double Ypos, double currentSpeed, float currentRotation){
		bulletCollection[bulletCount] = new Bullet(levelOne, Xpos, Ypos, Ypos, currentRotation);
		bulletCount++;
	}
	
	public void removeProjectile(int bulletIndex){
		bulletCollection[bulletIndex] = null;
		bulletCount--;
	}
	
	public Bullet[] getBulletCollection(){
		return bulletCollection;
	}
	
}
