package vision;

import java.awt.Point;

import person.Person;
import haxe.root.Array;
import haxe.root.Visibility;

public class Vision {
	
	public Person center;
	public Visibility visibility;
	
	private int size = 400;
	private int maxStorageColumn = size;
	private Boolean drawLights = false;
	private int gridMargin = 20;
	
	String rootId;
	double[][] walls;
	double[][] lights;
	double[][] blocks;
	
	
	double[][] mazeWalls = new double[][] {
		// Horizontal walls
		{20, 60, 60, 60}, {60, 60, 100, 60}, {100, 60, 140, 60}, {140, 60, 180, 60},
		{60, 100, 100, 100}, {100, 100, 140, 100},
		{260, 100, 300, 100}, {300, 100, 340, 100},
		{140, 140, 180, 140}, {180, 140, 220, 140},
		{300, 140, 340, 140}, {340, 140, 380, 140},
		{140, 260, 180, 260}, {180, 260, 220, 260},
		{215, 240, 225, 240}, {260, 220, 275, 220},
		// Vertical walls
		{300, 20, 300, 60},
		{180, 60, 180, 100}, {180, 100, 180, 140},
		{260, 60, 260, 100}, {340, 60, 340, 100},
		{180, 140, 180, 180}, {180, 180, 180, 220},
		{260, 140, 260, 180}, {260, 180, 260, 220},
		{140, 220, 140, 260}, {140, 260, 140, 300}, {140, 300, 140, 340},
		{220, 240, 220, 260}, {220, 340, 220, 380},
		// Wall with holes
		{220, 260, 220, 268}, {220, 270, 220, 278}, {220, 280, 220, 288},
		{220, 290, 220, 298}, {220, 300, 220, 308}, {220, 310, 220, 318},
		{220, 320, 220, 328}, {220, 330, 220, 338},
		// Pillars
		{210, 70, 230, 70}, {230, 70, 230, 90}, {230, 90, 222, 90}, {218, 90, 210, 90}, {210, 90, 210, 70},
		{51, 240, 60, 231}, {60, 231, 69, 240}, {69, 240, 60, 249}, {60, 249, 51, 240},
		// Curves
		{20, 140, 50, 140}, {50, 140, 80, 150}, {80, 150, 95, 180}, {95, 180, 100, 220},
		{100, 220, 100, 260}, {100, 260, 95, 300}, {95, 300, 80, 330},
		{300, 180, 320, 220}, {320, 220, 320, 240}, {320, 240, 310, 260},
		{310, 260, 305, 275}, {305, 275, 300, 300}, {300, 300, 300, 310},
		{300, 310, 305, 330}, {305, 330, 330, 350}, {330, 350, 360, 360},
	};

	double[][] mazeLights = new double[][] {
		// top hallway
		{40, 59}, {80, 21}, {120, 59}, {160, 21},
		{297, 23}, {303, 23}, {377, 23},
		{263, 97}, {337, 97},
		// upper left room
		{23, 63}, {177, 63}, {23, 137}, {177, 137},
		// round room on left
		{45, 235}, {45, 240}, {45, 245},
		// upper pillar
		{220, 80},
		// hallway on left
		{120, 280},
		// next to wall notch
		{217, 243},
		// inside room with holes
		{180, 290}, {180, 320}, {180, 350},
		// right curved room
		{320, 320},
		// right hallway
		{270, 170},
	};
	
	public Vision(Person center){
		this.center = center;
		makeFirstDiagram();
		redrawAll();
	}
	
	private void makeFirstDiagram() {
		String bla = "maze";
		makeDiagram(bla, mazeWalls, mazeLights);
		//makeDiagram(bla, {size: 400}, {x: 200, y: 200, r: 10}, [], mazeWalls, mazeLights);
	}

	private void makeDiagram(String rootId, double[][] mazeWalls, double[][] mazeLights) {		
		visibility = new Visibility();
		
		
	}
	
	public void redrawAll() {
		
		Array<Object> asdasd = new Array();
		
		ArrayConverter arrCon = new ArrayConverter();
		
		 visibility.loadMap(size, gridMargin,
				 asdasd,
				 //blocks.filter(function (block) { return block.x - block.r < size; }),
				 arrCon.doubleIntoObject(mazeWalls)
                 //walls.map(function (wall) { return {p1: {x: wall[0], y: wall[1]}, p2: {x: wall[2], y: wall[3]}}})
                );
		
		
		//int asdasdasd = visibility.output[0];
		
		visibility.setLightLocation(center.Xpos, center.Ypos);
		visibility.sweep(getMaxAngle());
		
		//redraw();
	}
	
	public VisibilityPath computeVisibleAreaPathsFloor() {
		Array<Object> output = visibility.output;
		
		VisibilityPath path1 = new VisibilityPath();
		VisibilityPath path2 = new VisibilityPath();
		VisibilityPath path3 = new VisibilityPath();
		
        for (int i = 0; i < output.length; i += 2) {
        	Point p1 = new Point();
        	p1.x = ((int) (haxe.lang.Runtime.getField_f(output.__get(i), "x", true)) );
        	p1.y = ((int) (haxe.lang.Runtime.getField_f(output.__get(i), "y", true)) );
            //Point p1 = (Point) output.__get(i);
        	Point p2 = new Point();
        	p2.x = ((int) (haxe.lang.Runtime.getField_f(output.__get(i+1), "x", true)) );
        	p2.y = ((int) (haxe.lang.Runtime.getField_f(output.__get(i+1), "y", true)) );
            //Point p2 = (Point) output.__get(i+1);
            if (isNaN(p1.x) || isNaN(p1.y) || isNaN(p2.x) || isNaN(p2.y)) {
                // These are collinear points that Visibility.hx
                // doesn't output properly. The triangle has zero area
                // so we can skip it.
                continue;
            }

            path1.push("L", p1.x, p1.y);
            System.out.println("# L x:"+ p1.x + " y:" + p1.y);
            path1.push("L", p2.x, p2.y);
            System.out.println("# L x:"+ p2.x + " y:" + p2.y);
            
            path2.push("M", center.Xpos, center.Ypos);
            path2.push("L", p1.x, p1.y);
            path2.push("M", center.Xpos, center.Ypos);
            path2.push("L", p2.x, p2.y);
            
            path3.push("M", p1.x, p1.y);
            path3.push("L", p2.x, p2.y);
        }

        return path1;
       // return {floor: path1, triangles: path2, walls: path3};
        
    }
	
	
	private void redraw() {
		//VisibilityPath paths = computeVisibleAreaPathsFloor(center, visibility.output);
	    
	    //drawFloor(g, paths);
		
		
		//drawBlocks(g);
	}

	private boolean isNaN(int x) {
		// TODO Auto-generated method stub
		return false;
	}

	private double getMaxAngle() {
        // The max angle is PI
        return Math.PI;
    }
}
