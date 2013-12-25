package de.polygonal._Printf;
import haxe.root.*;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class FormatToken extends haxe.lang.Enum
{
	static 
	{
		de.polygonal._Printf.FormatToken.constructs = new haxe.root.Array<java.lang.String>(new java.lang.String[]{"BareString", "Tag", "Property", "Unknown"});
	}
	public    FormatToken(int index, haxe.root.Array<java.lang.Object> params)
	{
		super(index, params);
	}
	
	
	public static  haxe.root.Array<java.lang.String> constructs;
	
	public static   de.polygonal._Printf.FormatToken BareString(java.lang.String str)
	{
		return new de.polygonal._Printf.FormatToken(((int) (0) ), ((haxe.root.Array<java.lang.Object>) (new haxe.root.Array<java.lang.Object>(new java.lang.Object[]{str})) ));
	}
	
	
	public static   de.polygonal._Printf.FormatToken Tag(de.polygonal._Printf.FormatDataType type, java.lang.Object args)
	{
		return new de.polygonal._Printf.FormatToken(((int) (1) ), ((haxe.root.Array<java.lang.Object>) (new haxe.root.Array<java.lang.Object>(new java.lang.Object[]{type, args})) ));
	}
	
	
	public static   de.polygonal._Printf.FormatToken Property(java.lang.String name)
	{
		return new de.polygonal._Printf.FormatToken(((int) (2) ), ((haxe.root.Array<java.lang.Object>) (new haxe.root.Array<java.lang.Object>(new java.lang.Object[]{name})) ));
	}
	
	
	public static   de.polygonal._Printf.FormatToken Unknown(java.lang.String str, int pos)
	{
		return new de.polygonal._Printf.FormatToken(((int) (3) ), ((haxe.root.Array<java.lang.Object>) (new haxe.root.Array<java.lang.Object>(new java.lang.Object[]{str, pos})) ));
	}
	
	
}


