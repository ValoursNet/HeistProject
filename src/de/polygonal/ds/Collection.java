package de.polygonal.ds;
import haxe.root.*;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  interface Collection<T> extends haxe.lang.IHxObject, de.polygonal.ds.Hashable
{
	   void free();
	
	   boolean contains(T x);
	
	   boolean remove(T x);
	
	   void clear(java.lang.Object purge);
	
	   de.polygonal.ds.Itr<T> iterator();
	
	   boolean isEmpty();
	
	   int size();
	
	   haxe.root.Array<T> toArray();
	
	   de.polygonal.ds.Collection<T> clone(java.lang.Object assign, haxe.lang.Function copier);
	
}


