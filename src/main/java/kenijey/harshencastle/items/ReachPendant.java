package kenijey.harshencastle.items;

import kenijey.harshencastle.api.EnumInventorySlots;
import kenijey.harshencastle.api.IHarshenProvider;
import net.minecraft.item.Item;

public class ReachPendant extends Item implements IHarshenProvider
{

	public ReachPendant() {
		setRegistryName("reach_pendant");
		setUnlocalizedName("reach_pendant");
	}
	
	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.NECK;
	}

}
