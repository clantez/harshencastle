package kenijey.harshencastle.base;

import io.netty.buffer.ByteBuf;
import kenijey.harshencastle.HarshenCastle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public abstract class BaseMessagePacket<REQ extends IMessage> implements IMessage, IMessageHandler<REQ, REQ>
{
	@Override
	public REQ onMessage(REQ message, MessageContext ctx) {
		onReceived(message, ctx.side == Side.SERVER ? ctx.getServerHandler().player : HarshenCastle.proxy.getPlayer());
		return null;
	}
	
	@Override
	public abstract void fromBytes(ByteBuf buf);
	
	@Override
	public abstract void toBytes(ByteBuf buf);
	
	public abstract void onReceived(REQ message, EntityPlayer player);

}
