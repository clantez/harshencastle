package kenijey.harshencastle.blocks;

import kenijey.harshencastle.base.BaseBlockHarshenSingleInventory;
import kenijey.harshencastle.base.BaseTileEntityHarshenSingleItemInventory;
import kenijey.harshencastle.tileentity.TileEntityHarshenDisplayBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;

public class HarshenDisplayBlock extends BaseBlockHarshenSingleInventory
{

	public HarshenDisplayBlock() {
		super(Material.ROCK);
		setRegistryName("harshen_display_block");
		setUnlocalizedName("harshen_display_block");
	}

	@Override
	public BaseTileEntityHarshenSingleItemInventory getTile() {
		return new TileEntityHarshenDisplayBlock();
	}
}
