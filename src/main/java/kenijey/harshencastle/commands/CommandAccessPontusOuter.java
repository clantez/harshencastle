package kenijey.harshencastle.commands;

import java.util.Collections;
import java.util.List;

import kenijey.harshencastle.base.BaseHarshenCommand;
import kenijey.harshencastle.handlers.HandlerPontusAllowed;
import kenijey.harshencastle.network.HarshenNetwork;
import kenijey.harshencastle.network.packets.MessagePacketPlayerHasAccess;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

public class CommandAccessPontusOuter extends BaseHarshenCommand {

	@Override
	public String getName() {
		return "pontuslevel";
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
		int i = parseInt(rawInt);
		player.getEntityData().setInteger("PontusBiomeLevel", i);
		HarshenNetwork.sendToPlayer((EntityPlayerMP) player, new MessagePacketPlayerHasAccess(player));
		HandlerPontusAllowed.setAllowed(player, i);
		message(sender, "success", player.getName(), i);
	}
	
	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args,
			BlockPos targetPos) {
		return args.length != 1 && args.length != 2 ? Collections.emptyList() : getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
	}
}
