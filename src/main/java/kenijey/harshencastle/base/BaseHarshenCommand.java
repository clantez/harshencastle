package kenijey.harshencastle.base;

import java.util.Collections;
import java.util.List;

import kenijey.harshencastle.network.HarshenNetwork;
import kenijey.harshencastle.network.packets.MessagePacketPlayerHasAccess;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

public abstract class BaseHarshenCommand extends CommandBase 
{
	@Override
	public abstract String getName();

	@Override
	public String getUsage(ICommandSender sender) {
		return "commands." + getName() + ".usage";
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		return 2;
	}
	
	@Override
	public abstract void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException;
	
	protected void message(ICommandSender sender, String translationSuffix, Object... args)
	{
		notifyCommandListener(sender, this, "commands." + getName() + "." + translationSuffix, args);
	}
}
