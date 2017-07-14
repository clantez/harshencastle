package kenijey.harshencastle;

import java.util.Random;

import kenijey.harshencastle.dimensions.DimensionPontus;
import kenijey.harshencastle.worldgenerators.pontus.PontusWorldGeneratorIniumOre;
import net.minecraft.block.BlockFlower;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGen implements IWorldGenerator
{
    private final WorldGenMinable soulore = new WorldGenMinable(HarshenBlocks.harshen_soul_ore.getDefaultState(), 3);
    private final WorldGenerator itiumOre = new PontusWorldGeneratorIniumOre();
    private final int chanceForNodeToSpawn;
	public WorldGen(int chanceForNodeToSpawn)
	{
		this.chanceForNodeToSpawn = chanceForNodeToSpawn;
	}
	
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		
		int dim = world.provider.getDimension();
		if(dim == 0)
		{
			oreGenerator(this.soulore, world, random, chunkX, chunkZ, 10, 0, 20);
	    	flowerGenerator(HarshenBlocks.harshen_soul_flower, world, random, chunkX, chunkZ, 15);
	    	flowerGenerator(HarshenBlocks.plant_of_gleam, world, random, chunkX, chunkZ, 15);
		}
		else if(dim == DimensionPontus.DIMENSION_ID)
		{
	    	oreGenerator(this.itiumOre, world, random, chunkX, chunkZ, 10, 0, 255);

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
