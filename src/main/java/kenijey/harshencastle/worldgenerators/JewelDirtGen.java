package kenijey.harshencastle.worldgenerators;

import java.util.ArrayList;
import java.util.Random;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.blocks.JewelDirt;
import kenijey.harshencastle.dimensions.DimensionPontus;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class JewelDirtGen extends WorldGenerator
{
	
    public boolean generate(World worldIn, Random rand, BlockPos position)
    {
    	BlockPos positionToGen = null;
    	if(worldIn.provider.getDimension() == 0)
    	{
    		BlockPos pos = position.down(rand.nextInt(3) + 1);
			if(worldIn.getBlockState(pos).getBlock() == Blocks.DIRT)
				positionToGen = pos;
    	}
    	else if(worldIn.provider.getDimension() == DimensionPontus.DIMENSION_ID && HarshenUtils.getTopBlock(worldIn, position).getY() - 10 > 0)
    		if(worldIn.isAirBlock(position))
    			positionToGen = new BlockPos(position.getX(), rand.nextInt() + 5, position.getZ());
    		else
    			positionToGen = position;
    	else
    		return false;
    	if(positionToGen == null)
    		return false;
    	ArrayList<Integer> dimensionList = new ArrayList<>();
    	dimensionList.add(0);
    	dimensionList.add(DimensionPontus.DIMENSION_ID);
    	worldIn.setBlockState(positionToGen, HarshenBlocks.jewel_dirt.getDefaultState().withProperty(JewelDirt.DIRT_TYPE, dimensionList.indexOf(worldIn.provider.getDimension())), 3);
    	return true;
    }
}
