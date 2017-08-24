package kenijey.harshencastle.blocks;

import kenijey.harshencastle.base.BaseBlockHarshenSingleInventory;
import kenijey.harshencastle.base.BaseTileEntityHarshenSingleItemInventory;
import kenijey.harshencastle.tileentity.TileEntityBloodFactory;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BloodFactory extends BaseBlockHarshenSingleInventory
{

	public BloodFactory() {
		super(Material.ROCK);
		setRegistryName("blood_factory");
		setUnlocalizedName("blood_factory");
	}

	@Override
	public BaseTileEntityHarshenSingleItemInventory getTile() {
		return new TileEntityBloodFactory();
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		if(worldIn.getBlockState(pos.down()).getBlock() instanceof BloodVessel)
			((BloodVessel)worldIn.getBlockState(pos.down()).getBlock()).checkForUpdate(worldIn, pos.down(), worldIn.getBlockState(pos.down()));
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
	}
}
