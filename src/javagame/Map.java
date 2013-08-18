package javagame;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import map.Building;
import map.MapParser;
import map.Room;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.pathfinding.TileBasedMap;
import org.newdawn.slick.util.pathfinding.navmesh.NavMesh;
import org.newdawn.slick.util.pathfinding.navmesh.NavMeshBuilder;
import org.newdawn.slick.util.pathfinding.navmesh.NavPath;
import org.newdawn.slick.util.pathfinding.navmesh.Space;

import Inventory.GroundObjects;
import Inventory.Gun;
import Inventory.InventoryParser;
import Inventory.Magasine;
import Inventory.WeaponCreator;

import person.Cop;
import person.Person;

public class Map {

	int mapWidth = 20;
	int mapHeight = 20;
	public int tileSize = 100;
	int[][] mapCollisions;
	
	public Building buildingOne = new Building(50, 50);
	
	public GroundObjects groundObjects = new GroundObjects();
	
	public WeaponCreator weaponCreator;// = new WeaponCreator();

	String path = Map.class.getProtectionDomain().getCodeSource().getLocation()
			.getPath();
	String mapsDirectory = "mapsDirectory";

	String openMapArrayTag = "<newMap>";
	String closeMapArrayTag = "</newMap>";

	String openPatrolArrayTag = "<patrolPath>";
	String closePatrolArrayTag = "</patrolPath>";
	public EffectCollection effectCollection;

	
	NavMesh navigationMesh = new NavMesh();
	TileMap meshTileMap;
	
	Person targetUnit;
	
	//UNUSED CURRENTLY. SEE BELOw
	public Map() {
		//navigationMesh(new Space(mapHeight, mapHeight, mapHeight, mapHeight));
		
		
		mapCollisions = createMap();

		mapCollisions[0][1] = 1;
		mapCollisions[0][2] = 1;

		mapCollisions[2][2] = 1;

		mapCollisions[8][8] = 2;

		// printMapCollisions();

		
		addItemsToGround();
		
		System.out.println("path: " + path);
		createDirectory();
		// writefile();
		readTextFile(path + mapsDirectory + "/map1.txt");
	}
	
	private void addItemsToGround(){
		LoadingList.setDeferredLoading(true);
		try {
			groundObjects.addToGround(new Magasine("Stanag", "M4 Stanag", "M4 Stanag mag", new Image("res/Stanag.png"), new Image("res/StanagGround.png"), 1, 10, 30), 290, 300);
			System.out.println("Added ground objs");
		} catch (SlickException e) {
			System.out.println("Failed to add ground objs");
			e.printStackTrace();
		}
		LoadingList.setDeferredLoading(false);
	}

	public Map(Projectiles projectiles) {
		
		
		MapParser mapParser = new MapParser();
		buildingOne = (Building) mapParser.parseFile("map/map1.json");
		
		/*
		Room roomOne = new Room(1,0,3,3);
		Room roomTwo = new Room(4,0,2,2);
		buildingOne.addRoom(roomOne);
		buildingOne.addRoom(roomTwo);
		*/
		
		mapCollisions = createMap();
		//createDirectory();
		// writefile();
		readTextFile(path + mapsDirectory + "/map1.txt");
		
		
		meshTileMap = new TileMap(mapCollisions);
		System.out.println("meshTileMap: " + meshTileMap.getWidthInTiles());
		
		
		//populateNavMesh();
		NavMeshBuilder asdfasdfasdf = new NavMeshBuilder();
		//navigationMesh = asdfasdfasdf.build(meshTileMap);
		
		navigationMesh = new NavMesh();
		
		
		ArrayList spaces = new ArrayList();
		
		for (Room room : buildingOne.roomCollection) {
			ArrayList roomSpaces = new ArrayList();
			for (int i = 0; i < room.height; i++) {
				for (int j = 0; j < room.width; j++) {
					//Space roomSpace = new Space(room.positionX + i, room.positionY + j, room.width, room.height);
					Space roomSpace = new Space(room.positionX + j, room.positionY + i, 1, 1);
					spaces.add(roomSpace);
					roomSpaces.add(roomSpace);
				}
			}
			//while (mergeSpaces(roomSpaces)) {}
			
			
			linkSpaces(roomSpaces);
					
			//navigationMesh.addSpace(roomSpace);
		}
		
		
		
		
		navigationMesh = new NavMesh(spaces);
		
		
		
		linkHorizontalDoors();
		linkVerticalDoors();
		
		
		
		System.out.println("navigationMesh.findSpace(0, 0): " + navigationMesh.findSpace(2, 3).getLinkCount());
		
		
		//NavPath goobo = navigationMesh.findPath(1, 1, 5, 5, true);
		
		//System.out.println("navigationMesh.getSpace(0): " + navigationMesh.getSpace(2));
		
		//System.out.println("navigationMesh.getSpace(0): " + navigationMesh.getSpace(1).getCost());
		//System.out.println("goobo: " + (goobo == null));
		//System.out.println("goobo: " + goobo.getX(1));
		
		addItemsToGround();
		//addExampleCop(projectiles);
	}
	
	private boolean mergeSpaces(ArrayList spaces) {
		for (int source=0;source<spaces.size();source++) {
			Space a = (Space) spaces.get(source);

			for (int target=source+1;target<spaces.size();target++) {
				Space b = (Space) spaces.get(target);

				if (a.canMerge(b)) {
					spaces.remove(a);
					spaces.remove(b);
					spaces.add(a.merge(b));
					return true;
				}
			}
		}

		return false;
	}

	
	private void linkVerticalDoors() {
		for (int i = 0; i < buildingOne.verticalDoorCollection.length; i++) {
			for (int j = 0; j < buildingOne.verticalDoorCollection[i].length; j++) {
				if(buildingOne.verticalDoorCollection[i][j] == 1){
					ArrayList spaces = new ArrayList();
					Space spaceOne = navigationMesh.findSpace(i, j);
					Space spaceTwo = navigationMesh.findSpace(i-1, j);
					
					spaces.add(spaceOne);
					spaces.add(spaceTwo);
					
					System.out.println(navigationMesh.findSpace(i, j));
					System.out.println(navigationMesh.findSpace(i-1, j));
					
					if(spaceOne != null && spaceTwo != null){
						linkSpaces(spaces);
						System.out.println("linked");
					}
				}
			}
		}
	}
	
	private void linkHorizontalDoors() {
		for (int i = 0; i < buildingOne.horizontalDoorCollection.length; i++) {
			for (int j = 0; j < buildingOne.horizontalDoorCollection[i].length; j++) {
				if(buildingOne.horizontalDoorCollection[i][j] == 1){
					ArrayList spaces = new ArrayList();
					Space spaceOne = navigationMesh.findSpace(j, i);
					Space spaceTwo = navigationMesh.findSpace(j, i-1);
					
					spaces.add(spaceOne);
					spaces.add(spaceTwo);
					
					System.out.println(navigationMesh.findSpace(j, i));
					System.out.println(navigationMesh.findSpace(j, i-1));
					
					if(spaceOne != null && spaceTwo != null){
						linkSpaces(spaces);
						System.out.println("linked");
					}
				}
			}
		}
	}

	private void linkSpaces(ArrayList spaces) {
		for (int source=0;source<spaces.size();source++) {
			Space a = (Space) spaces.get(source);

			for (int target=source+1;target<spaces.size();target++) {
				Space b = (Space) spaces.get(target);

				if (a.hasJoinedEdge(b)) {
					a.link(b);
					b.link(a);
				}
			}
		}
	}
	
	public NavPath findPath(int sX, int sY, int eX, int eY){
		NavPath goobo = navigationMesh.findPath(sX, sY, eX, eY, false);
		
		return goobo;
	}
	
	private void populateNavMesh(){
		for (int i = 0; i < mapCollisions.length; i++) {
			for (int j = 0; j < mapCollisions[i].length; j++) {
				Space newSpace = new Space(j*tileSize, i*tileSize, tileSize, tileSize);
				newSpace.clearCost();
				navigationMesh.addSpace(newSpace);
			}
		}
		
	}

	private void addExampleCop(Projectiles projectiles){
		double[][] path = new double[2][3];
		
		path[0][0] = 4*(tileSize + tileSize/2);
		path[0][1] = 4*(tileSize + tileSize/2);
		path[0][2] = 0;
		
		path[1][0] = 15*(tileSize + tileSize/2);
		path[1][1] = 2*(tileSize + tileSize/2);
		path[1][2] = 0;
		
		/*
		path[2][0] = 2*(tileSize + tileSize/2);
		path[2][1] = 5*(tileSize + tileSize/2);
		path[2][2] = 0;
		
		path[3][0] = 5*(tileSize + tileSize/2);
		path[3][1] = 5*(tileSize + tileSize/2);
		path[3][2] = 0;
		 */
		
		addCop(path,projectiles);
	}
	
	private void readTextFile(String aFileName){
		
		FileReader fr = null;
		try {
			fr = new FileReader(aFileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(fr != null){
			BufferedReader br = new BufferedReader(fr);
		
		   //try(br) {
		
			System.out.println("File read");
			String sCurrentLine;

			boolean readPatrol = false;
			List<String> readPatrolLines = new ArrayList<String>();
			String readPatrolTemp = null;

			boolean readMap = false;
			List<String> readMapLines = new ArrayList<String>();
			String readMapTemp = null;

			try {
				while ((sCurrentLine = br.readLine()) != null) {
					if (readMap) {
						readMapTemp = sCurrentLine;
						readMapLines.add(readMapTemp);
					}
					if (sCurrentLine.equals(openMapArrayTag)) {
						System.out.println("newMap:" + sCurrentLine);
						readMap = true;
					}
					if (sCurrentLine.equals(closeMapArrayTag)) {
						System.out.println("/newMap:" + sCurrentLine);
						readMap = false;
						readMapLines.remove(readMapTemp);
					}

					if (readPatrol) {
						readPatrolTemp = sCurrentLine;
						readPatrolLines.add(readPatrolTemp);
					}
					if (sCurrentLine.equals(openPatrolArrayTag)) {
						System.out.println("newPatrol:" + sCurrentLine);
						readPatrol = true;
					}
					if (sCurrentLine.equals(closePatrolArrayTag)) {
						System.out.println("/newPatrol:" + sCurrentLine);
						readPatrol = false;
						readPatrolLines.remove(readPatrolTemp);
					}
					// System.out.println(sCurrentLine);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			useReadMap(readMapLines);

			useReadPatrol(readPatrolLines);
		} else {
		//} catch (IOException e) {
			System.out.println("File could not be read");
			//e.printStackTrace();
		}
		
	}

	// Overwrites MapCollisions with ArrayList that was read in.
	private void useReadMap(List<String> readMapLines) {
		mapWidth = readMapLines.get(0).length();
		mapHeight = readMapLines.size();
		int board[][] = new int[mapHeight][mapWidth];

		int ln = 0;
		for (String s : readMapLines) {
			// System.out.println("s:" + s);
			String[] strArray = s.split(",");
			strArray[strArray.length - 1] = strArray[strArray.length - 1]
					.replace(";", "");

			int[] intArray = new int[strArray.length];
			for (int i = 0; i < strArray.length; i++) {
				intArray[i] = Integer.parseInt(strArray[i]);
			}
			board[ln] = intArray;
			ln++;
		}
		// overwrite old map array with new
		mapCollisions = board;
	}

	private void useReadPatrol(List<String> readPatrolLines) {

		
		// double[][] path;// = new double[3][3];
		/*
		 * path[0] = new double[]{100,900,0}; path[1] = new double[]{100,100,0};
		 * path[2] = new double[]{100,900,0};
		 */
		//int index = 0;
		for (String s : readPatrolLines) {
			
			String tempS = (String) s.subSequence(2, s.length());

			String[] strArray = tempS.split("\\(");
			
			double[][] path = new double[strArray.length][3];
			
			for (int i = 0; i < strArray.length; i++) {
				strArray[i] = strArray[i]
						.substring(0, strArray[i].length() - 2);

				String[] tempStrArray = strArray[i].split(",");

				path[i][0] = Double.parseDouble(tempStrArray[0])*tileSize + tileSize/2;
				path[i][1] = Double.parseDouble(tempStrArray[1])*tileSize + tileSize/2;

				if (tempStrArray.length > 2) {
					path[i][2] = Double.parseDouble(tempStrArray[2]);
				} else {
					path[i][2] = 0;
				}
				
				/*
				for (Double d : path[i]) {
					d = d*tileSize;
				}
				*/
				//System.out.println("path[index]: " + path[index][0]);

			}

			addCop(path, null);
			//index = index + 1;
		}

	}

	private void addCop(double[][] path, Projectiles projectiles) {
		System.out.println("cop added in map");
		Cop policeUnit = null;
		
		Projectiles proj = projectiles;
		//Projectiles preds = new Projectiles();
		
		
		//System.out.println("path[index]: " + path[0][0]);
		
		try {
			policeUnit = new Cop(this, proj);
			policeUnit.targetUnit = targetUnit;
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		if (path != null) {
			//System.out.println("non null path");
			policeUnit.setPath(path);
			policeUnit.Xpos = path[0][0];
			policeUnit.Ypos = path[0][1];
		}

		Play.people.add(policeUnit);
		
	}

	@SuppressWarnings("unused")
	private void writefile() {

		try {
			Writer output = null;
			File file = new File(path + mapsDirectory + "/map1.txt");
			output = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file.getPath()), "UTF-8"));

			output.write(openMapArrayTag);
			((BufferedWriter) output).newLine();
			for (int i = 0; i < mapCollisions.length; i++) {
				for (int j = 0; j < mapCollisions[i].length; j++) {
					output.write("" + mapCollisions[i][j]);
					if (mapCollisions[i].length != j + 1) {
						output.write(",");
					}
				}
				output.write(";");
				((BufferedWriter) output).newLine();
			}
			output.write(closeMapArrayTag);

			output.close();
			System.out.println("File has been written");

		} catch (Exception e) {
			System.out.println("Could not create file");
		}
	}

	private void createDirectory() {
		File theDir = new File(path + mapsDirectory);

		// if the directory does not exist, create it
		if (!theDir.exists()) {
			System.out.println("creating directory: " + mapsDirectory);
			boolean result = theDir.mkdir();
			if (result) {
				System.out.println("DIR created");
			}

		}
	}

	private int[][] createMap() {
		int board[][] = new int[mapHeight][mapWidth];
		return board;
	}

	public boolean isColliding(double playerX, double playerY) {
		int xPos = (int) Math.floor((playerX) / tileSize);
		int yPos = (int) Math.floor((playerY) / tileSize);

		if (yPos < mapHeight && xPos < mapWidth && yPos >= 0 && xPos >= 0) {
			if (mapCollisions[yPos][xPos] != 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	public void showPos(double playerX, double playerY) {
		int xPos = (int) Math.floor((playerX) / tileSize);
		int yPos = (int) Math.floor((playerY + tileSize) / tileSize);

		System.out.println("x: " + xPos + "  yPos: " + yPos);
	}

	public int[][] getMap() {
		return mapCollisions;
	}

	@SuppressWarnings("unused")
	private void printMapCollisions() {
		for (int i = 0; i < mapCollisions.length; i++) {
			for (int j = 0; j < mapCollisions[i].length; j++) {
				System.out.print(" " + mapCollisions[i][j]);
			}
			System.out.println("");
		}
	}

	// Util - Should be moved to util class/package/object
	public Point getTileAtPoint(int tileSize, double gX, double gY) {
		int rX = (int) (gX / tileSize);
		int rY = (int) (gY / tileSize);
		;

		Point rP = new Point(rX, rY);
		return rP;
	}
}
