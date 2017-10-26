package kenijey.harshencastle.network.packets;

import io.netty.buffer.ByteBuf;
import kenijey.harshencastle.base.BaseMessagePacket;
import kenijey.harshencastle.blocks.CauldronBlock;
import kenijey.harshencastle.tileentity.TileEntityCaulronBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;

public class MessagePacketUpdateCauldron extends BaseMessagePacket<MessagePacketUpdateCauldron>
{
	
	public MessagePacketUpdateCauldron() {
	}
	
	private BlockPos position;
	private boolean active;
	private int size;
	
	public MessagePacketUpdateCauldron(BlockPos pos, boolean active, int size) {
		this.position = pos;
		this.active = active;
		this.size = size;
	}

	@Override
	public void toBytes(ByteBuf buf) {		
		buf.writeInt(position.getX());
		buf.writeInt(position.getY());
		buf.writeInt(position.getZ());
		buf.writeBoolean(active);
		buf.writeInt(size);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.position = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
		this.active = buf.readBoolean();
		this.size = buf.readInt();
	}

	@Override
	public void onReceived(MessagePacketUpdateCauldron message, EntityPlayer player) {
		if(message.active && !CauldronBlock.CAULDRON_POSITIONS.contains(message.position))
		{
			CauldronBlock.CAULDRON_POSITIONS.add(message.position);
			TileEntityCaulronBlock te = ((TileEntityCaulronBlock)player.world.getTileEntity(message.position));
			if(te != null)
				te.setLegacySize(message.size);
			if(te.isLeader())
				te.setupCauldronMultiblock(message.size);
		}
		if(!message.active && CauldronBlock.CAULDRON_POSITIONS.contains(message.position))
		{
			CauldronBlock.CAULDRON_POSITIONS.remove(message.position);
		}	
		
		Minecraft.getMinecraft().renderGlobal.markBlockRangeForRenderUpdate(message.position.getX(), message.position.getY(), message.position.getZ(), 
				message.position.getX(), message.position.getY(), message.position.getZ());
	}
	
}
