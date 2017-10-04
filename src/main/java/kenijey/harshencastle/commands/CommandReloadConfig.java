package kenijey.harshencastle.commands;

import kenijey.harshencastle.base.BaseConfig;
import kenijey.harshencastle.base.BaseHarshenCommand;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class CommandReloadConfig extends BaseHarshenCommand
{

	@Override
	public String getName() {
		return "reloadharshen";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		BaseConfig.reloadAll();
		message(sender, "success");
	}

}
