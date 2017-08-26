package kenijey.harshencastle.handlers;

import java.util.HashMap;

import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.interfaces.IHarshenProvider;
import kenijey.harshencastle.itemstackhandlers.HarshenItemStackHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class HandlerHarshenInventory 
{	
	HashMap<EntityPlayer, Integer> tickMap = new HashMap<>(HarshenUtils.HASH_LIMIT);
	
	@SubscribeEvent
	public void playerTick(PlayerTickEvent event)
	{
		if(event.player.world.isRemote)
			return;
		if(!tickMap.containsKey(event.player))
			tickMap.put(event.player, 0);
		tickMap.put(event.player, tickMap.get(event.player) + 1);
		HarshenItemStackHandler handler = HarshenUtils.getHandler(event.player);
		for(int slot = 0; slot < handler.getSlots(); slot++)
			if(handler.getStackInSlot(slot).getItem() instanceof IHarshenProvider)
				((IHarshenProvider)handler.getStackInSlot(slot).getItem()).onTick(event.player, tickMap.get(event.player));
	}
}
