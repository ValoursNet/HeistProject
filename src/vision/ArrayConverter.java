package vision;

import haxe.root.Array;

public class ArrayConverter {
	
	public double[][] objectIntoDouble(Array<Object> objectArray){
		double[][] returnIntCollection = new double[objectArray.length][4];
		
		for (int i = 0; i < objectArray.length; i ++) {
			Object wall = objectArray.__get(i);
			returnIntCollection[i][0] = ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(wall, "p1", true), "x", true)) );
			returnIntCollection[i][1] = ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(wall, "p1", true), "y", true)) );
			returnIntCollection[i][2] = ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(wall, "p2", true), "x", true)) );
			returnIntCollection[i][3] = ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(wall, "p2", true), "y", true)) );
		}
		
		return returnIntCollection;
	}
	
	public Array<Object> doubleIntoObject(double[][] doubleCollection){
		
		Array<Object> returnObjArray = new Array();
		
		System.out.println("doubleCollection");
		System.out.println(doubleCollection.length);
		
		for (int i = 0; i < doubleCollection.length; i ++) {
			
			double x1 = doubleCollection[i][0];
			double y1 = doubleCollection[i][1];
			
			double x2 = doubleCollection[i][2];
			double y2 = doubleCollection[i][3];
			/*
			haxe.lang.Runtime.setField(wallP1, "x", doubleCollection[i][0]);
			haxe.lang.Runtime.setField(wallP1, "y", doubleCollection[i][1]);
			
			haxe.lang.Runtime.setField(wallP2, "x", doubleCollection[i][3]);
			haxe.lang.Runtime.setField(wallP2, "y", doubleCollection[i][4]);
			
			haxe.lang.Runtime.setField(wall, "p1", wallP1);
			haxe.lang.Runtime.setField(wall, "p2", wallP2);
			*/
			
			java.lang.Object segment = null;
			java.lang.Object p1 = new haxe.lang.DynamicObject(new haxe.root.Array<java.lang.String>(new java.lang.String[]{"begin", "segment", "visualize"}), new haxe.root.Array<java.lang.Object>(new java.lang.Object[]{false, segment, true}), new haxe.root.Array<java.lang.String>(new java.lang.String[]{"angle", "x", "y"}), new haxe.root.Array<java.lang.Object>(new java.lang.Object[]{((java.lang.Object) (0.0) ), ((java.lang.Object) (0.0) ), ((java.lang.Object) (0.0) )}));
			java.lang.Object p2 = new haxe.lang.DynamicObject(new haxe.root.Array<java.lang.String>(new java.lang.String[]{"begin", "segment", "visualize"}), new haxe.root.Array<java.lang.Object>(new java.lang.Object[]{false, segment, false}), new haxe.root.Array<java.lang.String>(new java.lang.String[]{"angle", "x", "y"}), new haxe.root.Array<java.lang.Object>(new java.lang.Object[]{((java.lang.Object) (0.0) ), ((java.lang.Object) (0.0) ), ((java.lang.Object) (0.0) )}));
			segment = new haxe.lang.DynamicObject(new haxe.root.Array<java.lang.String>(new java.lang.String[]{"p1", "p2"}), new haxe.root.Array<java.lang.Object>(new java.lang.Object[]{p1, p2}), new haxe.root.Array<java.lang.String>(new java.lang.String[]{"d"}), new haxe.root.Array<java.lang.Object>(new java.lang.Object[]{((java.lang.Object) (0.0) )}));
			haxe.lang.Runtime.setField_f(p1, "x", ((double) (x1) ));
			haxe.lang.Runtime.setField_f(p1, "y", ((double) (y1) ));
			haxe.lang.Runtime.setField_f(p2, "x", ((double) (x2) ));
			haxe.lang.Runtime.setField_f(p2, "y", ((double) (y2) ));
			haxe.lang.Runtime.setField(p1, "segment", segment);
			haxe.lang.Runtime.setField(p2, "segment", segment);
			haxe.lang.Runtime.setField(segment, "p1", p1);
			haxe.lang.Runtime.setField(segment, "p2", p2);
			
			returnObjArray.push(segment);
		}
		
		return returnObjArray;
	}
}
