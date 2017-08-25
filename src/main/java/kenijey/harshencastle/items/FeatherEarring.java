package kenijey.harshencastle.items;

import java.util.List;

import kenijey.harshencastle.base.BaseItemCustomInvEffect;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class FeatherEarring extends Item
{
	public FeatherEarring()
	{
		setUnlocalizedName("feather_earring");
		setRegistryName("feather_earring");
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("\u00A72" + new TextComponentTranslation("fearring1").getFormattedText());
		tooltip.add(" ");
		tooltip.add("\u00A73" + new TextComponentTranslation("fearring2").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

}
