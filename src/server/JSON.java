package server;

import org.newdawn.slick.SlickException;

import person.Criminal;
import person.Person;
import javagame.Map;
import javagame.Play;
import javagame.Projectiles;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@SuppressWarnings("unused")
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
		int holderID = obj.get("HOLDERID").getAsInt();
		projectiles.createDummyProjectile(xpos, ypos, currentSpeed, currentRotation, holderID);
	}
	
	//Suppressing function that is not used.
	//@SuppressWarnings("unused")
	private static void parsePerson(JsonObject obj, Projectiles projectiles) {
		int id = obj.get("ID").getAsInt();
		//int playerType = obj.get("PLAYERTYPE").getAsInt();
		int packetId = obj.get("PID").getAsInt();
		double xpos = obj.get("XPOS").getAsDouble();
		double ypos = obj.get("YPOS").getAsDouble();
		float currentRotation = obj.get("CURRENTROTATION").getAsFloat();
		boolean newPerson = true;
		for (Person person : Play.people) {
			if (person.id == id) {
					newPerson = false;
				if(!person.directControl){
					if(packetId>person.packetId){
						person.packetId = packetId;
						person.Xpos = xpos;
						person.Ypos = ypos;
						person.currentRotation = currentRotation;
					} else {
						System.out.println("old packet: " + packetId + " > person.packetId:" + person.packetId);
					}
				}
			}
		}
		
		if(newPerson){
			Criminal nPerson = null;
			try {
				nPerson = new Criminal(Play.levelOne, projectiles);
				nPerson.id = id;
				nPerson.Xpos = xpos;
				nPerson.Ypos = ypos;
				nPerson.currentRotation = currentRotation;
				Play.people.add(nPerson);
				
				System.out.println("added New Person");
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
