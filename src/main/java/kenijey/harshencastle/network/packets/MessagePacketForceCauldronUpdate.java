package kenijey.harshencastle.network.packets;

import io.netty.buffer.ByteBuf;
import kenijey.harshencastle.api.CauldronLiquid;
import kenijey.harshencastle.base.BaseMessagePacket;
import kenijey.harshencastle.network.HarshenNetwork;
import kenijey.harshencastle.tileentity.TileEntityCaulronBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;

public class MessagePacketForceCauldronUpdate extends BaseMessagePacket<MessagePacketForceCauldronUpdate>
{
	
	public MessagePacketForceCauldronUpdate() {
	}
	
	private BlockPos pos;
	private int level;
	private String fluidname;
	
	public MessagePacketForceCauldronUpdate(BlockPos pos, int level, String fluidName) {
		this.pos = pos;
		this.level = level;
		this.fluidname = fluidName;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(pos.getX());
		buf.writeInt(pos.getY());
		buf.writeInt(pos.getZ());
		
		buf.writeInt(level);
		
		ByteBufUtils.writeUTF8String(buf, fluidname);

	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
		
		this.level = buf.readInt();
		
		this.fluidname = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void onReceived(MessagePacketForceCauldronUpdate message, EntityPlayer player) {
		if(player.world.getTileEntity(message.pos) instanceof TileEntityCaulronBlock)
			TileEntityCaulronBlock.testForCauldron(player.world, message.pos);
		if(player.world.getTileEntity(message.pos) instanceof TileEntityCaulronBlock && ((TileEntityCaulronBlock)player.world.getTileEntity(message.pos)).isLeader())
		{
			((TileEntityCaulronBlock)player.world.getTileEntity(message.pos)).getController().fluid = CauldronLiquid.getFromName(message.fluidname);
			((TileEntityCaulronBlock)player.world.getTileEntity(message.pos)).getController().level = message.level;
		}
		if(!player.world.isRemote)
			HarshenNetwork.sendToAll(new MessagePacketForceCauldronUpdate(message.pos, message.level, message.fluidname));
	}

}
