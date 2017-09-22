package kenijey.harshencastle.base;

import java.util.ArrayList;
import java.util.Random;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.HarshenStructures;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.template.HarshenTemplate;
import kenijey.harshencastle.worldgenerators.pontus.PontusWorldRuinGenerator;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;

public class HarshenStructure 
{
	public static ArrayList<HarshenStructure> allStructures = new ArrayList<>();
	
	protected BlockPos originAddition;
	protected final ResourceLocation location;
	protected final String name;
	public String showName;
	protected BlockPos size;
	private static boolean hasLoaded;
	protected final float chance;
	protected final boolean useRuin;
	protected final boolean displaceDownwards;
	protected final int dimension;
	
	public HarshenStructure(String parentFolder, String name, float chance, boolean useRuin, int dimension, boolean displaceDownwards) {
		this.dimension = dimension;
		this.useRuin = useRuin;
		this.chance = chance;
		this.displaceDownwards = displaceDownwards;
		this.name = parentFolder + "/" + name;
		showName = name;
		this.location = new ResourceLocation(HarshenCastle.MODID, this.name);
	}
	
	public HarshenStructure(String parentFolder, String name, float chance, boolean useRuin, int dimension) {
		this(parentFolder, name, chance, useRuin, dimension, true);
	}
	
	public static ArrayList<HarshenStructure> get(int dimension)
	{
		ArrayList<HarshenStructure> structures = new ArrayList<>();
		for(HarshenStructure struc : allStructures)
			if(struc.dimension == dimension)
				structures.add(struc);
		return structures;
	}
	
	public static void load()
	{
		if(hasLoaded)
			return;
		for(HarshenStructure struc : allStructures)
		{
			HarshenTemplate t = HarshenTemplate.getTemplate(struc.location);
			struc.setSize(t.getSize());
			struc.setPos(t.getPos());
		}
		hasLoaded = true;
	}
	
	public boolean canLoadAt(int dimension, int chunkX, int chunkZ)
	{
		return true;
	}
	
	public boolean addPositionOnGenerate()
	{
		return false;
	}
	
	public BlockPos addPos()
	{
		return BlockPos.ORIGIN;
	}
	
	public void preAddition(World world, BlockPos pos, Random random){};
	public void postAddition(World world, BlockPos pos, Random random){};
	
	private void setSize(BlockPos size) {
		this.size = size;
	}
	
	private void setPos(BlockPos size) {
		this.size = size;
	}
	
	public void loadIntoWorld(World world, BlockPos pos, Random random)
	{
		if(world.isRemote)
			return;
		preAddition(world, pos, random);
		((WorldServer)world).getStructureTemplateManager().get(world.getMinecraftServer(), new ResourceLocation(HarshenCastle.MODID, name))
		.addBlocksToWorld(world, pos, new PlacementSettings().setIgnoreEntities(false).setIgnoreStructureBlock(true));
		postAddition(world, pos, random);
	}
	
	public boolean generateStucture(World world, Random random, int chunkX, int chunkZ)
	{
		if(random.nextFloat() < chance) {
	        int x = chunkX * 16 + random.nextInt(16);
	        int z = chunkZ * 16 + random.nextInt(16);
	        BlockPos pos = HarshenUtils.getTopBlock(world, new BlockPos(x, 0, z)).add(originAddition).add(addPos());
	        loadIntoWorld(world, pos, random);
	        if(useRuin)
	        	new PontusWorldRuinGenerator(size, getAdditionBlocks())
	        	.generate(world, random, pos);
	        for(int x1 = 0; x1 < size.getX(); x1++)
		        for(int z1 = 0; z1 < size.getZ(); z1++)
		        	if(world.getBlockState(pos.add(x1, -1, z1)).getBlock().isReplaceable(world, pos.add(x1, -1, z1)) && !world.isAirBlock(pos.add(x1, 0, z1)))
		        		for(int y1 = 1; world.getBlockState(pos.add(x1, -y1, z1)).getBlock().isReplaceable(world, pos.add(x1, -y1, z1)); y1++)
		        			world.setBlockState(pos.add(x1, -y1, z1), world.getBlockState(pos.add(x1, 0, z1)));
	        return true;
		}
		return false;
	}
	
	protected ArrayList<Block> getAdditionBlocks()
	{
		ArrayList<Block> blocks = new ArrayList<>();
		Block[] finalBlocks = HarshenUtils.listOf(
				HarshenBlocks.harshen_dimensional_wood_crate, 
				HarshenBlocks.pontus_dead_wood, 
				HarshenBlocks.harshen_dimensional_glass,
				HarshenBlocks.harshen_dimensional_stone,
				HarshenBlocks.pontus_chaotic_wood,
				HarshenBlocks.pontus_far_wood,
				HarshenBlocks.harshen_dimensional_dirt);
		if(addBlocks() != null)
			for(Block block : addBlocks())
				blocks.add(block);
			outLoop:
			for(Block block : finalBlocks)
			{
				if(removeBlocks() != null)
					for(Block blocksToRemove : removeBlocks())
						if(block == blocksToRemove)
							continue outLoop;
				blocks.add(block);
			}
		return blocks;
	}
	
	protected Block[] addBlocks()
	{
		return null;
	}
	
	protected Block[] removeBlocks()
	{
		return null;
	}
		
	public BlockPos getSize() {
		return size;
	}
	
	public BlockPos getOriginAddition() {
		return originAddition;
	}
	
	public ResourceLocation getLocation() {
		return location;
	}
}
