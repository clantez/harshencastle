package kenijey.harshencastle.items;

import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.base.BaseItemInventory;
import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import kenijey.harshencastle.interfaces.HarshenEvent;
import kenijey.harshencastle.objecthandlers.PlayerPunchedEvent;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class PunchyRing extends BaseItemInventory
{
	public PunchyRing()
	{
		setUnlocalizedName("punchy_ring");
		setRegistryName("punchy_ring");
	}
	
	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.RING1;
	}
	
	@Override
	public Object getProvider(ItemStack stack) {
		return new HandlerPunchyRing();
	}
	
	public class HandlerPunchyRing
	{
		@HarshenEvent
		public void onPlayerPunch(PlayerPunchedEvent event)
		{
			if(event.attacker.getHeldItemMainhand().isEmpty())
				event.setAmount(event.getAmount() + 2);
		}
	}

}
