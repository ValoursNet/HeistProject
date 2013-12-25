package de.polygonal._Printf;
import haxe.root.*;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class FormatDataType extends haxe.lang.Enum
{
	static 
	{
		de.polygonal._Printf.FormatDataType.constructs = new haxe.root.Array<java.lang.String>(new java.lang.String[]{"FmtInteger", "FmtFloat", "FmtString", "FmtPointer", "FmtNothing"});
		de.polygonal._Printf.FormatDataType.FmtString = new de.polygonal._Printf.FormatDataType(((int) (2) ), ((haxe.root.Array<java.lang.Object>) (new haxe.root.Array<java.lang.Object>(new java.lang.Object[]{})) ));
		de.polygonal._Printf.FormatDataType.FmtPointer = new de.polygonal._Printf.FormatDataType(((int) (3) ), ((haxe.root.Array<java.lang.Object>) (new haxe.root.Array<java.lang.Object>(new java.lang.Object[]{})) ));
		de.polygonal._Printf.FormatDataType.FmtNothing = new de.polygonal._Printf.FormatDataType(((int) (4) ), ((haxe.root.Array<java.lang.Object>) (new haxe.root.Array<java.lang.Object>(new java.lang.Object[]{})) ));
	}
	public    FormatDataType(int index, haxe.root.Array<java.lang.Object> params)
	{
		super(index, params);
	}
	
	
	public static  haxe.root.Array<java.lang.String> constructs;
	
	public static   de.polygonal._Printf.FormatDataType FmtInteger(de.polygonal._Printf.IntegerType integerType)
	{
		return new de.polygonal._Printf.FormatDataType(((int) (0) ), ((haxe.root.Array<java.lang.Object>) (new haxe.root.Array<java.lang.Object>(new java.lang.Object[]{integerType})) ));
	}
	
	
	public static   de.polygonal._Printf.FormatDataType FmtFloat(de.polygonal._Printf.FloatType floatType)
	{
		return new de.polygonal._Printf.FormatDataType(((int) (1) ), ((haxe.root.Array<java.lang.Object>) (new haxe.root.Array<java.lang.Object>(new java.lang.Object[]{floatType})) ));
	}
	
	
	public static  de.polygonal._Printf.FormatDataType FmtString;
	
	public static  de.polygonal._Printf.FormatDataType FmtPointer;
	
	public static  de.polygonal._Printf.FormatDataType FmtNothing;
	
}


