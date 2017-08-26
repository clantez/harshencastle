package kenijey.harshencastle.items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class OneRing extends Item
{
	public OneRing()
	{
		setUnlocalizedName("one_ring");
		setRegistryName("one_ring");
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("\u00A75" + new TextComponentTranslation("accessoryitem").getFormattedText() + " \u00A77" + new TextComponentTranslation("ring").getFormattedText());
		tooltip.add(" ");
		tooltip.add("\u00A76" + new TextComponentTranslation("onering1").getFormattedText());
		tooltip.add("\u00a76" + new TextComponentTranslation("onering2").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}


}
