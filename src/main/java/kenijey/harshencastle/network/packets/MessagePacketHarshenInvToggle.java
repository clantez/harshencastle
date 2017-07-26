package kenijey.harshencastle.network.packets;

import io.netty.buffer.ByteBuf;
import kenijey.harshencastle.base.BaseMessagePacket;
import kenijey.harshencastle.network.events.NetworkEventHarshenInvToggle;
import net.minecraft.entity.player.EntityPlayer;

public class MessagePacketHarshenInvToggle extends BaseMessagePacket<MessagePacketHarshenInvToggle>{

	@Override
	public void fromBytes(ByteBuf buf) {		
		
	}

	@Override
	public void toBytes(ByteBuf buf) {		
		
	}

	@Override
	public void handleServerSide(MessagePacketHarshenInvToggle message, EntityPlayer player) {	
		NetworkEventHarshenInvToggle.go(player);
	}

	@Override
	public void handleClientSide(MessagePacketHarshenInvToggle message, EntityPlayer player) {
		
	}

}
