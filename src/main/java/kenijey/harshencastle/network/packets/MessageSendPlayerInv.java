package kenijey.harshencastle.network.packets;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.base.BaseMessagePacket;
import kenijey.harshencastle.inventory.GuiHandler;
import kenijey.harshencastle.objecthandlers.HarshenItemStackHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;

public class MessageSendPlayerInv extends BaseMessagePacket<MessageSendPlayerInv>{

	public MessageSendPlayerInv() {
	}
	
	
	private HarshenItemStackHandler handler;
	public MessageSendPlayerInv(EntityPlayer player)
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
	public void handleServerSide(MessageSendPlayerInv message, EntityPlayer player) {
		player.getEntityData().setTag("harshenInventory", message.handler.serializeNBT());
	}

	@Override
	public void handleClientSide(MessageSendPlayerInv message, EntityPlayer player) {
		
	}

}

