package kenijey.harshencastle.items;

import kenijey.harshencastle.base.BaseItemInventory;
import kenijey.harshencastle.enums.inventory.EnumInventorySlots;

public class PunchyRing extends BaseItemInventory
{
	public PunchyRing()
	{
		setUnlocalizedName("punchy_ring");
		setRegistryName("punchy_ring");
	}
	
	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.RING1;
	}

}
