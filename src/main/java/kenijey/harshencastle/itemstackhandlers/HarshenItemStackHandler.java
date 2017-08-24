package kenijey.harshencastle.itemstackhandlers;

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
}
