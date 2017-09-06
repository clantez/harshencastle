package kenijey.harshencastle.network.packets;

import io.netty.buffer.ByteBuf;
import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.base.BaseMessagePacket;
import kenijey.harshencastle.handlers.HandlerPontusAllowed;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class MessagePacketSummonFirework extends BaseMessagePacket<MessagePacketSummonFirework>{

	@Override
	public void fromBytes(ByteBuf buf) {		
	}

	@Override
	public void toBytes(ByteBuf buf) {		
	}

	@Override
	public void handleServerSide(MessagePacketSummonFirework message, EntityPlayer player) {	
		player.world.spawnEntity(new EntityFireworkRocket(player.world, new ItemStack(Items.FIREWORKS), player));
		HarshenUtils.damageFirstOccuringItem(player, HarshenItems.elytra_pendant);
	}

	@Override
	public void handleClientSide(MessagePacketSummonFirework message, EntityPlayer player) {
	}

}
