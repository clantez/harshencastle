package kenijey.harshencastle.objecthandlers;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class HarshenItemStackHandler extends ItemStackHandler 
{
	public HarshenItemStackHandler(int amount) {
		super(amount);
	}
	
	private int slotLimit = 64;
	
	public void setSlotLimit(int slotLimit) {
		this.slotLimit = slotLimit;
	}
	
	@Override
	public int getSlotLimit(int slot) {
		return slotLimit;
	}
	
	public boolean containsItem(Item item)
	{
		for(ItemStack stack : stacks)
			if(stack.getItem() == item)
				return true;
		return false;
	}
}
