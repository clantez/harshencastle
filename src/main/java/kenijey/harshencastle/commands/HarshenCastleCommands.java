package kenijey.harshencastle.commands;

import java.util.Collections;
import java.util.List;

import kenijey.harshencastle.base.BaseConfig;
import kenijey.harshencastle.handlers.HandlerPontusAllowed;
import kenijey.harshencastle.handlers.server.HandlerSyncConfig;
import kenijey.harshencastle.interfaces.HarshenCommand;
import kenijey.harshencastle.interfaces.HarshenCommandTabList;
import kenijey.harshencastle.network.HarshenNetwork;
import kenijey.harshencastle.network.packets.MessagePacketPlayerHasAccess;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

public class HarshenCastleCommands 
{
	@HarshenCommand
	public static void reload(MinecraftServer server, ICommandSender sender, String[] args)
	{
		BaseConfig.reloadAll();
		for(EntityPlayer player : sender.getEntityWorld().playerEntities)
			HandlerSyncConfig.syncConfigWithClient((EntityPlayerMP) player);
		message(sender, "success");
	}
	
	@HarshenCommand
	public static void pontuslevel(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
	{
		EntityPlayer player = args.length == 1 ? CommandBase.getCommandSenderAsPlayer(sender) : CommandBase.getPlayer(server, sender, args[0]);
		String rawInt = args[args.length == 1 ? 0 : 1];
		int i = CommandBase.parseInt(rawInt);
		player.getEntityData().setInteger("PontusBiomeLevel", i);
		HarshenNetwork.sendToPlayer(player, new MessagePacketPlayerHasAccess(player));
		HandlerPontusAllowed.setAllowed(player, i);
		message(sender, "success", player.getName(), i);
	}
	
	@HarshenCommandTabList
	public static List<String> pontuslevel_tabList(MinecraftServer server, ICommandSender sender, String[] args,
			BlockPos targetPos)
	{
		return args.length != 1? Collections.emptyList() : CommandBase.getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
	}

	private static void message(ICommandSender sender, String translationSuffix, Object... args) {
		CommandHarshenCastleLoader.sendMessage(sender, translationSuffix, args);
	}
}
