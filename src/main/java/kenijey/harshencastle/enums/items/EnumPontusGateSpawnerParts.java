package kenijey.harshencastle.enums.items;

import net.minecraft.util.IStringSerializable;

public enum EnumPontusGateSpawnerParts implements IStringSerializable
{
	Part1("1", 0),
	Part2("2", 0),
	Part3("3", 0);
	
	private int meta;
	private String name;
	
	private EnumPontusGateSpawnerParts(String name, int meta)
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
		for(EnumPontusGateSpawnerParts l : EnumPontusGateSpawnerParts.values())
			s += l.getName() + " ";
		return s.split(" ");
	}
}
