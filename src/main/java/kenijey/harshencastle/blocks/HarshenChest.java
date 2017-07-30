package kenijey.harshencastle.blocks;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.base.BaseBlockHarshenStackedInventory;
import kenijey.harshencastle.base.BaseTileEntityHarshenStackedInventory;
import kenijey.harshencastle.gui.GuiHarshenChest;
import kenijey.harshencastle.tileentity.TileEntityHarshenChest;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.scoreboard.IScoreCriteria.EnumRenderType;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;

public class HarshenChest extends BaseBlockHarshenStackedInventory
{
	public HarshenChest() {
		super(Material.WOOD);
		setRegistryName("harshen_chest");
		setUnlocalizedName("harshen_chest");
	}

	@Override
	public BaseTileEntityHarshenStackedInventory getTile() {
		return new TileEntityHarshenChest();
	}
}
