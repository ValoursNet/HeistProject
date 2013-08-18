package map;

import java.util.HashSet;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Building {
	public int width;
	public int height;
	
	public int[][] horizontalWallCollection;
	public int[][] verticalWallCollection;
	
	public int[][] horizontalDoorCollection;
	public int[][] verticalDoorCollection;
	
	public HashSet<Room> roomCollection = new HashSet<Room>();
	public HashSet<Rectangle> wallCollection = new HashSet<Rectangle>();
	
	public Image interriorWallImage;
	public Image floorOneImage;
	
	public int unitSize = 100;
	
	public Building(int width, int height){
		this.width = width;
		this.height = height;
		
		horizontalWallCollection = new int[height+1][width];
		verticalWallCollection = new int[width+1][height];
		
		horizontalDoorCollection = new int[height+1][width];
		verticalDoorCollection = new int[width+1][height];

	}	
	
	public void addRoom(Room newRoom){
		roomCollection.add(newRoom);
		
		//walls
		combineHorizontalWallCollection(newRoom);
		combineVerticalWallCollection(newRoom);
		
		//doors
		combineVerticalDoorCollection(newRoom);
		combineHorizontalDoorCollection(newRoom);
		
		//doors
		removeVerticalWallsCollection();
		removeHorizontalWallsCollection();
	}
	
	private void removeVerticalWallsCollection(){
		for (int i = 0; i < verticalDoorCollection.length; i++) {
			int[] currentLine = verticalDoorCollection[i];
			for (int j = 0; j < currentLine.length; j++) {
				if( verticalDoorCollection[i][j] == 1){
					verticalWallCollection[i][j] = 0;
				}
			}
		}
	}
	
	private void removeHorizontalWallsCollection(){
		for (int i = 0; i < horizontalDoorCollection.length; i++) {
			int[] currentLine = horizontalDoorCollection[i];
			for (int j = 0; j < currentLine.length; j++) {
				if( horizontalDoorCollection[i][j] == 1){
					horizontalWallCollection[i][j] = 0;
				}
			}
		}
	}
	
	private void combineVerticalDoorCollection(Room newRoom){
		if(newRoom.verticalDoorCollection.length + newRoom.positionX < this.verticalDoorCollection.length){
			for (int i = 0; i < newRoom.verticalDoorCollection.length; i++) {
				int[] currentLine = newRoom.verticalDoorCollection[i];
				for (int j = 0; j < currentLine.length; j++) {
					if( newRoom.verticalDoorCollection[i][j] == 1){
						int wallX = i+newRoom.positionX;
						int wallY = j+newRoom.positionY;
						verticalDoorCollection[wallX][wallY] = 1;
						//wallCollection.add(new Rectangle(wallX*unitSize,wallY*unitSize,13,100));
					}
				}
			}
		}
	}
	
	private void combineHorizontalDoorCollection(Room newRoom){
		if(newRoom.horizontalDoorCollection.length + newRoom.positionY < this.horizontalDoorCollection.length){
			for (int i = 0; i < newRoom.horizontalDoorCollection.length; i++) {
				int[] currentLine = newRoom.horizontalDoorCollection[i];
				for (int j = 0; j < currentLine.length; j++) {
					if( newRoom.horizontalDoorCollection[i][j] == 1){
						int wallX = j+newRoom.positionX;
						int wallY = i+newRoom.positionY;
						horizontalDoorCollection[wallY][wallX] = 1;
						//wallCollection.add(new Rectangle(wallX*unitSize,wallY*unitSize,100,13));
					}
				}
			}
		}
	}
	
	private void combineVerticalWallCollection(Room newRoom){
		if(newRoom.verticalWallCollection.length + newRoom.positionX < this.verticalWallCollection.length){
			for (int i = 0; i < newRoom.verticalWallCollection.length; i++) {
				int[] currentLine = newRoom.verticalWallCollection[i];
				for (int j = 0; j < currentLine.length; j++) {
					if( newRoom.verticalWallCollection[i][j] == 1){
						int wallX = i+newRoom.positionX;
						int wallY = j+newRoom.positionY;
						verticalWallCollection[wallX][wallY] = 1;
						//wallCollection.add(new Rectangle(wallX*unitSize,wallY*unitSize,13,100));
					}
				}
			}
		}
	}
	
	private void combineHorizontalWallCollection(Room newRoom){
		if(newRoom.horizontalWallCollection.length + newRoom.positionY < this.horizontalWallCollection.length){
			for (int i = 0; i < newRoom.horizontalWallCollection.length; i++) {
				int[] currentLine = newRoom.horizontalWallCollection[i];
				for (int j = 0; j < currentLine.length; j++) {
					if( newRoom.horizontalWallCollection[i][j] == 1){
						int wallX = j+newRoom.positionX;
						int wallY = i+newRoom.positionY;
						horizontalWallCollection[wallY][wallX] = 1;
						//wallCollection.add(new Rectangle(wallX*unitSize,wallY*unitSize,100,13));
					}
				}
			}
		}
	}

	public void addWallsToCollection() {
		for (int i = 0; i < verticalWallCollection.length; i++) {
			int[] currentLine = verticalWallCollection[i];
			for (int j = 0; j < currentLine.length; j++) {
				if( verticalWallCollection[i][j] == 1){
					wallCollection.add(new Rectangle(i*unitSize,j*unitSize,13,100));
				}
			}
		}
			
		for (int k = 0; k < horizontalWallCollection.length; k++) {
			int[] currentLine = horizontalWallCollection[k];
			for (int l = 0; l < currentLine.length; l++) {
				if( horizontalWallCollection[k][l] == 1){
					wallCollection.add(new Rectangle(l*unitSize,k*unitSize,100,13));
				}
			}
		}
		
	}
	
}
