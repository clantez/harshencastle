package kenijey.harshencastle.handlers;

import java.util.ArrayList;

import kenijey.harshencastle.potions.HarshenPotions;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HandlerPotion {
	
	private static ArrayList<EntityLivingBase> arrayLivingWithEffect = new ArrayList<EntityLivingBase>();
	private static ArrayList<HandlerHarshenEffect> arrayEffectManager = new ArrayList<HandlerHarshenEffect>();
	
	@SubscribeEvent
	public void livingTick(LivingUpdateEvent event)
	{
		if(event.getEntity().world.isRemote)
			return;
		if(event.getEntityLiving().isPotionActive(HarshenPotions.potionHarshed))
		{
			if(!arrayLivingWithEffect.contains(event.getEntityLiving()))
			{
				arrayLivingWithEffect.add(event.getEntityLiving());
				for(PotionEffect effect : event.getEntityLiving().getActivePotionEffects())
					if(effect.getPotion().equals(HarshenPotions.potionHarshed))
						arrayEffectManager.add(new HandlerHarshenEffect(event.getEntityLiving(), effect.getAmplifier()));
			}
			arrayEffectManager.get(arrayLivingWithEffect.indexOf(event.getEntityLiving())).add();
		}
		else if(arrayLivingWithEffect.contains(event.getEntityLiving()))
		{
			arrayEffectManager.remove(arrayLivingWithEffect.indexOf(event.getEntityLiving()));
			arrayLivingWithEffect.remove(event.getEntityLiving());
		}		
	}
}

class HandlerHarshenEffect
{
	private int timer;
	private final int level;
	private final EntityLivingBase entity;
	private BlockPos position;
	public HandlerHarshenEffect(EntityLivingBase entity, int level)
	{
		this.entity = entity;
		this.position = this.entity.getPosition();
		this.level = level;
	}

	public void add()
	{
		if(timer++ >= 20)
		{
			timer = 0;
			this.entity.attackEntityFrom(DamageSource.MAGIC, (float) Math.floor(this.level * 2.5f));
		}
	}
}
