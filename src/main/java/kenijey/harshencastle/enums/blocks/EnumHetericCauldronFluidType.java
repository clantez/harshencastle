package kenijey.harshencastle.enums.blocks;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.fluids.HarshenFluids;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public enum EnumHetericCauldronFluidType implements IStringSerializable
{
	NONE("none", 0),
	HARSHING_WATER("harshing_water", 1, HarshenFluids.harshing_water),
	HARSHEN_DIMENSIONAL_FLUID("harshen_dimensional_fluid", 2, HarshenFluids.harshen_dimensional_fluid),
	BLOOD("blood", 3),
	LAVA("lava", 4, new ResourceLocation("minecraft", "textures/blocks/lava_still.png")),
	MILK("milk", 4, new ResourceLocation("minecraft", "textures/blocks/milk_still.png"));
;
	
	private final String name;
	private final int id;
	private final ResourceLocation resourceLoc;
	private Fluid fromBucket;
	
	private EnumHetericCauldronFluidType(String name, int id, ResourceLocation resourceLocation, Fluid fluid) {
		this.name = name;
		this.id = id;
		this.resourceLoc = resourceLocation;
		this.fromBucket = fluid;
	}
	
	private EnumHetericCauldronFluidType(String name, int id)
	{
		this(name, id, new ResourceLocation(HarshenCastle.MODID, "textures/blocks/" + name + "_still.png"), null);
	}
	
	private EnumHetericCauldronFluidType(String name, int id, Fluid fluid)
	{
		this(name, id, new ResourceLocation(HarshenCastle.MODID, "textures/blocks/" + name + "_still.png"), fluid);
	}
	
	private EnumHetericCauldronFluidType(String name, int id, ResourceLocation location)
	{
		this(name, id, location, null);
	}
	
	public static EnumHetericCauldronFluidType getFromFluid(Fluid fluid)
	{
		for(EnumHetericCauldronFluidType type : EnumHetericCauldronFluidType.values())
			if(type.fromBucket == fluid)
				return type;
		return NONE;
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
		return this.id * 3;
	}
	
	public static EnumHetericCauldronFluidType getMatch(int meta)
	{
		for(EnumHetericCauldronFluidType liquid : EnumHetericCauldronFluidType.values())
			if(liquid.getId() == meta)
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
