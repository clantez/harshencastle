package kenijey.harshencastle.blocks;

import java.util.ArrayList;
import java.util.Random;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.base.BaseHarshenBlockBreakableInPontus;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

public class HarshenDimensionalDirt extends BaseHarshenBlockBreakableInPontus
{
	public HarshenDimensionalDirt() 
	{
		super(Material.GRASS, "pickaxe");
        setUnlocalizedName("harshen_dimensional_dirt");
        setRegistryName("harshen_dimensional_dirt");
        blockSoundType = blockSoundType.GROUND;
        setTickRandomly(true);
    }
	ArrayList<Block> whiteListedBlocks = new ArrayList<Block>();
	
 	@Override
	public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random) {
 		whiteListedBlocks.clear();
 		whiteListedBlocks.add(HarshenBlocks.CROP_OF_GLEAM);
		if(worldIn.getBlockState(pos.add(0, 1, 0)).getBlock() instanceof BlockBush && 
				!whiteListedBlocks.contains(worldIn.getBlockState(pos.add(0, 1, 0)).getBlock()))
		{
			worldIn.setBlockState(pos.add(0, 1, 0), HarshenBlocks.HARSHEN_DESTROYED_PLANT.getDefaultState(), 2);
			if(worldIn.getBlockState(pos.add(0, 2, 0)).getBlock() instanceof BlockBush)
				worldIn.setBlockState(pos.add(0, 2, 0),Blocks.AIR.getDefaultState(), 2);
		}
	}
	
	@Override
	public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction,
			IPlantable plantable) {
		return true;
	}
	
}
