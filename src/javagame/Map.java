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

import org.newdawn.slick.SlickException;

import person.Cop;
import javagame.Play;

public class Map {

	int mapWidth = 20;
	int mapHeight = 20;
	public int tileSize = 40;
	int[][] mapCollisions;

	String path = Map.class.getProtectionDomain().getCodeSource().getLocation()
			.getPath();
	String mapsDirectory = "mapsDirectory";

	String openMapArrayTag = "<newMap>";
	String closeMapArrayTag = "</newMap>";

	String openPatrolArrayTag = "<patrolPath>";
	String closePatrolArrayTag = "</patrolPath>";

	public Map() {
		mapCollisions = createMap();

		mapCollisions[0][1] = 1;
		mapCollisions[0][2] = 1;

		mapCollisions[2][2] = 1;

		mapCollisions[8][8] = 2;

		// printMapCollisions();

		System.out.println("path: " + path);
		createDirectory();
		// writefile();
		readTextFile(path + mapsDirectory + "/map1.txt");
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

			addCop(path);
			//index = index + 1;
		}

	}

	private void addCop(double[][] path) {
		System.out.println("cop added in map");
		Cop policeUnit = null;
		
		Projectiles proj = Play.projectiles;
		//Projectiles preds = new Projectiles();
		
		
		//System.out.println("path[index]: " + path[0][0]);
		
		try {
			policeUnit = new Cop(this, proj);
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
