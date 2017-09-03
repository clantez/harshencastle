package kenijey.harshencastle.items;

import kenijey.harshencastle.base.BaseItemInventory;
import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

public class Telering extends BaseItemInventory
{
	public Telering()
	{
		setUnlocalizedName("telering");
		setRegistryName("telering");
		setMaxDamage(400);
	}
	
	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.RING1;
	}

	@Override
	public void onTick(EntityPlayer player, int tick) {
		player.addPotionEffect(new PotionEffect(MobEffects.LUCK, 100, 0, false, false));
	}
}
