package server;

import org.newdawn.slick.SlickException;

import person.Criminal;
import person.Person;
import javagame.Map;
import javagame.Play;
import javagame.Projectiles;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

//@SuppressWarnings("unused")
public class JSON {

	public static void parseJson(JsonElement element, Projectiles projectiles) {
		for (JsonElement json : element.getAsJsonArray()) {
			JsonObject obj = json.getAsJsonObject();
			
			//parseBullet(obj, projectiles);
			
			//Null error as we are not sending or recieving type atm.
			//Send type later.
			
			int type = obj.get("TYPE").getAsInt();
			switch (type) {
			case 1:
				parseBullet(obj, projectiles);
				break;
			case 2:
				parsePerson(obj, projectiles);
				break;
			default:
				System.out.println("Unkown object.");
			}
			
		}
	}

	private static void parseBullet(JsonObject obj, Projectiles projectiles) {
		double xpos = obj.get("XPOS").getAsDouble();
		double ypos = obj.get("YPOS").getAsDouble();
		double currentSpeed = obj.get("CURRENTSPEED").getAsDouble();
		float currentRotation = obj.get("CURRENTROTATION").getAsFloat();
		projectiles.createDummyProjectile(xpos, ypos, currentSpeed, currentRotation);
	}
	
	//Suppressing function that is not used.
	//@SuppressWarnings("unused")
	private static void parsePerson(JsonObject obj, Projectiles projectiles) {
		int id = obj.get("ID").getAsInt();
		//int playerType = obj.get("PLAYERTYPE").getAsInt();
		double xpos = obj.get("XPOS").getAsDouble();
		double ypos = obj.get("YPOS").getAsDouble();
		float currentRotation = obj.get("CURRENTROTATION").getAsFloat();
		boolean newPerson = true;
		for (Person person : Play.people) {
			if (person.id == id) {
				newPerson = false;
				person.Xpos = xpos;
				person.Ypos = ypos;
				person.currentRotation = currentRotation;
			}
		}
		
		if(newPerson){
			
			Criminal nPerson = null;
			try {
				Map levelOne = Play.people.iterator().next().levelOne;
				nPerson = new Criminal(levelOne, projectiles);
				
				nPerson.id = id;
				nPerson.Xpos = xpos;
				nPerson.Ypos = ypos;
				nPerson.currentRotation = currentRotation;
				//nPerson.multiplayerPerson = true;
				synchronized(Play.people){
					Play.people.add(nPerson);
				}
				
				System.out.println("added New Person");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
