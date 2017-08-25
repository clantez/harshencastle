package kenijey.harshencastle.interfaces;

import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import net.minecraft.entity.player.EntityPlayer;

public interface IHarshenProvider 
{
	public EnumInventorySlots getSlot();
	
	public void onTick(EntityPlayer player, int tick);
}
