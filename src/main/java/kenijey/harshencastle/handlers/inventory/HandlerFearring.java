package kenijey.harshencastle.handlers.inventory;

import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.interfaces.HarshenEvent;
import kenijey.harshencastle.objecthandlers.PlayerPunchedEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.GameType;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;

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
