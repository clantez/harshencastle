package kenijey.harshencastle.enums.items;

import net.minecraft.util.IStringSerializable;

public enum EnumProp implements IStringSerializable
{
	KnightSword("knight_sword", 0);
	
	private int meta;
	private String name;
	
	private EnumProp(String name, int meta)
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
		for(EnumProp l : EnumProp.values())
			s += l.getName() + " ";
		return s.split(" ");
	}
}
