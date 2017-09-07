package kenijey.harshencastle.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class HarshenMagicTable extends Block
{
	public HarshenMagicTable()
	{
		super(Material.WOOD);
		setRegistryName("harshen_magic_table");
		setUnlocalizedName("harshen_magic_table");
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
		tooltip.add("\u00A76" + new TextComponentTranslation("magic_table1").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

}
