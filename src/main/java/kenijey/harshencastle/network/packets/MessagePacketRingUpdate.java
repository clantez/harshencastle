package kenijey.harshencastle.network.packets;

import io.netty.buffer.ByteBuf;
import kenijey.harshencastle.base.BaseMessagePacket;
import kenijey.harshencastle.handlers.HandlerHarshenInventoryEffects;
import net.minecraft.entity.player.EntityPlayer;

public class MessagePacketRingUpdate extends BaseMessagePacket<MessagePacketRingUpdate>{

	public MessagePacketRingUpdate() {
	}
	
	private int ringType;
	
	public MessagePacketRingUpdate(int ringType)
	{
		this.ringType = ringType;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {		
		ringType = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {		
		buf.writeInt(ringType);
	}

	@Override
	public void onReceived(MessagePacketRingUpdate message, EntityPlayer player) {
		HandlerHarshenInventoryEffects.ringEvent(player, message.ringType);
	}
}
