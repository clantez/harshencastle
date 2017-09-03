package kenijey.harshencastle.items;

import kenijey.harshencastle.base.BaseItemInventory;
import kenijey.harshencastle.enums.inventory.EnumInventorySlots;

public class MineRing extends BaseItemInventory
{
	public MineRing()
	{
		setUnlocalizedName("minering");
		setRegistryName("minering");
		setMaxDamage(400);
	}
	
	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.RING1;
	}
}
