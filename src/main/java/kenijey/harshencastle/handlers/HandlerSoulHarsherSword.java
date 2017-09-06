package kenijey.harshencastle.handlers;

import com.google.common.collect.Lists;

import kenijey.harshencastle.armor.HarshenArmors;
import kenijey.harshencastle.base.BaseHarshenSword;
import kenijey.harshencastle.items.HarshenProps;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
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
			if((event.getSource() instanceof EntityDamageSource && ((EntityDamageSource)event.getSource()).getTrueSource() instanceof EntityLivingBase
					&& (((EntityLivingBase)((EntityDamageSource)event.getSource()).getTrueSource()).getHeldItemMainhand().getItem() instanceof BaseHarshenSword ||
						((EntityLivingBase)((EntityDamageSource)event.getSource()).getTrueSource()).getHeldItemMainhand().getItem() instanceof HarshenProps)
				   &&!(Lists.newArrayList(event.getEntityLiving().getArmorInventoryList().iterator()).get(3).getItem() == HarshenArmors.harshen_jaguar_armor_helmet
					&& Lists.newArrayList(event.getEntityLiving().getArmorInventoryList().iterator()).get(2).getItem() == HarshenArmors.harshen_jaguar_armor_chestplate
					&& Lists.newArrayList(event.getEntityLiving().getArmorInventoryList().iterator()).get(1).getItem() == HarshenArmors.harshen_jaguar_armor_leggings
					&& Lists.newArrayList(event.getEntityLiving().getArmorInventoryList().iterator()).get(0).getItem() == HarshenArmors.harshen_jaguar_armor_boots)))
				event.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.WITHER, 150, 1));
		}
		catch (ClassCastException clazz){}
	}
}
