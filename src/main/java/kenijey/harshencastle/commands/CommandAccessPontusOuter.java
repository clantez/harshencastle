package kenijey.harshencastle.commands;

import java.util.Arrays;
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

public class CommandAccessPontusOuter extends CommandBase {

	@Override
	public String getName() {
		return "accesspontus";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "commands.pontus.usage";
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		return 2;
	}
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException 
	{
		EntityPlayer player;
		String rawInt;
		if(args.length == 1)
		{
			player = getCommandSenderAsPlayer(sender);
			rawInt = args[0];
		}	
		else
		{
			player = getPlayer(server, sender, args[0]);
			rawInt = args[1];
		}
		try
		{
			int i = Integer.valueOf(rawInt);
		}
		catch (NumberFormatException e) {
			notifyCommandListener(sender, this, "commands.pontuslevel.number", rawInt);
		}
		player.getEntityData().setInteger("PontusBiomeLevel", Integer.valueOf(rawInt));
		HarshenNetwork.sendToPlayer((EntityPlayerMP) player, new MessagePacketPlayerHasAccess(player));
		notifyCommandListener(sender, this, "commands.allowpontus.achive", player.getName());
	}
	
	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args,
			BlockPos targetPos) {
		return args.length != 1 && args.length != 2 ? Collections.emptyList() : getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
	}
}
