package kenijey.harshencastle.items;

import kenijey.harshencastle.base.BaseItemInventory;
import kenijey.harshencastle.enums.inventory.EnumInventorySlots;

public class FieryRing extends BaseItemInventory {

	public FieryRing() {
		setUnlocalizedName("fiery_ring");
		setRegistryName("fiery_ring");
	}
	
	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.RING1;
	}

}
