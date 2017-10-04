package kenijey.harshencastle.network.packets;

import io.netty.buffer.ByteBuf;
import kenijey.harshencastle.base.BaseMessagePacket;
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
	public void onReceived(MessagePacketRequestInv message, EntityPlayer player) {
		HarshenNetwork.sendToPlayer(player, new MessageSendPlayerInvToClient(player));
	}

}

