package map;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javagame.Bullet;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MapParser {
	
	@SuppressWarnings("resource")
	public Building parseFile(String filePath) {
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
			String type = obj.get("Type").getAsString();
			if (type.equals("Map")) {
				System.out.println("new map found");
				
				Building building = null;				
				
				JsonArray buildingsCollection = obj.get("Buildings").getAsJsonArray();
				
				for (JsonElement obj1 : buildingsCollection) {
					int buildingHeight = obj1.getAsJsonObject().get("Height").getAsInt();
					int buildingWidth = obj1.getAsJsonObject().get("Width").getAsInt();
					
					building = new Building(buildingWidth,buildingHeight);
					
					
					JsonArray roomCollection = obj1.getAsJsonObject().get("Rooms").getAsJsonArray();
					for (JsonElement obj2 : roomCollection) {
						
						//int positionX = obj2.getAsJsonObject().get("Rooms").getAsJsonArray()[3].getAsInt();
						int positionX = obj2.getAsJsonObject().get("Position").getAsJsonArray().get(0).getAsInt();
						int positionY = obj2.getAsJsonObject().get("Position").getAsJsonArray().get(1).getAsInt();
						
						int width = obj2.getAsJsonObject().get("Width").getAsInt();
						int height = obj2.getAsJsonObject().get("Height").getAsInt();
						
						Room newRoom = new Room(positionX,positionY,width,height);						
						
						JsonElement obj3 = obj2.getAsJsonObject().get("Doors");
						if(obj3 != null){
							JsonArray doorCollection = obj3.getAsJsonArray();
							for (JsonElement obj4 : doorCollection) {
								//int orientation, int xPos, int yPos
								int orientation = obj4.getAsJsonObject().get("Orientation").getAsInt();
								int doorPositionX = obj4.getAsJsonObject().get("Position").getAsJsonArray().get(0).getAsInt();
								int doorPositionY = obj4.getAsJsonObject().get("Position").getAsJsonArray().get(1).getAsInt();
								newRoom.addDoor(orientation, doorPositionX, doorPositionY);
								System.out.println("door found");
							}
						}
						
						
						building.addRoom(newRoom);
						//int positionX, int positionY, int width, int height
						//Room newRoom = new Room();
						//building.addRoom(newRoom);
						//building.add
					}
				}
				
				/*
				String name = obj.get("Name").getAsString();
				String description = obj.get("Description").getAsString();
				int invWidth = obj.get("Width").getAsInt();
				int invHeight = obj.get("Height").getAsInt();
				int size = invWidth*invHeight;
				String inventoryPath = obj.get("InventoryImage").getAsString();
				String inGamePath = obj.get("GroundImage").getAsString();
				Image inventoryImage = new Image(inventoryPath);
				Image inGameImage = new Image(inGamePath);
				int fireRate = obj.get("RateOfFire").getAsInt();
				int spread = obj.get("Accuracy").getAsInt();
				int reloadTime = obj.get("ReloadTime").getAsInt();
				Gun gun = new Gun(name, description, inventoryImage, inGameImage, size, fireRate, spread, reloadTime);
				
				addAnimation("Idled", gun, obj.get("Animations").getAsJsonObject().get("Idled").getAsJsonObject());
				addAnimation("Shot", gun, obj.get("Animations").getAsJsonObject().get("Shot").getAsJsonObject());
				addAnimation("Reload", gun, obj.get("Animations").getAsJsonObject().get("Reload").getAsJsonObject());
				addAnimation("Aim", gun, obj.get("Animations").getAsJsonObject().get("Aim").getAsJsonObject());
				addAnimation("Idle", gun, obj.get("Animations").getAsJsonObject().get("Idle").getAsJsonObject());
				addAnimation("Aimed", gun, obj.get("Animations").getAsJsonObject().get("Aimed").getAsJsonObject());
				addAnimation("Empty", gun, obj.get("Animations").getAsJsonObject().get("Empty").getAsJsonObject());
				
				System.out.println("asdasfsagdsgdsf");
				
				return gun;
				*/
				return building;
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
