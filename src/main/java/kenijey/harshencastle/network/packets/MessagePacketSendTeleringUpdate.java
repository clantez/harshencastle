package kenijey.harshencastle.network.packets;

import io.netty.buffer.ByteBuf;
import kenijey.harshencastle.base.BaseMessagePacket;
import kenijey.harshencastle.handlers.HandlerHarshenInventoryEffects;
import kenijey.harshencastle.handlers.HandlerPontusAllowed;
import net.minecraft.entity.player.EntityPlayer;

public class MessagePacketSendTeleringUpdate extends BaseMessagePacket<MessagePacketSendTeleringUpdate>{

	public MessagePacketSendTeleringUpdate() {
	}
	
	private boolean updateFlag;
	
	public MessagePacketSendTeleringUpdate(boolean updateTo)
	{
		updateFlag = updateTo;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {		
		updateFlag = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf) {		
		buf.writeBoolean(updateFlag);
	}

	@Override
	public void handleServerSide(MessagePacketSendTeleringUpdate message, EntityPlayer player) {
		HandlerHarshenInventoryEffects.keyTeleringDown = message.updateFlag;
	}

	@Override
	public void handleClientSide(MessagePacketSendTeleringUpdate message, EntityPlayer player) {
	}

}
