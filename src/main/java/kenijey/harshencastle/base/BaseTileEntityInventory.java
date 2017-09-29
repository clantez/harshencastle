package kenijey.harshencastle.base;

import kenijey.harshencastle.base.BaseTileEntityHarshenInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;

public abstract class BaseTileEntityInventory extends BaseTileEntityHarshenInventory
{	
	public BaseTileEntityInventory(int slotAmounts) {
		super(slotAmounts);
	}
}
