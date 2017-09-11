package kenijey.harshencastle;

import java.util.HashMap;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.template.PlacementSettings;

public class HarshenStructure 
{		
	public static final HarshenStructure castle = new HarshenStructure("harshencastlevol4", new BlockPos(73, 65, 50), new BlockPos(-36, -20, 1));
	public static final HarshenStructure pontus_struc1 = new HarshenStructure("pontus/struc1", new BlockPos(26, 22, 26), new BlockPos(-8, 0, -12));
	public static final HarshenStructure shrine = new HarshenStructure("shrine", new BlockPos(7, 5, 7), new BlockPos(3, 1, 3));
	
	private final BlockPos getFromOrigin;
	private final ResourceLocation location;
	private final String name;
	private final BlockPos size;
	public HarshenStructure(String name,BlockPos size, BlockPos fromOrigin) {
		this.getFromOrigin = fromOrigin;
		this.size = size;
		this.name = name;
		this.location = new ResourceLocation(HarshenCastle.MODID, name);
	}
	
	public void loadIntoWorld(World world, BlockPos pos)
	{
		if(world.isRemote)
			return;
		((WorldServer)world).getStructureTemplateManager().get(world.getMinecraftServer(), new ResourceLocation(HarshenCastle.MODID, name))
		.addBlocksToWorld(world, pos, new PlacementSettings().setIgnoreEntities(false).setIgnoreStructureBlock(true));
	}
	
	public BlockPos getSize() {
		return size;
	}
	
	public BlockPos getGetFromOrigin() {
		return getFromOrigin;
	}
}
