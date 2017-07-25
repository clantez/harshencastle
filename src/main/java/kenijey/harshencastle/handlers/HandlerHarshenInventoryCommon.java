package kenijey.harshencastle.handlers;

import java.util.Arrays;

import kenijey.harshencastle.base.BaseItemCustomInvEffect;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
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
	
	@SubscribeEvent
	public void onPlayerDeath(LivingDeathEvent event)
	{
		if(event.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)event.getEntity();
			if(this.handler.getStackInSlot(0).getItem() == Items.TOTEM_OF_UNDYING)
			{
				event.setCanceled(true);
				if (player instanceof EntityPlayerMP)
	            {
	                EntityPlayerMP entityplayermp = (EntityPlayerMP)player;
	                entityplayermp.addStat(StatList.getObjectUseStats(Items.TOTEM_OF_UNDYING));
	                CriteriaTriggers.USED_TOTEM.trigger(entityplayermp, this.handler.getStackInSlot(0));
	            }
				
				this.handler.setStackInSlot(0, ItemStack.EMPTY);
				player.getEntityData().setTag("ItemStackHandler", handler.serializeNBT());
				HandlerHarshenInventoryClient.instance.load(player);	
				player.setHealth(1.0F);
				player.clearActivePotions();
				player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 900, 1));
				player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 100, 1));
				player.world.setEntityState(player, (byte)35);
			}
		}
	}
}
