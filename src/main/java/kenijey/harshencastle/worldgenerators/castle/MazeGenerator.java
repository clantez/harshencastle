package kenijey.harshencastle.worldgenerators.castle;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class MazeGenerator extends WorldGenerator
{
	private final BlockPos size;
	private final float chanceForBlock;
	private final IBlockState state;
	
	public MazeGenerator(BlockPos size, IBlockState state, float chanceForBlock) {
		this.chanceForBlock = chanceForBlock;
		this.state = state;
		this.size = size; 
	}
	
	
	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		for(int x = 0; x < size.getX(); x++)
			for(int z = 0; z < size.getZ(); z++)
				if(rand.nextFloat() < this.chanceForBlock)
					for(int y = 0; y < size.getY(); y++)
						worldIn.setBlockState(position.add(x, y, z), this.state, 3);
				else
					for(int y = 0; y < size.getY(); y++)
						worldIn.setBlockToAir(position.add(x, y, z));
		return false;
	}

}
