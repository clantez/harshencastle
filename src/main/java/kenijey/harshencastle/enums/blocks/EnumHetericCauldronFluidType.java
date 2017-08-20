package kenijey.harshencastle.enums.blocks;

import kenijey.harshencastle.HarshenCastle;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;

public enum EnumHetericCauldronFluidType implements IStringSerializable
{
	NONE("none", 0),
	HARSHING_WATER("harshing_water", 1 ),
	HARSHEN_DIMENSIONAL_FLUID("harshen_dimensional_fluid", 2),
	BLOOD("blood", 3);
	
	private final String name;
	private final int id;
	private final ResourceLocation resourceLoc;
	
	EnumHetericCauldronFluidType(String name, int id)
	{
		this.name = name;
		this.id = id;
		this.resourceLoc = new ResourceLocation(HarshenCastle.MODID, "textures/blocks/" + name + ".png");
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	public int getId(){
		return this.id;
	}
	
	public ResourceLocation getResourceLoc() {
		return resourceLoc;
	}
	
	public int getMetaId()
	{
		return this.id * 4;
	}
	
	public static EnumHetericCauldronFluidType getMatch(int id)
	{
		for(EnumHetericCauldronFluidType liquid : EnumHetericCauldronFluidType.values())
			if(liquid.getId() == id)
				return liquid;
		return NONE;
	}
	
	public static EnumHetericCauldronFluidType getMatch(String name)
	{
		for(EnumHetericCauldronFluidType liquid : EnumHetericCauldronFluidType.values())
			if(liquid.getName().equals(name))
				return liquid;
		return NONE;
	}	
}
