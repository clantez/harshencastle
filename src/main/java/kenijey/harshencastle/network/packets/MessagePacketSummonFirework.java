package kenijey.harshencastle.network.packets;

import io.netty.buffer.ByteBuf;
import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.base.BaseMessagePacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;

public class MessagePacketSummonFirework extends BaseMessagePacket<MessagePacketSummonFirework>{

	@Override
	public void fromBytes(ByteBuf buf) {		
	}

	@Override
	public void toBytes(ByteBuf buf) {		
	}

	@Override
	public void handleServerSide(MessagePacketSummonFirework message, EntityPlayer player) {	
		Vec3d vec3d = player.getLookVec();
        player.motionX += vec3d.x * 0.1D + (vec3d.x * 2.5D - player.motionX) * 0.5D;
        player.motionY += vec3d.y * 0.1D + (vec3d.y * 2.5D - player.motionY) * 0.5D;
        player.motionZ += vec3d.z * 0.1D + (vec3d.z * 2.5D - player.motionZ) * 0.5D;
		HarshenUtils.damageFirstOccuringItem(player, HarshenItems.elytra_pendant);
	}

	@Override
	public void handleClientSide(MessagePacketSummonFirework message, EntityPlayer player) {
	}

}
