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
			String type = obj.get("Type").getAsString();
			if (type.equals("Gun")) {
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
				return gun;
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
	
	public void addAnimation(String animationType,Gun gGun, JsonObject animObj){
		String idleImagePath = animObj.get("Image").getAsString();
		Image idleImage = null;
		try {
			idleImage = new Image(idleImagePath);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int idleFramesPerSecond = animObj.get("FramesPerSecond").getAsInt();
		int idleNumberOfFrames = animObj.get("NumberOfFrames").getAsInt();
		int idleWidth = animObj.get("Width").getAsInt();
		int idleHeight = animObj.get("Height").getAsInt();		
		int idleRotationX = animObj.get("RotationX").getAsInt();
		int idleRotationY = animObj.get("RotationY").getAsInt();
		gGun.addAnimation(animationType,idleImage,idleWidth,idleHeight,idleNumberOfFrames, idleFramesPerSecond,idleRotationX,idleRotationY);
	}
	
}
