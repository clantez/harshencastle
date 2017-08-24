package kenijey.harshencastle;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import kenijey.harshencastle.dimensions.DimensionPontus;
import kenijey.harshencastle.worldgenerators.castle.ChestGenerator;
import kenijey.harshencastle.worldgenerators.castle.MazeGenerator;
import kenijey.harshencastle.worldgenerators.pontus.PontusWorldGeneratorIniumOre;
import kenijey.harshencastle.worldgenerators.pontus.PontusWorldRuinGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGen implements IWorldGenerator
{
    private final WorldGenMinable soulore = new WorldGenMinable(HarshenBlocks.harshen_soul_ore.getDefaultState(), 3);
    private final WorldGenerator itiumOre = new PontusWorldGeneratorIniumOre();
	
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		
		int dim = world.provider.getDimension();
		if(dim == 0)
		{
			if(random.nextFloat() < 0.001f)
			{
				BlockPos position = getTopBlock(world, new BlockPos(chunkX * 16, 1, chunkZ * 16).add(random.nextInt(16), 0, random.nextInt(16))).add(-3, -1, -3);
				loadStructure(world, "shrine", position);
				position = position.add(3, 1, 3);
				world.setBlockState(position, Blocks.CHEST.getDefaultState().withProperty(BlockChest.FACING, EnumFacing.HORIZONTALS[random.nextInt(4)]), 3);
				if(world instanceof WorldServer && world.getTileEntity(position) != null)
				{
					List<ItemStack> list = Lists.<ItemStack>newArrayList();
					LootContext context = new LootContext(1f, (WorldServer) world, world.getLootTableManager(), null, HarshenUtils.getClosestPlayer(world, position), DamageSource.MAGIC);
					world.getLootTableManager().getLootTableFromLocation(HarshenLootTables.shrine).getPool("extras").generateLoot(list, new Random(), context);
					TileEntityChest chest = (TileEntityChest)world.getTileEntity(position);
					chest.setInventorySlotContents(13, world.getLootTableManager().getLootTableFromLocation(HarshenLootTables.shrine).generateLootForPools(new Random(), context).get(0));
					for(ItemStack stack : list)
						for(int count = 0; count < stack.getCount(); count++)
						{
							int slot = new Random().nextInt(27);
							while(chest.getStackInSlot(slot).getItem() != Item.getItemFromBlock(Blocks.AIR))
								slot = new Random().nextInt(27);
							ItemStack stack1 = stack.copy();
							stack1.setCount(1);
							chest.setInventorySlotContents(slot, stack1);
						}
				}
			}
			if(chunkX == 44 && chunkZ == 44)
			{
				BlockPos[] positionsOfFillableChests = {new BlockPos(9, 20, 36), new BlockPos(15, 20, 40), new BlockPos(15, 20, 40), new BlockPos(19, 20, 31)};
				BlockPos position = getTopBlock(world, new BlockPos(chunkX * 16, 1, chunkZ * 16)).add(-36, -20, 1);
				BlockPos castleSize = getSizeFromName(world, "harshencastlevol4");
				for(int i = 0; i < 4; i++)
					new MazeGenerator(new BlockPos(castleSize.getX(), 3, castleSize.getZ()), HarshenBlocks.harshen_dimensional_stone.getDefaultState(), 0.35f).generate(world, random, position.add(1, 1 + (i * 4), 2));
				loadStructure(world, "harshencastlevol4", position);
				for(int i = 0; i < 3; i++)
					new ChestGenerator(castleSize, 0.015f, HarshenLootTables.harshen_castle, true).generate(world, random, position.add(1, 1 + (i * 4), 2));
				for(BlockPos pos : positionsOfFillableChests)
					new ChestGenerator(BlockPos.ORIGIN, 1f, HarshenLootTables.harshen_castle, false).generate(world, random, position.add(pos));
			}
			oreGenerator(this.soulore, world, random, chunkX, chunkZ, 10, 0, 20);
	    	flowerGenerator(HarshenBlocks.harshen_soul_flower, world, random, chunkX, chunkZ, 15);
	    	flowerGenerator(HarshenBlocks.plant_of_gleam, world, random, chunkX, chunkZ, 15);
		}
		else if(dim == DimensionPontus.DIMENSION_ID)
		{
	    	oreGenerator(this.itiumOre, world, random, chunkX, chunkZ, 10, 0, 255);
	    	structureGenerator(world, random, chunkX, chunkZ, 5, "pontus/struc1", true, new BlockPos(26, 22, 26), new BlockPos(-8, 0, -12));
		}
	}
	
	private void oreGenerator(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn, int minHeight, int maxHeight) 
	{
		int heightDiff = maxHeight - minHeight + 1;
	    for (int i = 0; i < chancesToSpawn; i ++) {
	        int x = chunk_X * 16 + rand.nextInt(16);
	        int y = minHeight + rand.nextInt(heightDiff);
	        int z = chunk_Z * 16 + rand.nextInt(16);
	        generator.generate(world, rand, new BlockPos(x, y, z));
	    }
	}
	
	
	private void structureGenerator(World world, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn, String names, boolean useRuin, BlockPos size, BlockPos... addPositions)
	{
		if( rand.nextInt(100000) < chancesToSpawn) {
	        int x = chunk_X * 16 + rand.nextInt(16);
	        int z = chunk_Z * 16 + rand.nextInt(16);
	        int y = world.getTopSolidOrLiquidBlock(new BlockPos(x, 0, z)).getY();
	        int i = rand.nextInt(names.split(",").length);
	        loadStructure(world, names.split(",")[i], new BlockPos(x, y, z).add(addPositions[i]));
	        if(useRuin)
	        	new PontusWorldRuinGenerator(size, HarshenBlocks.harshen_dimensional_wood_crate, HarshenBlocks.pontus_dead_wood, HarshenBlocks.harshen_dimensional_glass)
	        	.generate(world, rand, new BlockPos(x, y, z).add(addPositions[i]));

		}
	}
	
	private void loadStructure(World world, String name, BlockPos pos)
	{
		((WorldServer)world).getStructureTemplateManager().get(world.getMinecraftServer(), new ResourceLocation(HarshenCastle.MODID, name))
		.addBlocksToWorld(world, pos, new PlacementSettings().setIgnoreEntities(false).setIgnoreStructureBlock(true));
	}
	
	private BlockPos getSizeFromName(World world, String name)
	{
		return ((WorldServer)world).getStructureTemplateManager().get(world.getMinecraftServer(), new ResourceLocation(HarshenCastle.MODID, name)).getSize();
	}
	
	private BlockPos getTopBlock(World world, BlockPos pos)
	{
		Chunk chunk = world.getChunkFromBlockCoords(pos);
        BlockPos blockpos;
        BlockPos blockpos1;

        for (blockpos = new BlockPos(pos.getX(), chunk.getTopFilledSegment() + 16, pos.getZ()); blockpos.getY() >= 0; blockpos = blockpos1)
        {
            blockpos1 = blockpos.down();
            IBlockState state = chunk.getBlockState(blockpos1);
            if ((state.getMaterial().blocksMovement() && !state.getBlock().isLeaves(state, world, blockpos1) && !state.getBlock().isFoliage(world, blockpos1))
            		|| state.getBlock() instanceof BlockLiquid)
            {
                break;
            }
        }

        return blockpos;
			
	}
	
	private void flowerGenerator(BlockFlower flower, World worldIn, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn)
	{
		if(chancesToSpawn > 100)
			chancesToSpawn=100;
		for(int i = 0; i < chancesToSpawn; i++)
			if(rand.nextInt(100) == 0)
			{
		int x = chunk_X * 16 + rand.nextInt(16);
		int z = chunk_Z * 16 + rand.nextInt(16);
		BlockPos position = worldIn.getTopSolidOrLiquidBlock(new BlockPos(x, 0, z));
		BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

        if (worldIn.isAirBlock(blockpos) && (worldIn.provider.isSurfaceWorld() || blockpos.getY() < 255) && 
        		flower.canBlockStay(worldIn, blockpos, flower.getDefaultState()))
        {
            worldIn.setBlockState(blockpos,flower.getDefaultState(), 2);
        }
			}

	}
	
}
