package kenijey.harshencastle.items;

import kenijey.harshencastle.base.BaseItemInventory;
import kenijey.harshencastle.enums.inventory.EnumInventorySlots;

public class CombatPendant extends BaseItemInventory
{
	
	public CombatPendant() {
		setRegistryName("combat_pendant");
		setUnlocalizedName("combat_pendant");
	}

	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.NECK;
	}
	
}
