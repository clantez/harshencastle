package kenijey.harshencastle.api;

import java.util.ArrayList;

import org.apache.commons.logging.LogFactory;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
/**
 * The liquid that can go inside the HereticCauldron.
 * It holds information about what to render as the liquid, and how large each one of these class is, relative to the cauldron 3 level limit.
 * 
 * @author Wyn Price
 *
 */
public class CauldronLiquid
{
	public static final CauldronLiquid NONE = new CauldronLiquid("none", (ResourceLocation)null);
	
	private final String name;
	private IBlockState state;
	private ResourceLocation resourceLoc;
	
	private int fillBy;
	
	private String modID;
	
	public static ArrayList<CauldronLiquid> allLiquids = new ArrayList<>();	
	
	private static int IDSET = 1;
	
	private CauldronLiquid(String name)
	{
		this.name = name;
		if(allLiquids == null)
			allLiquids = new ArrayList<CauldronLiquid>();
		allLiquids.add(this);
	}
	
	public CauldronLiquid(String name, ResourceLocation textureLocation)
	{
		this(name);
		this.resourceLoc = textureLocation;
	}

	public CauldronLiquid(String name, IBlockState blockState) {
		this(name);
		this.state = blockState;
	}
	
	public String getName() {
		return modID + ":" + name;
	}
	
	@Override
	public String toString() {
		return modID + ":" + name;
	}
	
	public CauldronLiquid setModID(String modID)
	{
		this.modID = modID;
		return this;
	}
	
	
	public Boolean hasState() {
		return state != null;
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
	
	public CauldronLiquid setFillBy(int fillBy) {
		this.fillBy = fillBy;
		return this;
	}
	
	public int getFillBy() {
		return fillBy;
	}
	
	public Object getStateOrLoc()
	{
		return state == null? resourceLoc : state;
	}

	public static CauldronLiquid getFromName(String name) {
		if(name == null || name.isEmpty()) return NONE;
		for(CauldronLiquid liquid : allLiquids)
			if(liquid.getName().equals(name))
				return liquid;
		if(!name.equals("null:none"))
			LogFactory.getLog("harshencastle").error("Tried to load an unregistered CauldronLiquid. " + name + " doesnt exist!");
		return NONE;
	}
}
