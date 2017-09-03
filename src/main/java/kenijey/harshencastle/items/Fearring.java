package kenijey.harshencastle.items;

import java.util.List;

import kenijey.harshencastle.base.BaseItemInventory;
import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class Fearring extends BaseItemInventory
{
	public Fearring()
	{
		setUnlocalizedName("fearring");
		setRegistryName("fearring");
	}

	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.LEFT_EAR;
	}

	@Override
	public void onTick(EntityPlayer player, int tick) {
		
		
	}

}