package kenijey.harshencastle.network.packets;

import io.netty.buffer.ByteBuf;
import kenijey.harshencastle.base.BaseMessagePacket;
import kenijey.harshencastle.handlers.HandlerHarshenInventoryEffects;
import kenijey.harshencastle.handlers.HandlerPontusAllowed;
import net.minecraft.entity.player.EntityPlayer;

public class MessagePacketRingUpdate extends BaseMessagePacket<MessagePacketRingUpdate>{

	public MessagePacketRingUpdate() {
	}
	
	private boolean updateFlag;
	private int ringType;
	
	public MessagePacketRingUpdate(boolean updateTo, int ringType)
	{
		updateFlag = updateTo;
		this.ringType = ringType;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {		
		updateFlag = buf.readBoolean();
		ringType = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {		
		buf.writeBoolean(updateFlag);
		buf.writeInt(ringType);
	}

	@Override
	public void handleServerSide(MessagePacketRingUpdate message, EntityPlayer player) {
		switch (message.ringType) {
		case 0:
			HandlerHarshenInventoryEffects.keyTeleringDown = message.updateFlag;
			break;
		case 1:
			HandlerHarshenInventoryEffects.keyMineringDown = message.updateFlag;

			break;
		default:
			break;
		}
	}

	@Override
	public void handleClientSide(MessagePacketRingUpdate message, EntityPlayer player) {
	}

}
