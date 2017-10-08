package kenijey.harshencastle.items;

import kenijey.harshencastle.base.BaseItemInventory;
import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import net.minecraft.entity.player.EntityPlayer;

public class RingOfFlight extends BaseItemInventory
{
	
	public RingOfFlight() {
		setRegistryName("ring_of_flight");
		setUnlocalizedName("ring_of_flight");
	}
	
	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.RING1;
	}

}
