package kenijey.harshencastle;

import java.util.ArrayList;

import kenijey.harshencastle.base.BasePontusResourceBiome;
import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import kenijey.harshencastle.handlers.HandlerPontusAllowed;
import kenijey.harshencastle.itemstackhandlers.HarshenItemStackHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class HarshenUtils
{
	
    public static final int DECIMAL_COLOR_WHITE = 16777215;
    public static final int DECIMAL_COLOR_GRAY_TEXT = 4210752;
    public static final int HASH_LIMIT = 1000000;
	
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
	
	public static ArrayList<Block> toArrayBlock(Block... blocks)
	{
		ArrayList<Block> arrayBlocks = new ArrayList<>();
		for(Block block : blocks)
			arrayBlocks.add(block);
		return arrayBlocks;
	}
	
	public static Block[] blockList(Block...blocks)
	{
		return blocks;
	}
	
	public static HarshenItemStackHandler getHandler(EntityPlayer player)
	{
		HarshenItemStackHandler handler = new HarshenItemStackHandler(EnumInventorySlots.values().length);
		handler.deserializeNBT(player.getEntityData().getCompoundTag("harshenInventory"));
		return handler;
	}
	
	public static EntityPlayer getClosestPlayer(World world, BlockPos position)
	{
		return world.getClosestPlayer(position.getX(), position.getY(), position.getZ(), Integer.MAX_VALUE, false);
	}
	
	public static BlockPos getTopBlock(World world, BlockPos pos)
	{
		Chunk chunk = world.getChunkFromBlockCoords(pos);
        BlockPos blockpos;
        BlockPos blockpos1;

        for (blockpos = new BlockPos(pos.getX(), chunk.getTopFilledSegment() + 16, pos.getZ()); blockpos.getY() >= 0; blockpos = blockpos1)
        {
            blockpos1 = blockpos.down();
            IBlockState state = chunk.getBlockState(blockpos1);
            if ((state.getMaterial().blocksMovement() && !state.getBlock().isLeaves(state, world, blockpos1) && !state.getBlock().isFoliage(world, blockpos1))
            		|| state.getBlock() instanceof BlockLiquid)
            {
                break;
            }
        }

        return blockpos;
			
	}

	public static String[] fillList(String string, Object[] objectList) {
		String[] list = new String[objectList.length];
		for(int i = 0; i < list.length; i++)
			list[i] = string;
		return list;
	}
}
