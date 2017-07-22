package kenijey.harshencastle.blocks;

import kenijey.harshencastle.base.BaseBlockHarshenSingleInventory;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class PedestalSlab extends BaseBlockHarshenSingleInventory
{
	public PedestalSlab() {
		super(Material.ROCK);
		setRegistryName("pedestal_slab");
		setUnlocalizedName("pedestal_slab");
		setHardness(5.0F);
		setResistance(5.0F);
	}

	@Override
	public TileEntity getTile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return new AxisAlignedBB(0, 0, 0, 1, 0.5, 1);
    }
}