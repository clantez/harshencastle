package kenijey.harshencastle.base;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public abstract class BaseTileEntityHarshenSingleItemInventory extends BaseTileEntityHarshenInventory implements net.minecraft.util.ITickable, ICapabilityProvider
{
	
	public BaseTileEntityHarshenSingleItemInventory(){
		super(1);
	}
	
	public boolean isSlotEmpty()
	{	 
		return super.isSlotEmpty(0);
	}
	
	
	public boolean setItem(ItemStack item)
	{
		return super.setItem(0, item);
	}
	
	protected void onItemAdded(int slot)
	{
		onItemAdded();
	}
	
	protected void onItemAdded()
	{
		
	}
		
	public void setItemAir()
	{
		super.setItemAir(0);
	}
	
	public ItemStack getItem()
	{
		return super.getItem(0);
	}
}

