package javagame;

import person.Person;

public class BloodSplat {

	Map levelOne;
	public double Xpos = 100;
	public double Ypos = 300;
	public float currentRotation = (float) 0;
	
	public BloodSplat (Map level, double Xpos, double Ypos, float currentRotation){
		this.levelOne = level;
		this.Xpos = Xpos;
		this.Ypos = Ypos;
		this.currentRotation = currentRotation;
	}
}
