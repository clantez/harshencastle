package kenijey.harshencastle.items;

import kenijey.harshencastle.base.BaseItemInventory;
import kenijey.harshencastle.enums.inventory.EnumInventorySlots;

public class LootingEarring extends BaseItemInventory
{

	public LootingEarring() {
		setRegistryName("looting_earring");
		setUnlocalizedName("looting_earring");
	}
	
	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.LEFT_EAR;
	}

}
