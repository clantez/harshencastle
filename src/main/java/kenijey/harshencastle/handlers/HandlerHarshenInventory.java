package kenijey.harshencastle.handlers;

import java.util.ArrayList;
import java.util.HashMap;

import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.interfaces.IHarshenProvider;
import kenijey.harshencastle.network.HarshenNetwork;
import kenijey.harshencastle.network.packets.MessageSendPlayerInvToClient;
import kenijey.harshencastle.objecthandlers.HarshenItemStackHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class HandlerHarshenInventory 
{	
	HashMap<EntityPlayer, Integer> tickMap = new HashMap<>();
	private static ArrayList<ItemStack> prevInv = new ArrayList<>();
	
	@SubscribeEvent
	public void playerTick(PlayerTickEvent event)
	{
		if(event.player.world.isRemote)
			return;
		if(!tickMap.containsKey(event.player))
			tickMap.put(event.player, 0);
		tickMap.put(event.player, tickMap.get(event.player) + 1);
		HarshenItemStackHandler handler = HarshenUtils.getHandler(event.player);
		if(prevInv.size() != 0)
			for(int slot = 0; slot < handler.getSlots(); slot++)
				if(!prevInv.get(slot).isItemEqual(handler.getStackInSlot(slot)) && handler.getStackInSlot(slot).getItem() instanceof IHarshenProvider)
					((IHarshenProvider)handler.getStackInSlot(slot).getItem()).onAdd(event.player);
				else if(!prevInv.get(slot).isEmpty() && handler.getStackInSlot(slot).isEmpty() && prevInv.get(slot).getItem() instanceof IHarshenProvider)
					((IHarshenProvider)prevInv.get(slot).getItem()).onRemove(event.player);
		prevInv.clear();
		for(int slot = 0; slot < handler.getSlots(); slot++)
		{
			if(handler.getStackInSlot(slot).getItem() instanceof IHarshenProvider)
				((IHarshenProvider)handler.getStackInSlot(slot).getItem()).onTick(event.player, tickMap.get(event.player));
			prevInv.add(handler.getStackInSlot(slot));
		}	
	}
	
	@SubscribeEvent
	public void onPlayerJoin(PlayerLoggedInEvent event)
	{
		if(!event.player.world.isRemote)
			HarshenNetwork.sendToPlayer((EntityPlayerMP) event.player, new MessageSendPlayerInvToClient(event.player));
	}
}
