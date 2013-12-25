package de.polygonal.ds;
import haxe.root.*;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class DA<T> extends haxe.lang.HxObject implements de.polygonal.ds.Collection<T>
{
	public    DA(haxe.lang.EmptyObject empty)
	{
		{
		}
		
	}
	
	
	public    DA(java.lang.Object reservedSize, java.lang.Object maxSize)
	{
		de.polygonal.ds.DA.__hx_ctor_de_polygonal_ds_DA(this, reservedSize, maxSize);
	}
	
	
	public static  <T1> void __hx_ctor_de_polygonal_ds_DA(de.polygonal.ds.DA<T1> __temp_me26, java.lang.Object reservedSize, java.lang.Object maxSize)
	{
		int __temp_maxSize25 = ( (( maxSize == null )) ? (((int) (-1) )) : (((int) (haxe.lang.Runtime.toInt(maxSize)) )) );
		int __temp_reservedSize24 = ( (( reservedSize == null )) ? (((int) (0) )) : (((int) (haxe.lang.Runtime.toInt(reservedSize)) )) );
		__temp_me26._size = 0;
		__temp_me26._iterator = null;
		__temp_me26.maxSize = -1;
		if (( __temp_reservedSize24 > 0 )) 
		{
			{
				haxe.root.Array<T1> a = null;
				a = new haxe.root.Array<T1>();
				{
					int _g = 0;
					while (( _g < __temp_reservedSize24 ))
					{
						int i = _g++;
						a.__set(i, null);
					}
					
				}
				
				__temp_me26._a = a;
			}
			
		}
		 else 
		{
			__temp_me26._a = new haxe.root.Array<T1>();
		}
		
		__temp_me26.key = de.polygonal.ds.HashKey._counter++;
		__temp_me26.reuseIterator = false;
	}
	
	
	public static   java.lang.Object __hx_createEmpty()
	{
		return new de.polygonal.ds.DA<java.lang.Object>(((haxe.lang.EmptyObject) (haxe.lang.EmptyObject.EMPTY) ));
	}
	
	
	public static   java.lang.Object __hx_create(haxe.root.Array arr)
	{
		return new de.polygonal.ds.DA<java.lang.Object>(((java.lang.Object) (arr.__get(0)) ), ((java.lang.Object) (arr.__get(1)) ));
	}
	
	
	public  int key;
	
	public  haxe.root.Array<T> _a;
	
	public  int _size;
	
	public  de.polygonal.ds.DAIterator<T> _iterator;
	
	public  int maxSize;
	
	public  boolean reuseIterator;
	
	public   void pack()
	{
		int s = this._a.length;
		if (( s == this._size )) 
		{
			return ;
		}
		
		haxe.root.Array<T> tmp = this._a;
		{
			haxe.root.Array<T> a = null;
			a = new haxe.root.Array<T>();
			{
				int _g = 0;
				while (( _g < this._size ))
				{
					int i = _g++;
					a.__set(i, null);
				}
				
			}
			
			this._a = a;
		}
		
		{
			int _g1 = 0;
			int _g = this._size;
			while (( _g1 < _g ))
			{
				int i = _g1++;
				this._a.__set(i, tmp.__get(i));
			}
			
		}
		
		{
			int _g1 = this._size;
			int _g = tmp.length;
			while (( _g1 < _g ))
			{
				int i = _g1++;
				tmp.__set(i, null);
			}
			
		}
		
	}
	
	
	public   void reserve(int x)
	{
		if (( this._size == x )) 
		{
			return ;
		}
		
		haxe.root.Array<T> tmp = this._a;
		{
			haxe.root.Array<T> a = null;
			a = new haxe.root.Array<T>();
			{
				int _g = 0;
				while (( _g < x ))
				{
					int i = _g++;
					a.__set(i, null);
				}
				
			}
			
			this._a = a;
		}
		
		if (( this._size < x )) 
		{
			int _g1 = 0;
			int _g = this._size;
			while (( _g1 < _g ))
			{
				int i = _g1++;
				this._a.__set(i, tmp.__get(i));
			}
			
		}
		
	}
	
	
	public final   void trim(int x)
	{
		this._size = x;
	}
	
	
	public final   T get(int i)
	{
		return this._a.__get(i);
	}
	
	
	public final   T getNext(int i)
	{
		return this._a.__get(( (( ( i + 1 ) == this._size )) ? (0) : (( i + 1 )) ));
	}
	
	
	public final   T getPrev(int i)
	{
		return this._a.__get(( (( ( i - 1 ) == -1 )) ? (( this._size - 1 )) : (( i - 1 )) ));
	}
	
	
	public final   void set(int i, T x)
	{
		this._a.__set(i, x);
		if (( i >= this._size )) 
		{
			this._size++;
		}
		
	}
	
	
	public final   void swp(int i, int j)
	{
		T tmp = this._a.__get(i);
		{
			this._a.__set(i, this._a.__get(j));
			if (( i >= this._size )) 
			{
				this._size++;
			}
			
		}
		
		{
			this._a.__set(j, tmp);
			if (( j >= this._size )) 
			{
				this._size++;
			}
			
		}
		
	}
	
	
	public final   void cpy(int i, int j)
	{
		this._a.__set(i, this._a.__get(j));
		if (( i >= this._size )) 
		{
			this._size++;
		}
		
	}
	
	
	public final   T front()
	{
		return this._a.__get(0);
	}
	
	
	public final   T back()
	{
		return this._a.__get(( this._size - 1 ));
	}
	
	
	public final   T popBack()
	{
		T x = this._a.__get(( this._size - 1 ));
		this._size--;
		return x;
	}
	
	
	public final   void pushBack(T x)
	{
		int i = this._size;
		this._a.__set(i, x);
		if (( i >= this._size )) 
		{
			this._size++;
		}
		
	}
	
	
	public final   T popFront()
	{
		{
			T x = this._a.__get(0);
			int k = ( this._size - 1 );
			int p = 0;
			while (( p < k ))
			{
				this._a.__set(p++, this._a.__get(p));
			}
			
			this._size--;
			return x;
		}
		
	}
	
	
	public final   void pushFront(T x)
	{
		int p = this._size;
		while (( p > 0 ))
		{
			this._a.__set(p--, this._a.__get(p));
		}
		
		this._a.__set(0, x);
		this._size++;
	}
	
	
	public final   void insertAt(int i, T x)
	{
		int p = this._size;
		while (( p > i ))
		{
			this._a.__set(p--, this._a.__get(p));
		}
		
		this._a.__set(i, x);
		this._size++;
	}
	
	
	public final   T removeAt(int i)
	{
		T x = this._a.__get(i);
		int k = ( this._size - 1 );
		int p = i;
		while (( p < k ))
		{
			this._a.__set(p++, this._a.__get(p));
		}
		
		this._size--;
		return x;
	}
	
	
	public final   void swapPop(int i)
	{
		this._a.__set(i, this._a.__get( -- this._size));
	}
	
	
	public   de.polygonal.ds.DA<T> removeRange(int i, int n, de.polygonal.ds.DA<T> output)
	{
		if (( output == null )) 
		{
			int s = this._size;
			int p = ( i + n );
			while (( p < s ))
			{
				this._a.__set(( p - n ), this._a.__get(p));
				p++;
			}
			
		}
		 else 
		{
			int s = this._size;
			int p = ( i + n );
			T e = null;
			int j = 0;
			while (( p < s ))
			{
				j = ( p - n );
				e = this._a.__get(j);
				{
					int i1 = output._size;
					output._a.__set(i1, e);
					if (( i1 >= output._size )) 
					{
						output._size++;
					}
					
				}
				
				this._a.__set(j, this._a.__get(p++));
			}
			
		}
		
		this._size -= n;
		return output;
	}
	
	
	public   de.polygonal.ds.DA<T> concat(de.polygonal.ds.DA<T> x, java.lang.Object copy)
	{
		boolean __temp_copy13 = ( (( copy == null )) ? (haxe.lang.Runtime.toBool(false)) : (haxe.lang.Runtime.toBool(copy)) );
		if (__temp_copy13) 
		{
			de.polygonal.ds.DA<T> copy1 = new de.polygonal.ds.DA<T>(((java.lang.Object) (null) ), ((java.lang.Object) (null) ));
			copy1._size = ( this._size + x._size );
			{
				int _g1 = 0;
				int _g = this._size;
				while (( _g1 < _g ))
				{
					int i = _g1++;
					copy1._a.__set(i, this._a.__get(i));
					if (( i >= copy1._size )) 
					{
						copy1._size++;
					}
					
				}
				
			}
			
			{
				int _g1 = this._size;
				int _g = ( this._size + x._size );
				while (( _g1 < _g ))
				{
					int i = _g1++;
					copy1._a.__set(i, x._a.__get(( i - this._size )));
					if (( i >= copy1._size )) 
					{
						copy1._size++;
					}
					
				}
				
			}
			
			return copy1;
		}
		 else 
		{
			int j = this._size;
			this._size += x._size;
			{
				int _g1 = 0;
				int _g = x._size;
				while (( _g1 < _g ))
				{
					int i = _g1++;
					this._a.__set(j++, x._a.__get(i));
				}
				
			}
			
			return this;
		}
		
	}
	
	
	public   int indexOf(T x, java.lang.Object from, java.lang.Object binarySearch, haxe.lang.Function comparator)
	{
		boolean __temp_binarySearch15 = ( (( binarySearch == null )) ? (haxe.lang.Runtime.toBool(false)) : (haxe.lang.Runtime.toBool(binarySearch)) );
		int __temp_from14 = ( (( from == null )) ? (((int) (0) )) : (((int) (haxe.lang.Runtime.toInt(from)) )) );
		if (( this._size == 0 )) 
		{
			return -1;
		}
		 else 
		{
			if (__temp_binarySearch15) 
			{
				if (( comparator != null )) 
				{
					return de.polygonal.ds.ArrayUtil.bsearchComparator(this._a, x, __temp_from14, ( this._size - 1 ), comparator);
				}
				 else 
				{
					int k = this._size;
					int l = __temp_from14;
					int m = 0;
					int h = k;
					while (( l < h ))
					{
						m = ( l + (( ( h - l ) >> 1 )) );
						if (( (((de.polygonal.ds.Comparable) (((java.lang.Object) (this._a.__get(m)) )) )).compare(x) < 0 )) 
						{
							l = ( m + 1 );
						}
						 else 
						{
							h = m;
						}
						
					}
					
					return ( (( ( l <= k ) && ( (((de.polygonal.ds.Comparable) (((java.lang.Object) (this._a.__get(l)) )) )).compare(x) == 0 ) )) ? (l) : ( - (l) ) );
				}
				
			}
			 else 
			{
				int i = __temp_from14;
				int j = -1;
				int k = ( this._size - 1 );
				do 
				{
					if (haxe.lang.Runtime.eq(this._a.__get(i), x)) 
					{
						j = i;
						break;
					}
					
				}
				while (( i++ < k ));
				return j;
			}
			
		}
		
	}
	
	
	public   int lastIndexOf(T x, java.lang.Object from)
	{
		int __temp_from16 = ( (( from == null )) ? (((int) (-1) )) : (((int) (haxe.lang.Runtime.toInt(from)) )) );
		if (( this._size == 0 )) 
		{
			return -1;
		}
		 else 
		{
			if (( __temp_from16 < 0 )) 
			{
				__temp_from16 = ( this._size + __temp_from16 );
			}
			
			int j = -1;
			int i = __temp_from16;
			do 
			{
				if (haxe.lang.Runtime.eq(this._a.__get(i), x)) 
				{
					j = i;
					break;
				}
				
			}
			while (( i-- > 0 ));
			return j;
		}
		
	}
	
	
	public   void reverse()
	{
		if (( this._a.length > this._size )) 
		{
			{
				haxe.root.Array<T> a = this._a;
				haxe.root.Array<T> b = new haxe.root.Array<T>();
				{
					int _g = 0;
					while (( _g < this._size ))
					{
						int i = _g++;
						b.__set(i, a.__get(i));
					}
					
				}
				
				this._a = b;
			}
			
		}
		
		this._a.reverse();
	}
	
	
	public   void assign(java.lang.Class<T> C, haxe.root.Array args, java.lang.Object n)
	{
		int __temp_n17 = ( (( n == null )) ? (((int) (0) )) : (((int) (haxe.lang.Runtime.toInt(n)) )) );
		if (( __temp_n17 > 0 )) 
		{
			this._size = __temp_n17;
		}
		 else 
		{
			__temp_n17 = this._size;
		}
		
		if (( args == null )) 
		{
			args = new haxe.root.Array(new java.lang.Object[]{});
		}
		
		{
			int _g = 0;
			while (( _g < ((int) (__temp_n17) ) ))
			{
				int i = _g++;
				this._a.__set(i, haxe.root.Type.createInstance(C, args));
			}
			
		}
		
	}
	
	
	public   de.polygonal.ds.DA<T> fill(T x, java.lang.Object n)
	{
		int __temp_n18 = ( (( n == null )) ? (((int) (0) )) : (((int) (haxe.lang.Runtime.toInt(n)) )) );
		if (( __temp_n18 > 0 )) 
		{
			this._size = __temp_n18;
		}
		 else 
		{
			__temp_n18 = this._size;
		}
		
		{
			int _g = 0;
			while (( _g < ((int) (__temp_n18) ) ))
			{
				int i = _g++;
				this._a.__set(i, x);
			}
			
		}
		
		return this;
	}
	
	
	public final   void memmove(int destination, int source, int n)
	{
		if (( source == destination )) 
		{
			return ;
		}
		 else 
		{
			if (( source <= destination )) 
			{
				int i = ( source + n );
				int j = ( destination + n );
				{
					int _g = 0;
					while (( _g < n ))
					{
						int k = _g++;
						i--;
						j--;
						this._a.__set(j, this._a.__get(i));
					}
					
				}
				
			}
			 else 
			{
				int i = source;
				int j = destination;
				{
					int _g = 0;
					while (( _g < n ))
					{
						int k = _g++;
						this._a.__set(j, this._a.__get(i));
						i++;
						j++;
					}
					
				}
				
			}
			
		}
		
	}
	
	
	public   java.lang.String join(java.lang.String x)
	{
		if (( this._size == 0 )) 
		{
			return "";
		}
		
		if (( this._size == 1 )) 
		{
			return haxe.root.Std.string(this._a.__get(0));
		}
		
		java.lang.String s = ( haxe.root.Std.string(this._a.__get(0)) + x );
		{
			int _g1 = 1;
			int _g = ( this._size - 1 );
			while (( _g1 < _g ))
			{
				int i = _g1++;
				s += haxe.root.Std.string(this._a.__get(i));
				s += x;
			}
			
		}
		
		s += haxe.root.Std.string(this._a.__get(( this._size - 1 )));
		return s;
	}
	
	
	public   void sort(haxe.lang.Function compare, java.lang.Object useInsertionSort, java.lang.Object first, java.lang.Object count)
	{
		int __temp_count21 = ( (( count == null )) ? (((int) (-1) )) : (((int) (haxe.lang.Runtime.toInt(count)) )) );
		int __temp_first20 = ( (( first == null )) ? (((int) (0) )) : (((int) (haxe.lang.Runtime.toInt(first)) )) );
		boolean __temp_useInsertionSort19 = ( (( useInsertionSort == null )) ? (haxe.lang.Runtime.toBool(false)) : (haxe.lang.Runtime.toBool(useInsertionSort)) );
		if (( this._size > 1 )) 
		{
			if (( __temp_count21 == -1 )) 
			{
				__temp_count21 = ( this._size - __temp_first20 );
			}
			
			if (( compare == null )) 
			{
				if (__temp_useInsertionSort19) 
				{
					this._insertionSortComparable(__temp_first20, __temp_count21);
				}
				 else 
				{
					this._quickSortComparable(__temp_first20, __temp_count21);
				}
				
			}
			 else 
			{
				if (__temp_useInsertionSort19) 
				{
					this._insertionSort(__temp_first20, __temp_count21, compare);
				}
				 else 
				{
					this._quickSort(__temp_first20, __temp_count21, compare);
				}
				
			}
			
		}
		
	}
	
	
	public final   boolean inRange(int i)
	{
		return ( ( i >= 0 ) && ( i < this._size ) );
	}
	
	
	public final   haxe.root.Array<T> getArray()
	{
		return this._a;
	}
	
	
	public   void free()
	{
		{
			int _g1 = 0;
			int _g = this._a.length;
			while (( _g1 < _g ))
			{
				int i = _g1++;
				this._a.__set(i, null);
			}
			
		}
		
		this._a = null;
		this._iterator = null;
	}
	
	
	public   boolean contains(T x)
	{
		boolean found = false;
		{
			int _g1 = 0;
			int _g = this._size;
			while (( _g1 < _g ))
			{
				int i = _g1++;
				if (haxe.lang.Runtime.eq(this._a.__get(i), x)) 
				{
					found = true;
					break;
				}
				
			}
			
		}
		
		return found;
	}
	
	
	public   boolean remove(T x)
	{
		if (( this._size == 0 )) 
		{
			return false;
		}
		
		int i = 0;
		int s = this._size;
		while (( i < s ))
		{
			if (haxe.lang.Runtime.eq(this._a.__get(i), x)) 
			{
				s--;
				int p = i;
				while (( p < s ))
				{
					this._a.__set(p, this._a.__get(( p + 1 )));
					 ++ p;
				}
				
				continue;
			}
			
			i++;
		}
		
		boolean found = ( ( this._size - s ) != 0 );
		this._size = s;
		return found;
	}
	
	
	public final   void clear(java.lang.Object purge)
	{
		boolean __temp_purge22 = ( (( purge == null )) ? (haxe.lang.Runtime.toBool(false)) : (haxe.lang.Runtime.toBool(purge)) );
		if (__temp_purge22) 
		{
			int _g1 = 0;
			int _g = this._a.length;
			while (( _g1 < _g ))
			{
				int i = _g1++;
				this._a.__set(i, null);
			}
			
		}
		
		this._size = 0;
	}
	
	
	public   de.polygonal.ds.Itr<T> iterator()
	{
		if (this.reuseIterator) 
		{
			if (( this._iterator == null )) 
			{
				this._iterator = new de.polygonal.ds.DAIterator<T>(((de.polygonal.ds.DA<T>) (this) ));
			}
			 else 
			{
				de.polygonal.ds.DAIterator<T> _this = this._iterator;
				_this._a = _this._f._a;
				_this._s = _this._f._size;
				_this._i = 0;
				de.polygonal.ds.DAIterator<T> __temp_expr102 = _this;
			}
			
			return this._iterator;
		}
		 else 
		{
			return new de.polygonal.ds.DAIterator<T>(((de.polygonal.ds.DA<T>) (this) ));
		}
		
	}
	
	
	public final   int size()
	{
		return this._size;
	}
	
	
	public final   boolean isEmpty()
	{
		return ( this._size == 0 );
	}
	
	
	public   haxe.root.Array<T> toArray()
	{
		haxe.root.Array<T> a = null;
		{
			haxe.root.Array<T> a1 = null;
			a1 = new haxe.root.Array<T>();
			{
				int _g = 0;
				while (( _g < this._size ))
				{
					int i = _g++;
					a1.__set(i, null);
				}
				
			}
			
			a = a1;
		}
		
		{
			int _g1 = 0;
			int _g = this._size;
			while (( _g1 < _g ))
			{
				int i = _g1++;
				a.__set(i, this._a.__get(i));
			}
			
		}
		
		return a;
	}
	
	
	public   de.polygonal.ds.Collection<T> clone(java.lang.Object assign, haxe.lang.Function copier)
	{
		boolean __temp_assign23 = ( (( assign == null )) ? (haxe.lang.Runtime.toBool(true)) : (haxe.lang.Runtime.toBool(assign)) );
		de.polygonal.ds.DA<T> copy = new de.polygonal.ds.DA<T>(((java.lang.Object) (this._size) ), ((java.lang.Object) (this.maxSize) ));
		copy._size = this._size;
		if (__temp_assign23) 
		{
			int _g1 = 0;
			int _g = this._size;
			while (( _g1 < _g ))
			{
				int i = _g1++;
				copy._a.__set(i, this._a.__get(i));
			}
			
		}
		 else 
		{
			if (( copier == null )) 
			{
				de.polygonal.ds.Cloneable c = null;
				{
					int _g1 = 0;
					int _g = this._size;
					while (( _g1 < _g ))
					{
						int i = _g1++;
						c = ((de.polygonal.ds.Cloneable) (((java.lang.Object) (this._a.__get(i)) )) );
						{
							T x = ((T) (c.clone()) );
							copy._a.__set(i, x);
						}
						
					}
					
				}
				
			}
			 else 
			{
				int _g1 = 0;
				int _g = this._size;
				while (( _g1 < _g ))
				{
					int i = _g1++;
					copy._a.__set(i, ((T) (copier.__hx_invoke1_o(0.0, this._a.__get(i))) ));
				}
				
			}
			
		}
		
		return copy;
	}
	
	
	public   void shuffle(de.polygonal.ds.DA<java.lang.Object> rval)
	{
		int s = this._size;
		if (( rval == null )) 
		{
			java.lang.Class m = java.lang.Math.class;
			while ((  -- s > 1 ))
			{
				int i = ((int) (( java.lang.Math.random() * s )) );
				T t = this._a.__get(s);
				this._a.__set(s, this._a.__get(i));
				this._a.__set(i, t);
			}
			
		}
		 else 
		{
			int j = 0;
			while ((  -- s > 1 ))
			{
				int i = ((int) (( ((double) (haxe.lang.Runtime.toDouble(((haxe.root.Array<java.lang.Object>) (((haxe.root.Array) (rval._a) )) ).__get(j++))) ) * s )) );
				T t = this._a.__get(s);
				this._a.__set(s, this._a.__get(i));
				this._a.__set(i, t);
			}
			
		}
		
	}
	
	
	@Override public   java.lang.String toString()
	{
		java.lang.String s = ( ( "{ DA size: " + this._size ) + " }" );
		if (( this._size == 0 )) 
		{
			return s;
		}
		
		s += "\n[\n";
		{
			int _g1 = 0;
			int _g = this._size;
			while (( _g1 < _g ))
			{
				int i = _g1++;
				s += de.polygonal.Printf.format("  %4d -> %s\n", new haxe.root.Array(new java.lang.Object[]{i, haxe.root.Std.string(this._a.__get(i))}));
			}
			
		}
		
		s += "]";
		return s;
	}
	
	
	public   void _quickSort(int first, int k, haxe.lang.Function cmp)
	{
		int last = ( ( first + k ) - 1 );
		int lo = first;
		int hi = last;
		if (( k > 1 )) 
		{
			int i0 = first;
			int i1 = ( i0 + (( k >> 1 )) );
			int i2 = ( ( i0 + k ) - 1 );
			T t0 = this._a.__get(i0);
			T t1 = this._a.__get(i1);
			T t2 = this._a.__get(i2);
			int mid = 0;
			int t = ((int) (cmp.__hx_invoke2_f(0.0, 0.0, t0, t2)) );
			if (( ( t < 0 ) && ( ((int) (cmp.__hx_invoke2_f(0.0, 0.0, t0, t1)) ) < 0 ) )) 
			{
				mid = ( (( ((int) (cmp.__hx_invoke2_f(0.0, 0.0, t1, t2)) ) < 0 )) ? (i1) : (i2) );
			}
			 else 
			{
				if (( ( ((int) (cmp.__hx_invoke2_f(0.0, 0.0, t1, t0)) ) < 0 ) && ( ((int) (cmp.__hx_invoke2_f(0.0, 0.0, t1, t2)) ) < 0 ) )) 
				{
					mid = ( (( t < 0 )) ? (i0) : (i2) );
				}
				 else 
				{
					mid = ( (( ((int) (cmp.__hx_invoke2_f(0.0, 0.0, t2, t0)) ) < 0 )) ? (i1) : (i0) );
				}
				
			}
			
			T pivot = this._a.__get(mid);
			this._a.__set(mid, this._a.__get(first));
			while (( lo < hi ))
			{
				while (( ( ((int) (cmp.__hx_invoke2_f(0.0, 0.0, pivot, this._a.__get(hi))) ) < 0 ) && ( lo < hi ) ))
				{
					hi--;
				}
				
				if (( hi != lo )) 
				{
					this._a.__set(lo, this._a.__get(hi));
					lo++;
				}
				
				while (( ( ((int) (cmp.__hx_invoke2_f(0.0, 0.0, pivot, this._a.__get(lo))) ) > 0 ) && ( lo < hi ) ))
				{
					lo++;
				}
				
				if (( hi != lo )) 
				{
					this._a.__set(hi, this._a.__get(lo));
					hi--;
				}
				
			}
			
			this._a.__set(lo, pivot);
			this._quickSort(first, ( lo - first ), cmp);
			this._quickSort(( lo + 1 ), ( last - lo ), cmp);
		}
		
	}
	
	
	public   void _quickSortComparable(int first, int k)
	{
		int last = ( ( first + k ) - 1 );
		int lo = first;
		int hi = last;
		if (( k > 1 )) 
		{
			int i0 = first;
			int i1 = ( i0 + (( k >> 1 )) );
			int i2 = ( ( i0 + k ) - 1 );
			java.lang.Object t0 = ((de.polygonal.ds.Comparable) (((java.lang.Object) (this._a.__get(i0)) )) );
			java.lang.Object t1 = ((de.polygonal.ds.Comparable) (((java.lang.Object) (this._a.__get(i1)) )) );
			java.lang.Object t2 = ((de.polygonal.ds.Comparable) (((java.lang.Object) (this._a.__get(i2)) )) );
			int mid = 0;
			int t = ((int) (haxe.lang.Runtime.toInt(((java.lang.Object) (haxe.lang.Runtime.callField(t0, "compare", new haxe.root.Array(new java.lang.Object[]{t2}))) ))) );
			if (( ( t < 0 ) && ( haxe.lang.Runtime.compare(((java.lang.Object) (haxe.lang.Runtime.callField(t0, "compare", new haxe.root.Array(new java.lang.Object[]{t1}))) ), 0) < 0 ) )) 
			{
				mid = ( (( haxe.lang.Runtime.compare(((java.lang.Object) (haxe.lang.Runtime.callField(t1, "compare", new haxe.root.Array(new java.lang.Object[]{t2}))) ), 0) < 0 )) ? (i1) : (i2) );
			}
			 else 
			{
				if (( ( haxe.lang.Runtime.compare(((java.lang.Object) (haxe.lang.Runtime.callField(t0, "compare", new haxe.root.Array(new java.lang.Object[]{t1}))) ), 0) < 0 ) && ( haxe.lang.Runtime.compare(((java.lang.Object) (haxe.lang.Runtime.callField(t1, "compare", new haxe.root.Array(new java.lang.Object[]{t2}))) ), 0) < 0 ) )) 
				{
					mid = ( (( t < 0 )) ? (i0) : (i2) );
				}
				 else 
				{
					mid = ( (( haxe.lang.Runtime.compare(((java.lang.Object) (haxe.lang.Runtime.callField(t2, "compare", new haxe.root.Array(new java.lang.Object[]{t0}))) ), 0) < 0 )) ? (i1) : (i0) );
				}
				
			}
			
			java.lang.Object pivot = ((de.polygonal.ds.Comparable) (((java.lang.Object) (this._a.__get(mid)) )) );
			this._a.__set(mid, this._a.__get(first));
			while (( lo < hi ))
			{
				while (( ( haxe.lang.Runtime.compare(((java.lang.Object) (haxe.lang.Runtime.callField(pivot, "compare", new haxe.root.Array(new java.lang.Object[]{((de.polygonal.ds.Comparable) (((java.lang.Object) (this._a.__get(hi)) )) )}))) ), 0) < 0 ) && ( lo < hi ) ))
				{
					hi--;
				}
				
				if (( hi != lo )) 
				{
					this._a.__set(lo, this._a.__get(hi));
					lo++;
				}
				
				while (( ( haxe.lang.Runtime.compare(((java.lang.Object) (haxe.lang.Runtime.callField(pivot, "compare", new haxe.root.Array(new java.lang.Object[]{((de.polygonal.ds.Comparable) (((java.lang.Object) (this._a.__get(lo)) )) )}))) ), 0) > 0 ) && ( lo < hi ) ))
				{
					lo++;
				}
				
				if (( hi != lo )) 
				{
					this._a.__set(hi, this._a.__get(lo));
					hi--;
				}
				
			}
			
			this._a.__set(lo, ((T) (pivot) ));
			this._quickSortComparable(first, ( lo - first ));
			this._quickSortComparable(( lo + 1 ), ( last - lo ));
		}
		
	}
	
	
	public   void _insertionSort(int first, int k, haxe.lang.Function cmp)
	{
		int _g1 = ( first + 1 );
		int _g = ( first + k );
		while (( _g1 < _g ))
		{
			int i = _g1++;
			T x = this._a.__get(i);
			int j = i;
			while (( j > first ))
			{
				T y = this._a.__get(( j - 1 ));
				if (( ((int) (cmp.__hx_invoke2_f(0.0, 0.0, y, x)) ) > 0 )) 
				{
					this._a.__set(j, y);
					j--;
				}
				 else 
				{
					break;
				}
				
			}
			
			this._a.__set(j, x);
		}
		
	}
	
	
	public   void _insertionSortComparable(int first, int k)
	{
		int _g1 = ( first + 1 );
		int _g = ( first + k );
		while (( _g1 < _g ))
		{
			int i = _g1++;
			T x = this._a.__get(i);
			int j = i;
			while (( j > first ))
			{
				T y = this._a.__get(( j - 1 ));
				if (( (((de.polygonal.ds.Comparable) (((java.lang.Object) (y) )) )).compare(x) > 0 )) 
				{
					this._a.__set(j, y);
					j--;
				}
				 else 
				{
					break;
				}
				
			}
			
			this._a.__set(j, x);
		}
		
	}
	
	
	public final   T __get(int i)
	{
		return this._a.__get(i);
	}
	
	
	public final   void __set(int i, T x)
	{
		this._a.__set(i, x);
	}
	
	
	public final   void __cpy(int i, int j)
	{
		this._a.__set(i, this._a.__get(j));
	}
	
	
	@Override public   double __hx_setField_f(java.lang.String field, double value, boolean handleProperties)
	{
		{
			boolean __temp_executeDef103 = true;
			switch (field.hashCode())
			{
				case 844081029:
				{
					if (field.equals("maxSize")) 
					{
						__temp_executeDef103 = false;
						this.maxSize = ((int) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 106079:
				{
					if (field.equals("key")) 
					{
						__temp_executeDef103 = false;
						this.key = ((int) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 91265248:
				{
					if (field.equals("_size")) 
					{
						__temp_executeDef103 = false;
						this._size = ((int) (value) );
						return value;
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef103) 
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
			boolean __temp_executeDef104 = true;
			switch (field.hashCode())
			{
				case -766976254:
				{
					if (field.equals("reuseIterator")) 
					{
						__temp_executeDef104 = false;
						this.reuseIterator = haxe.lang.Runtime.toBool(value);
						return value;
					}
					
					break;
				}
				
				
				case 106079:
				{
					if (field.equals("key")) 
					{
						__temp_executeDef104 = false;
						this.key = ((int) (haxe.lang.Runtime.toInt(value)) );
						return value;
					}
					
					break;
				}
				
				
				case 844081029:
				{
					if (field.equals("maxSize")) 
					{
						__temp_executeDef104 = false;
						this.maxSize = ((int) (haxe.lang.Runtime.toInt(value)) );
						return value;
					}
					
					break;
				}
				
				
				case 3042:
				{
					if (field.equals("_a")) 
					{
						__temp_executeDef104 = false;
						this._a = ((haxe.root.Array<T>) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 1273051597:
				{
					if (field.equals("_iterator")) 
					{
						__temp_executeDef104 = false;
						this._iterator = ((de.polygonal.ds.DAIterator<T>) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 91265248:
				{
					if (field.equals("_size")) 
					{
						__temp_executeDef104 = false;
						this._size = ((int) (haxe.lang.Runtime.toInt(value)) );
						return value;
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef104) 
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
			boolean __temp_executeDef105 = true;
			switch (field.hashCode())
			{
				case 90663372:
				{
					if (field.equals("__cpy")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("__cpy"))) );
					}
					
					break;
				}
				
				
				case 106079:
				{
					if (field.equals("key")) 
					{
						__temp_executeDef105 = false;
						return this.key;
					}
					
					break;
				}
				
				
				case 90678402:
				{
					if (field.equals("__set")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("__set"))) );
					}
					
					break;
				}
				
				
				case 3042:
				{
					if (field.equals("_a")) 
					{
						__temp_executeDef105 = false;
						return this._a;
					}
					
					break;
				}
				
				
				case 90666870:
				{
					if (field.equals("__get")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("__get"))) );
					}
					
					break;
				}
				
				
				case 91265248:
				{
					if (field.equals("_size")) 
					{
						__temp_executeDef105 = false;
						return this._size;
					}
					
					break;
				}
				
				
				case -1022587288:
				{
					if (field.equals("_insertionSortComparable")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("_insertionSortComparable"))) );
					}
					
					break;
				}
				
				
				case 1273051597:
				{
					if (field.equals("_iterator")) 
					{
						__temp_executeDef105 = false;
						return this._iterator;
					}
					
					break;
				}
				
				
				case -978978066:
				{
					if (field.equals("_insertionSort")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("_insertionSort"))) );
					}
					
					break;
				}
				
				
				case 844081029:
				{
					if (field.equals("maxSize")) 
					{
						__temp_executeDef105 = false;
						return this.maxSize;
					}
					
					break;
				}
				
				
				case 1118308774:
				{
					if (field.equals("_quickSortComparable")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("_quickSortComparable"))) );
					}
					
					break;
				}
				
				
				case -766976254:
				{
					if (field.equals("reuseIterator")) 
					{
						__temp_executeDef105 = false;
						return this.reuseIterator;
					}
					
					break;
				}
				
				
				case -188167252:
				{
					if (field.equals("_quickSort")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("_quickSort"))) );
					}
					
					break;
				}
				
				
				case 3432985:
				{
					if (field.equals("pack")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("pack"))) );
					}
					
					break;
				}
				
				
				case -1776922004:
				{
					if (field.equals("toString")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("toString"))) );
					}
					
					break;
				}
				
				
				case 1097075900:
				{
					if (field.equals("reserve")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("reserve"))) );
					}
					
					break;
				}
				
				
				case 2072332025:
				{
					if (field.equals("shuffle")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("shuffle"))) );
					}
					
					break;
				}
				
				
				case 3568674:
				{
					if (field.equals("trim")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("trim"))) );
					}
					
					break;
				}
				
				
				case 94756189:
				{
					if (field.equals("clone")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("clone"))) );
					}
					
					break;
				}
				
				
				case 102230:
				{
					if (field.equals("get")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("get"))) );
					}
					
					break;
				}
				
				
				case -1182381922:
				{
					if (field.equals("toArray")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("toArray"))) );
					}
					
					break;
				}
				
				
				case -75304087:
				{
					if (field.equals("getNext")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("getNext"))) );
					}
					
					break;
				}
				
				
				case 2058039875:
				{
					if (field.equals("isEmpty")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("isEmpty"))) );
					}
					
					break;
				}
				
				
				case -75232599:
				{
					if (field.equals("getPrev")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("getPrev"))) );
					}
					
					break;
				}
				
				
				case 3530753:
				{
					if (field.equals("size")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("size"))) );
					}
					
					break;
				}
				
				
				case 113762:
				{
					if (field.equals("set")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("set"))) );
					}
					
					break;
				}
				
				
				case 1182533742:
				{
					if (field.equals("iterator")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("iterator"))) );
					}
					
					break;
				}
				
				
				case 114316:
				{
					if (field.equals("swp")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("swp"))) );
					}
					
					break;
				}
				
				
				case 94746189:
				{
					if (field.equals("clear")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("clear"))) );
					}
					
					break;
				}
				
				
				case 98732:
				{
					if (field.equals("cpy")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("cpy"))) );
					}
					
					break;
				}
				
				
				case -934610812:
				{
					if (field.equals("remove")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("remove"))) );
					}
					
					break;
				}
				
				
				case 97705513:
				{
					if (field.equals("front")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("front"))) );
					}
					
					break;
				}
				
				
				case -567445985:
				{
					if (field.equals("contains")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("contains"))) );
					}
					
					break;
				}
				
				
				case 3015911:
				{
					if (field.equals("back")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("back"))) );
					}
					
					break;
				}
				
				
				case 3151468:
				{
					if (field.equals("free")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("free"))) );
					}
					
					break;
				}
				
				
				case -395470120:
				{
					if (field.equals("popBack")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("popBack"))) );
					}
					
					break;
				}
				
				
				case 1948915875:
				{
					if (field.equals("getArray")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("getArray"))) );
					}
					
					break;
				}
				
				
				case 1775438625:
				{
					if (field.equals("pushBack")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("pushBack"))) );
					}
					
					break;
				}
				
				
				case 1926540056:
				{
					if (field.equals("inRange")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("inRange"))) );
					}
					
					break;
				}
				
				
				case 629540440:
				{
					if (field.equals("popFront")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("popFront"))) );
					}
					
					break;
				}
				
				
				case 3536286:
				{
					if (field.equals("sort")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("sort"))) );
					}
					
					break;
				}
				
				
				case -791765201:
				{
					if (field.equals("pushFront")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("pushFront"))) );
					}
					
					break;
				}
				
				
				case 3267882:
				{
					if (field.equals("join")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("join"))) );
					}
					
					break;
				}
				
				
				case 541786316:
				{
					if (field.equals("insertAt")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("insertAt"))) );
					}
					
					break;
				}
				
				
				case 949219110:
				{
					if (field.equals("memmove")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("memmove"))) );
					}
					
					break;
				}
				
				
				case -512823337:
				{
					if (field.equals("removeAt")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("removeAt"))) );
					}
					
					break;
				}
				
				
				case 3143043:
				{
					if (field.equals("fill")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("fill"))) );
					}
					
					break;
				}
				
				
				case -1811391554:
				{
					if (field.equals("swapPop")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("swapPop"))) );
					}
					
					break;
				}
				
				
				case -1408204561:
				{
					if (field.equals("assign")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("assign"))) );
					}
					
					break;
				}
				
				
				case -306117863:
				{
					if (field.equals("removeRange")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("removeRange"))) );
					}
					
					break;
				}
				
				
				case 1099846370:
				{
					if (field.equals("reverse")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("reverse"))) );
					}
					
					break;
				}
				
				
				case -1354795244:
				{
					if (field.equals("concat")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("concat"))) );
					}
					
					break;
				}
				
				
				case -467511597:
				{
					if (field.equals("lastIndexOf")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("lastIndexOf"))) );
					}
					
					break;
				}
				
				
				case 1943291465:
				{
					if (field.equals("indexOf")) 
					{
						__temp_executeDef105 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("indexOf"))) );
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef105) 
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
			boolean __temp_executeDef106 = true;
			switch (field.hashCode())
			{
				case 844081029:
				{
					if (field.equals("maxSize")) 
					{
						__temp_executeDef106 = false;
						return ((double) (this.maxSize) );
					}
					
					break;
				}
				
				
				case 106079:
				{
					if (field.equals("key")) 
					{
						__temp_executeDef106 = false;
						return ((double) (this.key) );
					}
					
					break;
				}
				
				
				case 91265248:
				{
					if (field.equals("_size")) 
					{
						__temp_executeDef106 = false;
						return ((double) (this._size) );
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef106) 
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
			boolean __temp_executeDef107 = true;
			switch (field.hashCode())
			{
				case 90663372:
				{
					if (field.equals("__cpy")) 
					{
						__temp_executeDef107 = false;
						this.__cpy(((int) (haxe.lang.Runtime.toInt(dynargs.__get(0))) ), ((int) (haxe.lang.Runtime.toInt(dynargs.__get(1))) ));
					}
					
					break;
				}
				
				
				case 3432985:
				{
					if (field.equals("pack")) 
					{
						__temp_executeDef107 = false;
						this.pack();
					}
					
					break;
				}
				
				
				case 90678402:
				{
					if (field.equals("__set")) 
					{
						__temp_executeDef107 = false;
						this.__set(((int) (haxe.lang.Runtime.toInt(dynargs.__get(0))) ), ((T) (dynargs.__get(1)) ));
					}
					
					break;
				}
				
				
				case 1097075900:
				{
					if (field.equals("reserve")) 
					{
						__temp_executeDef107 = false;
						this.reserve(((int) (haxe.lang.Runtime.toInt(dynargs.__get(0))) ));
					}
					
					break;
				}
				
				
				case 90666870:
				{
					if (field.equals("__get")) 
					{
						__temp_executeDef107 = false;
						return this.__get(((int) (haxe.lang.Runtime.toInt(dynargs.__get(0))) ));
					}
					
					break;
				}
				
				
				case 3568674:
				{
					if (field.equals("trim")) 
					{
						__temp_executeDef107 = false;
						this.trim(((int) (haxe.lang.Runtime.toInt(dynargs.__get(0))) ));
					}
					
					break;
				}
				
				
				case -1022587288:
				{
					if (field.equals("_insertionSortComparable")) 
					{
						__temp_executeDef107 = false;
						this._insertionSortComparable(((int) (haxe.lang.Runtime.toInt(dynargs.__get(0))) ), ((int) (haxe.lang.Runtime.toInt(dynargs.__get(1))) ));
					}
					
					break;
				}
				
				
				case 102230:
				{
					if (field.equals("get")) 
					{
						__temp_executeDef107 = false;
						return this.get(((int) (haxe.lang.Runtime.toInt(dynargs.__get(0))) ));
					}
					
					break;
				}
				
				
				case -978978066:
				{
					if (field.equals("_insertionSort")) 
					{
						__temp_executeDef107 = false;
						this._insertionSort(((int) (haxe.lang.Runtime.toInt(dynargs.__get(0))) ), ((int) (haxe.lang.Runtime.toInt(dynargs.__get(1))) ), ((haxe.lang.Function) (dynargs.__get(2)) ));
					}
					
					break;
				}
				
				
				case -75304087:
				{
					if (field.equals("getNext")) 
					{
						__temp_executeDef107 = false;
						return this.getNext(((int) (haxe.lang.Runtime.toInt(dynargs.__get(0))) ));
					}
					
					break;
				}
				
				
				case 1118308774:
				{
					if (field.equals("_quickSortComparable")) 
					{
						__temp_executeDef107 = false;
						this._quickSortComparable(((int) (haxe.lang.Runtime.toInt(dynargs.__get(0))) ), ((int) (haxe.lang.Runtime.toInt(dynargs.__get(1))) ));
					}
					
					break;
				}
				
				
				case -75232599:
				{
					if (field.equals("getPrev")) 
					{
						__temp_executeDef107 = false;
						return this.getPrev(((int) (haxe.lang.Runtime.toInt(dynargs.__get(0))) ));
					}
					
					break;
				}
				
				
				case -188167252:
				{
					if (field.equals("_quickSort")) 
					{
						__temp_executeDef107 = false;
						this._quickSort(((int) (haxe.lang.Runtime.toInt(dynargs.__get(0))) ), ((int) (haxe.lang.Runtime.toInt(dynargs.__get(1))) ), ((haxe.lang.Function) (dynargs.__get(2)) ));
					}
					
					break;
				}
				
				
				case 113762:
				{
					if (field.equals("set")) 
					{
						__temp_executeDef107 = false;
						this.set(((int) (haxe.lang.Runtime.toInt(dynargs.__get(0))) ), ((T) (dynargs.__get(1)) ));
					}
					
					break;
				}
				
				
				case -1776922004:
				{
					if (field.equals("toString")) 
					{
						__temp_executeDef107 = false;
						return this.toString();
					}
					
					break;
				}
				
				
				case 114316:
				{
					if (field.equals("swp")) 
					{
						__temp_executeDef107 = false;
						this.swp(((int) (haxe.lang.Runtime.toInt(dynargs.__get(0))) ), ((int) (haxe.lang.Runtime.toInt(dynargs.__get(1))) ));
					}
					
					break;
				}
				
				
				case 2072332025:
				{
					if (field.equals("shuffle")) 
					{
						__temp_executeDef107 = false;
						this.shuffle(((de.polygonal.ds.DA<java.lang.Object>) (dynargs.__get(0)) ));
					}
					
					break;
				}
				
				
				case 98732:
				{
					if (field.equals("cpy")) 
					{
						__temp_executeDef107 = false;
						this.cpy(((int) (haxe.lang.Runtime.toInt(dynargs.__get(0))) ), ((int) (haxe.lang.Runtime.toInt(dynargs.__get(1))) ));
					}
					
					break;
				}
				
				
				case 94756189:
				{
					if (field.equals("clone")) 
					{
						__temp_executeDef107 = false;
						return this.clone(dynargs.__get(0), ((haxe.lang.Function) (dynargs.__get(1)) ));
					}
					
					break;
				}
				
				
				case 97705513:
				{
					if (field.equals("front")) 
					{
						__temp_executeDef107 = false;
						return this.front();
					}
					
					break;
				}
				
				
				case -1182381922:
				{
					if (field.equals("toArray")) 
					{
						__temp_executeDef107 = false;
						return this.toArray();
					}
					
					break;
				}
				
				
				case 3015911:
				{
					if (field.equals("back")) 
					{
						__temp_executeDef107 = false;
						return this.back();
					}
					
					break;
				}
				
				
				case 2058039875:
				{
					if (field.equals("isEmpty")) 
					{
						__temp_executeDef107 = false;
						return this.isEmpty();
					}
					
					break;
				}
				
				
				case -395470120:
				{
					if (field.equals("popBack")) 
					{
						__temp_executeDef107 = false;
						return this.popBack();
					}
					
					break;
				}
				
				
				case 3530753:
				{
					if (field.equals("size")) 
					{
						__temp_executeDef107 = false;
						return this.size();
					}
					
					break;
				}
				
				
				case 1775438625:
				{
					if (field.equals("pushBack")) 
					{
						__temp_executeDef107 = false;
						this.pushBack(((T) (dynargs.__get(0)) ));
					}
					
					break;
				}
				
				
				case 1182533742:
				{
					if (field.equals("iterator")) 
					{
						__temp_executeDef107 = false;
						return this.iterator();
					}
					
					break;
				}
				
				
				case 629540440:
				{
					if (field.equals("popFront")) 
					{
						__temp_executeDef107 = false;
						return this.popFront();
					}
					
					break;
				}
				
				
				case 94746189:
				{
					if (field.equals("clear")) 
					{
						__temp_executeDef107 = false;
						this.clear(dynargs.__get(0));
					}
					
					break;
				}
				
				
				case -791765201:
				{
					if (field.equals("pushFront")) 
					{
						__temp_executeDef107 = false;
						this.pushFront(((T) (dynargs.__get(0)) ));
					}
					
					break;
				}
				
				
				case -934610812:
				{
					if (field.equals("remove")) 
					{
						__temp_executeDef107 = false;
						return this.remove(((T) (dynargs.__get(0)) ));
					}
					
					break;
				}
				
				
				case 541786316:
				{
					if (field.equals("insertAt")) 
					{
						__temp_executeDef107 = false;
						this.insertAt(((int) (haxe.lang.Runtime.toInt(dynargs.__get(0))) ), ((T) (dynargs.__get(1)) ));
					}
					
					break;
				}
				
				
				case -567445985:
				{
					if (field.equals("contains")) 
					{
						__temp_executeDef107 = false;
						return this.contains(((T) (dynargs.__get(0)) ));
					}
					
					break;
				}
				
				
				case -512823337:
				{
					if (field.equals("removeAt")) 
					{
						__temp_executeDef107 = false;
						return this.removeAt(((int) (haxe.lang.Runtime.toInt(dynargs.__get(0))) ));
					}
					
					break;
				}
				
				
				case 3151468:
				{
					if (field.equals("free")) 
					{
						__temp_executeDef107 = false;
						this.free();
					}
					
					break;
				}
				
				
				case -1811391554:
				{
					if (field.equals("swapPop")) 
					{
						__temp_executeDef107 = false;
						this.swapPop(((int) (haxe.lang.Runtime.toInt(dynargs.__get(0))) ));
					}
					
					break;
				}
				
				
				case 1948915875:
				{
					if (field.equals("getArray")) 
					{
						__temp_executeDef107 = false;
						return this.getArray();
					}
					
					break;
				}
				
				
				case -306117863:
				{
					if (field.equals("removeRange")) 
					{
						__temp_executeDef107 = false;
						return this.removeRange(((int) (haxe.lang.Runtime.toInt(dynargs.__get(0))) ), ((int) (haxe.lang.Runtime.toInt(dynargs.__get(1))) ), ((de.polygonal.ds.DA<T>) (dynargs.__get(2)) ));
					}
					
					break;
				}
				
				
				case 1926540056:
				{
					if (field.equals("inRange")) 
					{
						__temp_executeDef107 = false;
						return this.inRange(((int) (haxe.lang.Runtime.toInt(dynargs.__get(0))) ));
					}
					
					break;
				}
				
				
				case -1354795244:
				{
					if (field.equals("concat")) 
					{
						__temp_executeDef107 = false;
						return this.concat(((de.polygonal.ds.DA<T>) (dynargs.__get(0)) ), dynargs.__get(1));
					}
					
					break;
				}
				
				
				case 3536286:
				{
					if (field.equals("sort")) 
					{
						__temp_executeDef107 = false;
						this.sort(((haxe.lang.Function) (dynargs.__get(0)) ), dynargs.__get(1), dynargs.__get(2), dynargs.__get(3));
					}
					
					break;
				}
				
				
				case 1943291465:
				{
					if (field.equals("indexOf")) 
					{
						__temp_executeDef107 = false;
						return this.indexOf(((T) (dynargs.__get(0)) ), dynargs.__get(1), dynargs.__get(2), ((haxe.lang.Function) (dynargs.__get(3)) ));
					}
					
					break;
				}
				
				
				case 3267882:
				{
					if (field.equals("join")) 
					{
						__temp_executeDef107 = false;
						return this.join(haxe.lang.Runtime.toString(dynargs.__get(0)));
					}
					
					break;
				}
				
				
				case -467511597:
				{
					if (field.equals("lastIndexOf")) 
					{
						__temp_executeDef107 = false;
						return this.lastIndexOf(((T) (dynargs.__get(0)) ), dynargs.__get(1));
					}
					
					break;
				}
				
				
				case 949219110:
				{
					if (field.equals("memmove")) 
					{
						__temp_executeDef107 = false;
						this.memmove(((int) (haxe.lang.Runtime.toInt(dynargs.__get(0))) ), ((int) (haxe.lang.Runtime.toInt(dynargs.__get(1))) ), ((int) (haxe.lang.Runtime.toInt(dynargs.__get(2))) ));
					}
					
					break;
				}
				
				
				case 1099846370:
				{
					if (field.equals("reverse")) 
					{
						__temp_executeDef107 = false;
						this.reverse();
					}
					
					break;
				}
				
				
				case 3143043:
				{
					if (field.equals("fill")) 
					{
						__temp_executeDef107 = false;
						return this.fill(((T) (dynargs.__get(0)) ), dynargs.__get(1));
					}
					
					break;
				}
				
				
				case -1408204561:
				{
					if (field.equals("assign")) 
					{
						__temp_executeDef107 = false;
						this.assign(((java.lang.Class<T>) (dynargs.__get(0)) ), ((haxe.root.Array) (dynargs.__get(1)) ), dynargs.__get(2));
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef107) 
			{
				return super.__hx_invokeField(field, dynargs);
			}
			
		}
		
		return null;
	}
	
	
	@Override public   void __hx_getFields(haxe.root.Array<java.lang.String> baseArr)
	{
		baseArr.push("reuseIterator");
		baseArr.push("maxSize");
		baseArr.push("_iterator");
		baseArr.push("_size");
		baseArr.push("_a");
		baseArr.push("key");
		{
			super.__hx_getFields(baseArr);
		}
		
	}
	
	
}


