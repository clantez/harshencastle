package kenijey.harshencastle.handlers;

import java.util.HashMap;
import java.util.UUID;

import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.interfaces.IHarshenProvider;
import kenijey.harshencastle.objecthandlers.HarshenItemStackHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;

public class HandlerPlayerInventoryOverDeath 
{
	private static HashMap<UUID, HarshenItemStackHandler> stackMap = new HashMap<>(HarshenUtils.HASH_LIMIT);
	
	@SubscribeEvent
	public void onPlayerDeath(LivingDeathEvent event)
	{
		if(event.getEntityLiving() instanceof EntityPlayer)
		{
			HarshenItemStackHandler handler = HarshenUtils.getHandler(event.getEntity().getEntityData());
			stackMap.put(event.getEntity().getUniqueID(), handler);
		}
	}
	
	@SubscribeEvent
	public void onPlayerRespawn(PlayerRespawnEvent event)
	{
		if(stackMap.containsKey(event.player.getUniqueID()))
		{
			event.player.getEntityData().setTag("harshenInventory", stackMap.get(event.player.getUniqueID()).serializeNBT());
			for(int i = 0; i < stackMap.get(event.player.getUniqueID()).getSlots(); i++)
				if(stackMap.get(event.player.getUniqueID()).getStackInSlot(i).getItem() instanceof IHarshenProvider)
					((IHarshenProvider)stackMap.get(event.player.getUniqueID()).getStackInSlot(i).getItem()).onAdd(event.player);

		}
	}
}
