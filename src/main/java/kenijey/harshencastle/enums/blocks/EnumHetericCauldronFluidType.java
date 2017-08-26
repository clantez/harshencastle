package kenijey.harshencastle.enums.blocks;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.fluids.HarshenFluids;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public enum EnumHetericCauldronFluidType implements IStringSerializable
{
	NONE("none"),
	HARSHING_WATER("harshing_water", HarshenFluids.harshing_water),
	HARSHEN_DIMENSIONAL_FLUID("harshen_dimensional_fluid", HarshenFluids.harshen_dimensional_fluid),
	BLOOD("blood"),
	LAVA("lava", new ResourceLocation("minecraft", "textures/blocks/lava_still.png")),
	MILK("milk");
;
	
	private final String name;
	private final ResourceLocation resourceLoc;
	private Fluid fromBucket;
	
	private EnumHetericCauldronFluidType(String name, ResourceLocation resourceLocation, Fluid fluid) {
		this.name = name;
		this.resourceLoc = resourceLocation;
		this.fromBucket = fluid;
	}
	
	private EnumHetericCauldronFluidType(String name)
	{
		this(name, new ResourceLocation(HarshenCastle.MODID, "textures/blocks/" + name + "_still.png"), null);
	}
	
	private EnumHetericCauldronFluidType(String name, Fluid fluid)
	{
		this(name, new ResourceLocation(HarshenCastle.MODID, "textures/blocks/" + name + "_still.png"), fluid);
	}
	
	private EnumHetericCauldronFluidType(String name, ResourceLocation location)
	{
		this(name, location, null);
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
	
	public ResourceLocation getResourceLoc() {
		return resourceLoc;
	}
}
