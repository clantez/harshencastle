package kenijey.harshencastle.items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class LightningStaff extends Item
{
	public LightningStaff()
	{
		setUnlocalizedName("lightning_staff");
		setRegistryName("lightning_staff");
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("\u00A7b" + new TextComponentTranslation("lightning_staff1").getFormattedText());
		tooltip.add("\u00A7b" + new TextComponentTranslation("lightning_staff2").getFormattedText());
		tooltip.add(new TextComponentTranslation(" ").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
}
