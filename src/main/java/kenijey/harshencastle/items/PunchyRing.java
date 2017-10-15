package kenijey.harshencastle.items;

import kenijey.harshencastle.api.EnumInventorySlots;
import kenijey.harshencastle.api.HarshenEvent;
import kenijey.harshencastle.api.IHarshenProvider;
import kenijey.harshencastle.objecthandlers.PlayerPunchedEvent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PunchyRing extends Item implements IHarshenProvider
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
