package kenijey.harshencastle.network;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.network.packets.MessagePacketOpenInv;
import kenijey.harshencastle.network.packets.MessagePacketPlayerHasAccess;
import kenijey.harshencastle.network.packets.MessagePacketTileEntityBloodPlacerUpdated;
import kenijey.harshencastle.network.packets.MessageSendPlayerInv;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class HarshenNetwork 
{
	private static SimpleNetworkWrapper INSTANCE;
	
	public static void preInit()
	{
		INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(HarshenCastle.MODID);
		registerMessage(MessagePacketPlayerHasAccess.class, Side.CLIENT);
		registerMessage(MessagePacketTileEntityBloodPlacerUpdated.class, Side.CLIENT);
		registerMessage(MessagePacketOpenInv.class, Side.SERVER);
		registerMessage(MessageSendPlayerInv.class, Side.SERVER);
	}
	
	
	private static int idCount = -1;
    public static void registerMessage(Class claz, Side recievingSide)
    {
    	INSTANCE.registerMessage(claz, claz, idCount++, recievingSide);
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
