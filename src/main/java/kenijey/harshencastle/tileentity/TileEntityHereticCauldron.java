package kenijey.harshencastle.tileentity;

import kenijey.harshencastle.base.BaseTileEntityHarshenSingleItemInventory;
import kenijey.harshencastle.blocks.HereticCauldron;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TileEntityHereticCauldron extends BaseTileEntityHarshenSingleItemInventory
{
	
	int active = 0;
	private int activeTimer = 0;
	int layersDrained = 0;
	public boolean isActive = false;
	private ItemStack switchedItem;
	private int[] drainPos = {50, 75, 100, Integer.MAX_VALUE};
	
	@Override
	public void update() {
		active++;
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
			world.setBlockState(pos, ((HereticCauldron)world.getBlockState(pos).getBlock()).removeLayer((world.getBlockState(pos))), 3);
			((TileEntityHereticCauldron)world.getTileEntity(pos)).setActiveTimer(activeTimer).setTimer(active).setActive(isActive).setHoldingItem(getItem()).setlayersDrained(layersDrained + 1).setSwitchedItem(switchedItem);
		}
			
	}
	
	public int getTimer()
	{
		return active;
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
		this.active = timer;
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