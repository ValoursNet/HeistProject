package haxe.root;
import haxe.root.*;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class Fun_85c4a602 extends haxe.lang.Function
{
	public    Fun_85c4a602(haxe.root.Array<java.lang.Object> len, haxe.root.Array<java.lang.Object> i)
	{
		super(0, 0);
		this.len = len;
		this.i = i;
	}
	
	
	@Override public   java.lang.Object __hx_invoke0_o()
	{
		return ( ((int) (haxe.lang.Runtime.toInt(this.i.__get(0))) ) < ((int) (haxe.lang.Runtime.toInt(this.len.__get(0))) ) );
	}
	
	
	public  haxe.root.Array<java.lang.Object> len;
	
	public  haxe.root.Array<java.lang.Object> i;
	
}


