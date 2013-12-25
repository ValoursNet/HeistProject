package de.polygonal.ds;
import haxe.root.*;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class CircularDLLIterator<T> extends haxe.lang.HxObject implements de.polygonal.ds.Itr<T>
{
	public    CircularDLLIterator(haxe.lang.EmptyObject empty)
	{
		{
		}
		
	}
	
	
	public    CircularDLLIterator(de.polygonal.ds.DLL<T> f)
	{
		de.polygonal.ds.CircularDLLIterator.__hx_ctor_de_polygonal_ds_CircularDLLIterator(this, f);
	}
	
	
	public static  <T1> void __hx_ctor_de_polygonal_ds_CircularDLLIterator(de.polygonal.ds.CircularDLLIterator<T1> __temp_me37, de.polygonal.ds.DLL<T1> f)
	{
		__temp_me37._f = f;
		{
			__temp_me37._walker = __temp_me37._f.head;
			__temp_me37._s = __temp_me37._f._size;
			__temp_me37._i = 0;
			__temp_me37._hook = null;
			de.polygonal.ds.CircularDLLIterator<T1> __temp_expr131 = __temp_me37;
		}
		
	}
	
	
	public static   java.lang.Object __hx_createEmpty()
	{
		return new de.polygonal.ds.CircularDLLIterator<java.lang.Object>(((haxe.lang.EmptyObject) (haxe.lang.EmptyObject.EMPTY) ));
	}
	
	
	public static   java.lang.Object __hx_create(haxe.root.Array arr)
	{
		return new de.polygonal.ds.CircularDLLIterator<java.lang.Object>(((de.polygonal.ds.DLL<java.lang.Object>) (arr.__get(0)) ));
	}
	
	
	public  de.polygonal.ds.DLL<T> _f;
	
	public  de.polygonal.ds.DLLNode<T> _walker;
	
	public  int _i;
	
	public  int _s;
	
	public  de.polygonal.ds.DLLNode<T> _hook;
	
	public final   de.polygonal.ds.Itr<T> reset()
	{
		this._walker = this._f.head;
		this._s = this._f._size;
		this._i = 0;
		this._hook = null;
		return this;
	}
	
	
	public final   boolean hasNext()
	{
		return ( this._i < this._s );
	}
	
	
	public final   T next()
	{
		T x = this._walker.val;
		this._hook = this._walker;
		this._walker = this._walker.next;
		this._i++;
		return x;
	}
	
	
	public final   void remove()
	{
		this._f.unlink(this._hook);
		this._i--;
		this._s--;
	}
	
	
	@Override public   double __hx_setField_f(java.lang.String field, double value, boolean handleProperties)
	{
		{
			boolean __temp_executeDef126 = true;
			switch (field.hashCode())
			{
				case 3060:
				{
					if (field.equals("_s")) 
					{
						__temp_executeDef126 = false;
						this._s = ((int) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 3050:
				{
					if (field.equals("_i")) 
					{
						__temp_executeDef126 = false;
						this._i = ((int) (value) );
						return value;
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef126) 
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
			boolean __temp_executeDef127 = true;
			switch (field.hashCode())
			{
				case 90942978:
				{
					if (field.equals("_hook")) 
					{
						__temp_executeDef127 = false;
						this._hook = ((de.polygonal.ds.DLLNode<T>) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 3047:
				{
					if (field.equals("_f")) 
					{
						__temp_executeDef127 = false;
						this._f = ((de.polygonal.ds.DLL<T>) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 3060:
				{
					if (field.equals("_s")) 
					{
						__temp_executeDef127 = false;
						this._s = ((int) (haxe.lang.Runtime.toInt(value)) );
						return value;
					}
					
					break;
				}
				
				
				case 1913277781:
				{
					if (field.equals("_walker")) 
					{
						__temp_executeDef127 = false;
						this._walker = ((de.polygonal.ds.DLLNode<T>) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 3050:
				{
					if (field.equals("_i")) 
					{
						__temp_executeDef127 = false;
						this._i = ((int) (haxe.lang.Runtime.toInt(value)) );
						return value;
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef127) 
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
			boolean __temp_executeDef128 = true;
			switch (field.hashCode())
			{
				case -934610812:
				{
					if (field.equals("remove")) 
					{
						__temp_executeDef128 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("remove"))) );
					}
					
					break;
				}
				
				
				case 3047:
				{
					if (field.equals("_f")) 
					{
						__temp_executeDef128 = false;
						return this._f;
					}
					
					break;
				}
				
				
				case 3377907:
				{
					if (field.equals("next")) 
					{
						__temp_executeDef128 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("next"))) );
					}
					
					break;
				}
				
				
				case 1913277781:
				{
					if (field.equals("_walker")) 
					{
						__temp_executeDef128 = false;
						return this._walker;
					}
					
					break;
				}
				
				
				case 696759469:
				{
					if (field.equals("hasNext")) 
					{
						__temp_executeDef128 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("hasNext"))) );
					}
					
					break;
				}
				
				
				case 3050:
				{
					if (field.equals("_i")) 
					{
						__temp_executeDef128 = false;
						return this._i;
					}
					
					break;
				}
				
				
				case 108404047:
				{
					if (field.equals("reset")) 
					{
						__temp_executeDef128 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("reset"))) );
					}
					
					break;
				}
				
				
				case 3060:
				{
					if (field.equals("_s")) 
					{
						__temp_executeDef128 = false;
						return this._s;
					}
					
					break;
				}
				
				
				case 90942978:
				{
					if (field.equals("_hook")) 
					{
						__temp_executeDef128 = false;
						return this._hook;
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef128) 
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
			boolean __temp_executeDef129 = true;
			switch (field.hashCode())
			{
				case 3060:
				{
					if (field.equals("_s")) 
					{
						__temp_executeDef129 = false;
						return ((double) (this._s) );
					}
					
					break;
				}
				
				
				case 3050:
				{
					if (field.equals("_i")) 
					{
						__temp_executeDef129 = false;
						return ((double) (this._i) );
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef129) 
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
			boolean __temp_executeDef130 = true;
			switch (field.hashCode())
			{
				case -934610812:
				{
					if (field.equals("remove")) 
					{
						__temp_executeDef130 = false;
						this.remove();
					}
					
					break;
				}
				
				
				case 108404047:
				{
					if (field.equals("reset")) 
					{
						__temp_executeDef130 = false;
						return this.reset();
					}
					
					break;
				}
				
				
				case 3377907:
				{
					if (field.equals("next")) 
					{
						__temp_executeDef130 = false;
						return this.next();
					}
					
					break;
				}
				
				
				case 696759469:
				{
					if (field.equals("hasNext")) 
					{
						__temp_executeDef130 = false;
						return this.hasNext();
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef130) 
			{
				return super.__hx_invokeField(field, dynargs);
			}
			
		}
		
		return null;
	}
	
	
	@Override public   void __hx_getFields(haxe.root.Array<java.lang.String> baseArr)
	{
		baseArr.push("_hook");
		baseArr.push("_s");
		baseArr.push("_i");
		baseArr.push("_walker");
		baseArr.push("_f");
		{
			super.__hx_getFields(baseArr);
		}
		
	}
	
	
}


