package person;

import javagame.Map;

public class Criminal extends Person{
	
	public Criminal(Map glevelOne) {
		levelOne = glevelOne;
		System.out.println("CRIMINAL ERE");
		
		directControl = true;
	}
	
}