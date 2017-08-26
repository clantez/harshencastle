package kenijey.harshencastle.itemstackhandlers;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class HarshenItemStackHandler extends ItemStackHandler 
{
	public HarshenItemStackHandler(int amount) {
		super(amount);
	}
	
	@Override
	public int getSlotLimit(int slot) {
		return 1;
	}
	
	public boolean containsItem(Item item)
	{
		for(ItemStack stack : stacks)
			if(stack.getItem() == item)
				return true;
		return false;
	}
}
