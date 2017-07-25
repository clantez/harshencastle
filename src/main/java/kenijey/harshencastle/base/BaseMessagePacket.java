package kenijey.harshencastle.base;

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
		if(ctx.side == Side.SERVER)
			handleServerSide(message, ctx.getServerHandler().player);
		else
			handleClientSide(message, HarshenCastle.proxy.getPlayer());
		return null;
	}
	
	public abstract void handleServerSide(REQ message, EntityPlayer player);

	public abstract void handleClientSide(REQ message, EntityPlayer player);

}
