package kenijey.harshencastle;

import java.awt.Point;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import kenijey.harshencastle.base.BasePontusResourceBiome;
import kenijey.harshencastle.biomes.HarshenBiomes;
import kenijey.harshencastle.biomes.PontusBiomeProvider;
import kenijey.harshencastle.config.HarshenConfigs;
import kenijey.harshencastle.enums.CauldronLiquid;
import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import kenijey.harshencastle.enums.items.EnumGlassContainer;
import kenijey.harshencastle.handlers.HandlerPontusAllowed;
import kenijey.harshencastle.interfaces.IVanillaProvider;
import kenijey.harshencastle.network.HarshenNetwork;
import kenijey.harshencastle.network.packets.MessagePacketSetItemInSlot;
import kenijey.harshencastle.objecthandlers.HarshenItemStackHandler;
import kenijey.harshencastle.objecthandlers.PlayerPunchedEvent;
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
import net.minecraft.network.play.server.SPacketEntityEffect;
import net.minecraft.network.play.server.SPacketPlayerAbilities;
import net.minecraft.network.play.server.SPacketRespawn;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistryEntry.Impl;

public class HarshenUtils
{
	
	public HarshenUtils() {
		switchClasses.put(Boolean.class, boolean.class);		
		switchClasses.put(Integer.class, int.class);		
		switchClasses.put(Double.class, double.class);
		switchClasses.put(Float.class, float.class);
		switchClasses.put(Character.class, char.class);
		switchClasses.put(Byte.class, byte.class);

	}
	
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
		return blocks;
	}
	
	public static void registerHandlers(Object... handlers)
	{
		for(Object o : handlers)
    	{
    		MinecraftForge.EVENT_BUS.register(o);
        	FMLCommonHandler.instance().bus().register(o);
    	}
	}
	
	public final static HashMap<Class, Class> switchClasses = new HashMap<>();
	
	public static Class getClass(Class claz)
	{
		if(claz.isArray()) claz = claz.getComponentType();
		return switchClasses.containsKey(claz) ? switchClasses.get(claz) : claz;
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
	
	public static boolean areInputsEqual(ArrayList<ItemStack> inputList, ArrayList<ItemStack> worldInputList)
	{
		ArrayList<ItemStack> doneItems = new ArrayList<>(worldInputList);
		stackTestingLoop:
		for(ItemStack stack : inputList)
			for(ItemStack stack1 : worldInputList)
				if(stack1.isItemEqual(stack) && doneItems.contains(stack1))
				{
					doneItems.remove(stack1);
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
	
	private static final HashMap<Impl, IVanillaProvider> INVENTORY_ITEMS = new HashMap<>();

	public static void registerInventoryItem(Impl impl, IVanillaProvider provider)
	{
		INVENTORY_ITEMS.put(impl, provider);
	}
	
	@Nullable
	public static IVanillaProvider getProvider(Impl impl)
	{
		return impl instanceof IVanillaProvider ? (IVanillaProvider) impl : INVENTORY_ITEMS.get(impl); 
	}
	
	@Nullable
	public static IVanillaProvider getProvider(ItemStack stack)
	{
		return getProvider(getImpl(stack));
	}
	
	public static boolean hasProvider(Impl impl)
	{
		return getProvider(impl) != null;
	}
	
	public static Impl getImpl(ItemStack stack)
	{
		return stack.getItem() instanceof ItemBlock ? ((ItemBlock)stack.getItem()).getBlock() : stack.getItem();
	}
	
	public static boolean hasProvider(ItemStack stack)
	{
		return hasProvider(getImpl(stack));
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
		return (event instanceof LivingEvent && (((LivingEvent)event).getEntityLiving() instanceof EntityPlayer ||
				(((LivingEvent)event).getEntity() != null && ((LivingEvent)event).getEntity().world.isRemote))) || event instanceof PlayerTickEvent || event instanceof PlayerPunchedEvent;
	}
	
	public static EntityPlayer getPlayer(Event event)
	{
		if(event instanceof LivingEvent && ((LivingEvent)event).getEntity() instanceof EntityPlayer)
			return (EntityPlayer)((LivingEvent)event).getEntity();
		if(event instanceof LivingEvent && ((LivingEvent)event).getEntity().world.isRemote)
			return HarshenCastle.proxy.getPlayer();
		if(event instanceof PlayerTickEvent)
			return ((PlayerTickEvent)event).player;
		if(event instanceof PlayerPunchedEvent)
			return ((PlayerPunchedEvent)event).player;
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
}
