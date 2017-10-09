package kenijey.harshencastle.items;

import kenijey.harshencastle.base.BaseItemInventory;
import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import kenijey.harshencastle.interfaces.HarshenEvent;
import kenijey.harshencastle.objecthandlers.PlayerPunchedEvent;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

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
	public Object getProvider(ItemStack stack) {
		return new HandlerFearring();
	}
	
	public class HandlerFearring
	{
		@HarshenEvent
		public void onPlayerTick(LivingHurtEvent event)
		{
			event.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 30));
		}
		
		@HarshenEvent
		public void onPlayerPunch(PlayerPunchedEvent event)
		{
			event.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 150));
		}
	}

}