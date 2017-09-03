package kenijey.harshencastle.items;

import kenijey.harshencastle.base.BaseItemInventory;
import kenijey.harshencastle.enums.inventory.EnumInventorySlots;

public class EnderPendant extends BaseItemInventory
{
	
	public EnderPendant() {
		setRegistryName("ender_pendant");
		setUnlocalizedName("ender_pendant");
	}
	
	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.NECK;
	}

}
