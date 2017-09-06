package kenijey.harshencastle.items;

import kenijey.harshencastle.base.BaseItemInventory;
import kenijey.harshencastle.enums.inventory.EnumInventorySlots;

public class ElytraPendant extends BaseItemInventory {

	public ElytraPendant() {
		setRegistryName("elytra_pendant");
		setUnlocalizedName("elytra_pendant");
		setMaxDamage(5000);
	}
	
	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.NECK;
	}

}
