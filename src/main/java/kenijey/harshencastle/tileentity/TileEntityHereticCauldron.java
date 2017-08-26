package kenijey.harshencastle.tileentity;

import java.util.HashMap;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.base.BaseTileEntityHarshenSingleItemInventory;
import kenijey.harshencastle.blocks.HereticCauldron;
import kenijey.harshencastle.enums.blocks.EnumHetericCauldronFluidType;
import kenijey.harshencastle.items.BloodCollector;
import kenijey.harshencastle.recipies.CauldronRecipes;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.UniversalBucket;

public class TileEntityHereticCauldron extends BaseTileEntityHarshenSingleItemInventory
{
	
	private int activeTimer = 0;
	int layersDrained = 0;
	public boolean isActive = false;
	private ItemStack switchedItem;
	private int[] drainPos = {50, 75, 100, Integer.MAX_VALUE};
	public static final HashMap<EnumHetericCauldronFluidType, Item> fluidMap = new HashMap<>(HarshenUtils.HASH_LIMIT);
	private EnumHetericCauldronFluidType fluid = EnumHetericCauldronFluidType.NONE;
	private int level = 1;
	
	@Override
	public void tick() {
		if(isActive)
		{
			if(activeTimer++ > 175)
			{
				deactivate();
				setItem(switchedItem);
				layersDrained = 0;
			}	
		}
		if(activeTimer >= drainPos[layersDrained])
		{
			if(layersDrained == 2)
				setItem(switchedItem);
			level = MathHelper.clamp(level - 1, 1, 3);
			((TileEntityHereticCauldron)world.getTileEntity(pos)).setActiveTimer(activeTimer).setTimer(this.timer).setActive(isActive).setHoldingItem(getItem()).setlayersDrained(layersDrained + 1).setSwitchedItem(switchedItem);
		}
			
	}
	
	public boolean onActivated(EntityPlayer playerIn, EnumHand hand)
	{
		ItemStack itemstack = playerIn.getHeldItem(hand);
        Item item = itemstack.getItem();
        if(isActive)
        	return true;
        if (itemstack.isEmpty())
        	return false;
        
        if(item instanceof BloodCollector && (fluid ==  EnumHetericCauldronFluidType.BLOOD || fluid == EnumHetericCauldronFluidType.NONE))
        {
        	if(level != 3)
        		if (playerIn.capabilities.isCreativeMode || (!playerIn.capabilities.isCreativeMode && ((BloodCollector)item).remove(playerIn, hand, 3)))
                {
        			this.world.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
        			level += fluid == EnumHetericCauldronFluidType.BLOOD ? 1 : 0;
        			fluid = EnumHetericCauldronFluidType.BLOOD;
        		}
        	return true;
        }
        if(fluidMap.containsValue(item) && fluid == EnumHetericCauldronFluidType.NONE)
        {
        	EnumHetericCauldronFluidType[] type = new EnumHetericCauldronFluidType[fluidMap.keySet().size()];
        	playerIn.setHeldItem(hand, ItemStack.EMPTY);
        	give(playerIn, hand, new ItemStack(Items.BUCKET));
        	level = 3;
			fluid = fluidMap.keySet().toArray(type)[valueOfLevel(item)];
	        this.world.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
	        return true;
        }
        else if(item instanceof UniversalBucket && fluid == EnumHetericCauldronFluidType.NONE)
        {
        	itemstack.shrink(1);
        	give(playerIn, hand, new ItemStack(Items.BUCKET));
        	level = 3;
        	fluid = EnumHetericCauldronFluidType.getFromFluid(((UniversalBucket)item).getFluid(itemstack).getFluid());
	        this.world.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
	        return true;
        }
        else if (item == Items.BUCKET && fluid != EnumHetericCauldronFluidType.BLOOD)
        {
            if (level == 3)
            {
                itemstack.shrink(1);
                this.world.playSound((EntityPlayer)null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                ItemStack stack = fluidMap.containsKey(fluid) ?  new ItemStack(fluidMap.get(fluid)) : FluidUtil.getFilledBucket(new FluidStack(FluidRegistry.getFluid(fluid.getName()), 1000));
            	give(playerIn, hand, stack);
            	level = 1;
                fluid = EnumHetericCauldronFluidType.NONE;	
            }
            return true;
        }
        else if(item == HarshenItems.ladle && level == 3)
        {
        	ItemStack stack = getItem();
        	if(CauldronRecipes.getRecipe(stack) != null && CauldronRecipes.getRecipe(stack).getCatalyst() == fluid)
            {
        		isActive = true;
            	setSwitchedItem(CauldronRecipes.getRecipe(stack).getOutput());
            	return true;
            }
		}
        return false;
	}
	
	private boolean give(EntityPlayer playerIn, EnumHand hand, ItemStack stack)
	{
		if(playerIn.getHeldItem(hand).isEmpty())
            playerIn.setHeldItem(hand, stack);
        else if (!playerIn.inventory.addItemStackToInventory(stack))
            playerIn.dropItem(stack, false);
		return true;
	}
	
	public EnumHetericCauldronFluidType getFluid() {
		return fluid;
	}
	
	public void setFluid(EnumHetericCauldronFluidType fluid) {
		this.fluid = fluid;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	private int valueOfLevel(Item item)
	{
		int i = 0;
		for(Item type : fluidMap.values())
		{
			if(type == item)
				break;
			i++;
		}
		return i;
			
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
	
	public void setSwitchedItem(ItemStack item)
	{
		this.switchedItem = item;
	}

	private void deactivate() {
		this.isActive = false;
		this.activeTimer = 0;
	}
}