package kenijey.harshencastle.network.packets;

import io.netty.buffer.ByteBuf;
import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.base.BaseMessagePacket;
import kenijey.harshencastle.inventory.GuiHandler;
import kenijey.harshencastle.network.HarshenNetwork;
import net.minecraft.entity.player.EntityPlayer;

public class MessagePacketRequestInv extends BaseMessagePacket<MessagePacketRequestInv>{

	@Override
	public void fromBytes(ByteBuf buf) {		
		
	}

	@Override
	public void toBytes(ByteBuf buf) {		
		
	}

	@Override
	public void handleServerSide(MessagePacketRequestInv message, EntityPlayer player) {
		HarshenNetwork.sendToPlayer(player, new MessageSendPlayerInvToClient(player));
	}

	@Override
	public void handleClientSide(MessagePacketRequestInv message, EntityPlayer player) {
		
	}

}

