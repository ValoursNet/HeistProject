package javagame;

import java.awt.Point;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class Map {
	
	int mapWidth = 20;
	int mapHeight = 20;
	public int tileSize = 60;
	int[][] mapCollisions;
	
	String path = Map.class.getProtectionDomain().getCodeSource().getLocation().getPath();
	String mapsDirectory = "mapsDirectory";
	
	public Map(){
		mapCollisions = createMap();
		
		mapCollisions[0][1] = 1;
		mapCollisions[0][2] = 1;
		
		mapCollisions[2][2] = 1;
		
		mapCollisions[8][8] = 2;
		
		printMapCollisions();
		
		System.out.println("path: " + path);
		createDirectory();
		writefile();
		
	}
	
	private void writefile(){

	    try{
	        Writer output = null;
	        File file = new File(path + mapsDirectory + "/map1.txt");
	        output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream( file.getPath() ),"UTF-8"));
	        
	        output.write("<newMap>");
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
	        output.write("</newMap>");

	        output.close();
	        System.out.println("File has been written");

	    }catch(Exception e){
	        System.out.println("Could not create file");
	    }
	}
	
	private void createDirectory(){
		File theDir = new File(path + mapsDirectory);

	  // if the directory does not exist, create it
	  if (!theDir.exists())
	  {
	    System.out.println("creating directory: " + mapsDirectory);
	    boolean result = theDir.mkdir();  
	    if(result){    
	       System.out.println("DIR created");  
	     }

	  }
	}
	
	private int[][] createMap(){
		int board[][] = new int[mapHeight][mapWidth];
		return board;
	}
	
	public boolean isColliding(double playerX, double playerY){
		int xPos = (int) Math.floor((playerX)/tileSize);
		int yPos = (int) Math.floor((playerY)/tileSize);
		
		if(yPos < mapHeight && xPos < mapWidth && yPos >= 0 && xPos >= 0){
			if(mapCollisions[yPos][xPos] != 0){
				return true;	
			} else {
				return false;
			}
		} else {
			return true;
		}
	}
	
	public void showPos(double playerX, double playerY){
		int xPos = (int) Math.floor((playerX)/tileSize);
		int yPos = (int) Math.floor((playerY+tileSize)/tileSize);

		System.out.println("x: "+xPos+"  yPos: "+yPos);
	}
	
	public int[][] getMap(){
		return mapCollisions;
	}
	
	private void printMapCollisions(){
		for (int i =0; i < mapCollisions.length; i++) {
			for (int j = 0; j < mapCollisions[i].length; j++) {
				System.out.print(" " + mapCollisions[i][j]);
			}
		System.out.println("");
		}
	}
	
	//Util - Should be moved to util class/package/object
	public Point getTileAtPoint(int tileSize, double gX, double gY){
		int rX = (int) (gX/tileSize);
		int rY = (int) (gY/tileSize);;

		Point rP = new Point(rX,rY);
		return rP;
	}
}
