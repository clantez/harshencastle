package kenijey.harshencastle.interfaces;

import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import net.minecraft.item.ItemStack;

public interface IVanillaProvider 
{
	public EnumInventorySlots getSlot();
	
	public default int toolTipLines(){ return 2;};
	
	public default Object getProvider(ItemStack stack){return null;};
	
	public default boolean multiplyEvent(ItemStack stack){return true;};
}
