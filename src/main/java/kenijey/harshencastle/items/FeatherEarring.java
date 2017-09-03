package kenijey.harshencastle.items;

import kenijey.harshencastle.base.BaseItemInventory;
import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

public class FeatherEarring extends BaseItemInventory
{
	public FeatherEarring()
	{
		setUnlocalizedName("feather_earring");
		setRegistryName("feather_earring");
	}

	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.LEFT_EAR;
	}

	@Override
	public void onTick(EntityPlayer player, int tick) {
		player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 50, 0, false, false));
		
	}

}
