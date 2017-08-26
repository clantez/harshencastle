package kenijey.harshencastle.handlers;

import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.HarshenUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HandlerHarshenInventoryEffects 
{
	
	@SubscribeEvent
	public void onLivingHurt(LivingHurtEvent event)
	{
		if(containsItem(event.getEntityLiving(), HarshenItems.fearring))
			event.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 30));

		else if(event.getSource() instanceof EntityDamageSource && ((EntityDamageSource)event.getSource()).getTrueSource() instanceof EntityPlayer
				&& containsItem(((EntityDamageSource)event.getSource()).getTrueSource(),  HarshenItems.fearring))
			event.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 150));

	}
	
	private boolean containsItem(Entity entity, Item item)
	{
		return entity instanceof EntityPlayer && HarshenUtils.getHandler((EntityPlayer) entity).containsItem(item);
	}
}
