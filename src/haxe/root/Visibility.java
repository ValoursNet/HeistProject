package haxe.root;
import haxe.root.*;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class Visibility extends haxe.lang.HxObject
{
	public    Visibility(haxe.lang.EmptyObject empty)
	{
		{
		}
		
	}
	
	
	public    Visibility()
	{
		haxe.root.Visibility.__hx_ctor__Visibility(this);
	}
	
	
	public static   void __hx_ctor__Visibility(haxe.root.Visibility __temp_me6)
	{
		__temp_me6.segments = new de.polygonal.ds.DLL<java.lang.Object>(((java.lang.Object) (null) ), ((java.lang.Object) (null) ));
		__temp_me6.endpoints = new de.polygonal.ds.DLL<java.lang.Object>(((java.lang.Object) (null) ), ((java.lang.Object) (null) ));
		__temp_me6.open = new de.polygonal.ds.DLL<java.lang.Object>(((java.lang.Object) (null) ), ((java.lang.Object) (null) ));
		__temp_me6.center = new haxe.lang.DynamicObject(new haxe.root.Array<java.lang.String>(new java.lang.String[]{}), new haxe.root.Array<java.lang.Object>(new java.lang.Object[]{}), new haxe.root.Array<java.lang.String>(new java.lang.String[]{"x", "y"}), new haxe.root.Array<java.lang.Object>(new java.lang.Object[]{((java.lang.Object) (0.0) ), ((java.lang.Object) (0.0) )}));
		__temp_me6.output = new haxe.root.Array<java.lang.Object>();
		__temp_me6.demo_intersectionsDetected = new haxe.root.Array<haxe.root.Array>(new haxe.root.Array[]{});
	}
	
	
	public static   int _endpoint_compare(java.lang.Object a, java.lang.Object b)
	{
		if (( haxe.lang.Runtime.compare(((double) (haxe.lang.Runtime.getField_f(a, "angle", true)) ), ((double) (haxe.lang.Runtime.getField_f(b, "angle", true)) )) > 0 )) 
		{
			return 1;
		}
		
		if (( haxe.lang.Runtime.compare(((double) (haxe.lang.Runtime.getField_f(a, "angle", true)) ), ((double) (haxe.lang.Runtime.getField_f(b, "angle", true)) )) < 0 )) 
		{
			return -1;
		}
		
		if (( haxe.lang.Runtime.toBool(( ! (haxe.lang.Runtime.toBool(haxe.lang.Runtime.getField(a, "begin", true))) )) && haxe.lang.Runtime.toBool(haxe.lang.Runtime.getField(b, "begin", true)) )) 
		{
			return 1;
		}
		
		if (( haxe.lang.Runtime.toBool(haxe.lang.Runtime.getField(a, "begin", true)) && haxe.lang.Runtime.toBool(( ! (haxe.lang.Runtime.toBool(haxe.lang.Runtime.getField(b, "begin", true))) )) )) 
		{
			return -1;
		}
		
		return 0;
	}
	
	
	public static   boolean leftOf(java.lang.Object s, java.lang.Object p)
	{
		double cross = ( ( (( ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(s, "p2", true), "x", true)) ) - ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(s, "p1", true), "x", true)) ) )) * (( ((double) (haxe.lang.Runtime.getField_f(p, "y", true)) ) - ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(s, "p1", true), "y", true)) ) )) ) - ( (( ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(s, "p2", true), "y", true)) ) - ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(s, "p1", true), "y", true)) ) )) * (( ((double) (haxe.lang.Runtime.getField_f(p, "x", true)) ) - ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(s, "p1", true), "x", true)) ) )) ) );
		return ( cross < 0 );
	}
	
	
	public static   java.lang.Object interpolate(java.lang.Object p, java.lang.Object q, double f)
	{
		{
			double __temp_odecl99 = ( ( ((double) (haxe.lang.Runtime.getField_f(p, "x", true)) ) * ((double) ((( 1 - f ))) ) ) + ( ((double) (haxe.lang.Runtime.getField_f(q, "x", true)) ) * ((double) (f) ) ) );
			double __temp_odecl100 = ( ( ((double) (haxe.lang.Runtime.getField_f(p, "y", true)) ) * ((double) ((( 1 - f ))) ) ) + ( ((double) (haxe.lang.Runtime.getField_f(q, "y", true)) ) * ((double) (f) ) ) );
			return new haxe.lang.DynamicObject(new haxe.root.Array<java.lang.String>(new java.lang.String[]{}), new haxe.root.Array<java.lang.Object>(new java.lang.Object[]{}), new haxe.root.Array<java.lang.String>(new java.lang.String[]{"x", "y"}), new haxe.root.Array<java.lang.Object>(new java.lang.Object[]{((java.lang.Object) (__temp_odecl99) ), ((java.lang.Object) (__temp_odecl100) )}));
		}
		
	}
	
	
	public static   java.lang.Object __hx_createEmpty()
	{
		return new haxe.root.Visibility(((haxe.lang.EmptyObject) (haxe.lang.EmptyObject.EMPTY) ));
	}
	
	
	public static   java.lang.Object __hx_create(haxe.root.Array arr)
	{
		return new haxe.root.Visibility();
	}
	
	
	public  de.polygonal.ds.DLL<java.lang.Object> segments;
	
	public  de.polygonal.ds.DLL<java.lang.Object> endpoints;
	
	public  java.lang.Object center;
	
	public  de.polygonal.ds.DLL<java.lang.Object> open;
	
	public  haxe.root.Array<java.lang.Object> output;
	
	public  haxe.root.Array<haxe.root.Array> demo_intersectionsDetected;
	
	public   void loadEdgeOfMap(int size, int margin)
	{
		this.addSegment(((double) (margin) ), ((double) (margin) ), ((double) (margin) ), ((double) (( size - margin )) ));
		this.addSegment(((double) (margin) ), ((double) (( size - margin )) ), ((double) (( size - margin )) ), ((double) (( size - margin )) ));
		this.addSegment(((double) (( size - margin )) ), ((double) (( size - margin )) ), ((double) (( size - margin )) ), ((double) (margin) ));
		this.addSegment(((double) (( size - margin )) ), ((double) (margin) ), ((double) (margin) ), ((double) (margin) ));
	}
	
	
	public   void loadMap(int size, int margin, haxe.root.Array<java.lang.Object> blocks, haxe.root.Array<java.lang.Object> walls)
	{
		this.segments.clear(null);
		this.endpoints.clear(null);
		this.loadEdgeOfMap(size, margin);
		{
			int _g = 0;
			while (( _g < blocks.length ))
			{
				java.lang.Object block = blocks.__get(_g);
				 ++ _g;
				double x = ((double) (haxe.lang.Runtime.getField_f(block, "x", true)) );
				double y = ((double) (haxe.lang.Runtime.getField_f(block, "y", true)) );
				double r = ((double) (haxe.lang.Runtime.getField_f(block, "r", true)) );
				this.addSegment(( x - r ), ( y - r ), ( x - r ), ( y + r ));
				this.addSegment(( x - r ), ( y + r ), ( x + r ), ( y + r ));
				this.addSegment(( x + r ), ( y + r ), ( x + r ), ( y - r ));
				this.addSegment(( x + r ), ( y - r ), ( x - r ), ( y - r ));
			}
			
		}
		
		{
			int _g = 0;
			while (( _g < walls.length ))
			{
				java.lang.Object wall = walls.__get(_g);
				 ++ _g;
				this.addSegment(((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(wall, "p1", true), "x", true)) ), ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(wall, "p1", true), "y", true)) ), ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(wall, "p2", true), "x", true)) ), ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(wall, "p2", true), "y", true)) ));
			}
			
		}
		
	}
	
	
	public   void addSegment(double x1, double y1, double x2, double y2)
	{
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
		this.segments.append(segment);
		this.endpoints.append(p1);
		this.endpoints.append(p2);
	}
	
	
	public   void setLightLocation(double x, double y)
	{
		haxe.lang.Runtime.setField_f(this.center, "x", ((double) (x) ));
		haxe.lang.Runtime.setField_f(this.center, "y", ((double) (y) ));
		{
			java.lang.Object __temp_iterator52 = this.segments.iterator();
			while (haxe.lang.Runtime.toBool(haxe.lang.Runtime.callField(__temp_iterator52, "hasNext", null)))
			{
				java.lang.Object segment = ((java.lang.Object) (haxe.lang.Runtime.callField(__temp_iterator52, "next", null)) );
				double dx = ( ( 0.5 * (( ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(segment, "p1", true), "x", true)) ) + ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(segment, "p2", true), "x", true)) ) )) ) - x );
				double dy = ( ( 0.5 * (( ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(segment, "p1", true), "y", true)) ) + ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(segment, "p2", true), "y", true)) ) )) ) - y );
				haxe.lang.Runtime.setField_f(segment, "d", ((double) (( ( dx * dx ) + ( dy * dy ) )) ));
				haxe.lang.Runtime.setField_f(haxe.lang.Runtime.getField(segment, "p1", true), "angle", ((double) (java.lang.Math.atan2(( ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(segment, "p1", true), "y", true)) ) - ((double) (y) ) ), ( ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(segment, "p1", true), "x", true)) ) - ((double) (x) ) ))) ));
				haxe.lang.Runtime.setField_f(haxe.lang.Runtime.getField(segment, "p2", true), "angle", ((double) (java.lang.Math.atan2(( ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(segment, "p2", true), "y", true)) ) - ((double) (y) ) ), ( ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(segment, "p2", true), "x", true)) ) - ((double) (x) ) ))) ));
				double dAngle = ( ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(segment, "p2", true), "angle", true)) ) - ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(segment, "p1", true), "angle", true)) ) );
				if (( dAngle <=  - (java.lang.Math.PI)  )) 
				{
					dAngle += ( 2 * java.lang.Math.PI );
				}
				
				if (( dAngle > java.lang.Math.PI )) 
				{
					dAngle -= ( 2 * java.lang.Math.PI );
				}
				
				haxe.lang.Runtime.setField(haxe.lang.Runtime.getField(segment, "p1", true), "begin", ( dAngle > 0.0 ));
				haxe.lang.Runtime.setField(haxe.lang.Runtime.getField(segment, "p2", true), "begin", ( ! (haxe.lang.Runtime.toBool(haxe.lang.Runtime.getField(haxe.lang.Runtime.getField(segment, "p1", true), "begin", true))) ));
			}
			
		}
		
	}
	
	
	public   boolean _segment_in_front_of(java.lang.Object a, java.lang.Object b, java.lang.Object relativeTo)
	{
		boolean A1 = false;
		{
			java.lang.Object p = haxe.root.Visibility.interpolate(haxe.lang.Runtime.getField(b, "p1", true), haxe.lang.Runtime.getField(b, "p2", true), 0.01);
			double cross = ( ( (( ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(a, "p2", true), "x", true)) ) - ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(a, "p1", true), "x", true)) ) )) * (( ((double) (haxe.lang.Runtime.getField_f(p, "y", true)) ) - ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(a, "p1", true), "y", true)) ) )) ) - ( (( ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(a, "p2", true), "y", true)) ) - ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(a, "p1", true), "y", true)) ) )) * (( ((double) (haxe.lang.Runtime.getField_f(p, "x", true)) ) - ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(a, "p1", true), "x", true)) ) )) ) );
			A1 = ( cross < 0 );
		}
		
		boolean A2 = false;
		{
			java.lang.Object p = haxe.root.Visibility.interpolate(haxe.lang.Runtime.getField(b, "p2", true), haxe.lang.Runtime.getField(b, "p1", true), 0.01);
			double cross = ( ( (( ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(a, "p2", true), "x", true)) ) - ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(a, "p1", true), "x", true)) ) )) * (( ((double) (haxe.lang.Runtime.getField_f(p, "y", true)) ) - ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(a, "p1", true), "y", true)) ) )) ) - ( (( ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(a, "p2", true), "y", true)) ) - ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(a, "p1", true), "y", true)) ) )) * (( ((double) (haxe.lang.Runtime.getField_f(p, "x", true)) ) - ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(a, "p1", true), "x", true)) ) )) ) );
			A2 = ( cross < 0 );
		}
		
		boolean A3 = false;
		{
			double cross = ( ( (( ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(a, "p2", true), "x", true)) ) - ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(a, "p1", true), "x", true)) ) )) * (( ((double) (haxe.lang.Runtime.getField_f(relativeTo, "y", true)) ) - ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(a, "p1", true), "y", true)) ) )) ) - ( (( ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(a, "p2", true), "y", true)) ) - ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(a, "p1", true), "y", true)) ) )) * (( ((double) (haxe.lang.Runtime.getField_f(relativeTo, "x", true)) ) - ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(a, "p1", true), "x", true)) ) )) ) );
			A3 = ( cross < 0 );
		}
		
		boolean B1 = false;
		{
			java.lang.Object p = haxe.root.Visibility.interpolate(haxe.lang.Runtime.getField(a, "p1", true), haxe.lang.Runtime.getField(a, "p2", true), 0.01);
			double cross = ( ( (( ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(b, "p2", true), "x", true)) ) - ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(b, "p1", true), "x", true)) ) )) * (( ((double) (haxe.lang.Runtime.getField_f(p, "y", true)) ) - ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(b, "p1", true), "y", true)) ) )) ) - ( (( ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(b, "p2", true), "y", true)) ) - ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(b, "p1", true), "y", true)) ) )) * (( ((double) (haxe.lang.Runtime.getField_f(p, "x", true)) ) - ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(b, "p1", true), "x", true)) ) )) ) );
			B1 = ( cross < 0 );
		}
		
		boolean B2 = false;
		{
			java.lang.Object p = haxe.root.Visibility.interpolate(haxe.lang.Runtime.getField(a, "p2", true), haxe.lang.Runtime.getField(a, "p1", true), 0.01);
			double cross = ( ( (( ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(b, "p2", true), "x", true)) ) - ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(b, "p1", true), "x", true)) ) )) * (( ((double) (haxe.lang.Runtime.getField_f(p, "y", true)) ) - ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(b, "p1", true), "y", true)) ) )) ) - ( (( ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(b, "p2", true), "y", true)) ) - ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(b, "p1", true), "y", true)) ) )) * (( ((double) (haxe.lang.Runtime.getField_f(p, "x", true)) ) - ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(b, "p1", true), "x", true)) ) )) ) );
			B2 = ( cross < 0 );
		}
		
		boolean B3 = false;
		{
			double cross = ( ( (( ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(b, "p2", true), "x", true)) ) - ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(b, "p1", true), "x", true)) ) )) * (( ((double) (haxe.lang.Runtime.getField_f(relativeTo, "y", true)) ) - ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(b, "p1", true), "y", true)) ) )) ) - ( (( ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(b, "p2", true), "y", true)) ) - ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(b, "p1", true), "y", true)) ) )) * (( ((double) (haxe.lang.Runtime.getField_f(relativeTo, "x", true)) ) - ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(b, "p1", true), "x", true)) ) )) ) );
			B3 = ( cross < 0 );
		}
		
		if (( ( B1 == B2 ) && ( B2 != B3 ) )) 
		{
			return true;
		}
		
		if (( ( A1 == A2 ) && ( A2 == A3 ) )) 
		{
			return true;
		}
		
		if (( ( A1 == A2 ) && ( A2 != A3 ) )) 
		{
			return false;
		}
		
		if (( ( B1 == B2 ) && ( B2 == B3 ) )) 
		{
			return false;
		}
		
		this.demo_intersectionsDetected.push(new haxe.root.Array<java.lang.Object>(new java.lang.Object[]{haxe.lang.Runtime.getField(a, "p1", true), haxe.lang.Runtime.getField(a, "p2", true), haxe.lang.Runtime.getField(b, "p1", true), haxe.lang.Runtime.getField(b, "p2", true)}));
		return false;
	}
	
	
	public   void sweep(java.lang.Object maxAngle)
	{
		double __temp_maxAngle5 = ( (( maxAngle == null )) ? (((double) (999.0) )) : (((double) (haxe.lang.Runtime.toDouble(maxAngle)) )) );
		this.output = new haxe.root.Array<java.lang.Object>(new java.lang.Object[]{});
		this.demo_intersectionsDetected = new haxe.root.Array<haxe.root.Array>(new haxe.root.Array[]{});
		this.endpoints.sort(((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (haxe.root.Visibility.class) ), haxe.lang.Runtime.toString("_endpoint_compare"))) ), true);
		this.open.clear(null);
		double beginAngle = 0.0;
		{
			int _g = 0;
			while (( _g < 2 ))
			{
				int pass = _g++;
				{
					java.lang.Object __temp_iterator53 = this.endpoints.iterator();
					while (haxe.lang.Runtime.toBool(haxe.lang.Runtime.callField(__temp_iterator53, "hasNext", null)))
					{
						java.lang.Object p = ((java.lang.Object) (haxe.lang.Runtime.callField(__temp_iterator53, "next", null)) );
						if (( ( pass == 1 ) && ( haxe.lang.Runtime.compare(((double) (haxe.lang.Runtime.getField_f(p, "angle", true)) ), __temp_maxAngle5) > 0 ) )) 
						{
							break;
						}
						
						java.lang.Object current_old = ( (( this.open._size == 0 )) ? (null) : (this.open.head.val) );
						if (haxe.lang.Runtime.toBool(haxe.lang.Runtime.getField(p, "begin", true))) 
						{
							de.polygonal.ds.DLLNode<java.lang.Object> node = this.open.head;
							while (( ( node != null ) && this._segment_in_front_of(haxe.lang.Runtime.getField(p, "segment", true), node.val, this.center) ))
							{
								node = node.next;
							}
							
							if (( node == null )) 
							{
								this.open.append(haxe.lang.Runtime.getField(p, "segment", true));
							}
							 else 
							{
								this.open.insertBefore(node, haxe.lang.Runtime.getField(p, "segment", true));
							}
							
						}
						 else 
						{
							this.open.remove(haxe.lang.Runtime.getField(p, "segment", true));
						}
						
						java.lang.Object current_new = ( (( this.open._size == 0 )) ? (null) : (this.open.head.val) );
						if (( ! (haxe.lang.Runtime.refEq(current_old, current_new)) )) 
						{
							if (( pass == 1 )) 
							{
								this.addTriangle(beginAngle, ((double) (haxe.lang.Runtime.getField_f(p, "angle", true)) ), current_old);
							}
							
							beginAngle = ((double) (haxe.lang.Runtime.getField_f(p, "angle", true)) );
						}
						
					}
					
				}
				
			}
			
		}
		
	}
	
	
	public   java.lang.Object lineIntersection(java.lang.Object p1, java.lang.Object p2, java.lang.Object p3, java.lang.Object p4)
	{
		double s = ( (( ( (( ((double) (haxe.lang.Runtime.getField_f(p4, "x", true)) ) - ((double) (haxe.lang.Runtime.getField_f(p3, "x", true)) ) )) * (( ((double) (haxe.lang.Runtime.getField_f(p1, "y", true)) ) - ((double) (haxe.lang.Runtime.getField_f(p3, "y", true)) ) )) ) - ( (( ((double) (haxe.lang.Runtime.getField_f(p4, "y", true)) ) - ((double) (haxe.lang.Runtime.getField_f(p3, "y", true)) ) )) * (( ((double) (haxe.lang.Runtime.getField_f(p1, "x", true)) ) - ((double) (haxe.lang.Runtime.getField_f(p3, "x", true)) ) )) ) )) / (( ( (( ((double) (haxe.lang.Runtime.getField_f(p4, "y", true)) ) - ((double) (haxe.lang.Runtime.getField_f(p3, "y", true)) ) )) * (( ((double) (haxe.lang.Runtime.getField_f(p2, "x", true)) ) - ((double) (haxe.lang.Runtime.getField_f(p1, "x", true)) ) )) ) - ( (( ((double) (haxe.lang.Runtime.getField_f(p4, "x", true)) ) - ((double) (haxe.lang.Runtime.getField_f(p3, "x", true)) ) )) * (( ((double) (haxe.lang.Runtime.getField_f(p2, "y", true)) ) - ((double) (haxe.lang.Runtime.getField_f(p1, "y", true)) ) )) ) )) );
		{
			double __temp_odecl90 = ( ((double) (haxe.lang.Runtime.getField_f(p1, "x", true)) ) + ( s * (( ((double) (haxe.lang.Runtime.getField_f(p2, "x", true)) ) - ((double) (haxe.lang.Runtime.getField_f(p1, "x", true)) ) )) ) );
			double __temp_odecl91 = ( ((double) (haxe.lang.Runtime.getField_f(p1, "y", true)) ) + ( s * (( ((double) (haxe.lang.Runtime.getField_f(p2, "y", true)) ) - ((double) (haxe.lang.Runtime.getField_f(p1, "y", true)) ) )) ) );
			return new haxe.lang.DynamicObject(new haxe.root.Array<java.lang.String>(new java.lang.String[]{}), new haxe.root.Array<java.lang.Object>(new java.lang.Object[]{}), new haxe.root.Array<java.lang.String>(new java.lang.String[]{"x", "y"}), new haxe.root.Array<java.lang.Object>(new java.lang.Object[]{((java.lang.Object) (__temp_odecl90) ), ((java.lang.Object) (__temp_odecl91) )}));
		}
		
	}
	
	
	public   void addTriangle(double angle1, double angle2, java.lang.Object segment)
	{
		java.lang.Object p1 = this.center;
		java.lang.Object p2 = null;
		{
			double __temp_odecl92 = ( ((double) (haxe.lang.Runtime.getField_f(this.center, "x", true)) ) + java.lang.Math.cos(angle1) );
			double __temp_odecl93 = ( ((double) (haxe.lang.Runtime.getField_f(this.center, "y", true)) ) + java.lang.Math.sin(angle1) );
			p2 = new haxe.lang.DynamicObject(new haxe.root.Array<java.lang.String>(new java.lang.String[]{}), new haxe.root.Array<java.lang.Object>(new java.lang.Object[]{}), new haxe.root.Array<java.lang.String>(new java.lang.String[]{"x", "y"}), new haxe.root.Array<java.lang.Object>(new java.lang.Object[]{((java.lang.Object) (__temp_odecl92) ), ((java.lang.Object) (__temp_odecl93) )}));
		}
		
		java.lang.Object p3 = new haxe.lang.DynamicObject(new haxe.root.Array<java.lang.String>(new java.lang.String[]{}), new haxe.root.Array<java.lang.Object>(new java.lang.Object[]{}), new haxe.root.Array<java.lang.String>(new java.lang.String[]{"x", "y"}), new haxe.root.Array<java.lang.Object>(new java.lang.Object[]{((java.lang.Object) (0.0) ), ((java.lang.Object) (0.0) )}));
		java.lang.Object p4 = new haxe.lang.DynamicObject(new haxe.root.Array<java.lang.String>(new java.lang.String[]{}), new haxe.root.Array<java.lang.Object>(new java.lang.Object[]{}), new haxe.root.Array<java.lang.String>(new java.lang.String[]{"x", "y"}), new haxe.root.Array<java.lang.Object>(new java.lang.Object[]{((java.lang.Object) (0.0) ), ((java.lang.Object) (0.0) )}));
		if (( ! (( segment == null )) )) 
		{
			haxe.lang.Runtime.setField_f(p3, "x", ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(segment, "p1", true), "x", true)) ));
			haxe.lang.Runtime.setField_f(p3, "y", ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(segment, "p1", true), "y", true)) ));
			haxe.lang.Runtime.setField_f(p4, "x", ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(segment, "p2", true), "x", true)) ));
			haxe.lang.Runtime.setField_f(p4, "y", ((double) (haxe.lang.Runtime.getField_f(haxe.lang.Runtime.getField(segment, "p2", true), "y", true)) ));
		}
		 else 
		{
			haxe.lang.Runtime.setField_f(p3, "x", ((double) (( ((double) (haxe.lang.Runtime.getField_f(this.center, "x", true)) ) + ( java.lang.Math.cos(angle1) * 500 ) )) ));
			haxe.lang.Runtime.setField_f(p3, "y", ((double) (( ((double) (haxe.lang.Runtime.getField_f(this.center, "y", true)) ) + ( java.lang.Math.sin(angle1) * 500 ) )) ));
			haxe.lang.Runtime.setField_f(p4, "x", ((double) (( ((double) (haxe.lang.Runtime.getField_f(this.center, "x", true)) ) + ( java.lang.Math.cos(angle2) * 500 ) )) ));
			haxe.lang.Runtime.setField_f(p4, "y", ((double) (( ((double) (haxe.lang.Runtime.getField_f(this.center, "y", true)) ) + ( java.lang.Math.sin(angle2) * 500 ) )) ));
		}
		
		java.lang.Object pBegin = this.lineIntersection(p3, p4, p1, p2);
		haxe.lang.Runtime.setField_f(p2, "x", ((double) (( ((double) (haxe.lang.Runtime.getField_f(this.center, "x", true)) ) + java.lang.Math.cos(angle2) )) ));
		haxe.lang.Runtime.setField_f(p2, "y", ((double) (( ((double) (haxe.lang.Runtime.getField_f(this.center, "y", true)) ) + java.lang.Math.sin(angle2) )) ));
		java.lang.Object pEnd = this.lineIntersection(p3, p4, p1, p2);
		this.output.push(pBegin);
		this.output.push(pEnd);
	}
	
	
	@Override public   double __hx_setField_f(java.lang.String field, double value, boolean handleProperties)
	{
		{
			boolean __temp_executeDef94 = true;
			switch (field.hashCode())
			{
				case -1364013995:
				{
					if (field.equals("center")) 
					{
						__temp_executeDef94 = false;
						this.center = ((java.lang.Object) (value) );
						return value;
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef94) 
			{
				return super.__hx_setField_f(field, value, handleProperties);
			}
			 else 
			{
				throw null;
			}
			
		}
		
	}
	
	
	@Override public   java.lang.Object __hx_setField(java.lang.String field, java.lang.Object value, boolean handleProperties)
	{
		{
			boolean __temp_executeDef95 = true;
			switch (field.hashCode())
			{
				case 1615878736:
				{
					if (field.equals("demo_intersectionsDetected")) 
					{
						__temp_executeDef95 = false;
						this.demo_intersectionsDetected = ((haxe.root.Array<haxe.root.Array>) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 1055868832:
				{
					if (field.equals("segments")) 
					{
						__temp_executeDef95 = false;
						this.segments = ((de.polygonal.ds.DLL<java.lang.Object>) (value) );
						return value;
					}
					
					break;
				}
				
				
				case -1005512447:
				{
					if (field.equals("output")) 
					{
						__temp_executeDef95 = false;
						this.output = ((haxe.root.Array<java.lang.Object>) (value) );
						return value;
					}
					
					break;
				}
				
				
				case -1860397698:
				{
					if (field.equals("endpoints")) 
					{
						__temp_executeDef95 = false;
						this.endpoints = ((de.polygonal.ds.DLL<java.lang.Object>) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 3417674:
				{
					if (field.equals("open")) 
					{
						__temp_executeDef95 = false;
						this.open = ((de.polygonal.ds.DLL<java.lang.Object>) (value) );
						return value;
					}
					
					break;
				}
				
				
				case -1364013995:
				{
					if (field.equals("center")) 
					{
						__temp_executeDef95 = false;
						this.center = ((java.lang.Object) (value) );
						return value;
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef95) 
			{
				return super.__hx_setField(field, value, handleProperties);
			}
			 else 
			{
				throw null;
			}
			
		}
		
	}
	
	
	@Override public   java.lang.Object __hx_getField(java.lang.String field, boolean throwErrors, boolean isCheck, boolean handleProperties)
	{
		{
			boolean __temp_executeDef96 = true;
			switch (field.hashCode())
			{
				case 523482569:
				{
					if (field.equals("addTriangle")) 
					{
						__temp_executeDef96 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("addTriangle"))) );
					}
					
					break;
				}
				
				
				case 1055868832:
				{
					if (field.equals("segments")) 
					{
						__temp_executeDef96 = false;
						return this.segments;
					}
					
					break;
				}
				
				
				case 1064296605:
				{
					if (field.equals("lineIntersection")) 
					{
						__temp_executeDef96 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("lineIntersection"))) );
					}
					
					break;
				}
				
				
				case -1860397698:
				{
					if (field.equals("endpoints")) 
					{
						__temp_executeDef96 = false;
						return this.endpoints;
					}
					
					break;
				}
				
				
				case 109850348:
				{
					if (field.equals("sweep")) 
					{
						__temp_executeDef96 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("sweep"))) );
					}
					
					break;
				}
				
				
				case -1364013995:
				{
					if (field.equals("center")) 
					{
						__temp_executeDef96 = false;
						return this.center;
					}
					
					break;
				}
				
				
				case -1720652868:
				{
					if (field.equals("_segment_in_front_of")) 
					{
						__temp_executeDef96 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("_segment_in_front_of"))) );
					}
					
					break;
				}
				
				
				case 3417674:
				{
					if (field.equals("open")) 
					{
						__temp_executeDef96 = false;
						return this.open;
					}
					
					break;
				}
				
				
				case -513808983:
				{
					if (field.equals("setLightLocation")) 
					{
						__temp_executeDef96 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("setLightLocation"))) );
					}
					
					break;
				}
				
				
				case -1005512447:
				{
					if (field.equals("output")) 
					{
						__temp_executeDef96 = false;
						return this.output;
					}
					
					break;
				}
				
				
				case -413010094:
				{
					if (field.equals("addSegment")) 
					{
						__temp_executeDef96 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("addSegment"))) );
					}
					
					break;
				}
				
				
				case 1615878736:
				{
					if (field.equals("demo_intersectionsDetected")) 
					{
						__temp_executeDef96 = false;
						return this.demo_intersectionsDetected;
					}
					
					break;
				}
				
				
				case 336623254:
				{
					if (field.equals("loadMap")) 
					{
						__temp_executeDef96 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("loadMap"))) );
					}
					
					break;
				}
				
				
				case 1351674370:
				{
					if (field.equals("loadEdgeOfMap")) 
					{
						__temp_executeDef96 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("loadEdgeOfMap"))) );
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef96) 
			{
				return super.__hx_getField(field, throwErrors, isCheck, handleProperties);
			}
			 else 
			{
				throw null;
			}
			
		}
		
	}
	
	
	@Override public   double __hx_getField_f(java.lang.String field, boolean throwErrors, boolean handleProperties)
	{
		{
			boolean __temp_executeDef97 = true;
			switch (field.hashCode())
			{
				case -1364013995:
				{
					if (field.equals("center")) 
					{
						__temp_executeDef97 = false;
						return ((double) (haxe.lang.Runtime.toDouble(this.center)) );
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef97) 
			{
				return super.__hx_getField_f(field, throwErrors, handleProperties);
			}
			 else 
			{
				throw null;
			}
			
		}
		
	}
	
	
	@Override public   java.lang.Object __hx_invokeField(java.lang.String field, haxe.root.Array dynargs)
	{
		{
			boolean __temp_executeDef98 = true;
			switch (field.hashCode())
			{
				case 523482569:
				{
					if (field.equals("addTriangle")) 
					{
						__temp_executeDef98 = false;
						this.addTriangle(((double) (haxe.lang.Runtime.toDouble(dynargs.__get(0))) ), ((double) (haxe.lang.Runtime.toDouble(dynargs.__get(1))) ), dynargs.__get(2));
					}
					
					break;
				}
				
				
				case 1351674370:
				{
					if (field.equals("loadEdgeOfMap")) 
					{
						__temp_executeDef98 = false;
						this.loadEdgeOfMap(((int) (haxe.lang.Runtime.toInt(dynargs.__get(0))) ), ((int) (haxe.lang.Runtime.toInt(dynargs.__get(1))) ));
					}
					
					break;
				}
				
				
				case 1064296605:
				{
					if (field.equals("lineIntersection")) 
					{
						__temp_executeDef98 = false;
						return this.lineIntersection(dynargs.__get(0), dynargs.__get(1), dynargs.__get(2), dynargs.__get(3));
					}
					
					break;
				}
				
				
				case 336623254:
				{
					if (field.equals("loadMap")) 
					{
						__temp_executeDef98 = false;
						this.loadMap(((int) (haxe.lang.Runtime.toInt(dynargs.__get(0))) ), ((int) (haxe.lang.Runtime.toInt(dynargs.__get(1))) ), ((haxe.root.Array<java.lang.Object>) (dynargs.__get(2)) ), ((haxe.root.Array<java.lang.Object>) (dynargs.__get(3)) ));
					}
					
					break;
				}
				
				
				case 109850348:
				{
					if (field.equals("sweep")) 
					{
						__temp_executeDef98 = false;
						this.sweep(dynargs.__get(0));
					}
					
					break;
				}
				
				
				case -413010094:
				{
					if (field.equals("addSegment")) 
					{
						__temp_executeDef98 = false;
						this.addSegment(((double) (haxe.lang.Runtime.toDouble(dynargs.__get(0))) ), ((double) (haxe.lang.Runtime.toDouble(dynargs.__get(1))) ), ((double) (haxe.lang.Runtime.toDouble(dynargs.__get(2))) ), ((double) (haxe.lang.Runtime.toDouble(dynargs.__get(3))) ));
					}
					
					break;
				}
				
				
				case -1720652868:
				{
					if (field.equals("_segment_in_front_of")) 
					{
						__temp_executeDef98 = false;
						return this._segment_in_front_of(dynargs.__get(0), dynargs.__get(1), dynargs.__get(2));
					}
					
					break;
				}
				
				
				case -513808983:
				{
					if (field.equals("setLightLocation")) 
					{
						__temp_executeDef98 = false;
						this.setLightLocation(((double) (haxe.lang.Runtime.toDouble(dynargs.__get(0))) ), ((double) (haxe.lang.Runtime.toDouble(dynargs.__get(1))) ));
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef98) 
			{
				return super.__hx_invokeField(field, dynargs);
			}
			
		}
		
		return null;
	}
	
	
	@Override public   void __hx_getFields(haxe.root.Array<java.lang.String> baseArr)
	{
		baseArr.push("demo_intersectionsDetected");
		baseArr.push("output");
		baseArr.push("open");
		baseArr.push("center");
		baseArr.push("endpoints");
		baseArr.push("segments");
		{
			super.__hx_getFields(baseArr);
		}
		
	}
	
	
}


