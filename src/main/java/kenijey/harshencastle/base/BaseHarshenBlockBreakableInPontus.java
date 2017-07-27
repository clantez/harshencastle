package kenijey.harshencastle.base;

import kenijey.harshencastle.dimensions.DimensionPontus;
import kenijey.harshencastle.items.SoulHarsherPickaxe;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BaseHarshenBlockBreakableInPontus extends BaseHarshenBlockCastle
{
	
	public BaseHarshenBlockBreakableInPontus() {
		super();
	}
	
	public BaseHarshenBlockBreakableInPontus(Material material)
	{
		super(material);
	}
	
	public BaseHarshenBlockBreakableInPontus(String harvestTool)
	{
		super(harvestTool);
	}
	
	public BaseHarshenBlockBreakableInPontus(Material material,String harvestTool)
	{
		super(material, harvestTool);
	}
	
	
	@Override
	public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		if((playerIn.getHeldItemMainhand().getItem() == Item.getItemFromBlock(Blocks.AIR)? playerIn.getHeldItemOffhand() : playerIn.getHeldItemMainhand()).getItem()
				instanceof SoulHarsherPickaxe && playerIn.dimension == DimensionPontus.DIMENSION_ID)
		{
			setHardness(1f);
			setResistance(1f);
		}
		else
		{
			setHardness(2400f);
			setResistance(2400f);
		}
	}
	
	@Override
	public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
	}

}
