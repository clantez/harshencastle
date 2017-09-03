package kenijey.harshencastle.items;

import kenijey.harshencastle.base.BaseItemInventory;
import kenijey.harshencastle.enums.inventory.EnumInventorySlots;

public class SoulShield extends BaseItemInventory {

	public SoulShield() {
		setRegistryName("soul_shield");
		setUnlocalizedName("soul_shield");
		setMaxDamage(500);
	}
	
	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.NECK;
	}

}
