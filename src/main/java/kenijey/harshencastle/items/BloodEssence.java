package kenijey.harshencastle.items;

import kenijey.harshencastle.interfaces.IBloodSupply;
import net.minecraft.item.Item;

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
