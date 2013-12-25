package de.polygonal.ds;
import haxe.root.*;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class DAIterator<T> extends haxe.lang.HxObject implements de.polygonal.ds.Itr<T>
{
	public    DAIterator(haxe.lang.EmptyObject empty)
	{
		{
		}
		
	}
	
	
	public    DAIterator(de.polygonal.ds.DA<T> f)
	{
		de.polygonal.ds.DAIterator.__hx_ctor_de_polygonal_ds_DAIterator(this, f);
	}
	
	
	public static  <T1> void __hx_ctor_de_polygonal_ds_DAIterator(de.polygonal.ds.DAIterator<T1> __temp_me27, de.polygonal.ds.DA<T1> f)
	{
		__temp_me27._f = f;
		{
			__temp_me27._a = __temp_me27._f._a;
			__temp_me27._s = __temp_me27._f._size;
			__temp_me27._i = 0;
			de.polygonal.ds.DAIterator<T1> __temp_expr114 = __temp_me27;
		}
		
	}
	
	
	public static   java.lang.Object __hx_createEmpty()
	{
		return new de.polygonal.ds.DAIterator<java.lang.Object>(((haxe.lang.EmptyObject) (haxe.lang.EmptyObject.EMPTY) ));
	}
	
	
	public static   java.lang.Object __hx_create(haxe.root.Array arr)
	{
		return new de.polygonal.ds.DAIterator<java.lang.Object>(((de.polygonal.ds.DA<java.lang.Object>) (arr.__get(0)) ));
	}
	
	
	public  de.polygonal.ds.DA<T> _f;
	
	public  haxe.root.Array<T> _a;
	
	public  int _i;
	
	public  int _s;
	
	public final   de.polygonal.ds.Itr<T> reset()
	{
		this._a = this._f._a;
		this._s = this._f._size;
		this._i = 0;
		return this;
	}
	
	
	public final   boolean hasNext()
	{
		return ( this._i < this._s );
	}
	
	
	public final   T next()
	{
		return this._a.__get(this._i++);
	}
	
	
	public final   void remove()
	{
		{
			de.polygonal.ds.DA<T> _this = this._f;
			int i =  -- this._i;
			T x = _this._a.__get(i);
			int k = ( _this._size - 1 );
			int p = i;
			while (( p < k ))
			{
				_this._a.__set(p++, _this._a.__get(p));
			}
			
			_this._size--;
			T __temp_expr108 = x;
		}
		
		this._s--;
	}
	
	
	public final   haxe.root.Array<T> __a(java.lang.Object f)
	{
		return ((haxe.root.Array<T>) (haxe.lang.Runtime.getField(f, "_a", true)) );
	}
	
	
	public final   int __size(java.lang.Object f)
	{
		return ((int) (haxe.lang.Runtime.getField_f(f, "_size", true)) );
	}
	
	
	@Override public   double __hx_setField_f(java.lang.String field, double value, boolean handleProperties)
	{
		{
			boolean __temp_executeDef109 = true;
			switch (field.hashCode())
			{
				case 3060:
				{
					if (field.equals("_s")) 
					{
						__temp_executeDef109 = false;
						this._s = ((int) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 3050:
				{
					if (field.equals("_i")) 
					{
						__temp_executeDef109 = false;
						this._i = ((int) (value) );
						return value;
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef109) 
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
			boolean __temp_executeDef110 = true;
			switch (field.hashCode())
			{
				case 3060:
				{
					if (field.equals("_s")) 
					{
						__temp_executeDef110 = false;
						this._s = ((int) (haxe.lang.Runtime.toInt(value)) );
						return value;
					}
					
					break;
				}
				
				
				case 3047:
				{
					if (field.equals("_f")) 
					{
						__temp_executeDef110 = false;
						this._f = ((de.polygonal.ds.DA<T>) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 3050:
				{
					if (field.equals("_i")) 
					{
						__temp_executeDef110 = false;
						this._i = ((int) (haxe.lang.Runtime.toInt(value)) );
						return value;
					}
					
					break;
				}
				
				
				case 3042:
				{
					if (field.equals("_a")) 
					{
						__temp_executeDef110 = false;
						this._a = ((haxe.root.Array<T>) (value) );
						return value;
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef110) 
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
			boolean __temp_executeDef111 = true;
			switch (field.hashCode())
			{
				case -1483932703:
				{
					if (field.equals("__size")) 
					{
						__temp_executeDef111 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("__size"))) );
					}
					
					break;
				}
				
				
				case 3047:
				{
					if (field.equals("_f")) 
					{
						__temp_executeDef111 = false;
						return this._f;
					}
					
					break;
				}
				
				
				case 94337:
				{
					if (field.equals("__a")) 
					{
						__temp_executeDef111 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("__a"))) );
					}
					
					break;
				}
				
				
				case 3042:
				{
					if (field.equals("_a")) 
					{
						__temp_executeDef111 = false;
						return this._a;
					}
					
					break;
				}
				
				
				case -934610812:
				{
					if (field.equals("remove")) 
					{
						__temp_executeDef111 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("remove"))) );
					}
					
					break;
				}
				
				
				case 3050:
				{
					if (field.equals("_i")) 
					{
						__temp_executeDef111 = false;
						return this._i;
					}
					
					break;
				}
				
				
				case 3377907:
				{
					if (field.equals("next")) 
					{
						__temp_executeDef111 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("next"))) );
					}
					
					break;
				}
				
				
				case 3060:
				{
					if (field.equals("_s")) 
					{
						__temp_executeDef111 = false;
						return this._s;
					}
					
					break;
				}
				
				
				case 696759469:
				{
					if (field.equals("hasNext")) 
					{
						__temp_executeDef111 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("hasNext"))) );
					}
					
					break;
				}
				
				
				case 108404047:
				{
					if (field.equals("reset")) 
					{
						__temp_executeDef111 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("reset"))) );
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef111) 
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
			boolean __temp_executeDef112 = true;
			switch (field.hashCode())
			{
				case 3060:
				{
					if (field.equals("_s")) 
					{
						__temp_executeDef112 = false;
						return ((double) (this._s) );
					}
					
					break;
				}
				
				
				case 3050:
				{
					if (field.equals("_i")) 
					{
						__temp_executeDef112 = false;
						return ((double) (this._i) );
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef112) 
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
			boolean __temp_executeDef113 = true;
			switch (field.hashCode())
			{
				case -1483932703:
				{
					if (field.equals("__size")) 
					{
						__temp_executeDef113 = false;
						return this.__size(dynargs.__get(0));
					}
					
					break;
				}
				
				
				case 108404047:
				{
					if (field.equals("reset")) 
					{
						__temp_executeDef113 = false;
						return this.reset();
					}
					
					break;
				}
				
				
				case 94337:
				{
					if (field.equals("__a")) 
					{
						__temp_executeDef113 = false;
						return this.__a(dynargs.__get(0));
					}
					
					break;
				}
				
				
				case 696759469:
				{
					if (field.equals("hasNext")) 
					{
						__temp_executeDef113 = false;
						return this.hasNext();
					}
					
					break;
				}
				
				
				case -934610812:
				{
					if (field.equals("remove")) 
					{
						__temp_executeDef113 = false;
						this.remove();
					}
					
					break;
				}
				
				
				case 3377907:
				{
					if (field.equals("next")) 
					{
						__temp_executeDef113 = false;
						return this.next();
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef113) 
			{
				return super.__hx_invokeField(field, dynargs);
			}
			
		}
		
		return null;
	}
	
	
	@Override public   void __hx_getFields(haxe.root.Array<java.lang.String> baseArr)
	{
		baseArr.push("_s");
		baseArr.push("_i");
		baseArr.push("_a");
		baseArr.push("_f");
		{
			super.__hx_getFields(baseArr);
		}
		
	}
	
	
}


