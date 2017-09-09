package kenijey.harshencastle.enums;

import java.util.ArrayList;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class CauldronLiquid 
{
	public static final CauldronLiquid NONE = new CauldronLiquid("none", (ResourceLocation)null);
	
	private final String name;
	private int id;
	private IBlockState state;
	private ResourceLocation resourceLoc;
	
	public static ArrayList<CauldronLiquid> allLiquids = new ArrayList<>();	
	
	private static int IDSET = -1;
	
	private CauldronLiquid(String name)
	{
		this.name = name;
		id = IDSET++;
		if(allLiquids == null)
			allLiquids = new ArrayList<CauldronLiquid>();
		allLiquids.add(this);
	}
	
	public CauldronLiquid(String name, ResourceLocation textureLocation)
	{
		this(name);
		this.resourceLoc = textureLocation;
	}
	
	public CauldronLiquid(String name, Fluid fluid) {
		this(name, fluid.getBlock().getDefaultState());

	}
	
	public CauldronLiquid(String name, IBlockState blockState) {
		this(name);
		this.state = blockState;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	
	public Boolean hasState() {
		return state != null;
	}
	
	public int getId() {
		return id;
	}
	
	public static CauldronLiquid getEmpty()
	{
		return NONE;
	}
	
	
	public static CauldronLiquid getFromState(IBlockState state)
	{
		for(CauldronLiquid liquid : allLiquids)
			if(liquid.hasState() && liquid.state.getBlock() == state.getBlock())
				return liquid;
			
		return NONE;
	}
	
	public Object getStateOrLoc()
	{
		return state == null? resourceLoc : state;
	}

	public static CauldronLiquid getFromId(int integer) {
		for(CauldronLiquid liquid : allLiquids)
			if(liquid.id == integer)
				return liquid;
		return NONE;
	}
}
