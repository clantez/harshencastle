package kenijey.harshencastle.network;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.network.packets.MessagePacketOpenInv;
import kenijey.harshencastle.network.packets.MessagePacketPlayerHasAccess;
import kenijey.harshencastle.network.packets.MessagePacketTileEntityBloodPlacerUpdated;
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
		INSTANCE.registerMessage(MessagePacketPlayerHasAccess.class, MessagePacketPlayerHasAccess.class, 0, Side.CLIENT);
		INSTANCE.registerMessage(MessagePacketTileEntityBloodPlacerUpdated.class, MessagePacketTileEntityBloodPlacerUpdated.class, 1, Side.CLIENT);
		INSTANCE.registerMessage(MessagePacketOpenInv.class, MessagePacketOpenInv.class, 2, Side.SERVER);
	}
	public static void sendToServer(IMessage message)
	{
		INSTANCE.sendToServer(message);
	}
	
	public static void sendToPlayer(EntityPlayerMP player, IMessage message)
	{
		INSTANCE.sendTo(message, player);
	}
	
	public static void sendToAll(IMessage message)
	{
		INSTANCE.sendToAll(message);
	}
}
