package kenijey.harshencastle.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HarshenHiddenPlate extends Block
{

	
	public HarshenHiddenPlate() {
		super(Material.ROCK);
        setUnlocalizedName("harshen_dimensional_plate");
        setRegistryName("harshen_dimensional_plate");
        setHarvestLevel("pickaxe", 3);
        setHardness(2500.0f);
        setResistance(2500.0f);
		
	}

	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		if(entityIn instanceof EntityPlayer)
			worldIn.setBlockState(pos, HarshenBlocks.harshen_hidden_plate_active.getDefaultState(), 3);
		super.onEntityWalk(worldIn, pos, entityIn);
	}
}
