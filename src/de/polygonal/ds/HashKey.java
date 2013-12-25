package de.polygonal.ds;
import haxe.root.*;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class HashKey extends haxe.lang.HxObject
{
	static 
	{
		de.polygonal.ds.HashKey._counter = 0;
	}
	public    HashKey(haxe.lang.EmptyObject empty)
	{
		{
		}
		
	}
	
	
	public    HashKey()
	{
		de.polygonal.ds.HashKey.__hx_ctor_de_polygonal_ds_HashKey(this);
	}
	
	
	public static   void __hx_ctor_de_polygonal_ds_HashKey(de.polygonal.ds.HashKey __temp_me39)
	{
		{
		}
		
	}
	
	
	public static  int _counter;
	
	public static   int next()
	{
		return de.polygonal.ds.HashKey._counter++;
	}
	
	
	public static   java.lang.Object __hx_createEmpty()
	{
		return new de.polygonal.ds.HashKey(((haxe.lang.EmptyObject) (haxe.lang.EmptyObject.EMPTY) ));
	}
	
	
	public static   java.lang.Object __hx_create(haxe.root.Array arr)
	{
		return new de.polygonal.ds.HashKey();
	}
	
	
}


