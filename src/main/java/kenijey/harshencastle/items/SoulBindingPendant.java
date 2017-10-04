package kenijey.harshencastle.items;

import kenijey.harshencastle.base.BaseItemInventory;
import kenijey.harshencastle.enums.inventory.EnumInventorySlots;

public class SoulBindingPendant extends BaseItemInventory
{

	public SoulBindingPendant() {
		setRegistryName("soul_binding_pendant");
		setUnlocalizedName("soul_binding_pendant");
		setMaxDamage(5);
	}
	
	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.NECK;
	}

}
