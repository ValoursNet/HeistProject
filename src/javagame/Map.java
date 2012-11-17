package javagame;

public class Map {
	
	int mapWidth = 8;
	int mapHeight = 8;
	int tileSize = 50;
	int[][] mapCollisions;
	
	public Map(){
		mapCollisions = createMap();
		
		mapCollisions[0][1] = 1;
		mapCollisions[0][2] = 1;
		
		printMapCollisions();
	}
	
	//Doesn't need them zeros.
	private int[][] createMap(){
		int board[][] = new int[mapHeight][mapWidth]; // 8x8
        
		for (int i=0; i < mapHeight; i++){
		   for (int j=0; j < mapWidth; j++){
		      board[i][j] = 0;
		   }
		}
		return board;
	}
	
	public boolean isColliding(double playerX, double playerY){
		int xPos = (int) Math.floor((playerX)/tileSize);
		int yPos = (int) Math.floor((playerY+tileSize)/tileSize);
		
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
	
	private void printMapCollisions(){
		for (int i =0; i < mapCollisions.length; i++) {
			for (int j = 0; j < mapCollisions[i].length; j++) {
				System.out.print(" " + mapCollisions[i][j]);
			}
		System.out.println("");
		}
	}
}
