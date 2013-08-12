package javagame;

import org.newdawn.slick.util.pathfinding.PathFindingContext;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

public class TileMap implements TileBasedMap {

	
	int[][] mapCollisions;
	int tileSize = 60;
	
	TileMap(int[][] mapCollisions){
		this.mapCollisions = mapCollisions;
	}
	
	@Override
	public boolean blocked(PathFindingContext arg0, int arg1, int arg2) {
		
		
		//int gridY = Math.round(arg1/tileSize);
		//int gridX = Math.round(arg2/tileSize);
		
		
		//System.out.println("arg1:" + arg1);
		//System.out.println("arg2:" + arg2);
		
		if(mapCollisions[arg2][arg1] == 1){
			//System.out.println("blocked");
			return true;
		} else {
			//System.out.println("not blocked:" + mapCollisions[gridY][gridX]);
		}
		return false;
	}

	@Override
	public float getCost(PathFindingContext arg0, int arg1, int arg2) {
		return 1f;
	}

	@Override
	public int getHeightInTiles() {
		return mapCollisions.length;
	}

	@Override
	public int getWidthInTiles() {
		return mapCollisions[0].length;
	}

	@Override
	public void pathFinderVisited(int arg0, int arg1) {
		//TODO only a notification
	}

}
