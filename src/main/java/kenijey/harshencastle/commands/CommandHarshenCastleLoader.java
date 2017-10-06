package kenijey.harshencastle.commands;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.base.BaseHarshenCommand;
import kenijey.harshencastle.objecthandlers.HarshenCommand;
import kenijey.harshencastle.objecthandlers.HarshenCommandTabList;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

public class CommandHarshenCastleLoader extends BaseHarshenCommand
{

	@Override
	public String getName() {
		return "harshencastle";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if(args.length > 0 && getAllAvalibleMethods().contains(args[0]))
			loadMethod(args[0], server, sender, args);
		else
			message(sender, "notfound", argsToObjs(args));
	}
	
	public ArrayList<String> getAllAvalibleMethods()
	{
		ArrayList<String> stringList = new ArrayList<>();
		for(Method method : HarshenCastleCommands.class.getMethods())
			if(method.getAnnotation(HarshenCommand.class) != null)
				stringList.add(method.getName());
		return stringList;
	}
	
	public void loadMethod(String methodName, MinecraftServer server, ICommandSender sender, String[] args)
	{
		try {
			getMethod(methodName, HarshenCommand.class).invoke(this, server, sender, getNewArgs(args));
		} catch (Throwable e){
			if(e instanceof CommandException)
				message(sender, args[0] + ".errored");
			else
				message(sender, "failed", argsToObjs(args));
		}		
	}
	
	public <T extends Annotation> Method getMethod(String methodName, Class<T> claz)
	{
		for(Method method : HarshenCastleCommands.class.getMethods())
			if(method.getAnnotation(claz) != null && method.getName().equalsIgnoreCase(methodName))
				return method;
		throw new NullPointerException("Somewhat impossible");
	}
	
	public static void sendMessage(ICommandSender sender, String translationSuffix, Object... args) {
		notifyCommandListener(sender, new CommandHarshenCastleLoader(), "commands.harshencastle." + Thread.currentThread().getStackTrace()[3].getMethodName() + "." + translationSuffix, args);
	}
	
	private Object[] argsToObjs(String... args)
	{
		Object[] objs = new Object[args.length];
		for(int i = 0; i < args.length; i++)
			objs[i] = args[i];
		return objs;
	}
	
	private String[] getNewArgs(String[] args)
	{
		String[] argList = new String[args.length - 1];
		for(int i = 1; i < args.length; i++)
			argList[i-1] = args[i];
		return argList;
	}
	
	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args,
			BlockPos targetPos) {
		if(args.length == 0) return super.getTabCompletions(server, sender, args, targetPos);
		else if(args.length == 1) return getAllAvalibleMethods();
		else
		{
			if(!getAllAvalibleMethods().contains(args[0]))
				return super.getTabCompletions(server, sender, args, targetPos);
			Method method = getMethod(args[0] + "_tabList", HarshenCommandTabList.class);
			if(method.getReturnType() == List.class)
				try {
					return (List<String>) method.invoke(new HarshenCastleCommands(), server, sender, getNewArgs(args), targetPos);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			return super.getTabCompletions(server, sender, args, targetPos);
		}
		
		
	}

}