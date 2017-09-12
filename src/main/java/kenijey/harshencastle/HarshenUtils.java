package kenijey.harshencastle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import kenijey.harshencastle.base.BasePontusResourceBiome;
import kenijey.harshencastle.config.BlocksEnabled;
import kenijey.harshencastle.config.ItemsEnabled;
import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import kenijey.harshencastle.enums.items.EnumGlassContainer;
import kenijey.harshencastle.handlers.HandlerPontusAllowed;
import kenijey.harshencastle.network.HarshenNetwork;
import kenijey.harshencastle.network.packets.MessagePacketItemInventoryDamaged;
import kenijey.harshencastle.objecthandlers.HarshenItemStackHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraftforge.oredict.OreDictionary;

public class HarshenUtils
{
	
    public static final int DECIMAL_COLOR_WHITE = 16777215;
    public static final int DECIMAL_COLOR_GRAY_TEXT = 4210752;
	
	public static boolean isLevelAcceptable(World world, BlockPos pos, EntityPlayer player)
	{
		return !(world.getBiome(pos) instanceof BasePontusResourceBiome) || 
				((BasePontusResourceBiome) world.getBiome(pos)).getLevel() <= 0 ||
				((BasePontusResourceBiome)world.getBiome(pos)).getLevel() <= HandlerPontusAllowed.getAllowed(player);
		
	}
	
	public static ArrayList<EntityItem> cookList(List<EntityItem> list)
	{
		ArrayList<EntityItem> newList = new ArrayList<>();
		for(EntityItem e : list)
			newList.add(new EntityItem(e.world, e.posX, e.posY, e.posZ, getStackCooked(e.getItem())));
		return newList;
	}
	
	public static String capitalize(String str)
	{
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
	
	public static ArrayList<ItemStack> cookStackList(List<ItemStack> list)
	{
		ArrayList<ItemStack> newList = new ArrayList<>();
		for(ItemStack stack : list)
			newList.add(getStackCooked(stack));
		return newList;
	}
	
	public static boolean glassContainerHasBlock(EnumGlassContainer glass)
	{
		 return glass.isSubContainer() && glass.getType().hasState() && glass.isPaste()
				 && Item.getItemFromBlock(((IBlockState)glass.getType().getStateOrLoc()).getBlock()) != Items.AIR;
	}
	
	public static ItemStack getStackCooked(ItemStack rawStack)
	{
		ItemStack stack = FurnaceRecipes.instance().getSmeltingResult(rawStack);
		stack.setCount(rawStack.getCount());
		return stack.isEmpty() ? rawStack : stack;
	}
	
	public static void cookAndReplaceList(List<EntityItem> list)
	{
		ArrayList<EntityItem> newlist = cookList(list);
		list.clear();
		for(EntityItem item : newlist)
			list.add(item);
	}
	
	public static void cookAndReplaceStackList(List<ItemStack> drops) 
	{
		ArrayList<ItemStack> newlist = cookStackList(drops);
		drops.clear();
		for(ItemStack item : newlist)
			drops.add(item);
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
	
	public static int getItemCount(Entity entity, Item item)
	{
		if(!(entity instanceof EntityPlayer))
			return 0;
		HarshenItemStackHandler handler = getHandler((EntityPlayer) entity);
		int count = 0;
		for(int i = 0; i < handler.getSlots(); i++)
			if(handler.getStackInSlot(i).getItem() == item)
				count++;
		return count;
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
                HarshenNetwork.sendToPlayer(player, new MessagePacketItemInventoryDamaged(i, amount));
        		break;
        	}
        player.getEntityData().setTag("harshenInventory", handler.serializeNBT());
	}
	
	public static ItemStack getFirstOccuringItem(EntityPlayer player, Item item)
	{
		HarshenItemStackHandler handler = HarshenUtils.getHandler(player);
        for(int i =0; i < handler.getSlots(); i++)
        	if(handler.getStackInSlot(i).getItem() == item)
        	{
        		return handler.getStackInSlot(i);
        	}
        return ItemStack.EMPTY;
	}
	
	public static void damageFirstOccuringItem(EntityPlayer player, Item item)
	{
		damageFirstOccuringItem(player, item, 1);
	}
	
	public static void damageOccuringItemNoPacket(EntityPlayer player, Item item, int amount)
	{
		HarshenItemStackHandler handler = HarshenUtils.getHandler(player);
        for(int i =0; i < handler.getSlots(); i++)
        	if(handler.getStackInSlot(i).getItem() == item)
        	{
        		handler.getStackInSlot(i).damageItem(amount, player);
        		break;
        	}
        player.getEntityData().setTag("harshenInventory", handler.serializeNBT());
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
		return exposeList(world.getLootTableManager().getLootTableFromLocation(locationOfTable).generateLootForPools(new Random(), context));
	}
	
	public static List<ItemStack> getItemsFromLootPool(World world, ResourceLocation locationOfTable, String poolName)
	{
		LootContext context = new LootContext(1f, (WorldServer) world, world.getLootTableManager(), null, null, DamageSource.MAGIC);
		List<ItemStack> list = Lists.<ItemStack>newArrayList();
		world.getLootTableManager().getLootTableFromLocation(locationOfTable).getPool(poolName).generateLoot(list, new Random(), context);
		return exposeList(list);
	}
	
	private static List<ItemStack> exposeList(List<ItemStack> list)
	{
		if(list.isEmpty())
			list.add(ItemStack.EMPTY);
		return list;
	}
	
	public static boolean isItemAvalible(ItemStack stack)
	{
		boolean flag = true;
		Item item = stack.getItem();
		if(item instanceof ItemBlock)
			flag = BlocksEnabled.isBlockEnabled(((ItemBlock)item).getBlock());
		else
			flag = ItemsEnabled.isItemEnabled(item);
		return flag;
	}
	
	public static boolean isItemFalse(ItemStack stack)
	{
		return !isItemAvalible(stack);
	}
	
	public static NBTTagCompound getNBT(ItemStack stack)
	{
		if(!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());
		return stack.getTagCompound();
	}
	
	public static ArrayList<Block> getBlocksFromString(String blockName)
	{
		ArrayList<Block> blocks = new ArrayList<>();
		if(!Arrays.asList(Blocks.AIR, null).contains(Block.getBlockFromName(blockName)))
			blocks.add(Block.getBlockFromName(blockName));
		for(ItemStack oreStack : OreDictionary.getOres(blockName))
			if(oreStack.getItem() instanceof ItemBlock)
				blocks.add(((ItemBlock)oreStack.getItem()).getBlock());
		return blocks;
	}

	public static String[] fillList(String string, Object[] objectList) {
		String[] list = new String[objectList.length];
		for(int i = 0; i < list.length; i++)
			list[i] = string;
		return list;
	}
}
