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
	public int getAmountPerSecond() {
		return 3;
	}

	@Override
	public int ticksUntillUsed() {
		return 200;
	}

}
