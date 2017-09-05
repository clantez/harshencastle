package kenijey.harshencastle.base;

import java.util.List;

import kenijey.harshencastle.interfaces.IHarshenProvider;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public abstract class BaseItemInventory extends Item implements IHarshenProvider
{
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("\u00A75" + new TextComponentTranslation("accessoryitem").getFormattedText() + " \u00A77" + new TextComponentTranslation(getSlot().getName()).getFormattedText());
		tooltip.add(" ");
		for(int i = 0; i < toolTipLines(); i ++)
			tooltip.add("\u00A73" + new TextComponentTranslation(getRegistryName().getResourcePath() + String.valueOf(i + 1)).getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	protected int toolTipLines()
	{
		return 2;
	}
}
