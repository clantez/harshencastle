package kenijey.harshencastle.items;

import kenijey.harshencastle.base.BaseItemInventory;
import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import net.minecraft.entity.player.EntityPlayer;

public class BloodyEarring extends BaseItemInventory
{
	public BloodyEarring()
	{
		setUnlocalizedName("bloody_earring");
		setRegistryName("bloody_earring");
	}

	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.LEFT_EAR;
	}

	@Override
	public void onTick(EntityPlayer player, int tick) {
		if(tick % 140 == 0)
			player.heal(2f);
	}
}
