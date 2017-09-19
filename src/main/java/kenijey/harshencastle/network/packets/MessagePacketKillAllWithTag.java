package kenijey.harshencastle.network.packets;

import io.netty.buffer.ByteBuf;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.base.BaseMessagePacket;
import kenijey.harshencastle.objecthandlers.HarshenItemStackHandler;
import kenijey.harshencastle.particle.ParticleItem;
import net.minecraft.entity.player.EntityPlayer;

import net.minecraftforge.fml.common.network.ByteBufUtils;
public class MessagePacketKillAllWithTag extends BaseMessagePacket<MessagePacketKillAllWithTag>{

	public MessagePacketKillAllWithTag() {
	}
	
	private String tag;
	
	public MessagePacketKillAllWithTag(String tag)
	{
		this.tag = tag;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {		
		tag = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {	
		ByteBufUtils.writeUTF8String(buf, tag);
	}

	@Override
	public void handleServerSide(MessagePacketKillAllWithTag message, EntityPlayer player) {
	}

	@Override
	public void handleClientSide(MessagePacketKillAllWithTag message, EntityPlayer player) {
		 ParticleItem.killAllParticlesWithTag(message.tag);
	}

}

