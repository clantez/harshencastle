package kenijey.harshencastle.network.packets;

import io.netty.buffer.ByteBuf;
import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.base.BaseMessagePacket;
import kenijey.harshencastle.enums.particle.EnumHarshenParticle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;

public class MessagePacketSpawnBloodParticle extends BaseMessagePacket<MessagePacketSpawnBloodParticle>{

	public MessagePacketSpawnBloodParticle() {
	}
	
	private Vec3d position;
	private Vec3d directionSpeed;
	private float scale;
	private boolean disableMoving;
	
	public MessagePacketSpawnBloodParticle(Vec3d position, Vec3d directionSpeed, float scale, boolean disableMoving)
	{
		this.position = position;
		this.directionSpeed = directionSpeed;
		this.scale = scale;
		this.disableMoving = disableMoving;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {		
		this.position = new Vec3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
		this.directionSpeed = new Vec3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
		this.scale = buf.readFloat();
		this.disableMoving = buf.readBoolean();

	}

	@Override
	public void toBytes(ByteBuf buf) {		
		buf.writeDouble(position.x);
		buf.writeDouble(position.y);
		buf.writeDouble(position.z);
		
		buf.writeDouble(directionSpeed.x);
		buf.writeDouble(directionSpeed.y);
		buf.writeDouble(directionSpeed.z);
				
		buf.writeFloat(scale);
		
		buf.writeBoolean(disableMoving);
		
		
	}

	@Override
	public void onReceived(MessagePacketSpawnBloodParticle message, EntityPlayer player) {	
		HarshenCastle.proxy.spawnParticle(EnumHarshenParticle.BLOOD, message.position, message.directionSpeed, message.scale, message.disableMoving);
	}
}
