package kenijey.harshencastle.commands;

import java.util.ArrayList;

import kenijey.harshencastle.base.BaseHarshenCommand;
import kenijey.harshencastle.blocks.HarshenDimensionalFlatPlate;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class CommandFlatPlate extends BaseHarshenCommand {

	@Override
	public String getName() {
		return "getflat";
	}
	
	private ArrayList<BlockPos> blockPositions = new ArrayList<BlockPos>();

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		int dis;
		if (args.length == 0)
			dis = 50;
		else
			dis = parseInt(args[0], 0, 250);
		int farDis = dis - (dis/2);
		int shorDis = 0 - (dis/2);	
		new Thread()
		{
			@Override
			public void run() 
			{
				blockPositions.clear();
				for(int x = shorDis; x < farDis; x++)
					for(int z = shorDis; z < farDis; z++)
						for(int y = 0; y < 256; y++)
						{
							if(sender.getEntityWorld().getBlockState(new BlockPos(x, y, z).add(sender.getPosition().getX(), 0, sender.getPosition().getZ())).getBlock() instanceof HarshenDimensionalFlatPlate)
								blockPositions.add(new BlockPos(x, y, z).add(sender.getPosition().getX(), 0, sender.getPosition().getZ()));

						}
				if(blockPositions.isEmpty())
					message(sender, "notfound");
				for(BlockPos pos : blockPositions)
					message(sender, "x:"+ pos.getX() + ", y:" + pos.getY() + ", z:" + pos.getZ());
			}
		}.start();
	}
}
