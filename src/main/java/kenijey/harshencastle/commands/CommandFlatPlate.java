package kenijey.harshencastle.commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import kenijey.harshencastle.blocks.HarshenBlocks;
import kenijey.harshencastle.blocks.HarshenDimensionalFlatPlate;
import kenijey.harshencastle.blocks.HarshenSoulOre;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockStone;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandGive;
import net.minecraft.command.CommandResultStats;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemWrittenBook;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class CommandFlatPlate extends CommandBase {

	@Override
	public String getName() {
		return "getflat";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "commands.getflat.usage";
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		return 2;
	}
	
	private ArrayList<BlockPos> blockPositions = new ArrayList<BlockPos>();

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		int dis;
		if (args.length == 0)
			dis = 50;
		else
		{
			try {
				Integer.valueOf(args[0]);
			} catch (NumberFormatException e) {
				notifyCommandListener(sender, this, "commands.getflat.failure", args[0]);
				return;
				}
			dis = Integer.valueOf(args[0]);
		}
			
		if(dis > 250)
		{
			notifyCommandListener(sender, this, "commands.getflat.toobig", args[0]);
			return;
		}
		
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
					sender.sendMessage(new TextComponentTranslation("commands.getflat.notfound"));
				for(BlockPos pos : blockPositions)
					sender.sendMessage(new TextComponentString( "x:"+ pos.getX() + ", y:" + pos.getY() + ", z:" + pos.getZ()));
			}
		}.start();
	}
}
