package kenijey.harshencastle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import kenijey.harshencastle.base.BasePontusResourceBiome;
import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import kenijey.harshencastle.handlers.HandlerPontusAllowed;
import kenijey.harshencastle.network.HarshenNetwork;
import kenijey.harshencastle.network.packets.MessagePacketItemInventoryDamaged;
import kenijey.harshencastle.objecthandlers.HarshenItemStackHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.storage.loot.LootContext;

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
		if(player.getEntityData().getCompoundTag("harshenInventory").getInteger("Size") != handler.getSlots())
		{
			HarshenItemStackHandler handler2 = getHandler(player.getEntityData());
			handler2.setSize(handler.getSlots());
			handler = handler2;
		}
		else
			handler.deserializeNBT(player.getEntityData().getCompoundTag("harshenInventory"));
		return handler;
	}
	
	public static HarshenItemStackHandler getHandler(NBTTagCompound nbt)
	{
		HarshenItemStackHandler handler = new HarshenItemStackHandler(nbt.getCompoundTag("harshenInventory").getInteger("Size"));
		handler.deserializeNBT(nbt.getCompoundTag("harshenInventory"));
		return handler;
	}
	
	public static boolean containsItem(Entity entity, Item item)
	{
		return entity instanceof EntityPlayer && getHandler((EntityPlayer) entity).containsItem(item);
	}
	
	public static void damageFirstOccuringItem(EntityPlayer player, Item item, int amount)
	{
		if(player.world.isRemote)
			return;
		HarshenItemStackHandler handler = HarshenUtils.getHandler(player);
        for(int i =0; i < handler.getSlots(); i++)
        	if(handler.getStackInSlot(i).getItem() == item)
        	{
        		handler.getStackInSlot(i).damageItem(amount, player);
                HarshenNetwork.sendToPlayer((EntityPlayerMP) player, new MessagePacketItemInventoryDamaged(i, amount));
        		break;
        	}
        player.getEntityData().setTag("harshenInventory", handler.serializeNBT());
	}
	
	public static void damageFirstOccuringItem(EntityPlayer player, Item item)
	{
		damageFirstOccuringItem(player, item, 1);
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
	
	public static BlockPos getTopBlock(World world, Vec3d vec)
	{
		return getTopBlock(world, new BlockPos(vec));
	}
	
	public static BlockPos getBottomBlockAir(World world, BlockPos pos)
	{
		Chunk chunk = world.getChunkFromBlockCoords(pos);
        BlockPos blockpos;
        BlockPos blockpos1;

        for (blockpos = new BlockPos(pos.getX(), 0, pos.getZ()); blockpos.getY() < world.getActualHeight()- 1; blockpos = blockpos1)
        {
            blockpos1 = blockpos.up();
            IBlockState state = chunk.getBlockState(blockpos1);
            if ((state.getBlock() instanceof BlockLiquid || world.isAirBlock(blockpos1)) &&
            		chunk.getBlockState(blockpos1.up()) instanceof BlockLiquid || world.isAirBlock(blockpos1.up()))
            {
                break;
            }
        }
        return blockpos.up(2);		
	}
	
	public static BlockPos getBottomBlockAir(World world, Vec3d vec)
	{
		return getBottomBlockAir(world, new BlockPos(vec));
	}
	
	public static List<ItemStack> getItemsFromLootTable(World world, ResourceLocation locationOfTable)
	{
		LootContext context = new LootContext(1f, (WorldServer) world, world.getLootTableManager(), null, null, DamageSource.MAGIC);
		return world.getLootTableManager().getLootTableFromLocation(locationOfTable).generateLootForPools(new Random(), context);
	}
	
	public static List<ItemStack> getItemsFromLootPool(World world, ResourceLocation locationOfTable, String poolName)
	{
		LootContext context = new LootContext(1f, (WorldServer) world, world.getLootTableManager(), null, null, DamageSource.MAGIC);
		List<ItemStack> list = Lists.<ItemStack>newArrayList();
		world.getLootTableManager().getLootTableFromLocation(locationOfTable).getPool(poolName).generateLoot(list, new Random(), context);
		return list;
	}

	public static String[] fillList(String string, Object[] objectList) {
		String[] list = new String[objectList.length];
		for(int i = 0; i < list.length; i++)
			list[i] = string;
		return list;
	}
}
