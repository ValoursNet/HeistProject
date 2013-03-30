package Inventory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class InventoryParser {
	
	@SuppressWarnings("resource")
	public InventoryObject parseFile(String filePath) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String text = "";
			String line = "";
			while ((line = br.readLine()) != null) {
				text += line;
			}
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(text);
			JsonObject obj = element.getAsJsonObject();
			String type = obj.get("type").getAsString();
			if (type.equals("gun")) {
				String name = obj.get("name").getAsString();
				String description = obj.get("description").getAsString();
				int size = obj.get("size").getAsInt();
				String inventoryPath = obj.get("inventoryImage").getAsString();
				String inGamePath = obj.get("inGameImage").getAsString();
				Image inventoryImage = new Image(inventoryPath);
				Image inGameImage = new Image(inGamePath);
				//Gun gun = new Gun(name, description, inventoryImage, inGameImage, size);
				//return gun;
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
