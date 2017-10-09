package kenijey.harshencastle.items;

import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import kenijey.harshencastle.interfaces.IHarshenProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;

public class Telering extends Item implements IHarshenProvider
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
		player.addPotionEffect(new PotionEffect(MobEffects.LUCK, 105, 0, false, false));
	}
}
