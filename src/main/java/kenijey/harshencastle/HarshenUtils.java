package kenijey.harshencastle;

import java.awt.Point;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

import kenijey.harshencastle.api.BlockItem;
import kenijey.harshencastle.api.CauldronLiquid;
import kenijey.harshencastle.api.EnumInventorySlots;
import kenijey.harshencastle.api.HarshenStack;
import kenijey.harshencastle.api.IHarshenProvider;
import kenijey.harshencastle.base.BasePontusResourceBiome;
import kenijey.harshencastle.biomes.HarshenBiomes;
import kenijey.harshencastle.biomes.PontusBiomeProvider;
import kenijey.harshencastle.config.HarshenConfigs;
import kenijey.harshencastle.enchantment.HarshenEnchantmetns;
import kenijey.harshencastle.enums.items.EnumGlassContainer;
import kenijey.harshencastle.handlers.HandlerPontusAllowed;
import kenijey.harshencastle.network.HarshenNetwork;
import kenijey.harshencastle.network.packets.MessagePacketSetItemInSlot;
import kenijey.harshencastle.objecthandlers.HarshenItemStackHandler;
import kenijey.harshencastle.objecthandlers.HarshenMap;
import kenijey.harshencastle.objecthandlers.PlayerPunchedEvent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketEntityEffect;
import net.minecraft.network.play.server.SPacketPlayerAbilities;
import net.minecraft.network.play.server.SPacketRespawn;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
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
		 return glassContainerHasState(glass)
				 && Item.getItemFromBlock(((IBlockState)glass.getType().getStateOrLoc()).getBlock()) != Items.AIR;
	}
	
	public static boolean glassContainerHasState(EnumGlassContainer glass)
	{
		return glass.isSubContainer() && glass.getType().hasState() && glass.isPaste();
	}
	
	public static boolean glassContainerHasBlock(CauldronLiquid liquid)
	{
		 return liquid.hasState() && Item.getItemFromBlock(((IBlockState)liquid.getStateOrLoc()).getBlock()) != Items.AIR;
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
	
	public static <T> ArrayList<T> toArray(T... list)
	{
		ArrayList<T> array = new ArrayList<>();
		for(T componant : list)
			array.add(componant);
		return array;
	}
	
	public static <T> T[] toList(List<T> array)
	{
		if(array.isEmpty())
			return listOf();
		T[] list = (T[]) Array.newInstance(array.get(0).getClass(), array.size());
		for(int i = 0; i < array.size(); i++)
			list[i] = array.get(i);
		return list;
	}
	
	public static <T> T[] listOf(T...list)
	{
		return list;
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
	
	public static void setStackInSlot(EntityPlayer player, int slot, ItemStack stack) 
	{
		if(player.world.isRemote)
			return;
		HarshenItemStackHandler handler = getHandler(player);
		handler.setStackInSlot(slot, stack);
		player.getEntityData().setTag("harshenInventory", handler.serializeNBT());
		HarshenNetwork.sendToPlayer(player, new MessagePacketSetItemInSlot(slot, getHandler(player).getStackInSlot(slot)));
	}
	
	public static void setStackInSlot(EntityPlayer player, Item item, ItemStack stack) 
	{
		if(player.world.isRemote)
			return;
		HarshenItemStackHandler handler = getHandler(player);
		for(int i = 0; i < handler.getSlots(); i++)
			if(handler.getStackInSlot(i).getItem() == item)
			{
				setStackInSlot(player, i, stack);
				return;
			}
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
                HarshenNetwork.sendToPlayer(player, new MessagePacketSetItemInSlot(i, handler.getStackInSlot(i)));
        		break;
        	}
        player.getEntityData().setTag("harshenInventory", handler.serializeNBT());
	}
	
	public static ItemStack getFirstOccuringItem(EntityPlayer player, Item item)
	{
		HarshenItemStackHandler handler = HarshenUtils.getHandler(player);
        for(int i =0; i < handler.getSlots(); i++)
        	if(handler.getStackInSlot(i).getItem() == item)
        		return handler.getStackInSlot(i);
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
            if ((state.getMaterial().blocksMovement() && !state.getBlock().isLeaves(state, world, blockpos1) && !state.getBlock().isFoliage(world, blockpos1) && state.getBlock() != Blocks.LOG
            		&& state.getBlock() != Blocks.LOG2) || state.getBlock() instanceof BlockLiquid)
            	break;
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
			flag = HarshenConfigs.BLOCKS.isEnabled(((ItemBlock)item).getBlock());
		else
			flag = HarshenConfigs.ITEMS.isEnabled(item);
		return flag;
	}
	
	public static boolean isItemFalse(HarshenStack stack)
	{
		for(ItemStack itemStack : stack.getStackList())
			if(!isItemAvalible(itemStack))
				return true;
		return false;
	}
	/**
	 * @Deprecated use the HarshenStack version
	 */
	@Deprecated 
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
		if(!HarshenUtils.toArray(Blocks.AIR, null).contains(Block.getBlockFromName(blockName)))
			blocks.add(Block.getBlockFromName(blockName));
		for(ItemStack oreStack : OreDictionary.getOres(blockName))
			if(oreStack.getItem() instanceof ItemBlock)
				blocks.add(((ItemBlock)oreStack.getItem()).getBlock());
		ArrayList<Block> finalBlocks = new ArrayList<>();
		for(Block b : blocks)
		{
			NonNullList<ItemStack> items = NonNullList.create();
			b.getSubBlocks(CreativeTabs.SEARCH, items);
			for(ItemStack stack : items)
				if(!stack.isEmpty())
					finalBlocks.add(Block.getBlockFromItem(stack.getItem()));
				else
					finalBlocks.add(b);
		}
		return finalBlocks;
	}
	
	public static void registerHandlers(Object... handlers)
	{
		for(Object o : handlers)
    	{
    		MinecraftForge.EVENT_BUS.register(o);
        	FMLCommonHandler.instance().bus().register(o);
    	}
	}
	
	public final static HarshenMap<Class, Class> SWITCH_CLASSES =  new HarshenMap<Class, Class>()
			.addToMap(Boolean.class, boolean.class)
			.addToMap(Integer.class, int.class)
			.addToMap(Double.class, double.class)
			.addToMap(Float.class, float.class)
			.addToMap(Character.class, char.class)
			.addToMap(Byte.class, byte.class);
	
	public static Class getClass(Class claz)
	{
		if(claz.isArray()) claz = claz.getComponentType();
		return SWITCH_CLASSES.containsKey(claz) ? SWITCH_CLASSES.get(claz) : claz;
	}
	
	public static boolean classSame(Class claz1, Class claz2)
	{
		return getClass(claz1) == getClass(claz2);
	}
	
	public static Method getMethod(String methodName, Class clas, Class... args)
	{
		int l = args.length;
		for(Method method : clas.getMethods())
		{
			if(!(method.getName().equals(methodName) && method.getParameterCount() == l))
				continue;
			ArrayList<Boolean> boolList = new ArrayList<>();
			for(int i = 0; i < method.getParameterTypes().length; i++)
				boolList.add(classSame(method.getParameterTypes()[i], args[i]) && method.getParameterTypes()[i].isArray() == args[i].isArray());
			if(!boolList.contains(false))
				return method;
		}
		return null;
	}
	
	public static Vec3d speedToPos(Vec3d posFrom, Vec3d posTo, double speed)
	{
		return new Vec3d((posTo.x - posFrom.x) / speed, (posTo.y - posFrom.y) / speed, (posTo.z - posFrom.z) / speed);
	}
	
	public static String getTagLine(World world, BlockPos position, String tagType)
	{
		return tagType + String.valueOf(world.provider.getDimension()) + "#"
				+ String.valueOf(position.getX()) + "," + String.valueOf(position.getY()) + "," + String.valueOf(position.getZ());
	}
	
	public static ArrayList<Block> getPontusBlocks(int chunkX, int y, int chunkZ)
	{
		BasePontusResourceBiome thisBiome = PontusBiomeProvider.biomeFromPosition(chunkX, chunkZ);
		ArrayList<Block> blockList = HarshenUtils.toArray(thisBiome.getGroundBlocks());
		for(BasePontusResourceBiome biome : HarshenBiomes.allBiomes)
			if(biome.distanceStartSpawn() < 0)
				continue;
			else if(PontusBiomeProvider.getDistance(HarshenUtils.chunkToPos(chunkX, chunkZ)) > biome.distanceStartSpawn()  - 80 &&
					PontusBiomeProvider.getDistance(HarshenUtils.chunkToPos(chunkX, chunkZ)) < biome.distanceStartSpawn()  + 80)
			{
				for(int i3 = 0; i3 < 19; i3 ++)
					blockList.addAll(HarshenUtils.toArray(thisBiome.getGroundBlocks()));
				for(int i3 = 0; i3 < Math.floorDiv(Math.round(80 - Math.abs(PontusBiomeProvider.getDistance(HarshenUtils.chunkToPos(chunkX, chunkZ)) - biome.distanceStartSpawn())), 4); i3 ++)
    				blockList.add(HarshenBiomes.allBiomes.get(HarshenBiomes.allBiomes.indexOf(thisBiome)
    						+ (PontusBiomeProvider.getDistance(HarshenUtils.chunkToPos(chunkX, chunkZ)) - biome.distanceStartSpawn() < 0 ? 1 : -1))
    							.getMergerBlock(PontusBiomeProvider.getDistance(HarshenUtils.chunkToPos(chunkX, chunkZ)) - biome.distanceStartSpawn() < 0));
				break;
			}
		if(y > thisBiome.getHeightForNonHeightBlocks() && thisBiome.getNonHightBlocks() != null)
		{
			ArrayList<Block> blockList1 = HarshenUtils.toArray(thisBiome.getGroundBlocks());
			for(Block block : blockList)
				if(!HarshenUtils.toArray(thisBiome.getNonHightBlocks()).contains(block))
					blockList1.add(block);
			blockList = blockList1;
		}
		return blockList;
	}
	
	/**@Deprecated use HarshenStack version instead*/
	@Deprecated
	public static boolean areInputsEqual(ArrayList<ItemStack> inputList, ArrayList<ItemStack> worldInputList)
	{
		ArrayList<ItemStack> doneItems = new ArrayList<>(worldInputList);
		stackTestingLoop:
		for(ItemStack stack : inputList)
			for(ItemStack stack1 : worldInputList)
				if(OreDictionary.itemMatches(stack, stack1, false) && doneItems.contains(stack1))
				{
					doneItems.remove(stack1);
					continue stackTestingLoop;
				}
		return doneItems.isEmpty();
	}
	
	public static boolean areHStacksEqual(ArrayList<HarshenStack> inputList, ArrayList<ItemStack> worldInputList)
	{
		ArrayList<ItemStack> doneItems = new ArrayList<>(worldInputList);
		stackTestingLoop:
		for(ItemStack stack : worldInputList)
			for(HarshenStack hStack : inputList)
				if(hStack.containsItem(stack))
				{
					doneItems.remove(stack);
					continue stackTestingLoop;
				}
		return doneItems.isEmpty();
	}
	
	public static ArrayList<Point> getPointsAroundCenter(Point in, Point center, int amountOfPoints) {
		ArrayList<Point> pointList = new ArrayList<>();
		Point previousPoint = new Point(in.x, in.y);
		for(int i = 0; i < amountOfPoints; i++)
		{
			pointList.add(previousPoint);
			previousPoint = rotatePointAbout(previousPoint, center, 360f / amountOfPoints);
		}
		return pointList;
	}
	
	public static Point rotatePointAbout(Point in, Point about, double degrees) {
		double rad = degrees * Math.PI / 180.0;
		double newX = Math.cos(rad) * (in.x - about.x) - Math.sin(rad) * (in.y - about.y) + about.x;
		double newY = Math.sin(rad) * (in.x - about.x) + Math.cos(rad) * (in.y - about.y) + about.y;
		return new Point((int) newX, (int) newY);
	}
	
	public static <T extends Entity> T getFirstEntityInDirection(World world, Vec3d pos, Vec3d lookVec, int range, Class<T> entity)
	{
		T entityToAttack = null;
		for(int i = 1; i < range; i++)
		{
		    AxisAlignedBB aabb = new AxisAlignedBB(pos.x + lookVec.x * i - 0.2d, pos.y + lookVec.y * i - 0.2d, pos.z + lookVec.z * i - 0.2d, pos.x + lookVec.x * i + 0.2d, pos.y + lookVec.y * i + 0.2d, pos.z + lookVec.z * i + 0.2d);
		    List<T> list = world.getEntitiesWithinAABB(entity, aabb);
		    if(!list.isEmpty())
		    {
		    	entityToAttack = list.get(0);
		    	break;
		    }
		}
		return entityToAttack;
	}
	
	private static final HashMap<BlockItem, IHarshenProvider> INVENTORY_ITEMS = new HashMap<>();

	public static void registerInventoryItem(BlockItem impl, IHarshenProvider provider)
	{
		INVENTORY_ITEMS.put(impl, provider);
	}
	
	@Nullable
	public static IHarshenProvider getProvider(BlockItem impl)
	{
		for(BlockItem item : INVENTORY_ITEMS.keySet())
			if(item.impl == impl.impl)
				return INVENTORY_ITEMS.get(item);
		return null; 
	}
	
	@Nullable
	public static IHarshenProvider getProvider(ItemStack stack)
	{
		return getProvider(getBlockItem(stack));
	}
	
	public static boolean hasProvider(BlockItem impl)
	{
		return getProvider(impl) != null;
	}
	
	public static BlockItem getBlockItem(ItemStack stack)
	{
		return stack.getItem() instanceof ItemBlock ? new BlockItem(((ItemBlock)stack.getItem()).getBlock()) : new BlockItem(stack.getItem());
	}
	
	public static boolean hasProvider(ItemStack stack)
	{
		return hasProvider(getBlockItem(stack));
	}
	
	public static void transferPlayerToDimension(EntityPlayerMP player, int dimensionIn, BlockPos pos)
    {
		transferPlayerToDimension(player, dimensionIn, pos, null);
    }
	
	public static void transferPlayerToDimension(EntityPlayerMP player, int dimensionIn, BlockPos pos, IBlockState state)
    {
        int i = player.dimension;
        WorldServer worldserver = player.mcServer.getWorld(player.dimension);
        player.dimension = dimensionIn;
        WorldServer worldserver1 = player.mcServer.getWorld(player.dimension);
        player.connection.sendPacket(new SPacketRespawn(player.dimension, worldserver1.getDifficulty(), worldserver1.getWorldInfo().getTerrainType(), player.interactionManager.getGameType()));
        player.mcServer.getPlayerList().updatePermissionLevel(player);
        worldserver.removeEntityDangerously(player);
        player.isDead = false;
        transferPlayerToWorld(player, i, worldserver, worldserver1, pos, state);
        player.mcServer.getPlayerList().preparePlayer(player, worldserver);
        player.connection.setPlayerLocation(player.posX, player.posY, player.posZ, player.rotationYaw, player.rotationPitch);
        player.interactionManager.setWorld(worldserver1);
        player.connection.sendPacket(new SPacketPlayerAbilities(player.capabilities));
        player.mcServer.getPlayerList().updateTimeAndWeatherForPlayer(player, worldserver1);
        player.mcServer.getPlayerList().syncPlayerInventory(player);

        for (PotionEffect potioneffect : player.getActivePotionEffects())
        {
            player.connection.sendPacket(new SPacketEntityEffect(player.getEntityId(), potioneffect));
        }
        net.minecraftforge.fml.common.FMLCommonHandler.instance().firePlayerChangedDimensionEvent(player, i, dimensionIn);
    }
	
	private static void transferPlayerToWorld(Entity entityIn, int lastDimension, WorldServer oldWorldIn, WorldServer toWorldIn, BlockPos pos, IBlockState state)
    {
        net.minecraft.world.WorldProvider pOld = oldWorldIn.provider;
        net.minecraft.world.WorldProvider pNew = toWorldIn.provider;
        double moveFactor = pOld.getMovementFactor() / pNew.getMovementFactor();
        double d0 = entityIn.posX * moveFactor;
        double d1 = entityIn.posZ * moveFactor;
        double d2 = 8.0D;
        float f = entityIn.rotationYaw;
        oldWorldIn.profiler.startSection("moving");

        if (entityIn.dimension == 1)
        {
            BlockPos blockpos;

            if (lastDimension == 1)
            {
                blockpos = toWorldIn.getSpawnPoint();
            }
            else
            {
                blockpos = toWorldIn.getSpawnCoordinate();
            }

            d0 = (double)blockpos.getX();
            entityIn.posY = (double)blockpos.getY();
            d1 = (double)blockpos.getZ();
            entityIn.setLocationAndAngles(d0, entityIn.posY, d1, 90.0F, 0.0F);

            if (entityIn.isEntityAlive())
            {
                oldWorldIn.updateEntityWithOptionalForce(entityIn, false);
            }
        }

        oldWorldIn.profiler.endSection();

        if (lastDimension != 1)
        {
            oldWorldIn.profiler.startSection("placing");

            if (entityIn.isEntityAlive())
            {
            	int y = toWorldIn.getTopSolidOrLiquidBlock(pos).getY();
            	BlockPos p = new BlockPos(pos.getX(), y, pos.getZ());
                entityIn.setLocationAndAngles(p.getX(), p.getY(), p.getZ(), entityIn.rotationYaw, entityIn.rotationPitch);
                if(state != null && toWorldIn.getBlockState(p.add(0, -1, 0)).getBlock() != state.getBlock())
                	toWorldIn.setBlockState(p.add(0, -1, 0), state, 3);
                toWorldIn.spawnEntity(entityIn);
                toWorldIn.updateEntityWithOptionalForce(entityIn, false);
            }

            oldWorldIn.profiler.endSection();
        }
        entityIn.setWorld(toWorldIn);
    }
	
	public static boolean isPlayerInvolved(Event event)
	{
		return getPlayer(event) != null;
	}
	
	public static boolean isSourceFromPlayer(DamageSource source)
	{
		return source instanceof EntityDamageSource && ((EntityDamageSource)source).getTrueSource() instanceof EntityPlayer;
	}
	
	
	public static EntityPlayer getPlayerFromSource(DamageSource source)
	{
		return (EntityPlayer)((EntityDamageSource)source).getTrueSource();
	}
	
	public static EntityPlayer getPlayer(Event event)
	{
		if(event instanceof LivingEvent && ((LivingEvent)event).getEntity() instanceof EntityPlayer)
			return (EntityPlayer)((LivingEvent)event).getEntity();
		if(event instanceof RenderGameOverlayEvent || event instanceof RenderWorldLastEvent || event instanceof ClientTickEvent)
			return HarshenCastle.proxy.getPlayer();
		if(event instanceof PlayerTickEvent)
			return ((PlayerTickEvent)event).player;
		if(event instanceof PlayerEvent)
			return ((PlayerEvent)event).player;
		if(event instanceof net.minecraftforge.event.entity.player.PlayerEvent)
			return ((net.minecraftforge.event.entity.player.PlayerEvent)event).getEntityPlayer();
		if(event instanceof PlayerPunchedEvent)
			return ((PlayerPunchedEvent)event).attacker;
		if(event instanceof LivingDropsEvent && isSourceFromPlayer(((LivingDropsEvent)event).getSource()))
			return getPlayerFromSource(((LivingDropsEvent)event).getSource());
		return null;
	}
	
	
	public static <T extends Annotation> Method getMethod(Class claz, Class<T> annotation, Class... parameters)
	{
		for(Method method : claz.getMethods())
			if(method.getAnnotation(annotation) != null)
				for(int i = 0; i < method.getParameterCount(); i++)
					if(getClass(method.getParameterTypes()[i]).isAssignableFrom(getClass(parameters[i])) && method.getParameterTypes()[i].isArray() == parameters[i].isArray())
						return method;
		return null;
	}
	
	private static final DataParameter<Byte> FLAGS = EntityDataManager.<Byte>createKey(Entity.class, DataSerializers.BYTE);
	
	public static void setFlag(Entity entity, int flag, boolean set)
	{
		byte b0 = ((Byte)entity.getDataManager().get(FLAGS)).byteValue();
        if (set) entity.getDataManager().set(FLAGS, Byte.valueOf((byte)(b0 | 1 << flag)));
        else entity.getDataManager().set(FLAGS, Byte.valueOf((byte)(b0 & ~(1 << flag))));
	}
	
	public static ItemStack getBookWith(Enchantment enchantment, int level)
	{
		return ItemEnchantedBook.getEnchantedItemStack(new EnchantmentData(enchantment, level));
	}
	
	public static ItemStack getMixupBook()
	{
		return getBookWith(HarshenEnchantmetns.MIXUP, 1);
	}
	
	public static boolean isSlotAllowed(ItemStack stack, EnumInventorySlots slotIn, EnumInventorySlots askingSlot)
	{
		return slotIn.isAllowed(askingSlot) || EnchantmentHelper.getEnchantmentLevel(HarshenEnchantmetns.MIXUP, stack) > 0;
	}
	
    public final static EnumEnchantmentType HARSHEN_ITEMS_ONLY = EnumHelper.addEnchantmentType("harshen_items", new Predicate<Item>() {
    	
		@Override
		public boolean apply(Item input) {
			return hasProvider(new BlockItem(input));
		}
	});
	
   public static byte[] serialize(Object obj)
   {
	   try {
		   ByteArrayOutputStream b = new ByteArrayOutputStream();
		   new ObjectOutputStream(b).writeObject(obj);
	       return b.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			return new byte[0];
		}
    }

    public static Object deserialize(byte[] bytes)
    {
    	try {
			return new ObjectInputStream(new ByteArrayInputStream(bytes)).readObject();
		} catch (ClassNotFoundException | IOException e) {	
			(e instanceof EOFException ? new IllegalArgumentException("A problem occurred (most likely your trying to register an object with a Anonymous inner type. Please dont)") : e).printStackTrace();
			return null;
		}
    }
    
    public static List<String> getAllOreDictionaryList()
    {
    	try {
        	Field f = OreDictionary.class.getDeclaredField("idToName");
        	f.setAccessible(true);
			List<String> stringList = (List<String>) f.get(new OreDictionary());
	    	f.setAccessible(false);
	    	ArrayList<String> finalList = new ArrayList<>();
	    	for(String s : stringList)
	    		if(!OreDictionary.getOres(s).isEmpty())
	    			finalList.add(s);
	    	return finalList;
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
    	return toArray();
    }
    
    public static ArrayList<ItemStack> getAllRelatives(ItemStack itemStack)
    {
    	ItemStack stack = itemStack.copy();
    	ArrayList<ItemStack> stackList = toArray();
    	for(String s : getAllOreDictionaryList())
    		stack1:
    		for(ItemStack stack1 : OreDictionary.getOres(s)){
    			if(OreDictionary.itemMatches(stack1, stack, false))
    			{
    				for(ItemStack stack2 : OreDictionary.getOres(s))
    					if(stack2.getMetadata() == OreDictionary.WILDCARD_VALUE)
    						stackList.addAll(getStacksWithWildCard(stack2));
    					else
    						stackList.add(stack2.copy());
    				break stack1;
    			}
    		}
    	return stackList.isEmpty() ? toArray(stack) : stackList;
    }
    
    public static ArrayList<ItemStack> getStacksWithWildCard(ItemStack wildCardStack)
    {
    	if(wildCardStack.getMetadata() != OreDictionary.WILDCARD_VALUE)
    		return toArray();
    	ArrayList<ItemStack> stackList = new ArrayList<>();
    	NonNullList<ItemStack> innerStacklist = NonNullList.create();
    	wildCardStack.getItem().getSubItems(CreativeTabs.SEARCH, innerStacklist);
		for(ItemStack stack : innerStacklist)
			stackList.add(stack.copy());
		return stackList;
    }
    
    public static ItemStack phaseBucket(Block block)
    {
    	if(block instanceof BlockLiquid || block instanceof IFluidBlock)
    	{
    		Fluid fluid = block instanceof BlockLiquid ? FluidRegistry.lookupFluidForBlock(block) : ((IFluidBlock)block).getFluid();
    		if(fluid == null)
    			return new ItemStack(block);
    		ItemStack stack = FluidUtil.getFilledBucket(new FluidStack(fluid, 1000));
    		return stack.isEmpty() ? new ItemStack(block) : stack; 
    	}
    	
    	return new ItemStack(block);
    }
    
    public static ItemStack getDye(EnumDyeColor color)
    {
    	return new ItemStack(Items.DYE, 1, color.getDyeDamage());
    }
    
    public static ItemStack getLapis()
    {
    	return getDye(EnumDyeColor.BLUE);
    }
    
    public static <T> List<T> getInstancesOfAnnotation(ASMDataTable asmDataTable, Class annotationClass, Class<T> instanceClass) {
		String annotationClassName = annotationClass.getCanonicalName();
		Set<ASMDataTable.ASMData> asmDatas = asmDataTable.getAll(annotationClassName);
		List<T> instances = new ArrayList<>();
		for (ASMDataTable.ASMData asmData : asmDatas) {
			try {
				Class<?> asmClass = Class.forName(asmData.getClassName());
				Class<? extends T> asmInstanceClass = asmClass.asSubclass(instanceClass);
				T instance = asmInstanceClass.newInstance();
				instances.add(instance);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | LinkageError e) {
				HarshenCastle.LOGGER.error("Failed to load: {}", asmData.getClassName(), e);
			}
		}
		return instances;
	}
    
    public static <T> T getObjectFromItemMap(HashMap<ItemStack, T> hashMap, ItemStack key)
    {
    	for(ItemStack stack : hashMap.keySet()){
    		if(stack.isItemEqual(key))
    			return hashMap.get(stack);
    	}
    	return null;
    }
    
    public static boolean give(EntityPlayer playerIn, EnumHand hand, ItemStack stack)
	{
		if(playerIn.getHeldItem(hand).isEmpty())
            playerIn.setHeldItem(hand, stack);
        else if (!playerIn.inventory.addItemStackToInventory(stack))
            playerIn.dropItem(stack, false);
		return true;
	}
} 
