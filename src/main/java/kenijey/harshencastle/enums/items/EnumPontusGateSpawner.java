package kenijey.harshencastle.enums.items;

import net.minecraft.util.IStringSerializable;

public enum EnumPontusGateSpawner implements IStringSerializable
{
	Normal("normal", 0),
	Enhanced("enhanced", 1);
	
	private int meta;
	private String name;
	
	private EnumPontusGateSpawner(String name, int meta)
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
		for(EnumPontusGateSpawner l : EnumPontusGateSpawner.values())
			s += l.getName() + " ";
		return s.split(" ");
	}
}
