package kenijey.harshencastle.enums.blocks;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.fluids.HarshenFluids;
import kenijey.harshencastle.interfaces.IIDSet;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public enum EnumHereticCauldronFluidType implements IStringSerializable, IIDSet
{
	NONE("none"),
	HARSHING_WATER("harshing_water", HarshenFluids.harshing_water),
	HARSHEN_DIMENSIONAL_FLUID("harshen_dimensional_fluid", HarshenFluids.harshen_dimensional_fluid),
	BLOOD("blood"),
	LAVA("lava", new ResourceLocation("textures/blocks/lava_still.png")),
	MILK("milk");
	
	private final String name;
	private final ResourceLocation resourceLoc;
	private int id;
	private Fluid fromBucket;
	private IBlockState state;
	
	private EnumHereticCauldronFluidType(String name, ResourceLocation resourceLocation, Fluid fluid) {
		this.name = name;
		this.resourceLoc = resourceLocation;
		this.fromBucket = fluid;
		if(fluid != null)
			this.state = fluid.getBlock().getDefaultState();
	}
	
	public static EnumHereticCauldronFluidType getFromId(int id)
	{
		for(EnumHereticCauldronFluidType type : EnumHereticCauldronFluidType.values())
			if(type.getId() == id)
				return type;
		return NONE;
	}
	
	public int getId() {
		return id;
	}
	
	
	@Override
	public void setId(int id) {
		this.id = id;
	}
	
	private EnumHereticCauldronFluidType(String name)
	{
		this(name, new ResourceLocation(HarshenCastle.MODID, "textures/blocks/" + name + "_still.png"), null);
	}
	
	private EnumHereticCauldronFluidType(String name, Fluid fluid)
	{
		this(name, new ResourceLocation(HarshenCastle.MODID, "textures/blocks/" + name + "_still.png"), fluid);
	}
	
	private EnumHereticCauldronFluidType(String name, ResourceLocation location)
	{
		this(name, location, null);
	}
	
	public static EnumHereticCauldronFluidType getFromFluid(Fluid fluid)
	{
		for(EnumHereticCauldronFluidType type : EnumHereticCauldronFluidType.values())
			if(type.fromBucket == fluid)
				return type;
		return NONE;
	}
	
	public Fluid getFromBucket() {
		return fromBucket;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	public ResourceLocation getResourceLoc() {
		return resourceLoc;
	}
	
	public Object getStateOrLoc()
	{
		return state == null? resourceLoc : state;
	}
}
