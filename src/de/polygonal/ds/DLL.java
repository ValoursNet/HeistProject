package de.polygonal.ds;
import haxe.root.*;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class DLL<T> extends haxe.lang.HxObject implements de.polygonal.ds.Collection<T>
{
	public    DLL(haxe.lang.EmptyObject empty)
	{
		{
		}
		
	}
	
	
	public    DLL(java.lang.Object reservedSize, java.lang.Object maxSize)
	{
		de.polygonal.ds.DLL.__hx_ctor_de_polygonal_ds_DLL(this, reservedSize, maxSize);
	}
	
	
	public static  <T1> void __hx_ctor_de_polygonal_ds_DLL(de.polygonal.ds.DLL<T1> __temp_me35, java.lang.Object reservedSize, java.lang.Object maxSize)
	{
		int __temp_maxSize34 = ( (( maxSize == null )) ? (((int) (-1) )) : (((int) (haxe.lang.Runtime.toInt(maxSize)) )) );
		int __temp_reservedSize33 = ( (( reservedSize == null )) ? (((int) (0) )) : (((int) (haxe.lang.Runtime.toInt(reservedSize)) )) );
		__temp_me35.maxSize = -1;
		__temp_me35._reservedSize = __temp_reservedSize33;
		__temp_me35._size = 0;
		__temp_me35._poolSize = 0;
		__temp_me35._circular = false;
		__temp_me35._iterator = null;
		if (( __temp_reservedSize33 > 0 )) 
		{
			__temp_me35._headPool = __temp_me35._tailPool = new de.polygonal.ds.DLLNode<T1>(((T1) (null) ), ((de.polygonal.ds.DLL<T1>) (__temp_me35) ));
		}
		
		__temp_me35.head = __temp_me35.tail = null;
		__temp_me35.key = de.polygonal.ds.HashKey._counter++;
		__temp_me35.reuseIterator = false;
	}
	
	
	public static   java.lang.Object __hx_createEmpty()
	{
		return new de.polygonal.ds.DLL<java.lang.Object>(((haxe.lang.EmptyObject) (haxe.lang.EmptyObject.EMPTY) ));
	}
	
	
	public static   java.lang.Object __hx_create(haxe.root.Array arr)
	{
		return new de.polygonal.ds.DLL<java.lang.Object>(((java.lang.Object) (arr.__get(0)) ), ((java.lang.Object) (arr.__get(1)) ));
	}
	
	
	public  int key;
	
	public  de.polygonal.ds.DLLNode<T> head;
	
	public  de.polygonal.ds.DLLNode<T> tail;
	
	public  int maxSize;
	
	public  boolean reuseIterator;
	
	public  int _size;
	
	public  int _reservedSize;
	
	public  int _poolSize;
	
	public  de.polygonal.ds.DLLNode<T> _headPool;
	
	public  de.polygonal.ds.DLLNode<T> _tailPool;
	
	public  boolean _circular;
	
	public  de.polygonal.ds.Itr<T> _iterator;
	
	public   boolean isCircular()
	{
		return this._circular;
	}
	
	
	public   void close()
	{
		if (this._circular) 
		{
			return ;
		}
		
		this._circular = true;
		if (( this.head != null )) 
		{
			this.tail.next = this.head;
			this.head.prev = this.tail;
		}
		
	}
	
	
	public   void open()
	{
		if ( ! (this._circular) ) 
		{
			return ;
		}
		
		this._circular = false;
		if (( this.head != null )) 
		{
			this.tail.next = null;
			this.head.prev = null;
		}
		
	}
	
	
	public   de.polygonal.ds.DLLNode<T> createNode(T x)
	{
		return new de.polygonal.ds.DLLNode<T>(((T) (x) ), ((de.polygonal.ds.DLL<T>) (this) ));
	}
	
	
	public   de.polygonal.ds.DLLNode<T> append(T x)
	{
		de.polygonal.ds.DLLNode<T> node = null;
		if (( ( this._reservedSize == 0 ) || ( this._poolSize == 0 ) )) 
		{
			node = new de.polygonal.ds.DLLNode<T>(((T) (x) ), ((de.polygonal.ds.DLL<T>) (this) ));
		}
		 else 
		{
			de.polygonal.ds.DLLNode<T> n = this._headPool;
			this._headPool = this._headPool.next;
			this._poolSize--;
			n.next = null;
			n.val = x;
			node = n;
		}
		
		if (( this.tail != null )) 
		{
			this.tail.next = node;
			node.prev = this.tail;
		}
		 else 
		{
			this.head = node;
		}
		
		this.tail = node;
		if (this._circular) 
		{
			this.tail.next = this.head;
			this.head.prev = this.tail;
		}
		
		this._size++;
		return node;
	}
	
	
	public   void appendNode(de.polygonal.ds.DLLNode<T> x)
	{
		if (( this.tail != null )) 
		{
			this.tail.next = x;
			x.prev = this.tail;
		}
		 else 
		{
			this.head = x;
		}
		
		this.tail = x;
		if (this._circular) 
		{
			this.tail.next = this.head;
			this.head.prev = this.tail;
		}
		
		this._size++;
	}
	
	
	public   de.polygonal.ds.DLLNode<T> prepend(T x)
	{
		de.polygonal.ds.DLLNode<T> node = null;
		if (( ( this._reservedSize == 0 ) || ( this._poolSize == 0 ) )) 
		{
			node = new de.polygonal.ds.DLLNode<T>(((T) (x) ), ((de.polygonal.ds.DLL<T>) (this) ));
		}
		 else 
		{
			de.polygonal.ds.DLLNode<T> n = this._headPool;
			this._headPool = this._headPool.next;
			this._poolSize--;
			n.next = null;
			n.val = x;
			node = n;
		}
		
		node.next = this.head;
		if (( this.head != null )) 
		{
			this.head.prev = node;
		}
		 else 
		{
			this.tail = node;
		}
		
		this.head = node;
		if (this._circular) 
		{
			this.tail.next = this.head;
			this.head.prev = this.tail;
		}
		
		this._size++;
		return node;
	}
	
	
	public   void prependNode(de.polygonal.ds.DLLNode<T> x)
	{
		x.next = this.head;
		if (( this.head != null )) 
		{
			this.head.prev = x;
		}
		 else 
		{
			this.tail = x;
		}
		
		this.head = x;
		if (this._circular) 
		{
			this.tail.next = this.head;
			this.head.prev = this.tail;
		}
		
		this._size++;
	}
	
	
	public   de.polygonal.ds.DLLNode<T> insertAfter(de.polygonal.ds.DLLNode<T> node, T x)
	{
		de.polygonal.ds.DLLNode<T> t = null;
		if (( ( this._reservedSize == 0 ) || ( this._poolSize == 0 ) )) 
		{
			t = new de.polygonal.ds.DLLNode<T>(((T) (x) ), ((de.polygonal.ds.DLL<T>) (this) ));
		}
		 else 
		{
			de.polygonal.ds.DLLNode<T> n = this._headPool;
			this._headPool = this._headPool.next;
			this._poolSize--;
			n.next = null;
			n.val = x;
			t = n;
		}
		
		node._insertAfter(t);
		if (( node == this.tail )) 
		{
			this.tail = t;
			if (this._circular) 
			{
				this.tail.next = this.head;
			}
			
		}
		
		this._size++;
		return t;
	}
	
	
	public   de.polygonal.ds.DLLNode<T> insertBefore(de.polygonal.ds.DLLNode<T> node, T x)
	{
		de.polygonal.ds.DLLNode<T> t = null;
		if (( ( this._reservedSize == 0 ) || ( this._poolSize == 0 ) )) 
		{
			t = new de.polygonal.ds.DLLNode<T>(((T) (x) ), ((de.polygonal.ds.DLL<T>) (this) ));
		}
		 else 
		{
			de.polygonal.ds.DLLNode<T> n = this._headPool;
			this._headPool = this._headPool.next;
			this._poolSize--;
			n.next = null;
			n.val = x;
			t = n;
		}
		
		node._insertBefore(t);
		if (( node == this.head )) 
		{
			this.head = t;
			if (this._circular) 
			{
				this.head.prev = this.tail;
			}
			
		}
		
		this._size++;
		return t;
	}
	
	
	public   de.polygonal.ds.DLLNode<T> unlink(de.polygonal.ds.DLLNode<T> node)
	{
		de.polygonal.ds.DLLNode<T> hook = node.next;
		if (( node == this.head )) 
		{
			this.head = this.head.next;
			if (this._circular) 
			{
				if (( this.head == this.tail )) 
				{
					this.head = null;
				}
				 else 
				{
					this.tail.next = this.head;
				}
				
			}
			
			if (( this.head == null )) 
			{
				this.tail = null;
			}
			
		}
		 else 
		{
			if (( node == this.tail )) 
			{
				this.tail = this.tail.prev;
				if (this._circular) 
				{
					this.head.prev = this.tail;
				}
				
				if (( this.tail == null )) 
				{
					this.head = null;
				}
				
			}
			
		}
		
		node._unlink();
		{
			T val = node.val;
			if (( ( this._reservedSize > 0 ) && ( this._poolSize < this._reservedSize ) )) 
			{
				this._tailPool = this._tailPool.next = node;
				node.val = null;
				this._poolSize++;
			}
			 else 
			{
				node._list = null;
			}
			
			T __temp_expr115 = val;
		}
		
		this._size--;
		return hook;
	}
	
	
	public   de.polygonal.ds.DLLNode<T> getNodeAt(int i)
	{
		de.polygonal.ds.DLLNode<T> node = this.head;
		{
			int _g = 0;
			while (( _g < i ))
			{
				int j = _g++;
				node = node.next;
			}
			
		}
		
		return node;
	}
	
	
	public   T removeHead()
	{
		de.polygonal.ds.DLLNode<T> node = this.head;
		if (( this.head == this.tail )) 
		{
			this.head = this.tail = null;
		}
		 else 
		{
			this.head = this.head.next;
			node.next = null;
			if (this._circular) 
			{
				this.head.prev = this.tail;
				this.tail.next = this.head;
			}
			 else 
			{
				this.head.prev = null;
			}
			
		}
		
		this._size--;
		{
			T val = node.val;
			if (( ( this._reservedSize > 0 ) && ( this._poolSize < this._reservedSize ) )) 
			{
				this._tailPool = this._tailPool.next = node;
				node.val = null;
				this._poolSize++;
			}
			 else 
			{
				node._list = null;
			}
			
			return val;
		}
		
	}
	
	
	public   T removeTail()
	{
		de.polygonal.ds.DLLNode<T> node = this.tail;
		if (( this.head == this.tail )) 
		{
			this.head = this.tail = null;
		}
		 else 
		{
			this.tail = this.tail.prev;
			node.prev = null;
			if (this._circular) 
			{
				this.tail.next = this.head;
				this.head.prev = this.tail;
			}
			 else 
			{
				this.tail.next = null;
			}
			
		}
		
		this._size--;
		{
			T val = node.val;
			if (( ( this._reservedSize > 0 ) && ( this._poolSize < this._reservedSize ) )) 
			{
				this._tailPool = this._tailPool.next = node;
				node.val = null;
				this._poolSize++;
			}
			 else 
			{
				node._list = null;
			}
			
			return val;
		}
		
	}
	
	
	public   void shiftUp()
	{
		if (( this._size > 1 )) 
		{
			de.polygonal.ds.DLLNode<T> t = this.head;
			if (( this.head.next == this.tail )) 
			{
				this.head = this.tail;
				this.head.prev = null;
				this.tail = t;
				this.tail.next = null;
				this.head.next = this.tail;
				this.tail.prev = this.head;
			}
			 else 
			{
				this.head = this.head.next;
				this.head.prev = null;
				this.tail.next = t;
				t.next = null;
				t.prev = this.tail;
				this.tail = t;
			}
			
			if (this._circular) 
			{
				this.tail.next = this.head;
				this.head.prev = this.tail;
			}
			
		}
		
	}
	
	
	public   void popDown()
	{
		if (( this._size > 1 )) 
		{
			de.polygonal.ds.DLLNode<T> t = this.tail;
			if (( this.tail.prev == this.head )) 
			{
				this.tail = this.head;
				this.tail.next = null;
				this.head = t;
				this.head.prev = null;
				this.head.next = this.tail;
				this.tail.prev = this.head;
			}
			 else 
			{
				this.tail = this.tail.prev;
				this.tail.next = null;
				this.head.prev = t;
				t.prev = null;
				t.next = this.head;
				this.head = t;
			}
			
			if (this._circular) 
			{
				this.tail.next = this.head;
				this.head.prev = this.tail;
			}
			
		}
		
	}
	
	
	public   de.polygonal.ds.DLLNode<T> nodeOf(T x, de.polygonal.ds.DLLNode<T> from)
	{
		de.polygonal.ds.DLLNode<T> node = ( (( from == null )) ? (this.head) : (from) );
		if (this._circular) 
		{
			while (( node != this.tail ))
			{
				if (haxe.lang.Runtime.eq(node.val, x)) 
				{
					return node;
				}
				
				node = node.next;
			}
			
			if (haxe.lang.Runtime.eq(node.val, x)) 
			{
				return node;
			}
			
		}
		 else 
		{
			while (( node != null ))
			{
				if (haxe.lang.Runtime.eq(node.val, x)) 
				{
					return node;
				}
				
				node = node.next;
			}
			
		}
		
		return null;
	}
	
	
	public   de.polygonal.ds.DLLNode<T> lastNodeOf(T x, de.polygonal.ds.DLLNode<T> from)
	{
		de.polygonal.ds.DLLNode<T> node = ( (( from == null )) ? (this.tail) : (from) );
		if (this._circular) 
		{
			while (( node != this.head ))
			{
				if (haxe.lang.Runtime.eq(node.val, x)) 
				{
					return node;
				}
				
				node = node.prev;
			}
			
			if (haxe.lang.Runtime.eq(node.val, x)) 
			{
				return node;
			}
			
		}
		 else 
		{
			while (( node != null ))
			{
				if (haxe.lang.Runtime.eq(node.val, x)) 
				{
					return node;
				}
				
				node = node.prev;
			}
			
		}
		
		return null;
	}
	
	
	public   void sort(haxe.lang.Function compare, java.lang.Object useInsertionSort)
	{
		boolean __temp_useInsertionSort28 = ( (( useInsertionSort == null )) ? (haxe.lang.Runtime.toBool(false)) : (haxe.lang.Runtime.toBool(useInsertionSort)) );
		if (( this._size > 1 )) 
		{
			if (this._circular) 
			{
				this.tail.next = null;
				this.head.prev = null;
			}
			
			if (( compare == null )) 
			{
				this.head = ( (__temp_useInsertionSort28) ? (this._insertionSortComparable(this.head)) : (this._mergeSortComparable(this.head)) );
			}
			 else 
			{
				this.head = ( (__temp_useInsertionSort28) ? (this._insertionSort(this.head, compare)) : (this._mergeSort(this.head, compare)) );
			}
			
			if (this._circular) 
			{
				this.tail.next = this.head;
				this.head.prev = this.tail;
			}
			
		}
		
	}
	
	
	public   void merge(de.polygonal.ds.DLL<T> x)
	{
		if (( x.head != null )) 
		{
			de.polygonal.ds.DLLNode<T> node = x.head;
			{
				int _g1 = 0;
				int _g = x._size;
				while (( _g1 < _g ))
				{
					int i = _g1++;
					node._list = this;
					node = node.next;
				}
				
			}
			
			if (( this.head != null )) 
			{
				this.tail.next = x.head;
				x.head.prev = this.tail;
				this.tail = x.tail;
			}
			 else 
			{
				this.head = x.head;
				this.tail = x.tail;
			}
			
			this._size += x._size;
			if (this._circular) 
			{
				this.tail.next = this.head;
				this.head.prev = this.tail;
			}
			
		}
		
	}
	
	
	public   de.polygonal.ds.DLL<T> concat(de.polygonal.ds.DLL<T> x)
	{
		de.polygonal.ds.DLL<T> c = new de.polygonal.ds.DLL<T>(((java.lang.Object) (null) ), ((java.lang.Object) (null) ));
		int k = x._size;
		if (( k > 0 )) 
		{
			de.polygonal.ds.DLLNode<T> node = x.tail;
			de.polygonal.ds.DLLNode<T> t = c.tail = new de.polygonal.ds.DLLNode<T>(((T) (node.val) ), ((de.polygonal.ds.DLL<T>) (c) ));
			node = node.prev;
			int i = ( k - 1 );
			while (( i-- > 0 ))
			{
				de.polygonal.ds.DLLNode<T> copy = new de.polygonal.ds.DLLNode<T>(((T) (node.val) ), ((de.polygonal.ds.DLL<T>) (c) ));
				copy.next = t;
				t.prev = copy;
				t = copy;
				node = node.prev;
			}
			
			c.head = t;
			c._size = k;
			if (( this._size > 0 )) 
			{
				de.polygonal.ds.DLLNode<T> node1 = this.tail;
				int i1 = this._size;
				while (( i1-- > 0 ))
				{
					de.polygonal.ds.DLLNode<T> copy = new de.polygonal.ds.DLLNode<T>(((T) (node1.val) ), ((de.polygonal.ds.DLL<T>) (c) ));
					copy.next = t;
					t.prev = copy;
					t = copy;
					node1 = node1.prev;
				}
				
				c.head = t;
				c._size += this._size;
			}
			
		}
		 else 
		{
			if (( this._size > 0 )) 
			{
				de.polygonal.ds.DLLNode<T> node = this.tail;
				de.polygonal.ds.DLLNode<T> t = c.tail = new de.polygonal.ds.DLLNode<T>(((T) (node.val) ), ((de.polygonal.ds.DLL<T>) (this) ));
				node = node.prev;
				int i = ( this._size - 1 );
				while (( i-- > 0 ))
				{
					de.polygonal.ds.DLLNode<T> copy = new de.polygonal.ds.DLLNode<T>(((T) (node.val) ), ((de.polygonal.ds.DLL<T>) (this) ));
					copy.next = t;
					t.prev = copy;
					t = copy;
					node = node.prev;
				}
				
				c.head = t;
				c._size = this._size;
			}
			
		}
		
		return c;
	}
	
	
	public   void reverse()
	{
		if (( this._size <= 1 )) 
		{
			return ;
		}
		 else 
		{
			if (( this._size <= 3 )) 
			{
				T t = this.head.val;
				this.head.val = this.tail.val;
				this.tail.val = t;
			}
			 else 
			{
				de.polygonal.ds.DLLNode<T> head = this.head;
				de.polygonal.ds.DLLNode<T> tail = this.tail;
				{
					int _g1 = 0;
					int _g = ( this._size >> 1 );
					while (( _g1 < _g ))
					{
						int i = _g1++;
						T t = head.val;
						head.val = tail.val;
						tail.val = t;
						head = head.next;
						tail = tail.prev;
					}
					
				}
				
			}
			
		}
		
	}
	
	
	public   java.lang.String join(java.lang.String x)
	{
		java.lang.String s = "";
		if (( this._size > 0 )) 
		{
			de.polygonal.ds.DLLNode<T> node = this.head;
			{
				int _g1 = 0;
				int _g = ( this._size - 1 );
				while (( _g1 < _g ))
				{
					int i = _g1++;
					s += ( haxe.root.Std.string(node.val) + x );
					node = node.next;
				}
				
			}
			
			s += haxe.root.Std.string(node.val);
		}
		
		return s;
	}
	
	
	public   void assign(java.lang.Class<T> C, haxe.root.Array args, java.lang.Object n)
	{
		int __temp_n29 = ( (( n == null )) ? (((int) (0) )) : (((int) (haxe.lang.Runtime.toInt(n)) )) );
		if (( __temp_n29 > 0 )) 
		{
			{
			}
			
		}
		 else 
		{
			__temp_n29 = this._size;
		}
		
		if (( args == null )) 
		{
			args = new haxe.root.Array(new java.lang.Object[]{});
		}
		
		de.polygonal.ds.DLLNode<T> node = this.head;
		{
			int _g = 0;
			while (( _g < ((int) (__temp_n29) ) ))
			{
				int i = _g++;
				node.val = haxe.root.Type.createInstance(C, args);
				node = node.next;
			}
			
		}
		
	}
	
	
	public   de.polygonal.ds.DLL<T> fill(T x, haxe.root.Array args, java.lang.Object n)
	{
		int __temp_n30 = ( (( n == null )) ? (((int) (0) )) : (((int) (haxe.lang.Runtime.toInt(n)) )) );
		if (( __temp_n30 > 0 )) 
		{
			{
			}
			
		}
		 else 
		{
			__temp_n30 = this._size;
		}
		
		de.polygonal.ds.DLLNode<T> node = this.head;
		{
			int _g = 0;
			while (( _g < ((int) (__temp_n30) ) ))
			{
				int i = _g++;
				node.val = x;
				node = node.next;
			}
			
		}
		
		return this;
	}
	
	
	public   void shuffle(de.polygonal.ds.DA<java.lang.Object> rval)
	{
		int s = this._size;
		if (( rval == null )) 
		{
			java.lang.Class m = java.lang.Math.class;
			while (( s > 1 ))
			{
				s--;
				int i = ((int) (( java.lang.Math.random() * s )) );
				de.polygonal.ds.DLLNode<T> node1 = this.head;
				{
					int _g = 0;
					while (( _g < ((int) (s) ) ))
					{
						int j = _g++;
						node1 = node1.next;
					}
					
				}
				
				T t = node1.val;
				de.polygonal.ds.DLLNode<T> node2 = this.head;
				{
					int _g = 0;
					while (( _g < ((int) (i) ) ))
					{
						int j = _g++;
						node2 = node2.next;
					}
					
				}
				
				node1.val = node2.val;
				node2.val = t;
			}
			
		}
		 else 
		{
			int j = 0;
			while (( s > 1 ))
			{
				s--;
				int i = ((int) (( ((double) (haxe.lang.Runtime.toDouble(((haxe.root.Array<java.lang.Object>) (((haxe.root.Array) (rval._a) )) ).__get(j++))) ) * s )) );
				de.polygonal.ds.DLLNode<T> node1 = this.head;
				{
					int _g = 0;
					while (( _g < ((int) (s) ) ))
					{
						int j1 = _g++;
						node1 = node1.next;
					}
					
				}
				
				T t = node1.val;
				de.polygonal.ds.DLLNode<T> node2 = this.head;
				{
					int _g = 0;
					while (( _g < ((int) (i) ) ))
					{
						int j1 = _g++;
						node2 = node2.next;
					}
					
				}
				
				node1.val = node2.val;
				node2.val = t;
			}
			
		}
		
		if (this._circular) 
		{
			this.tail.next = this.head;
			this.head.prev = this.tail;
		}
		
	}
	
	
	@Override public   java.lang.String toString()
	{
		java.lang.String s = ( ( ( ( "{ DLL size: " + this._size ) + ", circular: " ) + haxe.root.Std.string(this.isCircular()) ) + " }" );
		if (( this._size == 0 )) 
		{
			return s;
		}
		
		s += "\n[ head \n";
		de.polygonal.ds.DLLNode<T> node = this.head;
		{
			int _g1 = 0;
			int _g = this._size;
			while (( _g1 < _g ))
			{
				int i = _g1++;
				s += ( ( "  " + haxe.root.Std.string(node.val) ) + "\n" );
				node = node.next;
			}
			
		}
		
		s += "] tail";
		return s;
	}
	
	
	public   void free()
	{
		de.polygonal.ds.DLLNode<T> node = this.head;
		{
			int _g1 = 0;
			int _g = this._size;
			while (( _g1 < _g ))
			{
				int i = _g1++;
				de.polygonal.ds.DLLNode<T> next = node.next;
				node.free();
				node = next;
			}
			
		}
		
		this.head = this.tail = null;
		de.polygonal.ds.DLLNode<T> node1 = this._headPool;
		while (( node1 != null ))
		{
			de.polygonal.ds.DLLNode<T> next = node1.next;
			node1.free();
			node1 = next;
		}
		
		this._headPool = this._tailPool = null;
		this._iterator = null;
	}
	
	
	public   boolean contains(T x)
	{
		de.polygonal.ds.DLLNode<T> node = this.head;
		{
			int _g1 = 0;
			int _g = this._size;
			while (( _g1 < _g ))
			{
				int i = _g1++;
				if (haxe.lang.Runtime.eq(node.val, x)) 
				{
					return true;
				}
				
				node = node.next;
			}
			
		}
		
		return false;
	}
	
	
	public   boolean remove(T x)
	{
		int s = this._size;
		if (( s == 0 )) 
		{
			return false;
		}
		
		de.polygonal.ds.DLLNode<T> node = this.head;
		while (( node != null ))
		{
			if (haxe.lang.Runtime.eq(node.val, x)) 
			{
				node = this.unlink(node);
			}
			 else 
			{
				node = node.next;
			}
			
		}
		
		return ( this._size < s );
	}
	
	
	public   void clear(java.lang.Object purge)
	{
		boolean __temp_purge31 = ( (( purge == null )) ? (haxe.lang.Runtime.toBool(false)) : (haxe.lang.Runtime.toBool(purge)) );
		if (( __temp_purge31 || ( this._reservedSize > 0 ) )) 
		{
			de.polygonal.ds.DLLNode<T> node = this.head;
			{
				int _g1 = 0;
				int _g = this._size;
				while (( _g1 < _g ))
				{
					int i = _g1++;
					de.polygonal.ds.DLLNode<T> next = node.next;
					node.prev = null;
					node.next = null;
					{
						T val = node.val;
						if (( ( this._reservedSize > 0 ) && ( this._poolSize < this._reservedSize ) )) 
						{
							this._tailPool = this._tailPool.next = node;
							node.val = null;
							this._poolSize++;
						}
						 else 
						{
							node._list = null;
						}
						
						T __temp_expr116 = val;
					}
					
					node = next;
				}
				
			}
			
		}
		
		this.head = this.tail = null;
		this._size = 0;
	}
	
	
	public   de.polygonal.ds.Itr<T> iterator()
	{
		if (this.reuseIterator) 
		{
			if (( this._iterator == null )) 
			{
				if (this._circular) 
				{
					return new de.polygonal.ds.CircularDLLIterator<T>(((de.polygonal.ds.DLL<T>) (this) ));
				}
				 else 
				{
					return new de.polygonal.ds.DLLIterator<T>(((de.polygonal.ds.DLL<T>) (this) ));
				}
				
			}
			 else 
			{
				this._iterator.reset();
			}
			
			return this._iterator;
		}
		 else 
		{
			if (this._circular) 
			{
				return new de.polygonal.ds.CircularDLLIterator<T>(((de.polygonal.ds.DLL<T>) (this) ));
			}
			 else 
			{
				return new de.polygonal.ds.DLLIterator<T>(((de.polygonal.ds.DLL<T>) (this) ));
			}
			
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
		
		de.polygonal.ds.DLLNode<T> node = this.head;
		{
			int _g1 = 0;
			int _g = this._size;
			while (( _g1 < _g ))
			{
				int i = _g1++;
				a.__set(i, node.val);
				node = node.next;
			}
			
		}
		
		return a;
	}
	
	
	public   de.polygonal.ds.Collection<T> clone(java.lang.Object assign, haxe.lang.Function copier)
	{
		boolean __temp_assign32 = ( (( assign == null )) ? (haxe.lang.Runtime.toBool(true)) : (haxe.lang.Runtime.toBool(assign)) );
		if (( this._size == 0 )) 
		{
			de.polygonal.ds.DLL<T> copy = new de.polygonal.ds.DLL<T>(((java.lang.Object) (this._reservedSize) ), ((java.lang.Object) (this.maxSize) ));
			if (this._circular) 
			{
				copy._circular = true;
			}
			
			return copy;
		}
		
		de.polygonal.ds.DLL<T> copy = new de.polygonal.ds.DLL<T>(((java.lang.Object) (null) ), ((java.lang.Object) (null) ));
		copy._size = this._size;
		if (__temp_assign32) 
		{
			de.polygonal.ds.DLLNode<T> srcNode = this.head;
			de.polygonal.ds.DLLNode<T> dstNode = copy.head = new de.polygonal.ds.DLLNode<T>(((T) (this.head.val) ), ((de.polygonal.ds.DLL<T>) (copy) ));
			if (( this._size == 1 )) 
			{
				copy.tail = copy.head;
				if (this._circular) 
				{
					copy.tail.next = copy.head;
				}
				
				return copy;
			}
			
			de.polygonal.ds.DLLNode<T> dstNode0 = null;
			srcNode = srcNode.next;
			{
				int _g1 = 1;
				int _g = ( this._size - 1 );
				while (( _g1 < _g ))
				{
					int i = _g1++;
					dstNode0 = dstNode;
					de.polygonal.ds.DLLNode<T> srcNode0 = srcNode;
					dstNode = dstNode.next = new de.polygonal.ds.DLLNode<T>(((T) (srcNode.val) ), ((de.polygonal.ds.DLL<T>) (copy) ));
					dstNode.prev = dstNode0;
					srcNode0 = srcNode;
					srcNode = srcNode0.next;
				}
				
			}
			
			dstNode0 = dstNode;
			copy.tail = dstNode.next = new de.polygonal.ds.DLLNode<T>(((T) (srcNode.val) ), ((de.polygonal.ds.DLL<T>) (copy) ));
			copy.tail.prev = dstNode0;
		}
		 else 
		{
			if (( copier == null )) 
			{
				de.polygonal.ds.DLLNode<T> srcNode = this.head;
				de.polygonal.ds.Cloneable c = ((de.polygonal.ds.Cloneable) (((java.lang.Object) (this.head.val) )) );
				de.polygonal.ds.DLLNode<T> dstNode = copy.head = new de.polygonal.ds.DLLNode<T>(((T) (c.clone()) ), ((de.polygonal.ds.DLL<T>) (copy) ));
				if (( this._size == 1 )) 
				{
					copy.tail = copy.head;
					if (this._circular) 
					{
						copy.tail.next = copy.head;
					}
					
					return copy;
				}
				
				de.polygonal.ds.DLLNode<T> dstNode0 = null;
				srcNode = srcNode.next;
				{
					int _g1 = 1;
					int _g = ( this._size - 1 );
					while (( _g1 < _g ))
					{
						int i = _g1++;
						dstNode0 = dstNode;
						de.polygonal.ds.DLLNode<T> srcNode0 = srcNode;
						c = ((de.polygonal.ds.Cloneable) (((java.lang.Object) (srcNode.val) )) );
						dstNode = dstNode.next = new de.polygonal.ds.DLLNode<T>(((T) (c.clone()) ), ((de.polygonal.ds.DLL<T>) (copy) ));
						dstNode.prev = dstNode0;
						srcNode0 = srcNode;
						srcNode = srcNode0.next;
					}
					
				}
				
				c = ((de.polygonal.ds.Cloneable) (((java.lang.Object) (srcNode.val) )) );
				dstNode0 = dstNode;
				copy.tail = dstNode.next = new de.polygonal.ds.DLLNode<T>(((T) (c.clone()) ), ((de.polygonal.ds.DLL<T>) (copy) ));
				copy.tail.prev = dstNode0;
			}
			 else 
			{
				de.polygonal.ds.DLLNode<T> srcNode = this.head;
				de.polygonal.ds.DLLNode<T> dstNode = copy.head = new de.polygonal.ds.DLLNode<T>(((T) (copier.__hx_invoke1_o(0.0, this.head.val)) ), ((de.polygonal.ds.DLL<T>) (copy) ));
				if (( this._size == 1 )) 
				{
					copy.tail = copy.head;
					if (this._circular) 
					{
						copy.tail.next = copy.head;
					}
					
					return copy;
				}
				
				de.polygonal.ds.DLLNode<T> dstNode0 = null;
				srcNode = srcNode.next;
				{
					int _g1 = 1;
					int _g = ( this._size - 1 );
					while (( _g1 < _g ))
					{
						int i = _g1++;
						dstNode0 = dstNode;
						de.polygonal.ds.DLLNode<T> srcNode0 = srcNode;
						dstNode = dstNode.next = new de.polygonal.ds.DLLNode<T>(((T) (copier.__hx_invoke1_o(0.0, srcNode.val)) ), ((de.polygonal.ds.DLL<T>) (copy) ));
						dstNode.prev = dstNode0;
						srcNode0 = srcNode;
						srcNode = srcNode0.next;
					}
					
				}
				
				dstNode0 = dstNode;
				copy.tail = dstNode.next = new de.polygonal.ds.DLLNode<T>(((T) (copier.__hx_invoke1_o(0.0, srcNode.val)) ), ((de.polygonal.ds.DLL<T>) (copy) ));
				copy.tail.prev = dstNode0;
			}
			
		}
		
		if (this._circular) 
		{
			copy.tail.next = copy.head;
		}
		
		return copy;
	}
	
	
	public   de.polygonal.ds.DLLNode<T> _mergeSortComparable(de.polygonal.ds.DLLNode<T> node)
	{
		de.polygonal.ds.DLLNode<T> h = node;
		de.polygonal.ds.DLLNode<T> p = null;
		de.polygonal.ds.DLLNode<T> q = null;
		de.polygonal.ds.DLLNode<T> e = null;
		de.polygonal.ds.DLLNode<T> tail = null;
		int insize = 1;
		int nmerges = 0;
		int psize = 0;
		int qsize = 0;
		java.lang.Object i = null;
		while (true)
		{
			p = h;
			h = tail = null;
			nmerges = 0;
			while (( p != null ))
			{
				nmerges++;
				psize = 0;
				q = p;
				{
					int _g = 0;
					while (( _g < ((int) (insize) ) ))
					{
						int i1 = _g++;
						psize++;
						q = q.next;
						if (( q == null )) 
						{
							break;
						}
						
					}
					
				}
				
				qsize = insize;
				while (( ( psize > 0 ) || ( ( qsize > 0 ) && ( q != null ) ) ))
				{
					if (( psize == 0 )) 
					{
						e = q;
						q = q.next;
						qsize--;
					}
					 else 
					{
						if (( ( qsize == 0 ) || ( q == null ) )) 
						{
							e = p;
							p = p.next;
							psize--;
						}
						 else 
						{
							if (( (((de.polygonal.ds.Comparable) (((java.lang.Object) (p.val) )) )).compare(q.val) >= 0 )) 
							{
								e = p;
								p = p.next;
								psize--;
							}
							 else 
							{
								e = q;
								q = q.next;
								qsize--;
							}
							
						}
						
					}
					
					if (( tail != null )) 
					{
						tail.next = e;
					}
					 else 
					{
						h = e;
					}
					
					e.prev = tail;
					tail = e;
				}
				
				p = q;
			}
			
			tail.next = null;
			if (( nmerges <= 1 )) 
			{
				break;
			}
			
			insize <<= 1;
		}
		
		h.prev = null;
		this.tail = tail;
		return h;
	}
	
	
	public   de.polygonal.ds.DLLNode<T> _mergeSort(de.polygonal.ds.DLLNode<T> node, haxe.lang.Function cmp)
	{
		de.polygonal.ds.DLLNode<T> h = node;
		de.polygonal.ds.DLLNode<T> p = null;
		de.polygonal.ds.DLLNode<T> q = null;
		de.polygonal.ds.DLLNode<T> e = null;
		de.polygonal.ds.DLLNode<T> tail = null;
		int insize = 1;
		int nmerges = 0;
		int psize = 0;
		int qsize = 0;
		java.lang.Object i = null;
		while (true)
		{
			p = h;
			h = tail = null;
			nmerges = 0;
			while (( p != null ))
			{
				nmerges++;
				psize = 0;
				q = p;
				{
					int _g = 0;
					while (( _g < ((int) (insize) ) ))
					{
						int i1 = _g++;
						psize++;
						q = q.next;
						if (( q == null )) 
						{
							break;
						}
						
					}
					
				}
				
				qsize = insize;
				while (( ( psize > 0 ) || ( ( qsize > 0 ) && ( q != null ) ) ))
				{
					if (( psize == 0 )) 
					{
						e = q;
						q = q.next;
						qsize--;
					}
					 else 
					{
						if (( ( qsize == 0 ) || ( q == null ) )) 
						{
							e = p;
							p = p.next;
							psize--;
						}
						 else 
						{
							if (( ((int) (cmp.__hx_invoke2_f(0.0, 0.0, q.val, p.val)) ) >= 0 )) 
							{
								e = p;
								p = p.next;
								psize--;
							}
							 else 
							{
								e = q;
								q = q.next;
								qsize--;
							}
							
						}
						
					}
					
					if (( tail != null )) 
					{
						tail.next = e;
					}
					 else 
					{
						h = e;
					}
					
					e.prev = tail;
					tail = e;
				}
				
				p = q;
			}
			
			tail.next = null;
			if (( nmerges <= 1 )) 
			{
				break;
			}
			
			insize <<= 1;
		}
		
		h.prev = null;
		this.tail = tail;
		return h;
	}
	
	
	public   de.polygonal.ds.DLLNode<T> _insertionSortComparable(de.polygonal.ds.DLLNode<T> node)
	{
		de.polygonal.ds.DLLNode<T> h = node;
		de.polygonal.ds.DLLNode<T> n = h.next;
		while (( n != null ))
		{
			de.polygonal.ds.DLLNode<T> m = n.next;
			de.polygonal.ds.DLLNode<T> p = n.prev;
			T v = n.val;
			if (( (((de.polygonal.ds.Comparable) (((java.lang.Object) (p.val) )) )).compare(v) < 0 )) 
			{
				de.polygonal.ds.DLLNode<T> i = p;
				while (( i.prev != null ))
				{
					if (( (((de.polygonal.ds.Comparable) (((java.lang.Object) (i.prev.val) )) )).compare(v) < 0 )) 
					{
						i = i.prev;
					}
					 else 
					{
						break;
					}
					
				}
				
				if (( m != null )) 
				{
					p.next = m;
					m.prev = p;
				}
				 else 
				{
					p.next = null;
					this.tail = p;
				}
				
				if (( i == h )) 
				{
					n.prev = null;
					n.next = i;
					i.prev = n;
					h = n;
				}
				 else 
				{
					n.prev = i.prev;
					i.prev.next = n;
					n.next = i;
					i.prev = n;
				}
				
			}
			
			n = m;
		}
		
		return h;
	}
	
	
	public   de.polygonal.ds.DLLNode<T> _insertionSort(de.polygonal.ds.DLLNode<T> node, haxe.lang.Function cmp)
	{
		de.polygonal.ds.DLLNode<T> h = node;
		de.polygonal.ds.DLLNode<T> n = h.next;
		while (( n != null ))
		{
			de.polygonal.ds.DLLNode<T> m = n.next;
			de.polygonal.ds.DLLNode<T> p = n.prev;
			T v = n.val;
			if (( ((int) (cmp.__hx_invoke2_f(0.0, 0.0, v, p.val)) ) < 0 )) 
			{
				de.polygonal.ds.DLLNode<T> i = p;
				while (( i.prev != null ))
				{
					if (( ((int) (cmp.__hx_invoke2_f(0.0, 0.0, v, i.prev.val)) ) < 0 )) 
					{
						i = i.prev;
					}
					 else 
					{
						break;
					}
					
				}
				
				if (( m != null )) 
				{
					p.next = m;
					m.prev = p;
				}
				 else 
				{
					p.next = null;
					this.tail = p;
				}
				
				if (( i == h )) 
				{
					n.prev = null;
					n.next = i;
					i.prev = n;
					h = n;
				}
				 else 
				{
					n.prev = i.prev;
					i.prev.next = n;
					n.next = i;
					i.prev = n;
				}
				
			}
			
			n = m;
		}
		
		return h;
	}
	
	
	public final   boolean _valid(de.polygonal.ds.DLLNode<T> node)
	{
		return ( node != null );
	}
	
	
	public final   de.polygonal.ds.DLLNode<T> _getNode(T x)
	{
		if (( ( this._reservedSize == 0 ) || ( this._poolSize == 0 ) )) 
		{
			return new de.polygonal.ds.DLLNode<T>(((T) (x) ), ((de.polygonal.ds.DLL<T>) (this) ));
		}
		 else 
		{
			de.polygonal.ds.DLLNode<T> n = this._headPool;
			this._headPool = this._headPool.next;
			this._poolSize--;
			n.next = null;
			n.val = x;
			return n;
		}
		
	}
	
	
	public final   T _putNode(de.polygonal.ds.DLLNode<T> x)
	{
		T val = x.val;
		if (( ( this._reservedSize > 0 ) && ( this._poolSize < this._reservedSize ) )) 
		{
			this._tailPool = this._tailPool.next = x;
			x.val = null;
			this._poolSize++;
		}
		 else 
		{
			x._list = null;
		}
		
		return val;
	}
	
	
	public final   void __insertAfter(java.lang.Object f, de.polygonal.ds.DLLNode<T> x)
	{
		haxe.lang.Runtime.callField(f, "_insertAfter", new haxe.root.Array(new java.lang.Object[]{x}));
	}
	
	
	public final   void __insertBefore(java.lang.Object f, de.polygonal.ds.DLLNode<T> x)
	{
		haxe.lang.Runtime.callField(f, "_insertBefore", new haxe.root.Array(new java.lang.Object[]{x}));
	}
	
	
	public final   void __unlink(java.lang.Object f)
	{
		haxe.lang.Runtime.callField(f, "_unlink", null);
	}
	
	
	public final   void __list(java.lang.Object f, de.polygonal.ds.DLL<T> x)
	{
		haxe.lang.Runtime.setField(f, "_list", x);
	}
	
	
	@Override public   double __hx_setField_f(java.lang.String field, double value, boolean handleProperties)
	{
		{
			boolean __temp_executeDef117 = true;
			switch (field.hashCode())
			{
				case 725594012:
				{
					if (field.equals("_poolSize")) 
					{
						__temp_executeDef117 = false;
						this._poolSize = ((int) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 106079:
				{
					if (field.equals("key")) 
					{
						__temp_executeDef117 = false;
						this.key = ((int) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 1079670056:
				{
					if (field.equals("_reservedSize")) 
					{
						__temp_executeDef117 = false;
						this._reservedSize = ((int) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 844081029:
				{
					if (field.equals("maxSize")) 
					{
						__temp_executeDef117 = false;
						this.maxSize = ((int) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 91265248:
				{
					if (field.equals("_size")) 
					{
						__temp_executeDef117 = false;
						this._size = ((int) (value) );
						return value;
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef117) 
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
			boolean __temp_executeDef118 = true;
			switch (field.hashCode())
			{
				case 1273051597:
				{
					if (field.equals("_iterator")) 
					{
						__temp_executeDef118 = false;
						this._iterator = ((de.polygonal.ds.Itr<T>) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 106079:
				{
					if (field.equals("key")) 
					{
						__temp_executeDef118 = false;
						this.key = ((int) (haxe.lang.Runtime.toInt(value)) );
						return value;
					}
					
					break;
				}
				
				
				case -1407567874:
				{
					if (field.equals("_circular")) 
					{
						__temp_executeDef118 = false;
						this._circular = haxe.lang.Runtime.toBool(value);
						return value;
					}
					
					break;
				}
				
				
				case 3198432:
				{
					if (field.equals("head")) 
					{
						__temp_executeDef118 = false;
						this.head = ((de.polygonal.ds.DLLNode<T>) (value) );
						return value;
					}
					
					break;
				}
				
				
				case -605107733:
				{
					if (field.equals("_tailPool")) 
					{
						__temp_executeDef118 = false;
						this._tailPool = ((de.polygonal.ds.DLLNode<T>) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 3552336:
				{
					if (field.equals("tail")) 
					{
						__temp_executeDef118 = false;
						this.tail = ((de.polygonal.ds.DLLNode<T>) (value) );
						return value;
					}
					
					break;
				}
				
				
				case -1025369221:
				{
					if (field.equals("_headPool")) 
					{
						__temp_executeDef118 = false;
						this._headPool = ((de.polygonal.ds.DLLNode<T>) (value) );
						return value;
					}
					
					break;
				}
				
				
				case 844081029:
				{
					if (field.equals("maxSize")) 
					{
						__temp_executeDef118 = false;
						this.maxSize = ((int) (haxe.lang.Runtime.toInt(value)) );
						return value;
					}
					
					break;
				}
				
				
				case 725594012:
				{
					if (field.equals("_poolSize")) 
					{
						__temp_executeDef118 = false;
						this._poolSize = ((int) (haxe.lang.Runtime.toInt(value)) );
						return value;
					}
					
					break;
				}
				
				
				case -766976254:
				{
					if (field.equals("reuseIterator")) 
					{
						__temp_executeDef118 = false;
						this.reuseIterator = haxe.lang.Runtime.toBool(value);
						return value;
					}
					
					break;
				}
				
				
				case 1079670056:
				{
					if (field.equals("_reservedSize")) 
					{
						__temp_executeDef118 = false;
						this._reservedSize = ((int) (haxe.lang.Runtime.toInt(value)) );
						return value;
					}
					
					break;
				}
				
				
				case 91265248:
				{
					if (field.equals("_size")) 
					{
						__temp_executeDef118 = false;
						this._size = ((int) (haxe.lang.Runtime.toInt(value)) );
						return value;
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef118) 
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
			boolean __temp_executeDef119 = true;
			switch (field.hashCode())
			{
				case -1484141442:
				{
					if (field.equals("__list")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("__list"))) );
					}
					
					break;
				}
				
				
				case 106079:
				{
					if (field.equals("key")) 
					{
						__temp_executeDef119 = false;
						return this.key;
					}
					
					break;
				}
				
				
				case -68719117:
				{
					if (field.equals("__unlink")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("__unlink"))) );
					}
					
					break;
				}
				
				
				case 3198432:
				{
					if (field.equals("head")) 
					{
						__temp_executeDef119 = false;
						return this.head;
					}
					
					break;
				}
				
				
				case 1661238136:
				{
					if (field.equals("__insertBefore")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("__insertBefore"))) );
					}
					
					break;
				}
				
				
				case 3552336:
				{
					if (field.equals("tail")) 
					{
						__temp_executeDef119 = false;
						return this.tail;
					}
					
					break;
				}
				
				
				case 1438181059:
				{
					if (field.equals("__insertAfter")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("__insertAfter"))) );
					}
					
					break;
				}
				
				
				case 844081029:
				{
					if (field.equals("maxSize")) 
					{
						__temp_executeDef119 = false;
						return this.maxSize;
					}
					
					break;
				}
				
				
				case 2138594418:
				{
					if (field.equals("_putNode")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("_putNode"))) );
					}
					
					break;
				}
				
				
				case -766976254:
				{
					if (field.equals("reuseIterator")) 
					{
						__temp_executeDef119 = false;
						return this.reuseIterator;
					}
					
					break;
				}
				
				
				case -2012037831:
				{
					if (field.equals("_getNode")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("_getNode"))) );
					}
					
					break;
				}
				
				
				case 91265248:
				{
					if (field.equals("_size")) 
					{
						__temp_executeDef119 = false;
						return this._size;
					}
					
					break;
				}
				
				
				case -1463225603:
				{
					if (field.equals("_valid")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("_valid"))) );
					}
					
					break;
				}
				
				
				case 1079670056:
				{
					if (field.equals("_reservedSize")) 
					{
						__temp_executeDef119 = false;
						return this._reservedSize;
					}
					
					break;
				}
				
				
				case -978978066:
				{
					if (field.equals("_insertionSort")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("_insertionSort"))) );
					}
					
					break;
				}
				
				
				case 725594012:
				{
					if (field.equals("_poolSize")) 
					{
						__temp_executeDef119 = false;
						return this._poolSize;
					}
					
					break;
				}
				
				
				case -1022587288:
				{
					if (field.equals("_insertionSortComparable")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("_insertionSortComparable"))) );
					}
					
					break;
				}
				
				
				case -1025369221:
				{
					if (field.equals("_headPool")) 
					{
						__temp_executeDef119 = false;
						return this._headPool;
					}
					
					break;
				}
				
				
				case 138095735:
				{
					if (field.equals("_mergeSort")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("_mergeSort"))) );
					}
					
					break;
				}
				
				
				case -605107733:
				{
					if (field.equals("_tailPool")) 
					{
						__temp_executeDef119 = false;
						return this._tailPool;
					}
					
					break;
				}
				
				
				case 66857137:
				{
					if (field.equals("_mergeSortComparable")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("_mergeSortComparable"))) );
					}
					
					break;
				}
				
				
				case -1407567874:
				{
					if (field.equals("_circular")) 
					{
						__temp_executeDef119 = false;
						return this._circular;
					}
					
					break;
				}
				
				
				case 94756189:
				{
					if (field.equals("clone")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("clone"))) );
					}
					
					break;
				}
				
				
				case 1273051597:
				{
					if (field.equals("_iterator")) 
					{
						__temp_executeDef119 = false;
						return this._iterator;
					}
					
					break;
				}
				
				
				case -1182381922:
				{
					if (field.equals("toArray")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("toArray"))) );
					}
					
					break;
				}
				
				
				case 2003611113:
				{
					if (field.equals("isCircular")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("isCircular"))) );
					}
					
					break;
				}
				
				
				case 2058039875:
				{
					if (field.equals("isEmpty")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("isEmpty"))) );
					}
					
					break;
				}
				
				
				case 94756344:
				{
					if (field.equals("close")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("close"))) );
					}
					
					break;
				}
				
				
				case 3530753:
				{
					if (field.equals("size")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("size"))) );
					}
					
					break;
				}
				
				
				case 3417674:
				{
					if (field.equals("open")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("open"))) );
					}
					
					break;
				}
				
				
				case 1182533742:
				{
					if (field.equals("iterator")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("iterator"))) );
					}
					
					break;
				}
				
				
				case 1369040158:
				{
					if (field.equals("createNode")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("createNode"))) );
					}
					
					break;
				}
				
				
				case 94746189:
				{
					if (field.equals("clear")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("clear"))) );
					}
					
					break;
				}
				
				
				case -1411068134:
				{
					if (field.equals("append")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("append"))) );
					}
					
					break;
				}
				
				
				case -934610812:
				{
					if (field.equals("remove")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("remove"))) );
					}
					
					break;
				}
				
				
				case -2139564996:
				{
					if (field.equals("appendNode")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("appendNode"))) );
					}
					
					break;
				}
				
				
				case -567445985:
				{
					if (field.equals("contains")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("contains"))) );
					}
					
					break;
				}
				
				
				case -318366834:
				{
					if (field.equals("prepend")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("prepend"))) );
					}
					
					break;
				}
				
				
				case 3151468:
				{
					if (field.equals("free")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("free"))) );
					}
					
					break;
				}
				
				
				case 2121713328:
				{
					if (field.equals("prependNode")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("prependNode"))) );
					}
					
					break;
				}
				
				
				case -1776922004:
				{
					if (field.equals("toString")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("toString"))) );
					}
					
					break;
				}
				
				
				case -131260765:
				{
					if (field.equals("insertAfter")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("insertAfter"))) );
					}
					
					break;
				}
				
				
				case 2072332025:
				{
					if (field.equals("shuffle")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("shuffle"))) );
					}
					
					break;
				}
				
				
				case 253181848:
				{
					if (field.equals("insertBefore")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("insertBefore"))) );
					}
					
					break;
				}
				
				
				case 3143043:
				{
					if (field.equals("fill")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("fill"))) );
					}
					
					break;
				}
				
				
				case -840447469:
				{
					if (field.equals("unlink")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("unlink"))) );
					}
					
					break;
				}
				
				
				case -1408204561:
				{
					if (field.equals("assign")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("assign"))) );
					}
					
					break;
				}
				
				
				case 655843531:
				{
					if (field.equals("getNodeAt")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("getNodeAt"))) );
					}
					
					break;
				}
				
				
				case 3267882:
				{
					if (field.equals("join")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("join"))) );
					}
					
					break;
				}
				
				
				case 1098209412:
				{
					if (field.equals("removeHead")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("removeHead"))) );
					}
					
					break;
				}
				
				
				case 1099846370:
				{
					if (field.equals("reverse")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("reverse"))) );
					}
					
					break;
				}
				
				
				case 1098563316:
				{
					if (field.equals("removeTail")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("removeTail"))) );
					}
					
					break;
				}
				
				
				case -1354795244:
				{
					if (field.equals("concat")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("concat"))) );
					}
					
					break;
				}
				
				
				case 2061262525:
				{
					if (field.equals("shiftUp")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("shiftUp"))) );
					}
					
					break;
				}
				
				
				case 103785528:
				{
					if (field.equals("merge")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("merge"))) );
					}
					
					break;
				}
				
				
				case -395396461:
				{
					if (field.equals("popDown")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("popDown"))) );
					}
					
					break;
				}
				
				
				case 3536286:
				{
					if (field.equals("sort")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("sort"))) );
					}
					
					break;
				}
				
				
				case -1040171143:
				{
					if (field.equals("nodeOf")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("nodeOf"))) );
					}
					
					break;
				}
				
				
				case 1791554991:
				{
					if (field.equals("lastNodeOf")) 
					{
						__temp_executeDef119 = false;
						return ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (this) ), haxe.lang.Runtime.toString("lastNodeOf"))) );
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef119) 
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
			boolean __temp_executeDef120 = true;
			switch (field.hashCode())
			{
				case 725594012:
				{
					if (field.equals("_poolSize")) 
					{
						__temp_executeDef120 = false;
						return ((double) (this._poolSize) );
					}
					
					break;
				}
				
				
				case 106079:
				{
					if (field.equals("key")) 
					{
						__temp_executeDef120 = false;
						return ((double) (this.key) );
					}
					
					break;
				}
				
				
				case 1079670056:
				{
					if (field.equals("_reservedSize")) 
					{
						__temp_executeDef120 = false;
						return ((double) (this._reservedSize) );
					}
					
					break;
				}
				
				
				case 844081029:
				{
					if (field.equals("maxSize")) 
					{
						__temp_executeDef120 = false;
						return ((double) (this.maxSize) );
					}
					
					break;
				}
				
				
				case 91265248:
				{
					if (field.equals("_size")) 
					{
						__temp_executeDef120 = false;
						return ((double) (this._size) );
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef120) 
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
			boolean __temp_executeDef121 = true;
			switch (field.hashCode())
			{
				case -1484141442:
				{
					if (field.equals("__list")) 
					{
						__temp_executeDef121 = false;
						this.__list(dynargs.__get(0), ((de.polygonal.ds.DLL<T>) (dynargs.__get(1)) ));
					}
					
					break;
				}
				
				
				case 2003611113:
				{
					if (field.equals("isCircular")) 
					{
						__temp_executeDef121 = false;
						return this.isCircular();
					}
					
					break;
				}
				
				
				case -68719117:
				{
					if (field.equals("__unlink")) 
					{
						__temp_executeDef121 = false;
						this.__unlink(dynargs.__get(0));
					}
					
					break;
				}
				
				
				case 94756344:
				{
					if (field.equals("close")) 
					{
						__temp_executeDef121 = false;
						this.close();
					}
					
					break;
				}
				
				
				case 1661238136:
				{
					if (field.equals("__insertBefore")) 
					{
						__temp_executeDef121 = false;
						this.__insertBefore(dynargs.__get(0), ((de.polygonal.ds.DLLNode<T>) (dynargs.__get(1)) ));
					}
					
					break;
				}
				
				
				case 3417674:
				{
					if (field.equals("open")) 
					{
						__temp_executeDef121 = false;
						this.open();
					}
					
					break;
				}
				
				
				case 1438181059:
				{
					if (field.equals("__insertAfter")) 
					{
						__temp_executeDef121 = false;
						this.__insertAfter(dynargs.__get(0), ((de.polygonal.ds.DLLNode<T>) (dynargs.__get(1)) ));
					}
					
					break;
				}
				
				
				case 1369040158:
				{
					if (field.equals("createNode")) 
					{
						__temp_executeDef121 = false;
						return this.createNode(((T) (dynargs.__get(0)) ));
					}
					
					break;
				}
				
				
				case 2138594418:
				{
					if (field.equals("_putNode")) 
					{
						__temp_executeDef121 = false;
						return this._putNode(((de.polygonal.ds.DLLNode<T>) (dynargs.__get(0)) ));
					}
					
					break;
				}
				
				
				case -1411068134:
				{
					if (field.equals("append")) 
					{
						__temp_executeDef121 = false;
						return this.append(((T) (dynargs.__get(0)) ));
					}
					
					break;
				}
				
				
				case -2012037831:
				{
					if (field.equals("_getNode")) 
					{
						__temp_executeDef121 = false;
						return this._getNode(((T) (dynargs.__get(0)) ));
					}
					
					break;
				}
				
				
				case -2139564996:
				{
					if (field.equals("appendNode")) 
					{
						__temp_executeDef121 = false;
						this.appendNode(((de.polygonal.ds.DLLNode<T>) (dynargs.__get(0)) ));
					}
					
					break;
				}
				
				
				case -1463225603:
				{
					if (field.equals("_valid")) 
					{
						__temp_executeDef121 = false;
						return this._valid(((de.polygonal.ds.DLLNode<T>) (dynargs.__get(0)) ));
					}
					
					break;
				}
				
				
				case -318366834:
				{
					if (field.equals("prepend")) 
					{
						__temp_executeDef121 = false;
						return this.prepend(((T) (dynargs.__get(0)) ));
					}
					
					break;
				}
				
				
				case -978978066:
				{
					if (field.equals("_insertionSort")) 
					{
						__temp_executeDef121 = false;
						return this._insertionSort(((de.polygonal.ds.DLLNode<T>) (dynargs.__get(0)) ), ((haxe.lang.Function) (dynargs.__get(1)) ));
					}
					
					break;
				}
				
				
				case 2121713328:
				{
					if (field.equals("prependNode")) 
					{
						__temp_executeDef121 = false;
						this.prependNode(((de.polygonal.ds.DLLNode<T>) (dynargs.__get(0)) ));
					}
					
					break;
				}
				
				
				case -1022587288:
				{
					if (field.equals("_insertionSortComparable")) 
					{
						__temp_executeDef121 = false;
						return this._insertionSortComparable(((de.polygonal.ds.DLLNode<T>) (dynargs.__get(0)) ));
					}
					
					break;
				}
				
				
				case -131260765:
				{
					if (field.equals("insertAfter")) 
					{
						__temp_executeDef121 = false;
						return this.insertAfter(((de.polygonal.ds.DLLNode<T>) (dynargs.__get(0)) ), ((T) (dynargs.__get(1)) ));
					}
					
					break;
				}
				
				
				case 138095735:
				{
					if (field.equals("_mergeSort")) 
					{
						__temp_executeDef121 = false;
						return this._mergeSort(((de.polygonal.ds.DLLNode<T>) (dynargs.__get(0)) ), ((haxe.lang.Function) (dynargs.__get(1)) ));
					}
					
					break;
				}
				
				
				case 253181848:
				{
					if (field.equals("insertBefore")) 
					{
						__temp_executeDef121 = false;
						return this.insertBefore(((de.polygonal.ds.DLLNode<T>) (dynargs.__get(0)) ), ((T) (dynargs.__get(1)) ));
					}
					
					break;
				}
				
				
				case 66857137:
				{
					if (field.equals("_mergeSortComparable")) 
					{
						__temp_executeDef121 = false;
						return this._mergeSortComparable(((de.polygonal.ds.DLLNode<T>) (dynargs.__get(0)) ));
					}
					
					break;
				}
				
				
				case -840447469:
				{
					if (field.equals("unlink")) 
					{
						__temp_executeDef121 = false;
						return this.unlink(((de.polygonal.ds.DLLNode<T>) (dynargs.__get(0)) ));
					}
					
					break;
				}
				
				
				case 94756189:
				{
					if (field.equals("clone")) 
					{
						__temp_executeDef121 = false;
						return this.clone(dynargs.__get(0), ((haxe.lang.Function) (dynargs.__get(1)) ));
					}
					
					break;
				}
				
				
				case 655843531:
				{
					if (field.equals("getNodeAt")) 
					{
						__temp_executeDef121 = false;
						return this.getNodeAt(((int) (haxe.lang.Runtime.toInt(dynargs.__get(0))) ));
					}
					
					break;
				}
				
				
				case -1182381922:
				{
					if (field.equals("toArray")) 
					{
						__temp_executeDef121 = false;
						return this.toArray();
					}
					
					break;
				}
				
				
				case 1098209412:
				{
					if (field.equals("removeHead")) 
					{
						__temp_executeDef121 = false;
						return this.removeHead();
					}
					
					break;
				}
				
				
				case 2058039875:
				{
					if (field.equals("isEmpty")) 
					{
						__temp_executeDef121 = false;
						return this.isEmpty();
					}
					
					break;
				}
				
				
				case 1098563316:
				{
					if (field.equals("removeTail")) 
					{
						__temp_executeDef121 = false;
						return this.removeTail();
					}
					
					break;
				}
				
				
				case 3530753:
				{
					if (field.equals("size")) 
					{
						__temp_executeDef121 = false;
						return this.size();
					}
					
					break;
				}
				
				
				case 2061262525:
				{
					if (field.equals("shiftUp")) 
					{
						__temp_executeDef121 = false;
						this.shiftUp();
					}
					
					break;
				}
				
				
				case 1182533742:
				{
					if (field.equals("iterator")) 
					{
						__temp_executeDef121 = false;
						return this.iterator();
					}
					
					break;
				}
				
				
				case -395396461:
				{
					if (field.equals("popDown")) 
					{
						__temp_executeDef121 = false;
						this.popDown();
					}
					
					break;
				}
				
				
				case 94746189:
				{
					if (field.equals("clear")) 
					{
						__temp_executeDef121 = false;
						this.clear(dynargs.__get(0));
					}
					
					break;
				}
				
				
				case -1040171143:
				{
					if (field.equals("nodeOf")) 
					{
						__temp_executeDef121 = false;
						return this.nodeOf(((T) (dynargs.__get(0)) ), ((de.polygonal.ds.DLLNode<T>) (dynargs.__get(1)) ));
					}
					
					break;
				}
				
				
				case -934610812:
				{
					if (field.equals("remove")) 
					{
						__temp_executeDef121 = false;
						return this.remove(((T) (dynargs.__get(0)) ));
					}
					
					break;
				}
				
				
				case 1791554991:
				{
					if (field.equals("lastNodeOf")) 
					{
						__temp_executeDef121 = false;
						return this.lastNodeOf(((T) (dynargs.__get(0)) ), ((de.polygonal.ds.DLLNode<T>) (dynargs.__get(1)) ));
					}
					
					break;
				}
				
				
				case -567445985:
				{
					if (field.equals("contains")) 
					{
						__temp_executeDef121 = false;
						return this.contains(((T) (dynargs.__get(0)) ));
					}
					
					break;
				}
				
				
				case 3536286:
				{
					if (field.equals("sort")) 
					{
						__temp_executeDef121 = false;
						this.sort(((haxe.lang.Function) (dynargs.__get(0)) ), dynargs.__get(1));
					}
					
					break;
				}
				
				
				case 3151468:
				{
					if (field.equals("free")) 
					{
						__temp_executeDef121 = false;
						this.free();
					}
					
					break;
				}
				
				
				case 103785528:
				{
					if (field.equals("merge")) 
					{
						__temp_executeDef121 = false;
						this.merge(((de.polygonal.ds.DLL<T>) (dynargs.__get(0)) ));
					}
					
					break;
				}
				
				
				case -1776922004:
				{
					if (field.equals("toString")) 
					{
						__temp_executeDef121 = false;
						return this.toString();
					}
					
					break;
				}
				
				
				case -1354795244:
				{
					if (field.equals("concat")) 
					{
						__temp_executeDef121 = false;
						return this.concat(((de.polygonal.ds.DLL<T>) (dynargs.__get(0)) ));
					}
					
					break;
				}
				
				
				case 2072332025:
				{
					if (field.equals("shuffle")) 
					{
						__temp_executeDef121 = false;
						this.shuffle(((de.polygonal.ds.DA<java.lang.Object>) (dynargs.__get(0)) ));
					}
					
					break;
				}
				
				
				case 1099846370:
				{
					if (field.equals("reverse")) 
					{
						__temp_executeDef121 = false;
						this.reverse();
					}
					
					break;
				}
				
				
				case 3143043:
				{
					if (field.equals("fill")) 
					{
						__temp_executeDef121 = false;
						return this.fill(((T) (dynargs.__get(0)) ), ((haxe.root.Array) (dynargs.__get(1)) ), dynargs.__get(2));
					}
					
					break;
				}
				
				
				case 3267882:
				{
					if (field.equals("join")) 
					{
						__temp_executeDef121 = false;
						return this.join(haxe.lang.Runtime.toString(dynargs.__get(0)));
					}
					
					break;
				}
				
				
				case -1408204561:
				{
					if (field.equals("assign")) 
					{
						__temp_executeDef121 = false;
						this.assign(((java.lang.Class<T>) (dynargs.__get(0)) ), ((haxe.root.Array) (dynargs.__get(1)) ), dynargs.__get(2));
					}
					
					break;
				}
				
				
			}
			
			if (__temp_executeDef121) 
			{
				return super.__hx_invokeField(field, dynargs);
			}
			
		}
		
		return null;
	}
	
	
	@Override public   void __hx_getFields(haxe.root.Array<java.lang.String> baseArr)
	{
		baseArr.push("_iterator");
		baseArr.push("_circular");
		baseArr.push("_tailPool");
		baseArr.push("_headPool");
		baseArr.push("_poolSize");
		baseArr.push("_reservedSize");
		baseArr.push("_size");
		baseArr.push("reuseIterator");
		baseArr.push("maxSize");
		baseArr.push("tail");
		baseArr.push("head");
		baseArr.push("key");
		{
			super.__hx_getFields(baseArr);
		}
		
	}
	
	
}


