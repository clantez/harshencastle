package kenijey.harshencastle.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class Archive extends Block
{
	public Archive()
	{
		super(Material.WOOD);
		setRegistryName("archive");
		setUnlocalizedName("archive");
		setHardness(5.0F);
		setResistance(5.0F);
	}

	@Override
	 public boolean isOpaqueCube(IBlockState state)
	 {
	  return false;
	 }
	
	@Override
	 public boolean isFullCube(IBlockState state) 
	 {
	  return false;
	 }
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("\u00A76" + new TextComponentTranslation("archive1").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
}
