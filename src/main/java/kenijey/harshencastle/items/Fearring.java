package kenijey.harshencastle.items;

import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import kenijey.harshencastle.interfaces.HarshenEvent;
import kenijey.harshencastle.interfaces.IHarshenProvider;
import kenijey.harshencastle.objecthandlers.PlayerPunchedEvent;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class Fearring extends Item implements IHarshenProvider
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