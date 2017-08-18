package kenijey.harshencastle.network;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.network.packets.MessagePacketHarshenInvToggle;
import kenijey.harshencastle.network.packets.MessagePacketPlayerHasAccess;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class HarshenNetwork 
{
	private static SimpleNetworkWrapper INSTANCE;
	
	public static void preInit()
	{
		INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(HarshenCastle.MODID);
		INSTANCE.registerMessage(MessagePacketHarshenInvToggle.class, MessagePacketHarshenInvToggle.class, 0, Side.SERVER);
		INSTANCE.registerMessage(MessagePacketPlayerHasAccess.class, MessagePacketPlayerHasAccess.class, 1, Side.CLIENT);
	}
	
	public static void sendToServer(IMessage message)
	{
		INSTANCE.sendToServer(message);
	}
	
	public static void sendToPlayer(EntityPlayerMP player, IMessage message)
	{
		INSTANCE.sendTo(message, player);
	}
}
