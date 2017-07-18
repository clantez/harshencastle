package kenijey.harshencastle.handlers;

import kenijey.harshencastle.items.SoulHarsherSword;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HandlerSoulHarsherSword 
{
	@SubscribeEvent
	public void attackEntity(LivingHurtEvent event)
	{
		try
		{
			if(event.getSource() instanceof EntityDamageSource && ((EntityDamageSource)event.getSource()).getTrueSource() instanceof EntityLivingBase
					&& (((EntityLivingBase)((EntityDamageSource)event.getSource()).getTrueSource()).getHeldItemMainhand().getItem() instanceof SoulHarsherSword)
					|| ((EntityLivingBase)((EntityDamageSource)event.getSource()).getTrueSource()).getHeldItemOffhand().getItem() instanceof SoulHarsherSword)
				event.getEntityLiving().addPotionEffect(new PotionEffect(Potion.getPotionById(20), 150, 1));
		}
		catch (ClassCastException clazz){}
	}
}
