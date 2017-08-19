package kenijey.harshencastle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import kenijey.harshencastle.base.BasePontusResourceBiome;
import kenijey.harshencastle.handlers.HandlerPontusAllowed;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HarshenUtils 
{
	public static boolean isLevelAcceptable(World world, BlockPos pos, EntityPlayer player)
	{
		return !(world.getBiome(pos) instanceof BasePontusResourceBiome) || 
				((BasePontusResourceBiome) world.getBiome(pos)).getLevel() <= 0 ||
				((BasePontusResourceBiome)world.getBiome(pos)).getLevel() <= HandlerPontusAllowed.getAllowed(player);
		
	}
	
	public static BlockPos chunkToPos(BlockPos pos)
	{
		return chunkToPos(pos.getX(), pos.getZ());
	}
	
	public static BlockPos chunkToPos(int x, int z)
	{
		return new BlockPos(x * 16, 1, z * 16);
	}
	
	public static BlockPos posToChunk(BlockPos pos)
	{
		return new BlockPos(Math.floorDiv(pos.getX(), 16), 1, Math.floorDiv(pos.getZ(), 16));
	}
	
	public static int floor(int num)
	{
		return (int) Math.floor(num);
	}
	
	public static Block getRandomDiffrencBlock(Block[] blockList1, Block[] blockList2)
	{
		ArrayList<Block> diffrences = new ArrayList<Block>();
		for(Block block : blockList1)
			if(!Arrays.asList(blockList2).contains(block))
				diffrences.add(block);
		if(diffrences.isEmpty())
			return null;
		return diffrences.get(new Random().nextInt(diffrences.size()));
	}
}
