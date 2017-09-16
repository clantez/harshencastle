package kenijey.harshencastle.dimensions;

import kenijey.harshencastle.config.IdConfig;
import kenijey.harshencastle.dimensions.pontus.PontusWorldProvider;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class DimensionPontus 
{
	public static int DIMENSION_ID;
	public static final String DIM_NAME = "Pontus Dimension";
	public static final DimensionType PONTUS_DIMENSION = DimensionType.register("pontus", "_pontus", DIMENSION_ID, PontusWorldProvider.class, false);
			
	public static void mainRegistry()
	{
		DIMENSION_ID = IdConfig.PontusDimension;
		DimensionManager.registerDimension(DIMENSION_ID, DimensionPontus.PONTUS_DIMENSION);
	}
	
	public static boolean isTentDimension(World world)
	{
		return isTentDimension(world.provider.getDimension());
	}
	
	public static boolean isTentDimension(int id)
	{
		return id == DimensionPontus.DIMENSION_ID;
	}
}