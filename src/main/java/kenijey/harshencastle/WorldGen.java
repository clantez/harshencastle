package kenijey.harshencastle;

import java.util.Random;

import com.google.common.base.Predicate;

import kenijey.harshencastle.blocks.HarshenBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower.EnumFlowerType;
import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WorldGen implements IWorldGenerator
{
    private final WorldGenMinable ore = new WorldGenMinable(HarshenBlocks.harshen_soul_ore.getDefaultState(), 3);
    private final WorldGenMinable itiumOre = new WorldGenMinable(HarshenBlocks.itium_ore.getDefaultState(), 5);
    private final int chanceForNodeToSpawn;
	public WorldGen(int chanceForNodeToSpawn)
	{
		this.chanceForNodeToSpawn = chanceForNodeToSpawn;
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {

		switch (world.provider.getDimension()) {
	    case 0:
	    	oreGenerator(this.ore, world, random, chunkX, chunkZ, 10, 0, 20);
	    	flowerGenerator(world, random, chunkX, chunkZ, 5/**THE LOWER THE BETTER*/);
	        break;
	    default:
	    	break;
	    }
		
	}
	
	private void oreGenerator(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn, int minHeight, int maxHeight) 
	{
		int heightDiff = maxHeight - minHeight + 1;
	    for (int i = 0; i < chancesToSpawn; i ++) {
	        int x = chunk_X * 16 + rand.nextInt(16);
	        int y = minHeight + rand.nextInt(heightDiff);
	        int z = chunk_Z * 16 + rand.nextInt(16);
	        ore.generate(world, rand, new BlockPos(x, y, z));
	    }
	}
	
	
	
	
	
	private void flowerGenerator(World worldIn, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn)
	{
		for(int i = 0; i < chancesToSpawn; i++)
			if(rand.nextInt(400) == 0)
				return;
		int x = chunk_X * 16 + rand.nextInt(16);
		int z = chunk_Z * 16 + rand.nextInt(16);
		BlockPos position = worldIn.getTopSolidOrLiquidBlock(new BlockPos(x, 0, z));
		BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));

        if (worldIn.isAirBlock(blockpos) && (!worldIn.provider.isNether() || blockpos.getY() < 255) && HarshenBlocks.harshen_soul_flower.canBlockStay(worldIn, blockpos, HarshenBlocks.harshen_soul_flower.getDefaultState()))
        {
            worldIn.setBlockState(blockpos, HarshenBlocks.harshen_soul_flower.getDefaultState(), 2);
        }
	}
}
