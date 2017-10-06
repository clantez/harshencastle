package kenijey.harshencastle.handlers;

import java.util.ArrayList;

import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.armor.HarshenArmors;
import kenijey.harshencastle.potions.HarshenPotions;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.EntityEquipmentSlot.Type;
import net.minecraft.item.ItemArmor;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HandlerHarshenArmourEffects 
{
	ArrayList<ItemArmor> allArmour = new ArrayList<ItemArmor>(HarshenUtils.toArray(HarshenArmors.harshen_jaguar_armor_boots, HarshenArmors.harshen_jaguar_armor_leggings,
			HarshenArmors.harshen_jaguar_armor_chestplate, HarshenArmors.harshen_jaguar_armor_helmet));
	@SubscribeEvent
	public void onEntityTick(LivingUpdateEvent event)
	{
		int i = 0;
		for(EntityEquipmentSlot slot : EntityEquipmentSlot.values())
			if(slot.getSlotType() == Type.ARMOR && allArmour.contains(event.getEntityLiving().getItemStackFromSlot(slot).getItem()))
				i++;
		if(i == 4)
		{
			event.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 225, 0, false, false));
			event.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.SPEED, 30, 0, false, false));
			event.getEntityLiving().addPotionEffect(new PotionEffect(HarshenPotions.potionSoulless, 330, 0, false, false));
		}
				
	}
}
