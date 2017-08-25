package kenijey.harshencastle.handlers;

import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.items.ItemStackHandler;

public class HandlerHarshenInventoryCommon 
{
		
	private ItemStackHandler handler = new ItemStackHandler(EnumInventorySlots.values().length);
	
	int effectTimedTimer = 0;
	
	@SubscribeEvent
	public void playerTick(PlayerTickEvent event)
	{
		if(event.player.world.isRemote)
			return;
		System.out.println(event.player.getEntityData());
	}
}
