package kenijey.harshencastle.handlers.server;

import kenijey.harshencastle.config.AccessoryConfig;
import kenijey.harshencastle.network.HarshenNetwork;
import kenijey.harshencastle.network.packets.MessagePacketSendStartupData;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class HandlerSyncConfig 
{	
	@SubscribeEvent
	public void onPlayerJoin(PlayerLoggedInEvent event)
	{
		syncConfigWithClient((EntityPlayerMP) event.player);
	}
	
	public static void syncConfigWithClient(EntityPlayerMP player)
	{
		NBTTagCompound compound = new NBTTagCompound();
		compound.setDouble("reach_distance", AccessoryConfig.reachPendantLength);
		HarshenNetwork.sendToPlayer(player, new MessagePacketSendStartupData(compound));
	}
}
