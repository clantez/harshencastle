package kenijey.harshencastle.interfaces;

import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import net.minecraft.entity.player.EntityPlayer;

public interface IHarshenProvider 
{
	public EnumInventorySlots getSlot();
	
	public default void onTick(EntityPlayer player, int tick){};
		
	public default void onRemove(EntityPlayer player){};
	
	public default void onAdd(EntityPlayer player){};
}
