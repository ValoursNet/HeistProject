package javagame;

import java.util.HashSet;

public class EffectCollection {
	
	public  HashSet<BloodSplat> bloodCollection;
	
	public EffectCollection() {
		bloodCollection = new HashSet<BloodSplat>();
	}
	
	public void addBloodSplat(Map level, double Xpos, double Ypos, float currentRotation) {
		bloodCollection.add(new BloodSplat(level, Xpos, Ypos, currentRotation));
	}
	
}
