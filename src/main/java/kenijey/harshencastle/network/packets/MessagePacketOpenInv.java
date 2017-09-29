package kenijey.harshencastle.network.packets;

import io.netty.buffer.ByteBuf;
import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.base.BaseMessagePacket;
import kenijey.harshencastle.handlers.GuiHandler;
import net.minecraft.entity.player.EntityPlayer;

public class MessagePacketOpenInv extends BaseMessagePacket<MessagePacketOpenInv>{

	@Override
	public void fromBytes(ByteBuf buf) {		
		
	}

	@Override
	public void toBytes(ByteBuf buf) {		
		
	}

	@Override
	public void handleServerSide(MessagePacketOpenInv message, EntityPlayer player) {
		player.openGui(HarshenCastle.instance, GuiHandler.CUSTOMINVENTORY, player.world, (int)player.posX, (int)player.posY, (int)player.posZ);
	}

	@Override
	public void handleClientSide(MessagePacketOpenInv message, EntityPlayer player) {
		
	}

}

