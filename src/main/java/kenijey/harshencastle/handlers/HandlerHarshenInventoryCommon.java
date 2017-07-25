package kenijey.harshencastle.handlers;

import java.util.Arrays;
import java.util.Collections;

import kenijey.harshencastle.base.BaseItemCustomInvEffect;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.items.ItemStackHandler;

public class HandlerHarshenInventoryCommon 
{
		
	private ItemStackHandler handler = new ItemStackHandler(1);
	
	int effectTimedTimer = 0;
	
	Potion[] timedPotions = {MobEffects.REGENERATION, MobEffects.POISON, MobEffects.WITHER, MobEffects.HUNGER, MobEffects.SATURATION};
	Potion[] blackListed = {MobEffects.INSTANT_HEALTH, MobEffects.INSTANT_DAMAGE};
	
	@SubscribeEvent
	public void playerTick(PlayerTickEvent event)
	{
		if(event.player.world.isRemote)
			return;
		this.handler.deserializeNBT(event.player.getEntityData().getCompoundTag("ItemStackHandler"));
		if(this.handler.getStackInSlot(0).getItem() instanceof BaseItemCustomInvEffect)
		{
			PotionEffect potionEffect = ((BaseItemCustomInvEffect)this.handler.getStackInSlot(0).getItem()).effect();
			Potion potion = potionEffect.getPotion();
			if(Arrays.asList(blackListed).contains(potion))
				return;
			if(Arrays.asList(timedPotions).contains(potion))
				if(effectTimedTimer++ % 200 == 0)
					potion.performEffect(event.player, 0);
				else;
			else
				event.player.addPotionEffect(potionEffect);
		}
	}
}
