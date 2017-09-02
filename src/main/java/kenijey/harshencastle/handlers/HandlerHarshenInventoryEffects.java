package kenijey.harshencastle.handlers;

import java.util.HashMap;

import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.network.HarshenNetwork;
import kenijey.harshencastle.network.packets.MessagePacketPlayerTeleportEffects;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class HandlerHarshenInventoryEffects 
{
	
	HashMap<EntityPlayer, Integer> cooldownMap = new HashMap<>(HarshenUtils.HASH_LIMIT);
	
	public static boolean keyTeleringDown;

	
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
		if(keyTeleringDown && !cooldownMap.containsKey(event.player) && containsItem(event.player, HarshenItems.telering))
		{
			cooldownMap.put(event.player, 0);
			World world = event.player.world;
			Vec3d vec = new Vec3d(event.player.posX + (event.player.getLookVec().x * 4f),
					event.player.posY + (event.player.getLookVec().y * 4f), event.player.posZ + (event.player.getLookVec().z* 4f));
			BlockPos blockpos = HarshenUtils.getTopBlock(world, new BlockPos(vec));
			if(blockpos.getY() != -1 && !world.isRemote)
			{
				((EntityPlayerMP)event.player).connection.setPlayerLocation(vec.x, blockpos.getY(), vec.z, event.player.rotationYaw, event.player.rotationPitch);
				HarshenNetwork.sendToPlayer(((EntityPlayerMP)event.player), new MessagePacketPlayerTeleportEffects(blockpos));
				world.playSound((EntityPlayer)null, event.player.posX, event.player.posY, event.player.posZ, SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
                event.player.playSound(SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
			}
		}
		
		if(cooldownMap.containsKey(event.player))
		{
			cooldownMap.put(event.player, cooldownMap.get(event.player) + 1);
			if(cooldownMap.get(event.player) >= 15)
				cooldownMap.remove(event.player);	
		}
			
	}
	
	private boolean containsItem(Entity entity, Item item)
	{
		return entity instanceof EntityPlayer && HarshenUtils.getHandler((EntityPlayer) entity).containsItem(item);
	}
}
