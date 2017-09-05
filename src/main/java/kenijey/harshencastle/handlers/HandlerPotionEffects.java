package kenijey.harshencastle.handlers;

import java.util.ArrayList;
import java.util.UUID;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.damagesource.DamageSourceHarshed;
import kenijey.harshencastle.entity.EntitySoulPart;
import kenijey.harshencastle.potions.HarshenPotions;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HandlerPotionEffects {
	
	private ArrayList<EntityLivingBase> arrayLivingWithEffect = new ArrayList<EntityLivingBase>();
	private ArrayList<HandlerHarshenEffect> arrayEffectManager = new ArrayList<HandlerHarshenEffect>();
	private ArrayList<EntityLivingBase> arrayLivingNoSoul = new ArrayList<EntityLivingBase>();
	private ArrayList<EntityLivingBase> arrayNeedingToBeCured = new ArrayList<EntityLivingBase>();

	
	@SubscribeEvent
	public void livingTick(LivingUpdateEvent event)
	{
		try
		{
			if(event.getEntityLiving() instanceof EntitySoulPart && ((EntityLiving)event.getEntityLiving()).getAttackTarget().isPotionActive(HarshenPotions.potionSoulless))
				((EntityLiving)event.getEntityLiving()).setAttackTarget(null);
		}
		catch (NullPointerException e) {
		}
		if(event.getEntityLiving().isPotionActive(HarshenPotions.potionSoulless))
		{
			if(!arrayLivingNoSoul.contains(event.getEntityLiving()))
			{
				arrayLivingNoSoul.add(event.getEntityLiving());
				IAttributeInstance attribute = event.getEntityLiving().getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
				AttributeModifier modifier = new AttributeModifier(UUID.fromString("81c41407-0bb1-435d-91ca-449b8c8a0eec"), "healthTo1", -(event.getEntityLiving().getMaxHealth() - 2D), 0).setSaved(true);
				if(!attribute.hasModifier(modifier))	
					attribute.applyModifier(modifier);
			}
			if(event.getEntity().world.isRemote && !Minecraft.getMinecraft().entityRenderer.isShaderActive() && event.getEntityLiving().equals(HarshenCastle.proxy.getPlayer()))
				Minecraft.getMinecraft().entityRenderer.loadShader(new ResourceLocation("minecraft", "shaders/post/desaturate.json"));
		}
		else if(arrayLivingNoSoul.contains(event.getEntityLiving()))
		{
			if(event.getEntity().world.isRemote && event.getEntityLiving().equals(HarshenCastle.proxy.getPlayer()))
			{
				arrayLivingNoSoul.remove(event.getEntityLiving());
				Minecraft.getMinecraft().entityRenderer.stopUseShader();
			}
			event.getEntityLiving().getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).removeModifier(UUID.fromString("81c41407-0bb1-435d-91ca-449b8c8a0eec"));
			event.getEntityLiving().setHealth(event.getEntityLiving().getHealth());
		}
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
		if(event.getEntityLiving().isPotionActive(HarshenPotions.potionPure) && !arrayNeedingToBeCured.contains(event.getEntityLiving()))
		{
			arrayNeedingToBeCured.add(event.getEntityLiving());
			ArrayList<Potion> potionsToRemove = new ArrayList<>();
			for(PotionEffect effect : event.getEntityLiving().getActivePotionEffects())
				if(effect.getPotion().isBadEffect())
					potionsToRemove.add(effect.getPotion());
			for(Potion potion : potionsToRemove)
				event.getEntityLiving().removePotionEffect(potion);
		}
		else if(!event.getEntityLiving().isPotionActive(HarshenPotions.potionPure) && arrayNeedingToBeCured.contains(event.getEntityLiving()))
			arrayNeedingToBeCured.remove(event.getEntityLiving());
	}
}

class HandlerHarshenEffect
{
	private int timer = 1;
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
			this.entity.attackEntityFrom(DamageSourceHarshed.getSource(), (float) Math.floor(this.level * 2.5f) + 1);
		}
	}
}
