package kenijey.harshencastle.network.packets;

import io.netty.buffer.ByteBuf;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.base.BaseMessagePacket;
import kenijey.harshencastle.objecthandlers.HarshenItemStackHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;

public class MessageSendPlayerInvToClient extends BaseMessagePacket<MessageSendPlayerInvToClient>{

	public MessageSendPlayerInvToClient() {
	}
	
	
	private HarshenItemStackHandler handler;
	public MessageSendPlayerInvToClient(EntityPlayer player)
	{
		handler = HarshenUtils.getHandler(player);
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		NBTTagCompound nbt = ByteBufUtils.readTag(buf);
		handler = HarshenUtils.getHandler(nbt);
		handler.deserializeNBT(nbt);
	}

	@Override
	public void toBytes(ByteBuf buf) {	
		ByteBufUtils.writeTag(buf, handler.serializeNBT());
	}

	@Override
	public void handleServerSide(MessageSendPlayerInvToClient message, EntityPlayer player) {
	}

	@Override
	public void handleClientSide(MessageSendPlayerInvToClient message, EntityPlayer player) {
		if(player != null)
			player.getEntityData().setTag("harshenInventory", message.handler.serializeNBT());
	}

}
