package de.polygonal.ds;
import haxe.root.*;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class ArrayUtil extends haxe.lang.HxObject
{
	public    ArrayUtil(haxe.lang.EmptyObject empty)
	{
		{
		}
		
	}
	
	
	public    ArrayUtil()
	{
		de.polygonal.ds.ArrayUtil.__hx_ctor_de_polygonal_ds_ArrayUtil(this);
	}
	
	
	public static   void __hx_ctor_de_polygonal_ds_ArrayUtil(de.polygonal.ds.ArrayUtil __temp_me12)
	{
		{
		}
		
	}
	
	
	public static  <T> haxe.root.Array<T> alloc(int x)
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
		
		return a;
	}
	
	
	public static  <T> haxe.root.Array<T> shrink(haxe.root.Array<T> a, int x)
	{
		haxe.root.Array<T> b = new haxe.root.Array<T>();
		{
			int _g = 0;
			while (( _g < x ))
			{
				int i = _g++;
				b.__set(i, a.__get(i));
			}
			
		}
		
		return b;
	}
	
	
	public static  <T> haxe.root.Array<T> copy(haxe.root.Array<T> src, haxe.root.Array<T> dst, java.lang.Object min, java.lang.Object max)
	{
		int __temp_max9 = ( (( max == null )) ? (((int) (-1) )) : (((int) (haxe.lang.Runtime.toInt(max)) )) );
		int __temp_min8 = ( (( min == null )) ? (((int) (0) )) : (((int) (haxe.lang.Runtime.toInt(min)) )) );
		if (( __temp_max9 == -1 )) 
		{
			__temp_max9 = src.length;
		}
		
		int j = 0;
		{
			int _g = __temp_min8;
			while (( _g < ((int) (__temp_max9) ) ))
			{
				int i = _g++;
				dst.__set(j++, src.__get(i));
			}
			
		}
		
		return dst;
	}
	
	
	public static  <T> void fill(haxe.root.Array<T> dst, T x, java.lang.Object k)
	{
		int __temp_k10 = ( (( k == null )) ? (((int) (-1) )) : (((int) (haxe.lang.Runtime.toInt(k)) )) );
		if (( __temp_k10 == -1 )) 
		{
			__temp_k10 = dst.length;
		}
		
		{
			int _g = 0;
			while (( _g < ((int) (__temp_k10) ) ))
			{
				int i = _g++;
				dst.__set(i, x);
			}
			
		}
		
	}
	
	
	public static  <T> void assign(haxe.root.Array<T> dst, java.lang.Class<T> C, haxe.root.Array args, java.lang.Object k)
	{
		int __temp_k11 = ( (( k == null )) ? (((int) (-1) )) : (((int) (haxe.lang.Runtime.toInt(k)) )) );
		if (( __temp_k11 == -1 )) 
		{
			__temp_k11 = dst.length;
		}
		
		if (( args == null )) 
		{
			args = new haxe.root.Array(new java.lang.Object[]{});
		}
		
		{
			int _g = 0;
			while (( _g < ((int) (__temp_k11) ) ))
			{
				int i = _g++;
				dst.__set(i, haxe.root.Type.createInstance(C, args));
			}
			
		}
		
	}
	
	
	public static  <T> void memmove(haxe.root.Array<T> a, int destination, int source, int n)
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
						a.__set(j, a.__get(i));
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
						a.__set(j, a.__get(i));
						i++;
						j++;
					}
					
				}
				
			}
			
		}
		
	}
	
	
	public static  <T> int bsearchComparator(haxe.root.Array<T> a, T x, int min, int max, haxe.lang.Function comparator)
	{
		int l = min;
		int m = 0;
		int h = ( max + 1 );
		while (( l < h ))
		{
			m = ( l + (( ( h - l ) >> 1 )) );
			if (( ((int) (comparator.__hx_invoke2_f(0.0, 0.0, a.__get(m), x)) ) < 0 )) 
			{
				l = ( m + 1 );
			}
			 else 
			{
				h = m;
			}
			
		}
		
		if (( ( l <= max ) && ( ((int) (comparator.__hx_invoke2_f(0.0, 0.0, a.__get(l), x)) ) == 0 ) )) 
		{
			return l;
		}
		 else 
		{
			return  ~ (l) ;
		}
		
	}
	
	
	public static   int bsearchInt(haxe.root.Array<java.lang.Object> a, int x, int min, int max)
	{
		int l = min;
		int m = 0;
		int h = ( max + 1 );
		while (( l < h ))
		{
			m = ( l + (( ( h - l ) >> 1 )) );
			if (( ((int) (haxe.lang.Runtime.toInt(a.__get(m))) ) < x )) 
			{
				l = ( m + 1 );
			}
			 else 
			{
				h = m;
			}
			
		}
		
		if (( ( l <= max ) && ( ((int) (haxe.lang.Runtime.toInt(a.__get(l))) ) == x ) )) 
		{
			return l;
		}
		 else 
		{
			return  ~ (l) ;
		}
		
	}
	
	
	public static   int bsearchFloat(haxe.root.Array<java.lang.Object> a, double x, int min, int max)
	{
		int l = min;
		int m = 0;
		int h = ( max + 1 );
		while (( l < h ))
		{
			m = ( l + (( ( h - l ) >> 1 )) );
			if (( ((double) (haxe.lang.Runtime.toDouble(a.__get(m))) ) < x )) 
			{
				l = ( m + 1 );
			}
			 else 
			{
				h = m;
			}
			
		}
		
		if (( ( l <= max ) && ( ((double) (haxe.lang.Runtime.toDouble(a.__get(l))) ) == x ) )) 
		{
			return l;
		}
		 else 
		{
			return  ~ (l) ;
		}
		
	}
	
	
	public static  <T> void shuffle(haxe.root.Array<T> a, haxe.root.Array<java.lang.Object> rval)
	{
		int s = a.length;
		if (( rval == null )) 
		{
			java.lang.Class m = java.lang.Math.class;
			while ((  -- s > 1 ))
			{
				int i = ((int) (( java.lang.Math.random() * s )) );
				T t = a.__get(s);
				a.__set(s, a.__get(i));
				a.__set(i, t);
			}
			
		}
		 else 
		{
			int j = 0;
			while ((  -- s > 1 ))
			{
				int i = ((int) (( ((double) (haxe.lang.Runtime.toDouble(rval.__get(j++))) ) * s )) );
				T t = a.__get(s);
				a.__set(s, a.__get(i));
				a.__set(i, t);
			}
			
		}
		
	}
	
	
	public static   void sortRange(haxe.root.Array<java.lang.Object> a, haxe.lang.Function compare, boolean useInsertionSort, int first, int count)
	{
		int k = a.length;
		if (( k > 1 )) 
		{
			if (useInsertionSort) 
			{
				de.polygonal.ds.ArrayUtil._insertionSort(a, first, count, compare);
			}
			 else 
			{
				de.polygonal.ds.ArrayUtil._quickSort(a, first, count, compare);
			}
			
		}
		
	}
	
	
	public static   haxe.root.Array<haxe.root.Array> quickPerm(int n)
	{
		haxe.root.Array<haxe.root.Array> results = new haxe.root.Array<haxe.root.Array>(new haxe.root.Array[]{});
		haxe.root.Array<java.lang.Object> a = new haxe.root.Array<java.lang.Object>(new java.lang.Object[]{});
		haxe.root.Array<java.lang.Object> p = new haxe.root.Array<java.lang.Object>(new java.lang.Object[]{});
		int i = 0;
		int j = 0;
		int tmp = 0;
		{
			int _g = 0;
			while (( _g < n ))
			{
				int i1 = _g++;
				a.__set(i1, ( i1 + 1 ));
				p.__set(i1, 0);
			}
			
		}
		
		results.push(((haxe.root.Array) (((haxe.root.Array<java.lang.Object>) (((haxe.root.Array) (a.copy()) )) )) ));
		i = 1;
		while (( i < n ))
		{
			if (( ((int) (haxe.lang.Runtime.toInt(p.__get(i))) ) < i )) 
			{
				j = ( ( i % 2 ) * ((int) (haxe.lang.Runtime.toInt(p.__get(i))) ) );
				tmp = ((int) (haxe.lang.Runtime.toInt(a.__get(j))) );
				a.__set(j, ((int) (haxe.lang.Runtime.toInt(a.__get(i))) ));
				a.__set(i, tmp);
				results.push(((haxe.root.Array) (((haxe.root.Array<java.lang.Object>) (((haxe.root.Array) (a.copy()) )) )) ));
				{
					int __temp_arrVal67 = ((int) (haxe.lang.Runtime.toInt(p.__get(i))) );
					int __temp_arrRet68 = __temp_arrVal67++;
					p.__set(i, __temp_arrVal67);
					int __temp_expr101 = __temp_arrRet68;
				}
				
				i = 1;
			}
			 else 
			{
				p.__set(i, 0);
				i++;
			}
			
		}
		
		return results;
	}
	
	
	public static  <T> boolean equals(haxe.root.Array<T> a, haxe.root.Array<T> b)
	{
		if (( a.length != b.length )) 
		{
			return false;
		}
		
		{
			int _g1 = 0;
			int _g = a.length;
			while (( _g1 < _g ))
			{
				int i = _g1++;
				if (( ! (haxe.lang.Runtime.eq(a.__get(i), b.__get(i))) )) 
				{
					return false;
				}
				
			}
			
		}
		
		return true;
	}
	
	
	public static  <T> haxe.root.Array<haxe.root.Array> split(haxe.root.Array<T> a, int n, int k)
	{
		haxe.root.Array<haxe.root.Array> output = new haxe.root.Array<haxe.root.Array>();
		haxe.root.Array<T> b = null;
		{
			int _g = 0;
			while (( _g < n ))
			{
				int i = _g++;
				if (( ( i % k ) == 0 )) 
				{
					output.__set(( i / k ), ((haxe.root.Array) (b = new haxe.root.Array<T>(( (T[]) (new java.lang.Object[] {}) ))) ));
				}
				
				b.push(a.__get(i));
			}
			
		}
		
		return output;
	}
	
	
	public static   void _insertionSort(haxe.root.Array<java.lang.Object> a, int first, int k, haxe.lang.Function cmp)
	{
		int _g1 = ( first + 1 );
		int _g = ( first + k );
		while (( _g1 < _g ))
		{
			int i = _g1++;
			double x = ((double) (haxe.lang.Runtime.toDouble(a.__get(i))) );
			int j = i;
			while (( j > first ))
			{
				double y = ((double) (haxe.lang.Runtime.toDouble(a.__get(( j - 1 )))) );
				if (( ((int) (cmp.__hx_invoke2_f(y, x, haxe.lang.Runtime.undefined, haxe.lang.Runtime.undefined)) ) > 0 )) 
				{
					a.__set(j, y);
					j--;
				}
				 else 
				{
					break;
				}
				
			}
			
			a.__set(j, x);
		}
		
	}
	
	
	public static   void _quickSort(haxe.root.Array<java.lang.Object> a, int first, int k, haxe.lang.Function cmp)
	{
		int last = ( ( first + k ) - 1 );
		int lo = first;
		int hi = last;
		if (( k > 1 )) 
		{
			int i0 = first;
			int i1 = ( i0 + (( k >> 1 )) );
			int i2 = ( ( i0 + k ) - 1 );
			double t0 = ((double) (haxe.lang.Runtime.toDouble(a.__get(i0))) );
			double t1 = ((double) (haxe.lang.Runtime.toDouble(a.__get(i1))) );
			double t2 = ((double) (haxe.lang.Runtime.toDouble(a.__get(i2))) );
			int mid = 0;
			int t = ((int) (cmp.__hx_invoke2_f(t0, t2, haxe.lang.Runtime.undefined, haxe.lang.Runtime.undefined)) );
			if (( ( t < 0 ) && ( ((int) (cmp.__hx_invoke2_f(t0, t1, haxe.lang.Runtime.undefined, haxe.lang.Runtime.undefined)) ) < 0 ) )) 
			{
				mid = ( (( ((int) (cmp.__hx_invoke2_f(t1, t2, haxe.lang.Runtime.undefined, haxe.lang.Runtime.undefined)) ) < 0 )) ? (i1) : (i2) );
			}
			 else 
			{
				if (( ( ((int) (cmp.__hx_invoke2_f(t1, t0, haxe.lang.Runtime.undefined, haxe.lang.Runtime.undefined)) ) < 0 ) && ( ((int) (cmp.__hx_invoke2_f(t1, t2, haxe.lang.Runtime.undefined, haxe.lang.Runtime.undefined)) ) < 0 ) )) 
				{
					mid = ( (( t < 0 )) ? (i0) : (i2) );
				}
				 else 
				{
					mid = ( (( ((int) (cmp.__hx_invoke2_f(t2, t0, haxe.lang.Runtime.undefined, haxe.lang.Runtime.undefined)) ) < 0 )) ? (i1) : (i0) );
				}
				
			}
			
			double pivot = ((double) (haxe.lang.Runtime.toDouble(a.__get(mid))) );
			a.__set(mid, ((double) (haxe.lang.Runtime.toDouble(a.__get(first))) ));
			while (( lo < hi ))
			{
				while (( ( ((int) (cmp.__hx_invoke2_f(pivot, ((double) (haxe.lang.Runtime.toDouble(a.__get(hi))) ), haxe.lang.Runtime.undefined, haxe.lang.Runtime.undefined)) ) < 0 ) && ( lo < hi ) ))
				{
					hi--;
				}
				
				if (( hi != lo )) 
				{
					a.__set(lo, ((double) (haxe.lang.Runtime.toDouble(a.__get(hi))) ));
					lo++;
				}
				
				while (( ( ((int) (cmp.__hx_invoke2_f(pivot, ((double) (haxe.lang.Runtime.toDouble(a.__get(lo))) ), haxe.lang.Runtime.undefined, haxe.lang.Runtime.undefined)) ) > 0 ) && ( lo < hi ) ))
				{
					lo++;
				}
				
				if (( hi != lo )) 
				{
					a.__set(hi, ((double) (haxe.lang.Runtime.toDouble(a.__get(lo))) ));
					hi--;
				}
				
			}
			
			a.__set(lo, pivot);
			de.polygonal.ds.ArrayUtil._quickSort(a, first, ( lo - first ), cmp);
			de.polygonal.ds.ArrayUtil._quickSort(a, ( lo + 1 ), ( last - lo ), cmp);
		}
		
	}
	
	
	public static   java.lang.Object __hx_createEmpty()
	{
		return new de.polygonal.ds.ArrayUtil(((haxe.lang.EmptyObject) (haxe.lang.EmptyObject.EMPTY) ));
	}
	
	
	public static   java.lang.Object __hx_create(haxe.root.Array arr)
	{
		return new de.polygonal.ds.ArrayUtil();
	}
	
	
}


