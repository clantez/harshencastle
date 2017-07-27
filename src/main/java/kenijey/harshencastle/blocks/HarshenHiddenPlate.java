package kenijey.harshencastle.blocks;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.base.BaseHarshenBlockCastle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HarshenHiddenPlate extends BaseHarshenBlockCastle
{

	
	public HarshenHiddenPlate() {
        setUnlocalizedName("harshen_dimensional_plate");
        setRegistryName("harshen_dimensional_plate");		
	}

	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		if(entityIn instanceof EntityPlayer)
			worldIn.setBlockState(pos, HarshenBlocks.harshen_hidden_plate_active.getDefaultState(), 3);
		super.onEntityWalk(worldIn, pos, entityIn);
	}
}
