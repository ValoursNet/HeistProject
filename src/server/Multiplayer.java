package server;

import java.util.HashSet;

import person.Person;

import javagame.Bullet;
import javagame.Play;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class Multiplayer {

	public static HashSet<Bullet> bullets = new HashSet<Bullet>();
	public static HashSet<Person> people = new HashSet<Person>();

	public static void update(Server server) {
		if (bullets.size() > 0) {
			JsonArray array = new JsonArray();
			for (Bullet bullet : bullets) {
				JsonObject obj = new JsonObject();
				obj.add("TYPE", new JsonPrimitive(1));
				obj.add("XPOS", new JsonPrimitive(bullet.Xpos));
				obj.add("YPOS", new JsonPrimitive(bullet.Ypos));
				obj.add("CURRENTSPEED", new JsonPrimitive(bullet.currentSpeed));
				obj.add("CURRENTROTATION", new JsonPrimitive(bullet.currentRotation));
				obj.add("HOLDERID", new JsonPrimitive(bullet.shooter.id));
				JsonElement element = obj.getAsJsonObject();
				array.add(element);
			}
			if (Play.host) {
				server.sendToAll(array.toString());
			} else {
				Client.sendToAll(array.toString());
			}
			bullets = new HashSet<Bullet>();
		}
		

		if (people.size() > 0) {
			JsonArray array = new JsonArray();
			for (Person person : people) {
				if(person.directControl){
					JsonObject obj = new JsonObject();
					obj.add("TYPE", new JsonPrimitive(2));
					obj.add("ID", new JsonPrimitive(person.id));
					person.packetId = person.packetId + 1;
					obj.add("PID", new JsonPrimitive(person.packetId));
					obj.add("XPOS", new JsonPrimitive(person.Xpos));
					obj.add("YPOS", new JsonPrimitive(person.Ypos));
					//obj.add("CURRENTSPEED", new JsonPrimitive(bullet.currentSpeed));
					obj.add("CURRENTROTATION", new JsonPrimitive(person.currentRotation));
					JsonElement element = obj.getAsJsonObject();
					array.add(element);
				}
			}
			if (Play.host) {
				server.sendToAll(array.toString());
			} else {
				Client.sendToAll(array.toString());
			}
			people = new HashSet<Person>();
		}
	}

}
