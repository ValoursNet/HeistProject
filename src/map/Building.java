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
		
		//horizontalWallCollection[0][0] = 1;
		//verticalWallCollection[0][1] = 1;
	}	
	
	public void addRoom(Room newRoom){
		roomCollection.add(newRoom);
		combineHorizontalWallCollection(newRoom);
		combineVerticalWallCollection(newRoom);
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
						wallCollection.add(new Rectangle(wallX*unitSize,wallY*unitSize,13,100));
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
						wallCollection.add(new Rectangle(wallX*unitSize,wallY*unitSize,100,13));
					}
				}
			}
		}
	}
}
