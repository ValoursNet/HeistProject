package map;

public class Room {
	public int positionX = 1;
	public int positionY = 1;
	
	public int width = 3;
	public int height = 2;
	
	public int unitSize = 100;
	
	int[][] horizontalWallCollection;
	int[][] verticalWallCollection;
	
	public Room(int positionX, int positionY, int width, int height){
		this.positionX = positionX;
		this.positionY = positionY;
		this.width = width;
		this.height=height;
		populateWallCollection();
	}
	
	private void populateWallCollection(){
		horizontalWallCollection = new int[height+1][width];
		verticalWallCollection = new int[width+1][height];
		
		for (int i = 0; i < horizontalWallCollection[0].length; i++) {
			horizontalWallCollection[0][i] = 1;
			horizontalWallCollection[horizontalWallCollection.length-1][i] = 1;
		}
		
		for (int i = 0; i < verticalWallCollection[0].length; i++) {
			verticalWallCollection[0][i] = 1;
			verticalWallCollection[verticalWallCollection.length-1][i] = 1;
		}
	}
	
	void addDoor(int orientation, int xPos, int yPos){
		if(orientation == 1){
			horizontalWallCollection[yPos][xPos] = 0;
		} else if(orientation == 0){
			verticalWallCollection[xPos][yPos] = 0;
		}
	}
	
}
