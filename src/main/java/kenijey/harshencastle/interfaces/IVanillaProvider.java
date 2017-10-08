package kenijey.harshencastle.interfaces;

import kenijey.harshencastle.enums.inventory.EnumInventorySlots;

public interface IVanillaProvider 
{
	public EnumInventorySlots getSlot();
	
	public default int toolTipLines(){ return 2;};
}
