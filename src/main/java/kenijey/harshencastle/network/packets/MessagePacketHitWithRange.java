package kenijey.harshencastle.network.packets;

import io.netty.buffer.ByteBuf;
import kenijey.harshencastle.base.BaseMessagePacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.ByteBufUtils;

public class MessagePacketHitWithRange extends BaseMessagePacket<MessagePacketHitWithRange>{

	private int entityId;

    public MessagePacketHitWithRange() { 
    }

    public MessagePacketHitWithRange(int parEntityId) 
    {
    	entityId = parEntityId;
    }

    @Override
    public void fromBytes(ByteBuf buf) 
    {
	     entityId = ByteBufUtils.readVarInt(buf, 4);
    }

    @Override
    public void toBytes(ByteBuf buf) 
    {
    	ByteBufUtils.writeVarInt(buf, entityId, 4);
    }

	@Override
	public void onReceived(MessagePacketHitWithRange message, EntityPlayer player)
	{
        player.attackTargetEntityWithCurrentItem(player.world.getEntityByID(message.entityId));
    }

}
