package kenijey.harshencastle.interfaces;

import kenijey.harshencastle.enums.inventory.EnumInventorySlots;

public interface IHarshenProvider 
{
	public EnumInventorySlots getSlot();
	
	public void onTick(int tick);
}
