package kenijey.harshencastle.items;

import kenijey.harshencastle.base.BaseItemInventory;
import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import kenijey.harshencastle.handlers.inventory.HandlerFearring;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class Fearring extends BaseItemInventory
{
	public Fearring()
	{
		setUnlocalizedName("fearring");
		setRegistryName("fearring");
	}

	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.LEFT_EAR;
	}

	@Override
	public Object getProvider(ItemStack stack) {
		return new HandlerFearring();
	}

}