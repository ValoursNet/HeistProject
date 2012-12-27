package javagame;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

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

		//printMapCollisions();

		System.out.println("path: " + path);
		createDirectory();
		writefile();
		//readTextFile(path + mapsDirectory + "/map1.txt");
	}

	 private void readTextFile(String aFileName) {
		 try (BufferedReader br = new BufferedReader(new FileReader(aFileName)))
			{
			 System.out.println("File read");
				String sCurrentLine;
				
				boolean readPatrol = false;
				List<String> readPatrolLines = new ArrayList<String>();
		        String readPatrolTemp = null;
				
				boolean readMap = false;
				List<String> readMapLines = new ArrayList<String>();
		        String readMapTemp = null;
				
				while ((sCurrentLine = br.readLine()) != null) {
					if(readMap){
						readMapTemp = sCurrentLine;
						readMapLines.add(readMapTemp);
					}
					if(sCurrentLine.equals(openMapArrayTag)){
						System.out.println("newMap:" + sCurrentLine);
						readMap = true;
					}
					if(sCurrentLine.equals(closeMapArrayTag)){
						System.out.println("/newMap:" + sCurrentLine);
						readMap = false;
						readMapLines.remove(readMapTemp);
					}
					
					
					if(readPatrol){
						readPatrolTemp = sCurrentLine;
						readPatrolLines.add(readPatrolTemp);
					}
					if(sCurrentLine.equals(openPatrolArrayTag)){
						System.out.println("newPatrol:" + sCurrentLine);
						readPatrol = true;
					}
					if(sCurrentLine.equals(closePatrolArrayTag)){
						System.out.println("/newPatrol:" + sCurrentLine);
						readPatrol = false;
						readPatrolLines.remove(readPatrolTemp);
					}
					//System.out.println(sCurrentLine);
				}
				
				useReadMap(readMapLines);
				
				useReadPatrol(readPatrolLines);
				
			} catch (IOException e) {
				System.out.println("File could not be read");
				e.printStackTrace();
			} 
	  }
	 
	 //Overwrites MapCollisions with ArrayList that was read in.
	 private void useReadMap(List<String> readMapLines){
		mapWidth = readMapLines.get(0).length();
		mapHeight = readMapLines.size();
		int board[][] = new int[mapHeight][mapWidth];
		
		int ln = 0;
		for (String s : readMapLines){
			//System.out.println("s:" + s);
			String[] strArray = s.split(",");
			strArray[strArray.length - 1] = strArray[strArray.length - 1].replace(";","");
			
			int[] intArray = new int[strArray.length];
			for(int i = 0; i < strArray.length; i++) {
			    intArray[i] = Integer.parseInt(strArray[i]);
			}
			board[ln] = intArray;
			ln++;
		}
		//overwrite old map array with new
		mapCollisions = board;
	 }
	 
	 @SuppressWarnings("unused")
	private void useReadPatrol(List<String> readPatrolLines){
		
		double[][] path;// = new double[3][3];
		/*
		path[0] = new double[]{100,900,0};
		path[1] = new double[]{100,100,0};
		path[2] = new double[]{100,900,0};
		*/
		
		for (String s : readPatrolLines){
			
			//String[] strArray = s.split(",");
			
			//s.replaceAll("[]","");
			String tempS = (String) s.subSequence(2, s.length());
			
			String[] strArray = tempS.split("\\(");
			
			
			for(int i=0; i<strArray.length; i++){
				//int i = 4;
				
				strArray[i] = strArray[i].substring(0, strArray[i].length() - 2);
				//strArray[i].replace("\\)","");
				
				System.out.println("s:" + strArray[i]);
				
				
				/*
				for(int j = 0; j< strArray.length; i++)
				{
					path[j] = Double.parseDouble(strArray[j]);
				}
				*/
			}
			
			//String[] strArray = s.split("(");
			
			//strArray[strArray.length-1] = strArray[strArray.length-1].substring(0, strArray[strArray.length-1].length() - 1);
			//System.out.println(strArray);
			
		}
		 	/*
		 	Point rP = new Point(rX, rY);
		 
			int ln = 0;
			for (String s : readPatrolLines){
				//System.out.println("s:" + s);
				String[] strArray = s.split(",");
				strArray[strArray.length - 1] = strArray[strArray.length - 1].replace(";","");
				
				int[] intArray = new int[strArray.length];
				for(int i = 0; i < strArray.length; i++) {
				    intArray[i] = Integer.parseInt(strArray[i]);
				}
				board[ln] = intArray;
				ln++;
			}
			*/
		 }
	
	@SuppressWarnings("unused")
	private void writefile(){

	    try{
	        Writer output = null;
	        File file = new File(path + mapsDirectory + "/map1.txt");
	        output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream( file.getPath() ),"UTF-8"));
	        
	        output.write(openMapArrayTag);
	        ((BufferedWriter) output).newLine();
	        for (int i =0; i < mapCollisions.length; i++) {
				for (int j = 0; j < mapCollisions[i].length; j++) {
					output.write("" + mapCollisions[i][j]);
					if(mapCollisions[i].length != j+1){
						output.write(",");
					}
				}
				output.write(";");
				((BufferedWriter) output).newLine();
			}
	        output.write(closeMapArrayTag);

	        output.close();
	        System.out.println("File has been written");

	    }catch(Exception e){
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
