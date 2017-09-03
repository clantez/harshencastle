package kenijey.harshencastle.items;

import java.util.List;

import kenijey.harshencastle.base.BaseItemInventory;
import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class ZombiPendant extends BaseItemInventory
{
	public ZombiPendant()
	{
		setUnlocalizedName("zombi_pendant");
		setRegistryName("zombi_pendant");
	}
	
	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.NECK;
	}

}
