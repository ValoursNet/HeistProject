package haxe.root;
import haxe.root.*;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class Fun_45a8b9af<T> extends haxe.lang.Function
{
	public    Fun_45a8b9af(haxe.root.Array<java.lang.Object> i, haxe.root.Array<haxe.root.Array> _g)
	{
		super(0, 0);
		this.i = i;
		this._g = _g;
	}
	
	
	@Override public   java.lang.Object __hx_invoke0_o()
	{
		T[] __temp_stmt78 = ((haxe.root.Array<T>) (((haxe.root.Array) (this._g.__get(0)) )) ).__a;
		int __temp_stmt79 = 0;
		{
			int __temp_arrIndex50 = 0;
			int __temp_arrVal48 = ((int) (haxe.lang.Runtime.toInt(this.i.__get(__temp_arrIndex50))) );
			int __temp_arrRet49 = __temp_arrVal48++;
			int __temp_expr80 = ((int) (haxe.lang.Runtime.toInt(this.i.__set(__temp_arrIndex50, __temp_arrVal48))) );
			__temp_stmt79 = __temp_arrRet49;
		}
		
		return __temp_stmt78[__temp_stmt79];
	}
	
	
	public  haxe.root.Array<java.lang.Object> i;
	
	public  haxe.root.Array<haxe.root.Array> _g;
	
}


