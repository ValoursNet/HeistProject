package server;

import java.util.HashSet;

import javagame.Bullet;
import javagame.Play;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class Multiplayer {

	public static HashSet<Bullet> bullets = new HashSet<Bullet>();

	public static void update(Server server) {
		if (bullets.size() > 0) {
			JsonArray array = new JsonArray();
			for (Bullet bullet : bullets) {
				JsonObject obj = new JsonObject();
				obj.add("XPOS", new JsonPrimitive(bullet.Xpos));
				obj.add("YPOS", new JsonPrimitive(bullet.Ypos));
				obj.add("CURRENTSPEED", new JsonPrimitive(bullet.currentSpeed));
				obj.add("CURRENTROTATION", new JsonPrimitive(bullet.currentRotation));
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
	}

}
