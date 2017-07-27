package kenijey.harshencastle.fluids.blocks;

import java.util.ArrayList;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.base.BaseFluidBlock;
import kenijey.harshencastle.fluids.HarshenFluids;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HarshenDimensionalFluidBlock extends BaseFluidBlock
{
    public HarshenDimensionalFluidBlock()
    {
        super(HarshenFluids.harshen_dimensional_fluid);
    }
    
    @Override
    public boolean checkForMixing(World worldIn, BlockPos pos, IBlockState state) {
    	 for (EnumFacing enumfacing : EnumFacing.values())
    		 if(worldIn.getBlockState(pos.offset(enumfacing)).getBlock() instanceof BlockDirt ||
    	    			worldIn.getBlockState(pos.offset(enumfacing)).getBlock() instanceof BlockGrass ||
    	    			worldIn.getBlockState(pos.offset(enumfacing)).getBlock() instanceof BlockLeaves ||
    	    			(enumfacing != EnumFacing.DOWN && (
    	    					worldIn.getBlockState(pos.offset(enumfacing).add(0, -1, 0)).getBlock() instanceof BlockDirt ||
    	            			worldIn.getBlockState(pos.offset(enumfacing).add(0, -1, 0)).getBlock() instanceof BlockGrass ||
    	            			worldIn.getBlockState(pos.offset(enumfacing).add(0, -1, 0)).getBlock() instanceof BlockLeaves)))
    	    			
    	    		worldIn.setBlockState(pos.offset(enumfacing).add(0, -1, 0), HarshenBlocks.harshen_dimensional_dirt.getDefaultState(), 3);
    	return super.checkForMixing(worldIn, pos, state);
    }

	@Override
	protected ArrayList<PotionEffect> getPotions() {
		ArrayList<PotionEffect> effects = new ArrayList<PotionEffect>();
		effects.add(new PotionEffect(Potion.getPotionById(9), 250));
		effects.add(new PotionEffect(Potion.getPotionById(2), 250));
		return null;
	}

	@Override
	protected Block getBlockWhenSourceHit() {
		return HarshenBlocks.harshen_dimensional_dirt;
	}

	@Override
	protected Block getBlockWhenOtherHit() {
		return HarshenBlocks.harshen_dimensional_dirt;
	}

}