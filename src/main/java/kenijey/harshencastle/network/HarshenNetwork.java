package kenijey.harshencastle.network;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.network.packets.MessagePacketForceCauldronUpdate;
import kenijey.harshencastle.network.packets.MessagePacketHitWithRange;
import kenijey.harshencastle.network.packets.MessagePacketKillAllWithTag;
import kenijey.harshencastle.network.packets.MessagePacketOpenInv;
import kenijey.harshencastle.network.packets.MessagePacketPlayerHasAccess;
import kenijey.harshencastle.network.packets.MessagePacketPlayerTeleportEffects;
import kenijey.harshencastle.network.packets.MessagePacketRequestInv;
import kenijey.harshencastle.network.packets.MessagePacketReviveInventory;
import kenijey.harshencastle.network.packets.MessagePacketRingUpdate;
import kenijey.harshencastle.network.packets.MessagePacketSetItemInSlot;
import kenijey.harshencastle.network.packets.MessagePacketSpawnBloodParticle;
import kenijey.harshencastle.network.packets.MessagePacketSpawnItemParticles;
import kenijey.harshencastle.network.packets.MessagePacketSummonFirework;
import kenijey.harshencastle.network.packets.MessagePacketTileEntityBloodPlacerUpdated;
import kenijey.harshencastle.network.packets.MessagePacketUpdateCauldron;
import kenijey.harshencastle.network.packets.MessagePacketUpdateComplexEntity;
import kenijey.harshencastle.network.packets.MessagePacketUpdateXrayBlock;
import kenijey.harshencastle.network.packets.MessageSendPlayerInvToClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
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
		registerMessage(MessagePacketPlayerHasAccess.class, Side.CLIENT);
		registerMessage(MessagePacketTileEntityBloodPlacerUpdated.class, Side.CLIENT);
		registerMessage(MessagePacketOpenInv.class, Side.SERVER);
		registerMessage(MessageSendPlayerInvToClient.class, Side.CLIENT);
		registerMessage(MessagePacketPlayerTeleportEffects.class, Side.CLIENT);
		registerMessage(MessagePacketRingUpdate.class, Side.SERVER);
		registerMessage(MessagePacketSetItemInSlot.class, Side.CLIENT);
		registerMessage(MessagePacketUpdateXrayBlock.class, Side.SERVER);
		registerMessage(MessagePacketSummonFirework.class, Side.SERVER);
		registerMessage(MessagePacketRequestInv.class, Side.SERVER);
		registerMessage(MessagePacketSpawnItemParticles.class, Side.CLIENT);
		registerMessage(MessagePacketSpawnBloodParticle.class, Side.CLIENT);
		registerMessage(MessagePacketKillAllWithTag.class, Side.CLIENT);
		registerMessage(MessagePacketHitWithRange.class, Side.SERVER);
		registerMessage(MessagePacketReviveInventory.class, Side.CLIENT);
		registerMessage(MessagePacketUpdateComplexEntity.class, Side.CLIENT);
		registerMessage(MessagePacketUpdateCauldron.class, Side.CLIENT);
		registerMessage(MessagePacketForceCauldronUpdate.class, Side.CLIENT);
		registerMessage(MessagePacketForceCauldronUpdate.class, Side.SERVER);
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
	
	public static void sendToPlayer(EntityPlayer player, IMessage message)
	{
		INSTANCE.sendTo(message, (EntityPlayerMP) player);
	}
	
	public static void sendToPlayersInWorld(World world, IMessage message)
	{
		if(world == null)
			sendToAll(message);
		else if(!world.isRemote)
			INSTANCE.sendToDimension(message, world.provider.getDimension());
	}
	
	public static void sendToAll(IMessage message)
	{
		INSTANCE.sendToAll(message);
	}
}
