package de.polygonal;
import haxe.root.*;

@SuppressWarnings(value={"rawtypes", "unchecked"})
public  class Printf extends haxe.lang.HxObject
{
	static 
	{
		de.polygonal.Printf._initialized = false;
	}
	public    Printf(haxe.lang.EmptyObject empty)
	{
		{
		}
		
	}
	
	
	public    Printf()
	{
		de.polygonal.Printf.__hx_ctor_de_polygonal_Printf(this);
	}
	
	
	public static   void __hx_ctor_de_polygonal_Printf(de.polygonal.Printf __temp_me7)
	{
		{
		}
		
	}
	
	
	public static  haxe.ds.IntMap<de.polygonal._Printf.FormatDataType> dataTypeMap;
	
	public static  haxe.ds.IntMap<haxe.lang.Function> formatIntFuncHash;
	
	public static  haxe.ds.IntMap<haxe.lang.Function> formatFloatFuncHash;
	
	public static  haxe.ds.IntMap<haxe.lang.Function> formatStringFuncHash;
	
	public static  boolean _initialized;
	
	public static   void init()
	{
		de.polygonal.Printf.dataTypeMap = de.polygonal.Printf.makeDataTypeMap();
		de.polygonal.Printf.formatIntFuncHash = new haxe.ds.IntMap<haxe.lang.Function>();
		de.polygonal.Printf.formatIntFuncHash.set(1, ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (de.polygonal.Printf.class) ), haxe.lang.Runtime.toString("formatSignedDecimal"))) ));
		de.polygonal.Printf.formatIntFuncHash.set(2, ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (de.polygonal.Printf.class) ), haxe.lang.Runtime.toString("formatUnsignedDecimal"))) ));
		de.polygonal.Printf.formatIntFuncHash.set(0, ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (de.polygonal.Printf.class) ), haxe.lang.Runtime.toString("formatCharacter"))) ));
		de.polygonal.Printf.formatIntFuncHash.set(4, ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (de.polygonal.Printf.class) ), haxe.lang.Runtime.toString("formatHexadecimal"))) ));
		de.polygonal.Printf.formatIntFuncHash.set(3, ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (de.polygonal.Printf.class) ), haxe.lang.Runtime.toString("formatOctal"))) ));
		de.polygonal.Printf.formatIntFuncHash.set(5, ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (de.polygonal.Printf.class) ), haxe.lang.Runtime.toString("formatBinary"))) ));
		de.polygonal.Printf.formatFloatFuncHash = new haxe.ds.IntMap<haxe.lang.Function>();
		de.polygonal.Printf.formatFloatFuncHash.set(0, ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (de.polygonal.Printf.class) ), haxe.lang.Runtime.toString("formatNormalFloat"))) ));
		de.polygonal.Printf.formatFloatFuncHash.set(1, ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (de.polygonal.Printf.class) ), haxe.lang.Runtime.toString("formatScientific"))) ));
		de.polygonal.Printf.formatFloatFuncHash.set(2, ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (de.polygonal.Printf.class) ), haxe.lang.Runtime.toString("formatNaturalFloat"))) ));
		de.polygonal.Printf.formatStringFuncHash = new haxe.ds.IntMap<haxe.lang.Function>();
		de.polygonal.Printf.formatStringFuncHash.set(2, ((haxe.lang.Function) (new haxe.lang.Closure(((java.lang.Object) (de.polygonal.Printf.class) ), haxe.lang.Runtime.toString("formatString"))) ));
	}
	
	
	public static   haxe.ds.IntMap<de.polygonal._Printf.FormatDataType> makeDataTypeMap()
	{
		haxe.ds.IntMap<de.polygonal._Printf.FormatDataType> hash = new haxe.ds.IntMap<de.polygonal._Printf.FormatDataType>();
		hash.set(105, de.polygonal._Printf.FormatDataType.FmtInteger(de.polygonal._Printf.IntegerType.ISignedDecimal));
		hash.set(100, de.polygonal._Printf.FormatDataType.FmtInteger(de.polygonal._Printf.IntegerType.ISignedDecimal));
		hash.set(117, de.polygonal._Printf.FormatDataType.FmtInteger(de.polygonal._Printf.IntegerType.IUnsignedDecimal));
		hash.set(99, de.polygonal._Printf.FormatDataType.FmtInteger(de.polygonal._Printf.IntegerType.ICharacter));
		hash.set(120, de.polygonal._Printf.FormatDataType.FmtInteger(de.polygonal._Printf.IntegerType.IHex));
		hash.set(88, de.polygonal._Printf.FormatDataType.FmtInteger(de.polygonal._Printf.IntegerType.IHex));
		hash.set(111, de.polygonal._Printf.FormatDataType.FmtInteger(de.polygonal._Printf.IntegerType.IOctal));
		hash.set(98, de.polygonal._Printf.FormatDataType.FmtInteger(de.polygonal._Printf.IntegerType.IBin));
		hash.set(102, de.polygonal._Printf.FormatDataType.FmtFloat(de.polygonal._Printf.FloatType.FNormal));
		hash.set(101, de.polygonal._Printf.FormatDataType.FmtFloat(de.polygonal._Printf.FloatType.FScientific));
		hash.set(69, de.polygonal._Printf.FormatDataType.FmtFloat(de.polygonal._Printf.FloatType.FScientific));
		hash.set(103, de.polygonal._Printf.FormatDataType.FmtFloat(de.polygonal._Printf.FloatType.FNatural));
		hash.set(71, de.polygonal._Printf.FormatDataType.FmtFloat(de.polygonal._Printf.FloatType.FNatural));
		hash.set(115, de.polygonal._Printf.FormatDataType.FmtString);
		hash.set(112, de.polygonal._Printf.FormatDataType.FmtPointer);
		hash.set(110, de.polygonal._Printf.FormatDataType.FmtNothing);
		return hash;
	}
	
	
	public static   java.lang.String format(java.lang.String fmt, haxe.root.Array args)
	{
		if ( ! (de.polygonal.Printf._initialized) ) 
		{
			de.polygonal.Printf._initialized = true;
			de.polygonal.Printf.init();
		}
		
		{
			int _g1 = 0;
			int _g = args.length;
			while (( _g1 < _g ))
			{
				int i = _g1++;
				if (( args.__get(i) == null )) 
				{
					args.__set(i, "null");
				}
				
			}
			
		}
		
		java.lang.String output = "";
		int argIndex = 0;
		haxe.root.Array<de.polygonal._Printf.FormatToken> tokens = de.polygonal.Printf.tokenize(fmt);
		{
			int _g = 0;
			while (( _g < tokens.length ))
			{
				de.polygonal._Printf.FormatToken token = tokens.__get(_g);
				 ++ _g;
				{
					de.polygonal._Printf.FormatToken __temp_cond54 = (token);
					switch (__temp_cond54.index)
					{
						case 3:
						{
							throw haxe.lang.HaxeException.wrap("invalid format specifier");
						}
						
						
						case 0:
						{
							java.lang.String str = haxe.lang.Runtime.toString(__temp_cond54.params.__get(0));
							output += str;
							break;
						}
						
						
						case 2:
						{
							java.lang.String name = haxe.lang.Runtime.toString(__temp_cond54.params.__get(0));
							if ( ! (haxe.root.Reflect.hasField(args.__get(0), name)) ) 
							{
								throw haxe.lang.HaxeException.wrap(( "no field named " + name ));
							}
							
							output += haxe.root.Std.string(haxe.root.Reflect.field(args.__get(0), name));
							break;
						}
						
						
						case 1:
						{
							java.lang.Object tagArgs = __temp_cond54.params.__get(1);
							de.polygonal._Printf.FormatDataType type = ((de.polygonal._Printf.FormatDataType) (__temp_cond54.params.__get(0)) );
							haxe.lang.Runtime.setField(tagArgs, "width", ( (( ! (( haxe.lang.Runtime.getField(tagArgs, "width", true) == null )) )) ? (haxe.lang.Runtime.getField(tagArgs, "width", true)) : (((int) (haxe.lang.Runtime.toInt(args.__get(argIndex++))) )) ));
							haxe.lang.Runtime.setField(tagArgs, "precision", ( (( ! (( haxe.lang.Runtime.getField(tagArgs, "precision", true) == null )) )) ? (haxe.lang.Runtime.getField(tagArgs, "precision", true)) : (((int) (haxe.lang.Runtime.toInt(args.__get(argIndex++))) )) ));
							java.lang.Object value = args.__get(argIndex++);
							haxe.lang.Function formatFunction = null;
							{
								de.polygonal._Printf.FormatDataType __temp_cond55 = (type);
								switch (__temp_cond55.index)
								{
									case 1:
									{
										de.polygonal._Printf.FloatType floatType = ((de.polygonal._Printf.FloatType) (__temp_cond55.params.__get(0)) );
										formatFunction = de.polygonal.Printf.formatFloatFuncHash.get(haxe.root.Type.enumIndex(floatType));
										break;
									}
									
									
									case 0:
									{
										de.polygonal._Printf.IntegerType integerType = ((de.polygonal._Printf.IntegerType) (__temp_cond55.params.__get(0)) );
										formatFunction = de.polygonal.Printf.formatIntFuncHash.get(haxe.root.Type.enumIndex(integerType));
										break;
									}
									
									
									case 2:
									{
										formatFunction = de.polygonal.Printf.formatStringFuncHash.get(2);
										break;
									}
									
									
									case 3:
									{
										throw haxe.lang.HaxeException.wrap("specifier \'p\' is not supported");
									}
									
									
									case 4:
									{
										throw haxe.lang.HaxeException.wrap("specifier \'n\' is not supported");
									}
									
									
								}
								
							}
							
							output += haxe.lang.Runtime.toString(formatFunction.__hx_invoke2_o(0.0, 0.0, value, tagArgs));
							break;
						}
						
						
					}
					
				}
				
			}
			
		}
		
		return output;
	}
	
	
	public static   haxe.root.Array<de.polygonal._Printf.FormatToken> tokenize(java.lang.String fmt)
	{
		int length = fmt.length();
		haxe.root.StringBuf lastStr = new haxe.root.StringBuf();
		int i = 0;
		int c = 0;
		haxe.root.Array<de.polygonal._Printf.FormatToken> tokens = new haxe.root.Array<de.polygonal._Printf.FormatToken>();
		while (( i < length ))
		{
			int c1 = 0;
			{
				int i1 = i++;
				c1 = ( (( i1 < fmt.length() )) ? (((int) (fmt.charAt(i1)) )) : (-1) );
			}
			
			if (( c1 == 37 )) 
			{
				{
					int i1 = i++;
					c1 = ( (( i1 < fmt.length() )) ? (((int) (fmt.charAt(i1)) )) : (-1) );
				}
				
				if (( c1 == 37 )) 
				{
					lastStr.addChar(c1);
				}
				 else 
				{
					if (( lastStr.toString().length() > 0 )) 
					{
						tokens.push(de.polygonal._Printf.FormatToken.BareString(lastStr.toString()));
						lastStr = new haxe.root.StringBuf();
					}
					
					de.polygonal._Printf.FormatToken token = null;
					if (( c1 == 40 )) 
					{
						int endPos = haxe.lang.StringExt.indexOf(fmt, ")", i);
						if (( endPos == -1 )) 
						{
							token = de.polygonal._Printf.FormatToken.Unknown("named param", i);
						}
						 else 
						{
							java.lang.String paramName = haxe.lang.StringExt.substr(fmt, i, ( endPos - i ));
							i = ( endPos + 1 );
							token = de.polygonal._Printf.FormatToken.Property(paramName);
						}
						
					}
					 else 
					{
						java.lang.Object params = new haxe.lang.DynamicObject(new haxe.root.Array<java.lang.String>(new java.lang.String[]{}), new haxe.root.Array<java.lang.Object>(new java.lang.Object[]{}), new haxe.root.Array<java.lang.String>(new java.lang.String[]{"flags", "pos", "precision", "width"}), new haxe.root.Array<java.lang.Object>(new java.lang.Object[]{((java.lang.Object) (((double) (0) )) ), ((java.lang.Object) (((double) (-1) )) ), ((java.lang.Object) (((double) (-1) )) ), ((java.lang.Object) (((double) (-1) )) )}));
						while (( ( ( ( ( c1 == 45 ) || ( c1 == 43 ) ) || ( c1 == 35 ) ) || ( c1 == 48 ) ) || ( c1 == 32 ) ))
						{
							if (( c1 == 45 )) 
							{
								java.lang.Object __temp_dynop60 = params;
								haxe.lang.Runtime.setField_f(__temp_dynop60, "flags", ((double) (( ((int) (haxe.lang.Runtime.getField_f(__temp_dynop60, "flags", true)) ) | ((int) (1) ) )) ));
							}
							 else 
							{
								if (( c1 == 43 )) 
								{
									java.lang.Object __temp_dynop59 = params;
									haxe.lang.Runtime.setField_f(__temp_dynop59, "flags", ((double) (( ((int) (haxe.lang.Runtime.getField_f(__temp_dynop59, "flags", true)) ) | ((int) (2) ) )) ));
								}
								 else 
								{
									if (( c1 == 35 )) 
									{
										java.lang.Object __temp_dynop58 = params;
										haxe.lang.Runtime.setField_f(__temp_dynop58, "flags", ((double) (( ((int) (haxe.lang.Runtime.getField_f(__temp_dynop58, "flags", true)) ) | ((int) (8) ) )) ));
									}
									 else 
									{
										if (( c1 == 48 )) 
										{
											java.lang.Object __temp_dynop57 = params;
											haxe.lang.Runtime.setField_f(__temp_dynop57, "flags", ((double) (( ((int) (haxe.lang.Runtime.getField_f(__temp_dynop57, "flags", true)) ) | ((int) (16) ) )) ));
										}
										 else 
										{
											if (( c1 == 32 )) 
											{
												java.lang.Object __temp_dynop56 = params;
												haxe.lang.Runtime.setField_f(__temp_dynop56, "flags", ((double) (( ((int) (haxe.lang.Runtime.getField_f(__temp_dynop56, "flags", true)) ) | ((int) (4) ) )) ));
											}
											
										}
										
									}
									
								}
								
							}
							
							{
								int i1 = i++;
								c1 = ( (( i1 < fmt.length() )) ? (((int) (fmt.charAt(i1)) )) : (-1) );
							}
							
						}
						
						if (( ( (( ((int) (haxe.lang.Runtime.getField_f(params, "flags", true)) ) & ((int) (1) ) )) != 0 ) && ( (( ((int) (haxe.lang.Runtime.getField_f(params, "flags", true)) ) & ((int) (16) ) )) != 0 ) )) 
						{
							java.lang.Object __temp_dynop61 = params;
							haxe.lang.Runtime.setField_f(__temp_dynop61, "flags", ((double) (( ((int) (haxe.lang.Runtime.getField_f(__temp_dynop61, "flags", true)) ) & ((int) (268435439) ) )) ));
						}
						
						if (( ( (( ((int) (haxe.lang.Runtime.getField_f(params, "flags", true)) ) & ((int) (4) ) )) != 0 ) && ( (( ((int) (haxe.lang.Runtime.getField_f(params, "flags", true)) ) & ((int) (2) ) )) != 0 ) )) 
						{
							java.lang.Object __temp_dynop62 = params;
							haxe.lang.Runtime.setField_f(__temp_dynop62, "flags", ((double) (( ((int) (haxe.lang.Runtime.getField_f(__temp_dynop62, "flags", true)) ) & ((int) (268435451) ) )) ));
						}
						
						if (( c1 == 42 )) 
						{
							haxe.lang.Runtime.setField(params, "width", null);
							{
								int i1 = i++;
								c1 = ( (( i1 < fmt.length() )) ? (((int) (fmt.charAt(i1)) )) : (-1) );
							}
							
						}
						 else 
						{
							if (( ( c1 >= 48 ) && ( c1 <= 57 ) )) 
							{
								haxe.lang.Runtime.setField_f(params, "width", ((double) (0) ));
								while (( ( c1 >= 48 ) && ( c1 <= 57 ) ))
								{
									haxe.lang.Runtime.setField_f(params, "width", ((double) (( ( c1 - 48 ) + ( ((int) (haxe.lang.Runtime.toInt(haxe.lang.Runtime.getField(params, "width", true))) ) * ((int) (10) ) ) )) ));
									{
										int i1 = i++;
										c1 = ( (( i1 < fmt.length() )) ? (((int) (fmt.charAt(i1)) )) : (-1) );
									}
									
								}
								
								if (( c1 == 36 )) 
								{
									haxe.lang.Runtime.setField_f(params, "pos", ((double) (( ((int) (haxe.lang.Runtime.toInt(haxe.lang.Runtime.getField(params, "width", true))) ) - ((int) (1) ) )) ));
									haxe.lang.Runtime.setField_f(params, "width", ((double) (-1) ));
									{
										int i1 = i++;
										c1 = ( (( i1 < fmt.length() )) ? (((int) (fmt.charAt(i1)) )) : (-1) );
									}
									
									if (( c1 == 42 )) 
									{
										haxe.lang.Runtime.setField(params, "width", null);
										{
											int i1 = i++;
											c1 = ( (( i1 < fmt.length() )) ? (((int) (fmt.charAt(i1)) )) : (-1) );
										}
										
									}
									 else 
									{
										if (( ( c1 >= 48 ) && ( c1 <= 57 ) )) 
										{
											haxe.lang.Runtime.setField_f(params, "width", ((double) (0) ));
											while (( ( c1 >= 48 ) && ( c1 <= 57 ) ))
											{
												haxe.lang.Runtime.setField_f(params, "width", ((double) (( ( c1 - 48 ) + ( ((int) (haxe.lang.Runtime.toInt(haxe.lang.Runtime.getField(params, "width", true))) ) * ((int) (10) ) ) )) ));
												{
													int i1 = i++;
													c1 = ( (( i1 < fmt.length() )) ? (((int) (fmt.charAt(i1)) )) : (-1) );
												}
												
											}
											
										}
										
									}
									
								}
								
							}
							
						}
						
						if (( c1 == 46 )) 
						{
							{
								int i1 = i++;
								c1 = ( (( i1 < fmt.length() )) ? (((int) (fmt.charAt(i1)) )) : (-1) );
							}
							
							if (( c1 == 42 )) 
							{
								haxe.lang.Runtime.setField(params, "precision", null);
								{
									int i1 = i++;
									c1 = ( (( i1 < fmt.length() )) ? (((int) (fmt.charAt(i1)) )) : (-1) );
								}
								
							}
							 else 
							{
								if (( ( c1 >= 48 ) && ( c1 <= 57 ) )) 
								{
									haxe.lang.Runtime.setField_f(params, "precision", ((double) (0) ));
									while (( ( c1 >= 48 ) && ( c1 <= 57 ) ))
									{
										haxe.lang.Runtime.setField_f(params, "precision", ((double) (( ( c1 - 48 ) + ( ((int) (haxe.lang.Runtime.toInt(haxe.lang.Runtime.getField(params, "precision", true))) ) * ((int) (10) ) ) )) ));
										{
											int i1 = i++;
											c1 = ( (( i1 < fmt.length() )) ? (((int) (fmt.charAt(i1)) )) : (-1) );
										}
										
									}
									
								}
								 else 
								{
									haxe.lang.Runtime.setField_f(params, "precision", ((double) (0) ));
								}
								
							}
							
						}
						
						while (( ( ( c1 == 104 ) || ( c1 == 108 ) ) || ( c1 == 76 ) ))
						{
							switch (c1)
							{
								case 104:
								{
									java.lang.Object __temp_dynop63 = params;
									haxe.lang.Runtime.setField_f(__temp_dynop63, "flags", ((double) (( ((int) (haxe.lang.Runtime.getField_f(__temp_dynop63, "flags", true)) ) | ((int) (32) ) )) ));
									break;
								}
								
								
								case 108:
								{
									java.lang.Object __temp_dynop64 = params;
									haxe.lang.Runtime.setField_f(__temp_dynop64, "flags", ((double) (( ((int) (haxe.lang.Runtime.getField_f(__temp_dynop64, "flags", true)) ) | ((int) (128) ) )) ));
									break;
								}
								
								
								case 76:
								{
									java.lang.Object __temp_dynop65 = params;
									haxe.lang.Runtime.setField_f(__temp_dynop65, "flags", ((double) (( ((int) (haxe.lang.Runtime.getField_f(__temp_dynop65, "flags", true)) ) | ((int) (64) ) )) ));
									break;
								}
								
								
							}
							
							{
								int i1 = i++;
								c1 = ( (( i1 < fmt.length() )) ? (((int) (fmt.charAt(i1)) )) : (-1) );
							}
							
						}
						
						if (( ( ( c1 == 69 ) || ( c1 == 71 ) ) || ( c1 == 88 ) )) 
						{
							java.lang.Object __temp_dynop66 = params;
							haxe.lang.Runtime.setField_f(__temp_dynop66, "flags", ((double) (( ((int) (haxe.lang.Runtime.getField_f(__temp_dynop66, "flags", true)) ) | ((int) (256) ) )) ));
						}
						
						de.polygonal._Printf.FormatDataType type = de.polygonal.Printf.dataTypeMap.get(c1);
						if (( type == null )) 
						{
							token = de.polygonal._Printf.FormatToken.Unknown(Character.toString((char) c1), i);
						}
						 else 
						{
							token = de.polygonal._Printf.FormatToken.Tag(type, params);
						}
						
					}
					
					tokens.push(token);
				}
				
			}
			 else 
			{
				lastStr.addChar(c1);
			}
			
		}
		
		if (( lastStr.toString().length() > 0 )) 
		{
			tokens.push(de.polygonal._Printf.FormatToken.BareString(lastStr.toString()));
		}
		
		return tokens;
	}
	
	
	public static   java.lang.String formatBinary(int value, java.lang.Object args)
	{
		java.lang.String output = "";
		int flags = ((int) (haxe.lang.Runtime.getField_f(args, "flags", true)) );
		java.lang.Object precision = haxe.lang.Runtime.getField(args, "precision", true);
		java.lang.Object width = haxe.lang.Runtime.getField(args, "width", true);
		if (haxe.lang.Runtime.eq(precision, -1)) 
		{
			precision = 1;
		}
		
		if (( value != 0 )) 
		{
			if (( (( flags & 32 )) != 0 )) 
			{
				value &= 65535;
			}
			
			int i = value;
			do 
			{
				output = ( (( (( (( i & 1 )) > 0 )) ? ("1") : ("0") )) + output );
				i >>>= 1;
			}
			while (( i > 0 ));
			if (( haxe.lang.Runtime.compare(precision, 1) > 0 )) 
			{
				if (( haxe.lang.Runtime.compare(precision, output.length()) > 0 )) 
				{
					output = de.polygonal.Printf.lpad(output, "0", ((int) (haxe.lang.Runtime.toInt(precision)) ));
				}
				
				if (( (( flags & 8 )) != 0 )) 
				{
					output = ( "b" + output );
				}
				
			}
			
		}
		
		return ( (( (( flags & 1 )) != 0 )) ? (( (( haxe.lang.Runtime.compare(width, output.length()) > 0 )) ? (de.polygonal.Printf.rpad(output, " ", ((int) (haxe.lang.Runtime.toInt(width)) ))) : (output) )) : (( (( haxe.lang.Runtime.compare(width, output.length()) > 0 )) ? (de.polygonal.Printf.lpad(output, ( (( (( flags & 16 )) != 0 )) ? ("0") : (" ") ), ((int) (haxe.lang.Runtime.toInt(width)) ))) : (output) )) );
	}
	
	
	public static   java.lang.String formatOctal(int value, java.lang.Object args)
	{
		java.lang.String output = "";
		int flags = ((int) (haxe.lang.Runtime.getField_f(args, "flags", true)) );
		java.lang.Object precision = haxe.lang.Runtime.getField(args, "precision", true);
		java.lang.Object width = haxe.lang.Runtime.getField(args, "width", true);
		if (haxe.lang.Runtime.eq(precision, -1)) 
		{
			precision = 1;
		}
		
		if (( value != 0 )) 
		{
			if (( (( flags & 32 )) != 0 )) 
			{
				value &= 65535;
			}
			
			output = de.polygonal.Printf.toOct(value);
			if (( (( flags & 8 )) != 0 )) 
			{
				output = ( "0" + output );
			}
			
			if (( ( haxe.lang.Runtime.compare(precision, 1) > 0 ) && ( haxe.lang.Runtime.compare(output.length(), precision) < 0 ) )) 
			{
				output = de.polygonal.Printf.lpad(output, "0", ((int) (haxe.lang.Runtime.toInt(precision)) ));
			}
			
		}
		
		return ( (( (( flags & 1 )) != 0 )) ? (( (( haxe.lang.Runtime.compare(width, output.length()) > 0 )) ? (de.polygonal.Printf.rpad(output, " ", ((int) (haxe.lang.Runtime.toInt(width)) ))) : (output) )) : (( (( haxe.lang.Runtime.compare(width, output.length()) > 0 )) ? (de.polygonal.Printf.lpad(output, ( (( (( flags & 16 )) != 0 )) ? ("0") : (" ") ), ((int) (haxe.lang.Runtime.toInt(width)) ))) : (output) )) );
	}
	
	
	public static   java.lang.String formatHexadecimal(int value, java.lang.Object args)
	{
		java.lang.String output = "";
		int flags = ((int) (haxe.lang.Runtime.getField_f(args, "flags", true)) );
		java.lang.Object precision = haxe.lang.Runtime.getField(args, "precision", true);
		java.lang.Object width = haxe.lang.Runtime.getField(args, "width", true);
		if (haxe.lang.Runtime.eq(precision, -1)) 
		{
			precision = 1;
		}
		
		if (( value != 0 )) 
		{
			if (( (( flags & 32 )) != 0 )) 
			{
				value &= 65535;
			}
			
			{
				int x = value;
				java.lang.String s = "";
				java.lang.String hexChars = "0123456789ABCDEF";
				do 
				{
					s = ( haxe.lang.StringExt.charAt(hexChars, ( x & 15 )) + s );
					x >>>= 4;
				}
				while (( x > 0 ));
				output = s;
			}
			
			if (( ( haxe.lang.Runtime.compare(precision, 1) > 0 ) && ( haxe.lang.Runtime.compare(output.length(), precision) < 0 ) )) 
			{
				output = de.polygonal.Printf.lpad(output, "0", ((int) (haxe.lang.Runtime.toInt(precision)) ));
			}
			
			if (( ( (( flags & 8 )) != 0 ) && ( value != 0 ) )) 
			{
				output = ( "0x" + output );
			}
			
			output = ( (( (( flags & 256 )) != 0 )) ? (output.toUpperCase()) : (output.toLowerCase()) );
		}
		
		return ( (( (( flags & 1 )) != 0 )) ? (( (( haxe.lang.Runtime.compare(width, output.length()) > 0 )) ? (de.polygonal.Printf.rpad(output, " ", ((int) (haxe.lang.Runtime.toInt(width)) ))) : (output) )) : (( (( haxe.lang.Runtime.compare(width, output.length()) > 0 )) ? (de.polygonal.Printf.lpad(output, ( (( (( flags & 16 )) != 0 )) ? ("0") : (" ") ), ((int) (haxe.lang.Runtime.toInt(width)) ))) : (output) )) );
	}
	
	
	public static   java.lang.String formatUnsignedDecimal(int value, java.lang.Object args)
	{
		java.lang.String output = null;
		java.lang.Object precision = haxe.lang.Runtime.getField(args, "precision", true);
		if (( value >= 0 )) 
		{
			output = de.polygonal.Printf.formatSignedDecimal(value, args);
		}
		 else 
		{
			long x = ((long) (( ( ((long) (0) ) << 32 ) | ( value & 0xffffffffL ) )) );
			output = ( haxe.root.Std.string(x) + "" );
			if (( ( haxe.lang.Runtime.compare(precision, 1) > 0 ) && ( haxe.lang.Runtime.compare(output.length(), precision) < 0 ) )) 
			{
				output = de.polygonal.Printf.lpad(output, "0", ((int) (haxe.lang.Runtime.toInt(precision)) ));
			}
			
			output = de.polygonal.Printf.padNumber(output, ((double) (value) ), ((int) (haxe.lang.Runtime.getField_f(args, "flags", true)) ), ((int) (haxe.lang.Runtime.toInt(haxe.lang.Runtime.getField(args, "width", true))) ));
		}
		
		return output;
	}
	
	
	public static   java.lang.String formatNaturalFloat(double value, java.lang.Object args)
	{
		haxe.lang.Runtime.setField_f(args, "precision", ((double) (0) ));
		java.lang.String formatedFloat = de.polygonal.Printf.formatNormalFloat(value, args);
		java.lang.String formatedScientific = de.polygonal.Printf.formatScientific(value, args);
		if (( (( ((int) (haxe.lang.Runtime.getField_f(args, "flags", true)) ) & ((int) (8) ) )) != 0 )) 
		{
			if (( haxe.lang.StringExt.indexOf(formatedFloat, ".", null) != -1 )) 
			{
				int pos = ( formatedFloat.length() - 1 );
				while (( (( (( pos < formatedFloat.length() )) ? (((int) (formatedFloat.charAt(pos)) )) : (-1) )) == 48 ))
				{
					pos--;
				}
				
				formatedFloat = haxe.lang.StringExt.substr(formatedFloat, 0, pos);
			}
			
		}
		
		return ( (( formatedFloat.length() <= formatedScientific.length() )) ? (formatedFloat) : (formatedScientific) );
	}
	
	
	public static   java.lang.String formatScientific(double value, java.lang.Object args)
	{
		java.lang.String output = "";
		int flags = ((int) (haxe.lang.Runtime.getField_f(args, "flags", true)) );
		java.lang.Object precision = haxe.lang.Runtime.getField(args, "precision", true);
		if (haxe.lang.Runtime.eq(precision, -1)) 
		{
			precision = 6;
		}
		
		int sign = 0;
		int exponent = 0;
		if (( value == 0 )) 
		{
			sign = 0;
			exponent = 0;
			output += "0";
			if (( haxe.lang.Runtime.compare(precision, 0) > 0 )) 
			{
				output += ".";
				{
					int _g = 0;
					while (( _g < ((int) (haxe.lang.Runtime.toInt(precision)) ) ))
					{
						int i = _g++;
						output += "0";
					}
					
				}
				
			}
			
		}
		 else 
		{
			sign = ( (( value > 0. )) ? (1) : (( (( value < 0. )) ? (-1) : (0) )) );
			value = java.lang.Math.abs(value);
			exponent = ((int) (java.lang.Math.floor(( java.lang.Math.log(value) / 2.302585092994046 ))) );
			value = ( value / java.lang.Math.pow(((double) (10) ), ((double) (exponent) )) );
			double p = java.lang.Math.pow(0.1, ((double) (haxe.lang.Runtime.toDouble(precision)) ));
			{
				int min = -2147483647;
				double t = ( value / p );
				if (( ( t < 2147483647 ) && ( t > min ) )) 
				{
					value = ( ((int) (java.lang.Math.round(t)) ) * p );
				}
				 else 
				{
					t = ( (( t > 0 )) ? (( t + .5 )) : (( (( t < 0 )) ? (( t - .5 )) : (t) )) );
					value = ( (( t - ( t % 1 ) )) * p );
				}
				
			}
			
		}
		
		output += ( (( sign < 0 )) ? ("-") : (( (( (( flags & 2 )) != 0 )) ? ("+") : ("") )) );
		if (( value != 0 )) 
		{
			output += de.polygonal.Printf.rpad(haxe.lang.StringExt.substr(de.polygonal.Printf.str(value), 0, ((int) (haxe.lang.Runtime.toInt(haxe.lang.Runtime.plus(precision, 2))) )), "0", ((int) (haxe.lang.Runtime.toInt(haxe.lang.Runtime.plus(precision, 2))) ));
		}
		
		output += ( (( (( flags & 256 )) != 0 )) ? ("E") : ("e") );
		output += ( (( exponent >= 0 )) ? ("+") : ("-") );
		if (( exponent < 10 )) 
		{
			output += "00";
		}
		 else 
		{
			if (( exponent < 100 )) 
			{
				output += "0";
			}
			
		}
		
		output += de.polygonal.Printf.str(de.polygonal.Printf.iabs(exponent));
		return output;
	}
	
	
	public static   java.lang.String formatSignedDecimal(int value, java.lang.Object args)
	{
		java.lang.String output = null;
		int flags = ((int) (haxe.lang.Runtime.getField_f(args, "flags", true)) );
		java.lang.Object precision = haxe.lang.Runtime.getField(args, "precision", true);
		java.lang.Object width = haxe.lang.Runtime.getField(args, "width", true);
		if (( haxe.lang.Runtime.eq(precision, 0) && ( value == 0 ) )) 
		{
			output = "";
		}
		 else 
		{
			if (( (( flags & 32 )) != 0 )) 
			{
				value &= 65535;
			}
			
			output = de.polygonal.Printf.str(de.polygonal.Printf.iabs(value));
			if (( ( haxe.lang.Runtime.compare(precision, 1) > 0 ) && ( haxe.lang.Runtime.compare(output.length(), precision) < 0 ) )) 
			{
				output = de.polygonal.Printf.lpad(output, "0", ((int) (haxe.lang.Runtime.toInt(precision)) ));
			}
			
			if (( (( flags & 16 )) != 0 )) 
			{
				output = de.polygonal.Printf.lpad(output, "0", ( (( value < 0 )) ? (( ((int) (haxe.lang.Runtime.toInt(width)) ) - ((int) (1) ) )) : (((int) (haxe.lang.Runtime.toInt(width)) )) ));
			}
			
			if (( value < 0 )) 
			{
				output = ( "-" + output );
			}
			
		}
		
		if (( value >= 0 )) 
		{
			if (( (( flags & 2 )) != 0 )) 
			{
				output = ( "+" + output );
			}
			 else 
			{
				if (( (( flags & 4 )) != 0 )) 
				{
					output = ( " " + output );
				}
				
			}
			
		}
		
		if (( (( flags & 1 )) != 0 )) 
		{
			output = de.polygonal.Printf.rpad(output, " ", ((int) (haxe.lang.Runtime.toInt(haxe.lang.Runtime.getField(args, "width", true))) ));
		}
		 else 
		{
			output = de.polygonal.Printf.lpad(output, " ", ((int) (haxe.lang.Runtime.toInt(haxe.lang.Runtime.getField(args, "width", true))) ));
		}
		
		return output;
	}
	
	
	public static   java.lang.String formatString(java.lang.String x, java.lang.Object args)
	{
		java.lang.String output = x;
		java.lang.Object precision = haxe.lang.Runtime.getField(args, "precision", true);
		java.lang.Object width = haxe.lang.Runtime.getField(args, "width", true);
		if (( haxe.lang.Runtime.compare(precision, 0) > 0 )) 
		{
			output = haxe.lang.StringExt.substr(x, 0, precision);
		}
		
		int k = output.length();
		if (( ( haxe.lang.Runtime.compare(width, 0) > 0 ) && ( haxe.lang.Runtime.compare(k, width) < 0 ) )) 
		{
			if (( (( ((int) (haxe.lang.Runtime.getField_f(args, "flags", true)) ) & ((int) (1) ) )) != 0 )) 
			{
				output = de.polygonal.Printf.rpad(output, " ", ((int) (haxe.lang.Runtime.toInt(width)) ));
			}
			 else 
			{
				output = de.polygonal.Printf.lpad(output, " ", ((int) (haxe.lang.Runtime.toInt(width)) ));
			}
			
		}
		
		return output;
	}
	
	
	public static   java.lang.String formatNormalFloat(double value, java.lang.Object args)
	{
		java.lang.String output = null;
		int flags = ((int) (haxe.lang.Runtime.getField_f(args, "flags", true)) );
		java.lang.Object precision = haxe.lang.Runtime.getField(args, "precision", true);
		java.lang.Object width = haxe.lang.Runtime.getField(args, "width", true);
		if (haxe.lang.Runtime.eq(precision, -1)) 
		{
			precision = 6;
		}
		
		if (haxe.lang.Runtime.eq(precision, 0)) 
		{
			output = de.polygonal.Printf.str(de.polygonal.Printf.iabs(((int) (java.lang.Math.round(value)) )));
			if (( (( flags & 8 )) != 0 )) 
			{
				output += ".";
			}
			
		}
		 else 
		{
			{
				double y = java.lang.Math.pow(.1, ((double) (haxe.lang.Runtime.toDouble(precision)) ));
				int min = -2147483647;
				double t = ( value / y );
				if (( ( t < 2147483647 ) && ( t > min ) )) 
				{
					value = ( ((int) (java.lang.Math.round(t)) ) * y );
				}
				 else 
				{
					t = ( (( t > 0 )) ? (( t + .5 )) : (( (( t < 0 )) ? (( t - .5 )) : (t) )) );
					value = ( (( t - ( t % 1 ) )) * y );
				}
				
			}
			
			java.lang.Object decimalPlaces = precision;
			if (java.lang.Double.isNaN(value)) 
			{
				output = "NaN";
			}
			 else 
			{
				int t = ((int) (java.lang.Math.pow(((double) (10) ), ((double) (haxe.lang.Runtime.toDouble(decimalPlaces)) ))) );
				output = de.polygonal.Printf.str(( ((double) (((int) (( value * t )) )) ) / t ));
				int i = haxe.lang.StringExt.indexOf(output, ".", null);
				if (( i != -1 )) 
				{
					int _g = haxe.lang.StringExt.substr(output, ( i + 1 ), null).length();
					while (( _g < ((int) (haxe.lang.Runtime.toInt(decimalPlaces)) ) ))
					{
						int i1 = _g++;
						output += "0";
					}
					
				}
				 else 
				{
					output += ".";
					{
						int _g = 0;
						while (( _g < ((int) (haxe.lang.Runtime.toInt(decimalPlaces)) ) ))
						{
							int i1 = _g++;
							output += "0";
						}
						
					}
					
				}
				
			}
			
		}
		
		if (( ( (( flags & 2 )) != 0 ) && ( value >= 0 ) )) 
		{
			output = ( "+" + output );
		}
		 else 
		{
			if (( ( (( flags & 4 )) != 0 ) && ( value >= 0 ) )) 
			{
				output = ( " " + output );
			}
			
		}
		
		if (( (( flags & 16 )) != 0 )) 
		{
			output = de.polygonal.Printf.lpad(output, "0", ( (( value < 0 )) ? (( ((int) (haxe.lang.Runtime.toInt(width)) ) - ((int) (1) ) )) : (((int) (haxe.lang.Runtime.toInt(width)) )) ));
		}
		
		if (( (( flags & 1 )) != 0 )) 
		{
			output = de.polygonal.Printf.rpad(output, " ", ((int) (haxe.lang.Runtime.toInt(width)) ));
		}
		 else 
		{
			output = de.polygonal.Printf.lpad(output, " ", ((int) (haxe.lang.Runtime.toInt(width)) ));
		}
		
		return output;
	}
	
	
	public static   java.lang.String formatCharacter(int x, java.lang.Object args)
	{
		java.lang.String output = Character.toString((char) x);
		if (( haxe.lang.Runtime.compare(haxe.lang.Runtime.getField(args, "width", true), 1) > 0 )) 
		{
			if (( (( ((int) (haxe.lang.Runtime.getField_f(args, "flags", true)) ) & ((int) (1) ) )) != 0 )) 
			{
				output = de.polygonal.Printf.rpad(output, " ", ((int) (haxe.lang.Runtime.toInt(haxe.lang.Runtime.getField(args, "width", true))) ));
			}
			 else 
			{
				output = de.polygonal.Printf.lpad(output, " ", ((int) (haxe.lang.Runtime.toInt(haxe.lang.Runtime.getField(args, "width", true))) ));
			}
			
		}
		
		return output;
	}
	
	
	public static   java.lang.String padNumber(java.lang.String x, double n, int flags, int width)
	{
		int k = x.length();
		if (( ( width > 0 ) && ( k < width ) )) 
		{
			if (( (( flags & 1 )) != 0 )) 
			{
				x = de.polygonal.Printf.rpad(x, " ", width);
			}
			 else 
			{
				if (( n >= 0 )) 
				{
					x = de.polygonal.Printf.lpad(x, ( (( (( flags & 16 )) != 0 )) ? ("0") : (" ") ), width);
				}
				 else 
				{
					if (( (( flags & 16 )) != 0 )) 
					{
						x = ( "-" + de.polygonal.Printf.lpad(haxe.lang.StringExt.substr(x, 1, null), "0", width) );
					}
					 else 
					{
						x = de.polygonal.Printf.lpad(x, " ", width);
					}
					
				}
				
			}
			
		}
		
		return x;
	}
	
	
	public static   java.lang.String lpad(java.lang.String s, java.lang.String c, int l)
	{
		if (( c.length() <= 0 )) 
		{
			throw haxe.lang.HaxeException.wrap("c.length <= 0");
		}
		
		while (( s.length() < l ))
		{
			s = ( c + s );
		}
		
		return s;
	}
	
	
	public static   java.lang.String rpad(java.lang.String s, java.lang.String c, int l)
	{
		if (( c.length() <= 0 )) 
		{
			throw haxe.lang.HaxeException.wrap("c.length <= 0");
		}
		
		while (( s.length() < l ))
		{
			s = ( s + c );
		}
		
		return s;
	}
	
	
	public static   java.lang.String toHex(int x)
	{
		java.lang.String s = "";
		java.lang.String hexChars = "0123456789ABCDEF";
		do 
		{
			s = ( haxe.lang.StringExt.charAt(hexChars, ( x & 15 )) + s );
			x >>>= 4;
		}
		while (( x > 0 ));
		return s;
	}
	
	
	public static   java.lang.String toOct(int x)
	{
		java.lang.String s = "";
		int t = x;
		do 
		{
			s = ( (( t & 7 )) + s );
			t >>>= 3;
		}
		while (( t > 0 ));
		return s;
	}
	
	
	public static   int iabs(int x)
	{
		return ((int) (java.lang.Math.abs(((double) (x) ))) );
	}
	
	
	public static  <T> java.lang.String str(T x)
	{
		return haxe.root.Std.string(x);
	}
	
	
	public static   int codeAt(java.lang.String x, int i)
	{
		return ( (( i < x.length() )) ? (((int) (x.charAt(i)) )) : (-1) );
	}
	
	
	public static   boolean isDigit(int x)
	{
		return ( ( x >= 48 ) && ( x <= 57 ) );
	}
	
	
	public static   double roundTo(double x, double y)
	{
		int min = -2147483647;
		double t = ( x / y );
		if (( ( t < 2147483647 ) && ( t > min ) )) 
		{
			return ( ((int) (java.lang.Math.round(t)) ) * y );
		}
		 else 
		{
			t = ( (( t > 0 )) ? (( t + .5 )) : (( (( t < 0 )) ? (( t - .5 )) : (t) )) );
			return ( (( t - ( t % 1 ) )) * y );
		}
		
	}
	
	
	public static   java.lang.Object __hx_createEmpty()
	{
		return new de.polygonal.Printf(((haxe.lang.EmptyObject) (haxe.lang.EmptyObject.EMPTY) ));
	}
	
	
	public static   java.lang.Object __hx_create(haxe.root.Array arr)
	{
		return new de.polygonal.Printf();
	}
	
	
}


