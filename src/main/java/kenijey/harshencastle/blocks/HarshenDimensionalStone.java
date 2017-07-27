package kenijey.harshencastle.blocks;

import kenijey.harshencastle.base.BaseHarshenBlockCastle;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.IPlantable;

public class HarshenDimensionalStone extends BaseHarshenBlockCastle 
{
	public HarshenDimensionalStone() 
	{
        setUnlocalizedName("harshen_dimensional_stone");
        setRegistryName("harshen_dimensional_stone");
    }
	
	@Override
	public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction,
			IPlantable plantable) {
		return false;
	}
}
