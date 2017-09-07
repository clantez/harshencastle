package kenijey.harshencastle.enums.blocks;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.fluids.HarshenFluids;
import kenijey.harshencastle.interfaces.IIDSet;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public enum EnumHereticCauldronFluidType implements IStringSerializable, IIDSet
{
	NONE("none"),
	HARSHING_WATER("harshing_water", HarshenFluids.harshing_water),
	HARSHEN_DIMENSIONAL_FLUID("harshen_dimensional_fluid", HarshenFluids.harshen_dimensional_fluid),
	BLOOD("blood"),
	LAVA("lava", new ResourceLocation("textures/blocks/lava_still.png"), Blocks.LAVA.getDefaultState()),
	MILK("milk");
	
	private final String name;
	private final ResourceLocation resourceLoc;
	private int id;
	private IBlockState state;
	
	private EnumHereticCauldronFluidType(String name, ResourceLocation resourceLocation, IBlockState state) {
		this.name = name;
		this.resourceLoc = resourceLocation;
		this.state = state;
	}
	
	private EnumHereticCauldronFluidType(String name)
	{
		this(name, new ResourceLocation(HarshenCastle.MODID, "textures/blocks/" + name + "_still.png"), (IBlockState)null);
	}
	
	private EnumHereticCauldronFluidType(String name, IBlockState state)
	{
		this(name, new ResourceLocation(HarshenCastle.MODID, "textures/blocks/" + name + "_still.png"), state);
	}
	
	private EnumHereticCauldronFluidType(String name, ResourceLocation location)
	{
		this(name, location, (IBlockState)null);
	}
	
	private EnumHereticCauldronFluidType(String name, Fluid fluid) {
		this(name, fluid.getBlock().getDefaultState());
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
	
	public static EnumHereticCauldronFluidType getFromState(IBlockState state)
	{
		for(EnumHereticCauldronFluidType type : EnumHereticCauldronFluidType.values())
			if(type.state.getBlock() == state.getBlock())
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
	
	public Object getStateOrLoc()
	{
		return state == null? resourceLoc : state;
	}
}
