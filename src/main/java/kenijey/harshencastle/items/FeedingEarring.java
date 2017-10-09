package kenijey.harshencastle.items;

import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import kenijey.harshencastle.interfaces.IHarshenProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

public class FeedingEarring extends Item implements IHarshenProvider
{
	
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
		if(player.getFoodStats().getFoodLevel() < 20)
			player.getFoodStats().setFoodLevel(20);
	}

}
