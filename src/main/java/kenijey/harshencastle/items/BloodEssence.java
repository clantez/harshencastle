package kenijey.harshencastle.items;

import java.util.List;

import kenijey.harshencastle.interfaces.IBloodSupply;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class BloodEssence extends Item implements IBloodSupply
{
	public BloodEssence()
	{
		setUnlocalizedName("blood_essence");
		setRegistryName("blood_essence");
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("\u00A74" + new TextComponentTranslation("bessence1").getFormattedText());
		//super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public int getAmountPerSecond() {
		return 3;
	}

	@Override
	public int ticksUntillUsed() {
		return 200;
	}

}
