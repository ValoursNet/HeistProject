package de.polygonal.ds;
import haxe.root.*;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class DLLNode<T> extends haxe.lang.HxObject
{
	public    DLLNode(haxe.lang.EmptyObject empty)
	{
		{
		}
		
	}
	
	
	public    DLLNode(T x, de.polygonal.ds.DLL<T> list)
	{
		de.polygonal.ds.DLLNode.__hx_ctor_de_polygonal_ds_DLLNode(this, x, list);
	}
	
	
	public static  <T1> void __hx_ctor_de_polygonal_ds_DLLNode(de.polygonal.ds.DLLNode<T1> __temp_me38, T1 x, de.polygonal.ds.DLL<T1> list)
	{
		__temp_me38.val = x;
		__temp_me38._list = list;
	}
	
	
	public static   java.lang.Object __hx_createEmpty()
	{
		return new de.polygonal.ds.DLLNode<java.lang.Object>(((haxe.lang.EmptyObject) (haxe.lang.EmptyObject.EMPTY) ));
	}
	
	
	public static   java.lang.Object __hx_create(haxe.root.Array arr)
	{
		return new de.polygonal.ds.DLLNode<java.lang.Object>(((java.lang.Object) (arr.__get(0)) ), ((de.polygonal.ds.DLL<java.lang.Object>) (arr.__get(1)) ));
	}
	
	
	public  T val;
	
	public  de.polygonal.ds.DLLNode<T> next;
	
	public  de.polygonal.ds.DLLNode<T> prev;
	
	public  de.polygonal.ds.DLL<T> _list;
	
	public   void free()
	{
		this.val = null;
		this.next = this.prev = null;
		this._list = null;
	}
	
	
	public final   boolean isHead()
	{
		return ( this == this._list.head );
	}
	
	
	public final   boolean isTail()
	{
		return ( this == this._list.tail );
	}
	
	
	public final   boolean hasNext()
	{
		return ( this.next != null );
	}
	
	
	public final   boolean hasPrev()
	{
		return ( this.prev != null );
	}
	
	
	public final   T nextVal()
	{
		return this.next.val;
	}
	
	
	public final   T prevVal()
	{
		return this.prev.val;
	}
	
	
	public final   de.polygonal.ds.DLL<T> getList()
	{
		return this._list;
	}
	
	
	public final   de.polygonal.ds.DLLNode<T> unlink()
	{
		return this._list.unlink(this);
	}
	
	
	public final   de.polygonal.ds.DLLNode<T> prepend(de.polygonal.ds.DLLNode<T> node)
	{
		node.next = this;
		this.prev = node;
		return node;
	}
	
	
	public final   de.polygonal.ds.DLLNode<T> append(de.polygonal.ds.DLLNode<T> node)
	{
		this.next = node;
		node.prev = this;
		return node;
	}
	
	
	public final   de.polygonal.ds.DLLNode<T> prependTo(de.polygonal.ds.DLLNode<T> node)
	{
		this.next = node;
		if (( node != null )) 
		{
			node.prev = this;
		}
		
		return this;
	}
	
	
	public final   de.polygonal.ds.DLLNode<T> appendTo(de.polygonal.ds.DLLNode<T> node)
	{
		this.prev = node;
		if (( node != null )) 
		{
			node.next = this;
		}
		
		return this;
	}
	
	
	@Override public   java.lang.String toString()
	{
		return ( ( "{ DLLNode " + haxe.root.Std.string(this.val) ) + " }" );
	}
	
	
	public final   de.polygonal.ds.DLLNode<T> _unlink()
	{
		de.polygonal.ds.DLLNode<T> t = this.next;
		if (( this.prev != null )) 
		{
			this.prev.next = this.next;
		}
		
		if (( this.next != null )) 
		{
			this.next.prev = this.prev;
		}
		
		this.next = this.prev = null;
		return t;
	}
	
	
	public final   void _insertAfter(de.polygonal.ds.DLLNode<T> node)
	{
		node.next = this.next;
		node.prev = this;
		if (( this.next != null )) 
		{
			this.next.prev = node;
		}
		
		this.next = node;
	}
	
	
	public final   void _insertBefore(de.polygonal.ds.DLLNode<T> node)
	{
		node.next = this;
		node.prev = this.prev;
		if (( this.prev != null )) 
		{
			this.prev.next = node;
		}
		
		this.prev = node;
	}
	
	
	@Override public   double __hx_setField_f(java.lang.String field, double value, boolean handleProperties)
	{
		{
			boolean __temp_executeDef132 = true;
			switch (field.hashCode())
			{
				case 116513:
				{
					if (field.equals("val")) 
					{
						__temp_executeDef132 = false;
						this.val = ((T) (((java.lang.Object) (value) )) );
						return value;
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef132) 
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
			boolean __temp_executeDef133 = true;
			switch (field.hashCode())
			{
				case 91056509:
				{
					if (field.equals("_list")) 
					{
						__temp_executeDef133 = false;
						this._list = ((de.polygonal.ds.DLL<T>) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 116513:
				{
					if (field.equals("val")) 
					{
						__temp_executeDef133 = false;
						this.val = ((T) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 3449395:
				{
					if (field.equals("prev")) 
					{
						__temp_executeDef133 = false;
						this.prev = ((de.polygonal.ds.DLLNode<T>) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 3377907:
				{
					if (field.equals("next")) 
					{
						__temp_executeDef133 = false;
						this.next = ((de.polygonal.ds.DLLNode<T>) (value) );
						return value;
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef133) 
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
			boolean __temp_executeDef134 = true;
			switch (field.hashCode())
			{
				case -1850300041:
				{
					if (field.equals("_insertBefore")) 
					{
						__temp_executeDef134 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("_insertBefore"))) );
					}
					
					break;
				}
				
				
				case 116513:
				{
					if (field.equals("val")) 
					{
						__temp_executeDef134 = false;
						return this.val;
					}
					
					break;
				}
				
				
				case -753304348:
				{
					if (field.equals("_insertAfter")) 
					{
						__temp_executeDef134 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("_insertAfter"))) );
					}
					
					break;
				}
				
				
				case 3377907:
				{
					if (field.equals("next")) 
					{
						__temp_executeDef134 = false;
						return this.next;
					}
					
					break;
				}
				
				
				case 1868023602:
				{
					if (field.equals("_unlink")) 
					{
						__temp_executeDef134 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("_unlink"))) );
					}
					
					break;
				}
				
				
				case 3449395:
				{
					if (field.equals("prev")) 
					{
						__temp_executeDef134 = false;
						return this.prev;
					}
					
					break;
				}
				
				
				case -1776922004:
				{
					if (field.equals("toString")) 
					{
						__temp_executeDef134 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("toString"))) );
					}
					
					break;
				}
				
				
				case 91056509:
				{
					if (field.equals("_list")) 
					{
						__temp_executeDef134 = false;
						return this._list;
					}
					
					break;
				}
				
				
				case 1173191477:
				{
					if (field.equals("appendTo")) 
					{
						__temp_executeDef134 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("appendTo"))) );
					}
					
					break;
				}
				
				
				case 3151468:
				{
					if (field.equals("free")) 
					{
						__temp_executeDef134 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("free"))) );
					}
					
					break;
				}
				
				
				case -1007846743:
				{
					if (field.equals("prependTo")) 
					{
						__temp_executeDef134 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("prependTo"))) );
					}
					
					break;
				}
				
				
				case -1180456406:
				{
					if (field.equals("isHead")) 
					{
						__temp_executeDef134 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("isHead"))) );
					}
					
					break;
				}
				
				
				case -1411068134:
				{
					if (field.equals("append")) 
					{
						__temp_executeDef134 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("append"))) );
					}
					
					break;
				}
				
				
				case -1180102502:
				{
					if (field.equals("isTail")) 
					{
						__temp_executeDef134 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("isTail"))) );
					}
					
					break;
				}
				
				
				case -318366834:
				{
					if (field.equals("prepend")) 
					{
						__temp_executeDef134 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("prepend"))) );
					}
					
					break;
				}
				
				
				case 696759469:
				{
					if (field.equals("hasNext")) 
					{
						__temp_executeDef134 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("hasNext"))) );
					}
					
					break;
				}
				
				
				case -840447469:
				{
					if (field.equals("unlink")) 
					{
						__temp_executeDef134 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("unlink"))) );
					}
					
					break;
				}
				
				
				case 696830957:
				{
					if (field.equals("hasPrev")) 
					{
						__temp_executeDef134 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("hasPrev"))) );
					}
					
					break;
				}
				
				
				case -75359980:
				{
					if (field.equals("getList")) 
					{
						__temp_executeDef134 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("getList"))) );
					}
					
					break;
				}
				
				
				case 1847065390:
				{
					if (field.equals("nextVal")) 
					{
						__temp_executeDef134 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("nextVal"))) );
					}
					
					break;
				}
				
				
				case -318202898:
				{
					if (field.equals("prevVal")) 
					{
						__temp_executeDef134 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("prevVal"))) );
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef134) 
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
			boolean __temp_executeDef135 = true;
			switch (field.hashCode())
			{
				case 116513:
				{
					if (field.equals("val")) 
					{
						__temp_executeDef135 = false;
						return ((double) (haxe.lang.Runtime.toDouble(((java.lang.Object) (this.val) ))) );
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef135) 
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
			boolean __temp_executeDef136 = true;
			switch (field.hashCode())
			{
				case -1850300041:
				{
					if (field.equals("_insertBefore")) 
					{
						__temp_executeDef136 = false;
						this._insertBefore(((de.polygonal.ds.DLLNode<T>) (dynargs.__get(0)) ));
					}
					
					break;
				}
				
				
				case 3151468:
				{
					if (field.equals("free")) 
					{
						__temp_executeDef136 = false;
						this.free();
					}
					
					break;
				}
				
				
				case -753304348:
				{
					if (field.equals("_insertAfter")) 
					{
						__temp_executeDef136 = false;
						this._insertAfter(((de.polygonal.ds.DLLNode<T>) (dynargs.__get(0)) ));
					}
					
					break;
				}
				
				
				case -1180456406:
				{
					if (field.equals("isHead")) 
					{
						__temp_executeDef136 = false;
						return this.isHead();
					}
					
					break;
				}
				
				
				case 1868023602:
				{
					if (field.equals("_unlink")) 
					{
						__temp_executeDef136 = false;
						return this._unlink();
					}
					
					break;
				}
				
				
				case -1180102502:
				{
					if (field.equals("isTail")) 
					{
						__temp_executeDef136 = false;
						return this.isTail();
					}
					
					break;
				}
				
				
				case -1776922004:
				{
					if (field.equals("toString")) 
					{
						__temp_executeDef136 = false;
						return this.toString();
					}
					
					break;
				}
				
				
				case 696759469:
				{
					if (field.equals("hasNext")) 
					{
						__temp_executeDef136 = false;
						return this.hasNext();
					}
					
					break;
				}
				
				
				case 1173191477:
				{
					if (field.equals("appendTo")) 
					{
						__temp_executeDef136 = false;
						return this.appendTo(((de.polygonal.ds.DLLNode<T>) (dynargs.__get(0)) ));
					}
					
					break;
				}
				
				
				case 696830957:
				{
					if (field.equals("hasPrev")) 
					{
						__temp_executeDef136 = false;
						return this.hasPrev();
					}
					
					break;
				}
				
				
				case -1007846743:
				{
					if (field.equals("prependTo")) 
					{
						__temp_executeDef136 = false;
						return this.prependTo(((de.polygonal.ds.DLLNode<T>) (dynargs.__get(0)) ));
					}
					
					break;
				}
				
				
				case 1847065390:
				{
					if (field.equals("nextVal")) 
					{
						__temp_executeDef136 = false;
						return this.nextVal();
					}
					
					break;
				}
				
				
				case -1411068134:
				{
					if (field.equals("append")) 
					{
						__temp_executeDef136 = false;
						return this.append(((de.polygonal.ds.DLLNode<T>) (dynargs.__get(0)) ));
					}
					
					break;
				}
				
				
				case -318202898:
				{
					if (field.equals("prevVal")) 
					{
						__temp_executeDef136 = false;
						return this.prevVal();
					}
					
					break;
				}
				
				
				case -318366834:
				{
					if (field.equals("prepend")) 
					{
						__temp_executeDef136 = false;
						return this.prepend(((de.polygonal.ds.DLLNode<T>) (dynargs.__get(0)) ));
					}
					
					break;
				}
				
				
				case -75359980:
				{
					if (field.equals("getList")) 
					{
						__temp_executeDef136 = false;
						return this.getList();
					}
					
					break;
				}
				
				
				case -840447469:
				{
					if (field.equals("unlink")) 
					{
						__temp_executeDef136 = false;
						return this.unlink();
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef136) 
			{
				return super.__hx_invokeField(field, dynargs);
			}
			
		}
		
		return null;
	}
	
	
	@Override public   void __hx_getFields(haxe.root.Array<java.lang.String> baseArr)
	{
		baseArr.push("_list");
		baseArr.push("prev");
		baseArr.push("next");
		baseArr.push("val");
		{
			super.__hx_getFields(baseArr);
		}
		
	}
	
	
}


