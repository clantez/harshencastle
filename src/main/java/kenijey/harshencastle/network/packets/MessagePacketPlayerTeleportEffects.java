package kenijey.harshencastle.network.packets;

import io.netty.buffer.ByteBuf;
import kenijey.harshencastle.base.BaseMessagePacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MessagePacketPlayerTeleportEffects extends BaseMessagePacket<MessagePacketPlayerTeleportEffects>{

	public MessagePacketPlayerTeleportEffects() {
	}
	
	private BlockPos position;
	
	public MessagePacketPlayerTeleportEffects(BlockPos position)
	{
		this.position = position;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {		
		this.position = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
	}

	@Override
	public void toBytes(ByteBuf buf) {		
		buf.writeInt(position.getX());
		buf.writeInt(position.getY());
		buf.writeInt(position.getZ());
	}

	@Override
	public void handleServerSide(MessagePacketPlayerTeleportEffects message, EntityPlayer player) {	
		
	}

	@Override
	public void handleClientSide(MessagePacketPlayerTeleportEffects message, EntityPlayer player) {
		World world = player.world;
		BlockPos blockpos = message.position;
		for (int j = 0; j < 128; ++j)
        {
            double d0 = world.rand.nextDouble();
            float f = (world.rand.nextFloat() - 0.5F) * 0.2F;
            float f1 = (world.rand.nextFloat() - 0.5F) * 0.2F;
            float f2 = (world.rand.nextFloat() - 0.5F) * 0.2F;
            double d1 = (double)blockpos.getX() + (double)(player.getPosition().getX() - blockpos.getX()) * d0 + (world.rand.nextDouble() - 0.5D) + 0.5D;
            double d2 = (double)blockpos.getY() + (double)(player.getPosition().getY() - blockpos.getY()) * d0 + world.rand.nextDouble() - 0.5D;
            double d3 = (double)blockpos.getZ() + (double)(player.getPosition().getZ() - blockpos.getZ()) * d0 + (world.rand.nextDouble() - 0.5D) + 0.5D;
            world.spawnParticle(EnumParticleTypes.PORTAL, d1, d2, d3, (double)f, (double)f1, (double)f2);
        }
	}

}
