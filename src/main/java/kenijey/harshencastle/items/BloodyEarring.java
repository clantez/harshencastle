package kenijey.harshencastle.items;

import java.util.List;

import kenijey.harshencastle.base.BaseItemInventory;
import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import kenijey.harshencastle.interfaces.IBloodSupply;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class BloodyEarring extends BaseItemInventory implements IBloodSupply
{
	public BloodyEarring()
	{
		setUnlocalizedName("bloody_earring");
		setRegistryName("bloody_earring");
	}
	
	@Override
	public int getAmountPerSecond() {
		return 1;
	}

	@Override
	public int ticksUntillUsed() {
		return 100;
	}

	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.LEFT_EAR;
	}

	@Override
	public void onTick(EntityPlayer player, int tick) {
		if(tick % 140 == 0)
			player.heal(2f);
	}
}
