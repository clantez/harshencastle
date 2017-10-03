package kenijey.harshencastle.items;

import kenijey.harshencastle.base.BaseItemInventory;
import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import net.minecraft.entity.player.EntityPlayer;

public class FeedingEarring extends BaseItemInventory {
	
	public FeedingEarring() {
		setRegistryName("feeding_earring");
		setUnlocalizedName("feeding_earring");
		this.canRepair = false;
	}

	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.LEFT_EAR;
	}
	
	@Override
	public void onTick(EntityPlayer player, int tick) {
		if(tick%140==0)
			player.getFoodStats().addStats(1, 1.0F);
	}

}
