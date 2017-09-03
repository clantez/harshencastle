package kenijey.harshencastle.network.packets;

import io.netty.buffer.ByteBuf;
import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.base.BaseMessagePacket;
import kenijey.harshencastle.inventory.GuiHandler;
import kenijey.harshencastle.objecthandlers.HarshenItemStackHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;

public class MessagePacketItemInventoryDamaged extends BaseMessagePacket<MessagePacketItemInventoryDamaged>{

	public MessagePacketItemInventoryDamaged() {
	}
	
	private int slotId;
	private int amount;
	
	public MessagePacketItemInventoryDamaged(int slotId, int amount)
	{
		this.slotId = slotId;
		this.amount = amount;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {		
		slotId = buf.readInt();
		amount = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {	
		buf.writeInt(slotId);
		buf.writeInt(amount);
	}

	@Override
	public void handleServerSide(MessagePacketItemInventoryDamaged message, EntityPlayer player) {
	}

	@Override
	public void handleClientSide(MessagePacketItemInventoryDamaged message, EntityPlayer player) {
		 HarshenItemStackHandler handler = HarshenUtils.getHandler(player);
  		 handler.getStackInSlot(message.slotId).damageItem(message.amount, player);
         player.getEntityData().setTag("harshenInventory", handler.serializeNBT());
	}

}

