package kenijey.harshencastle.items;

import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.api.EnumInventorySlots;
import kenijey.harshencastle.api.HarshenEvent;
import kenijey.harshencastle.api.IHarshenProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class SoulShield extends Item implements IHarshenProvider
{

	public SoulShield() {
		setRegistryName("soul_shield");
		setUnlocalizedName("soul_shield");
		setMaxDamage(500);
	}
	
	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.NECK;
	}
	
	@Override
	public Object getProvider(ItemStack stack) {
		return new SoulShieldHandler();
	}
	
	public class SoulShieldHandler
	{
		@HarshenEvent
		public void onLivingHurt(LivingHurtEvent event)
		{
			HarshenUtils.damageFirstOccuringItem((EntityPlayer) event.getEntityLiving(), HarshenItems.SOUL_SHIELD, (int) event.getAmount() * 2);
			event.setAmount(0);
		}
	}

}
