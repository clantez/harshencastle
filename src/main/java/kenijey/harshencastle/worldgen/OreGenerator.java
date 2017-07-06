package kenijey.harshencastle.worldgen;

import java.util.Random;

import com.google.common.base.Predicate;

import kenijey.harshencastle.blocks.HarshenBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OreGenerator implements IWorldGenerator
{
    private final WorldGenMinable ore = new WorldGenMinable(HarshenBlocks.harshen_soul_ore.getDefaultState(), 5);
    private final int chanceForNodeToSpawn;
	public OreGenerator(int chanceForNodeToSpawn)
	{
		this.chanceForNodeToSpawn = chanceForNodeToSpawn;
	}

    private void addOreSpawn(World world, Random random, BlockPos pos)
    {
        for(int x = 0; x < chanceForNodeToSpawn; x++)
        {
        	ore.generate(world, random, pos.add(random.nextInt(16), 1 + random.nextInt(13), random.nextInt(16)));
        }
    }

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		
		switch (world.provider.getDimensionType()) {
	    case OVERWORLD:
	        break;
	    default:
	    	break;
	    }
		
	}
}
