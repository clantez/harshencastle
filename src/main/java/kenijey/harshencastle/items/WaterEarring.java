package kenijey.harshencastle.items;

import kenijey.harshencastle.base.BaseItemInventory;
import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

public class WaterEarring extends BaseItemInventory
{

	public WaterEarring() {
		setRegistryName("water_earring");
		setUnlocalizedName("water_earring");
	}
	
	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.LEFT_EAR;
	}
	
	@Override
	public void onTick(EntityPlayer player, int tick) {
		player.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 105));
	}

}
