package kenijey.harshencastle.enums.items;

import net.minecraft.util.IStringSerializable;

public enum EnumGlassContainer implements IStringSerializable
{
	EMPTY("empty", 0),
	VOID("void", 1);
	
	private int meta;
	private String name;
	
	private EnumGlassContainer(String name, int meta)
	{
		this.name = name;
		this.meta = meta;
	}

	@Override
	public String getName() {
		return name;
	}
	
	public int getMeta()
	{
		return this.meta;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	public static String[] getNames()
	{
		String s = "";
		for(EnumGlassContainer l : EnumGlassContainer.values())
			s += l.getName() + " ";
		return s.split(" ");
	}
}
