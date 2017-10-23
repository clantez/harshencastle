package kenijey.harshencastle.tileentity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.api.CauldronLiquid;
import kenijey.harshencastle.base.BaseTileEntityHarshenSingleItemInventory;
import kenijey.harshencastle.blocks.BloodBlock;
import kenijey.harshencastle.enums.items.EnumGlassContainer;
import kenijey.harshencastle.enums.particle.EnumHarshenParticle;
import kenijey.harshencastle.internal.HarshenRegistry;
import kenijey.harshencastle.items.BloodCollector;
import kenijey.harshencastle.network.HarshenNetwork;
import kenijey.harshencastle.network.packets.MessagePacketSpawnBloodParticle;
import kenijey.harshencastle.network.packets.MessagePacketSpawnItemParticles;
import kenijey.harshencastle.objecthandlers.FaceRenderer;
import kenijey.harshencastle.recipies.CauldronRecipes;
import kenijey.harshencastle.recipies.HereticRitualRecipes;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileEntityHereticCauldron extends BaseTileEntityHarshenSingleItemInventory
{
	boolean alterSameGlassContainer = false;
	private int activeTimer = 0;
	private int overstandingTimer = 0;
	int layersDrained = 0;
	public boolean isActive = false;
	public boolean isActiveInBackground = false;
	private ItemStack switchedItem = ItemStack.EMPTY;
	private int[] drainPos = {50, 75, 100, Integer.MAX_VALUE};
	private CauldronLiquid fluid = CauldronLiquid.NONE;
	private int level = 0;
	private CauldronLiquid workingFluid = CauldronLiquid.NONE;
	private HereticRitualRecipes overstandingRecipe;
	private HashMap<BlockPos, ItemStack> pedestalMap = new HashMap<>(); 
	
	@Override
	public void tick() {
		if(level <= 0 && fluid != CauldronLiquid.NONE)
		{
			level = 0;
			fluid = CauldronLiquid.NONE;
		}
		else if(fluid == CauldronLiquid.NONE && level > 0)
		{
			level = 0;
			fluid = CauldronLiquid.NONE;
		}
		if(isActive)
		{
			if(activeTimer++ > 175)
			{
				deactivate();
				setItem(switchedItem);
				layersDrained = 0;
			}	
		}
		if(overstandingRecipe != null)
		{
			if(!checkForLargeRitual(false))
				killRitual();
			else if(overstandingTimer-- <= 100)
			{
				for(TileEntityHarshenDimensionalPedestal pedestal : pedestals)
				{
					if(pedestal.getItem().getItem() == Items.AIR ||  new Random().nextInt(overstandingTimer / 2) == 0)
					{
						Vec3d vec = new Vec3d(pedestal.getPos()).addVector(0.5d, 0.9d, 0.5d);
						HarshenNetwork.sendToPlayersInWorld(world, new MessagePacketSpawnItemParticles(pedestalMap.get(pedestal.getPos()), vec, 
								HarshenUtils.speedToPos(vec, new Vec3d(this.pos).addVector(0.5, 2, 0.5), 20), (float)randPos() + 1f, false, 20, "cauldron_ritual"));
						pedestal.deactiveateNonController();
						pedestal.setItemAir();
						continue;
					}
				}
				ArrayList<BlockPos> localBloodPos = new ArrayList<>();
				for(BlockPos pos : bloodPos)
					if(new Random().nextInt(overstandingTimer / 2) == 0)
						{
							world.setBlockToAir(pos);
							deletedBloodPos.add(pos);
						}
					else localBloodPos.add(pos);
				bloodPos.clear();
				for(BlockPos pos : localBloodPos)
					bloodPos.add(pos);
				boolean flag = !localBloodPos.isEmpty();
				for(TileEntityHarshenDimensionalPedestal pedestal : pedestals)
					if(!pedestal.getItem().isEmpty())
						flag = true;
				if(!flag)
				{
					pedestals.clear();
					setActive(true);
					overstandingRecipe = null;
					pedestalMap.clear();
				}
			}
			for(BlockPos pos : bloodPos)
				for(int i = 0; i < 7; i ++)
					{
						Vec3d vec = new Vec3d(pos).addVector(randPos(), -0.1, randPos());
						HarshenNetwork.sendToPlayersInWorld(world, new MessagePacketSpawnBloodParticle(vec, 
								HarshenUtils.speedToPos(vec, new Vec3d(this.pos).addVector(0.5, 2, 0.5), 30), 1f, false));
					}
		}
			
		if(activeTimer >= drainPos[layersDrained])
		{
			if(layersDrained == 2)
				setItem(switchedItem);
			if(world.isRemote)
				return;
			double[] yPosOfDrains = {0.7D, 0.8D, 0.9D};
			level--;
			for(int i = 0; i < 35; i++)
				HarshenCastle.proxy.spawnParticle(EnumHarshenParticle.CAULDRON,
						new Vec3d(pos).addVector((new Random().nextDouble() / 2) + 0.25D, yPosOfDrains[layersDrained], (new Random().nextDouble() / 2) + 0.25D), new Vec3d(0, 0.01d, 0), 1f, false,
						workingFluid.getStateOrLoc());

			reactivate(1);
		}
	}
	
	public boolean onActivated(EntityPlayer playerIn, EnumHand hand)
	{
		if(isActiveInBackground || isActive)
			return true;
		ItemStack itemstack = playerIn.getHeldItem(hand);
        Item item = itemstack.getItem();
        if (itemstack.isEmpty())
        	return false;
        boolean flag;
        if(item instanceof BloodCollector && (fluid ==  EnumGlassContainer.BLOOD.getType() || fluid == CauldronLiquid.NONE))
        {
        	if(level != 3)
        		if (playerIn.capabilities.isCreativeMode || (!playerIn.capabilities.isCreativeMode && ((BloodCollector)item).remove(playerIn, hand, 3)))
                {
        			this.world.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
        			level ++;
        			fluid = EnumGlassContainer.BLOOD.getType();
        		}
        	return true;
        }
        CauldronLiquid potentionalLiquid = HarshenRegistry.getLiquidFromStack(itemstack);
        if(potentionalLiquid != null && (level <= 0 || (fluid == potentionalLiquid && level + potentionalLiquid.getFillBy() < 4)))
        {
        	fluid = HarshenRegistry.getRelativeFluid(potentionalLiquid);
        	level += potentionalLiquid.getFillBy();
        	itemstack.shrink(1);
        	if(HarshenRegistry.getOutPutItem(potentionalLiquid) != null)
        		HarshenUtils.give(playerIn, hand, HarshenRegistry.getOutPutItem(potentionalLiquid));
        	return true;
        }
        ItemStack potentionalItem = HarshenRegistry.getOutPutItem(fluid);
        if(potentionalItem != null && !itemstack.isEmpty() && potentionalItem.isItemEqual(itemstack) && level - fluid.getFillBy() >= 0)
        {
        	level -= fluid.getFillBy();
        	ItemStack oldStack = itemstack.copy();
        	itemstack.shrink(1);
        	HarshenUtils.give(playerIn, hand, HarshenRegistry.getInputFromOutput(fluid));
        	return true;
        }
        else if(item == HarshenItems.RITUAL_STICK)
        {
        	ItemStack stack = getItem();
        	switch (itemstack.getMetadata()) {
			case 0:
				if(CauldronRecipes.getRecipe(stack, fluid) != null && level == 3)
	            {
	        		isActive = true;
	            	setSwitchedItem(CauldronRecipes.getRecipe(stack, fluid).getOutput());
	            	workingFluid = fluid;
	            	return true;
	            }
				break;
			case 1:
				checkForLargeRitual(true, playerIn);
				return true;
			default:
				break;
			}
		}
        return false;
	}
	
	
	private ArrayList<TileEntityHarshenDimensionalPedestal> pedestals = new ArrayList<>();
	private ArrayList<BlockPos> bloodPos = new ArrayList<>();
	private ArrayList<BlockPos> deletedBloodPos = new ArrayList<>();
	private ArrayList<BlockPos> erroredPositions = new ArrayList<>();
	private ArrayList<Block> blockErrorList = new ArrayList<>();
	private ArrayList<IBlockState> renderBlockErrorList = new ArrayList<>();

	
	private boolean checkForLargeRitual(boolean setRecipe, EntityPlayer... players)
	{
		if(setRecipe)
		{
			deletedBloodPos.clear();
			killRitual();
		}
		ArrayList<BlockPos> bloodPos = new ArrayList<>();
		pedestals.clear();
		blockErrorList.clear();
		renderBlockErrorList.clear();
		if(world.isRemote && setRecipe && players[0] != null && players[0].getUniqueID().equals(HarshenCastle.proxy.getPlayer().getUniqueID()))
			HarshenCastle.proxy.resetErroredPositions();
		erroredPositions.clear();
		ArrayList<Integer> maxList = new ArrayList<>(HarshenUtils.toArray(-4, 5));
		maxList.add(Math.abs(maxList.get(0)));
		maxList.add(Math.abs(maxList.get(1)));
		boolean switchFlag = true;
		for(int x = maxList.get(0); x < maxList.get(1); x++)
			for(int z = maxList.get(0); z < maxList.get(1); z++)
			{
				if(!(maxList.contains(x) && maxList.contains(z)) && switchFlag)
					if(world.getBlockState(pos.add(x, -1, z)).getBlock() != Blocks.OBSIDIAN)
						setError(pos.add(x, -1, z), Blocks.OBSIDIAN);
					else;
				else if(world.getBlockState(pos.add(x, -1, z)).getBlock() == Blocks.OBSIDIAN)
					setError(pos.add(x, -1, z), Blocks.BARRIER);
				switchFlag = !switchFlag;
			}
		for(int x = -1; x < 2; x++)
			for(int z = -1; z < 2; z++)
				if(!(x == 0 && z == 0))
					if(world.getBlockState(pos.add(x, 0, z)).getBlock() != HarshenBlocks.BLOOD_BLOCK && !deletedBloodPos.contains(pos.add(x, 0, z)))
						setError(pos.add(x, 0, z), HarshenBlocks.BLOOD_BLOCK);
					else
						bloodPos.add(pos.add(x, 0, z));
		for(EnumFacing facing : EnumFacing.HORIZONTALS)
		{
			if(world.getBlockState(pos.offset(facing, 2)).getBlock() != HarshenBlocks.BLOOD_BLOCK && !deletedBloodPos.contains(pos.offset(facing, 2)))
				setError(pos.offset(facing, 2), HarshenBlocks.BLOOD_BLOCK);
			else
				bloodPos.add(pos.offset(facing, 2));
			if(world.getBlockState(pos.offset(facing, 3)).getBlock() != HarshenBlocks.HARSHEN_DIMENSIONAL_PEDESTAL)
				setError(pos.offset(facing, 3), HarshenBlocks.HARSHEN_DIMENSIONAL_PEDESTAL);
			else
				pedestals.add((TileEntityHarshenDimensionalPedestal)world.getTileEntity(pos.offset(facing, 3)));
		}
		ArrayList<Integer> pedestalDistanceList = new ArrayList<>(HarshenUtils.toArray(-2, 2));
		for(int x : pedestalDistanceList)
			for(int z : pedestalDistanceList)
				if(world.getBlockState(pos.add(x, 0, z)).getBlock() != HarshenBlocks.HARSHEN_DIMENSIONAL_PEDESTAL)
					setError(pos.add(x, 0, z), HarshenBlocks.HARSHEN_DIMENSIONAL_PEDESTAL);
				else
					pedestals.add((TileEntityHarshenDimensionalPedestal)world.getTileEntity(pos.add(x, 0, z)));
		
		ArrayList<ItemStack> stacks = new ArrayList<>();
		for(TileEntityHarshenDimensionalPedestal pedestal : pedestals)
			stacks.add(pedestal.getItem());
		HereticRitualRecipes recipe = HereticRitualRecipes.getRecipe(getItem(), fluid, stacks);
		if(erroredPositions.isEmpty() && recipe != null && setRecipe)
		{	
			deletedBloodPos = new ArrayList<>(bloodPos);
			for(TileEntityHarshenDimensionalPedestal pedestal : pedestals)
			{
				pedestalMap.put(pedestal.getPos(), pedestal.getItem());
				pedestal.setActiveNonController();
			}
			for(BlockPos pos : bloodPos)
				((BloodBlock)world.getBlockState(pos).getBlock()).setRitualState(pos, true);
			this.overstandingRecipe = recipe;
			this.bloodPos.clear();
			this.bloodPos = bloodPos;
			overstandingTimer = 300;
			workingFluid = fluid;
			setSwitchedItem(recipe.getOutput());
			isActiveInBackground = true;
		}

		if(setRecipe && players[0] != null && world.isRemote)
		{
			if(!erroredPositions.isEmpty())
			{
				players[0].sendStatusMessage(new TextComponentTranslation("ritual.fail.position", erroredPositions.get(0).getX(), erroredPositions.get(0).getY(), erroredPositions.get(0).getZ(),
						blockErrorList.get(0) == Blocks.BARRIER ? I18n.translateToLocal("ritual.not") + " " + blockErrorList.get(1).getLocalizedName() : blockErrorList.get(0).getLocalizedName(),
						blockErrorList.get(1).getLocalizedName()), false);
				for(int i = 0; i < erroredPositions.size(); i++)
					HarshenCastle.proxy.addErroredPosition(new FaceRenderer(erroredPositions.get(i), renderBlockErrorList.get(i)));
			}
			else if(this.overstandingRecipe == null)
				players[0].sendStatusMessage(new TextComponentTranslation("ritual.fail.recipe"), false);

		}
		return this.overstandingRecipe != null && erroredPositions.isEmpty();

	}
	
	private void setError(BlockPos pos, Block expected)
	{
		erroredPositions.add(pos);
		blockErrorList.add(expected);
		blockErrorList.add(world.getBlockState(pos).getBlock());
		renderBlockErrorList.add(expected.getDefaultState());
	}
	
	public void killRitual()
	{
		for(TileEntityHarshenDimensionalPedestal pedestal : pedestals)
			pedestal.deactiveateNonController();
		overstandingRecipe = null;
		workingFluid = CauldronLiquid.NONE;
		isActiveInBackground = false;
		deletedBloodPos.clear();
		bloodPos.clear();
		pedestals.clear();
	}
	
	private boolean particle = false;
	
	public boolean setParticle()
	{
		if(!particle)
		{
			particle = true;
			return false;
		}
		return true;
	}
	
	public CauldronLiquid getFluid() {
		return fluid;
	}
	
	public CauldronLiquid getWorkingFluid() {
		return workingFluid;
	}
	
	public TileEntityHereticCauldron setFluid(CauldronLiquid fluid) {
		this.fluid = fluid;
		return this;
	}
	
	public int getLevel() {
		return level;
	}
	
	public TileEntityHereticCauldron setLevel(int level) {
		this.level = level;
		return this;
	}
	
	public int getActiveTimer()
	{
		return activeTimer;
	}
		
	public TileEntityHereticCauldron setlayersDrained(int layer)
	{
		this.layersDrained = layer;
		return this;
	}
	
	public TileEntityHereticCauldron setActiveTimer(int timer)
	{
		this.activeTimer = timer;
		return this;
	}
	
	public TileEntityHereticCauldron setTimer(int timer)
	{
		this.timer = timer;
		return this;
	}
	
	public TileEntityHereticCauldron setActive(boolean active)
	{
		this.isActive = active;
		return this;
	}
	
	public TileEntityHereticCauldron setHoldingItem(ItemStack item)
	{
		setItem(item);
		return this;
	}
	
	public ItemStack getSwitchedItem()
	{
		return switchedItem;
	}
	
	public TileEntityHereticCauldron setSwitchedItem(ItemStack item)
	{
		this.switchedItem = item;
		return this;
	}

	private void deactivate() {
		this.isActive = false;
		this.isActiveInBackground = false;
		this.activeTimer = 0;
		workingFluid = CauldronLiquid.NONE;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		fluid = CauldronLiquid.getFromName(compound.getString("cauldronType"));
		level = compound.getInteger("cauldronLevel");
		isActive = compound.getBoolean("isActive");
		switchedItem = new ItemStack(compound.getCompoundTag("switchedItemStack"));
		super.readFromNBT(compound);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setString("cauldronType", fluid.getName());
		nbt.setInteger("cauldronLevel", level);
		nbt.setBoolean("isActive", isActive);
		nbt.setTag("switchedItemStack", switchedItem.serializeNBT());
		return super.writeToNBT(nbt);
	}
	


	public void reactivate(int layerAddition) {
		((TileEntityHereticCauldron)world.getTileEntity(pos)).setActiveTimer(activeTimer).setTimer(this.timer).setActive(isActive)
		.setHoldingItem(getItem()).setlayersDrained(layersDrained + layerAddition).setSwitchedItem(switchedItem).setFluid(fluid).setLevel(level);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		if (capability  == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return (T) this.handler;
		return super.getCapability(capability, facing);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return true;
		return super.hasCapability(capability, facing);
	}
}