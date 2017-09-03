package kenijey.harshencastle.items;

import kenijey.harshencastle.base.BaseItemInventory;
import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

public class OneRing extends BaseItemInventory
{
	public OneRing()
	{
		setUnlocalizedName("one_ring");
		setRegistryName("one_ring");
	}

	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.RING1;
	}

	@Override
	public void onTick(EntityPlayer player, int tick) {
		player.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 7, 0, false, false));
	}

}
