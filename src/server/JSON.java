package server;

import person.Person;
import javagame.Play;
import javagame.Projectiles;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

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
				parsePerson(obj);
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

	private static void parsePerson(JsonObject obj) {
		int id = obj.get("ID").getAsInt();
		int playerType = obj.get("PLAYERTYPE").getAsInt();
		double xpos = obj.get("XPOS").getAsDouble();
		double ypos = obj.get("YPOS").getAsDouble();
		float currentRotation = obj.get("CURRENTROTATION").getAsFloat();
		for (Person person : Play.people) {
			if (person.id == id) {
				person.Xpos = xpos;
				person.Ypos = ypos;
				person.currentRotation = currentRotation;
			}
		}
	}

}
