package kenijey.harshencastle.handlers;

import java.util.HashMap;

import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.HarshenUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class HandlerHarshenInventoryEffects 
{
	
	HashMap<EntityPlayer, Integer> cooldownMap = new HashMap<>(HarshenUtils.HASH_LIMIT);
	HashMap<EntityPlayer, Integer> chargeMap = new HashMap<>(HarshenUtils.HASH_LIMIT);

	
	@SubscribeEvent
	public void onLivingHurt(LivingHurtEvent event)
	{
		if(containsItem(event.getEntityLiving(), HarshenItems.fearring))
			event.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 30));

		else if(event.getSource() instanceof EntityDamageSource && ((EntityDamageSource)event.getSource()).getTrueSource() instanceof EntityPlayer
				&& containsItem(((EntityDamageSource)event.getSource()).getTrueSource(),  HarshenItems.fearring))
			event.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 150));

	}
	
	@SubscribeEvent
	public void playerTick(PlayerTickEvent event)
	{
		if(event.player.world.isRemote)
			return;
		if(event.player.isSneaking() && event.player.getHeldItemOffhand().getItem() == Item.getItemFromBlock(Blocks.AIR) && !cooldownMap.containsKey(event.player) && containsItem(event.player, HarshenItems.telering))
		{
			if(chargeMap.containsKey(event.player))
				chargeMap.put(event.player, chargeMap.get(event.player) + 1);
			else
				chargeMap.put(event.player, 0);
			if(chargeMap.get(event.player) >= 5)
			{
				chargeMap.remove(event.player);
				cooldownMap.put(event.player, 0);
				Vec3d vec = new Vec3d(event.player.posX + (event.player.getLookVec().x * 4f),
						event.player.posY + (event.player.getLookVec().y * 4f), event.player.posZ + (event.player.getLookVec().z* 4f));
				BlockPos pos = HarshenUtils.getTopBlock(event.player.world, new BlockPos(vec));
				if(pos.getY() != -1)
				((EntityPlayerMP)event.player).connection.setPlayerLocation(vec.x, pos.getY(), vec.z, event.player.rotationYaw, event.player.rotationPitch);
				
			}
		}
		
		if(cooldownMap.containsKey(event.player))
		{
			cooldownMap.put(event.player, cooldownMap.get(event.player) + 1);
			if(cooldownMap.get(event.player) >= 10)
				cooldownMap.remove(event.player);	
		}
			
	}
	
	private boolean containsItem(Entity entity, Item item)
	{
		return entity instanceof EntityPlayer && HarshenUtils.getHandler((EntityPlayer) entity).containsItem(item);
	}
}
