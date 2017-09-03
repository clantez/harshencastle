package kenijey.harshencastle.handlers;

import java.util.ArrayList;

import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.network.HarshenNetwork;
import kenijey.harshencastle.network.packets.MessagePacketItemInventoryDamaged;
import kenijey.harshencastle.network.packets.MessagePacketPlayerTeleportEffects;
import kenijey.harshencastle.objecthandlers.HarshenItemStackHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HandlerHarshenInventoryEffects 
{	
	@SubscribeEvent
	public void onLivingHurt(LivingHurtEvent event)
	{
		if(HarshenUtils.containsItem(event.getEntityLiving(), HarshenItems.fearring))
			event.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 30));

		if(event.getSource() instanceof EntityDamageSource && ((EntityDamageSource)event.getSource()).getTrueSource() instanceof EntityPlayer)
		{
			EntityPlayer player = ((EntityPlayer)((EntityDamageSource)event.getSource()).getTrueSource());
			if(HarshenUtils.containsItem(player,  HarshenItems.fearring))
				event.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 150));
			if(HarshenUtils.containsItem(player, HarshenItems.punchy_ring) && player.getHeldItemMainhand().getItem() == Items.AIR)
				event.setAmount(event.getAmount() + 1);
		}
		if(HarshenUtils.containsItem(event.getEntityLiving(), HarshenItems.zombi_pendant) &&
				(event.getSource() instanceof EntityDamageSource && ((EntityDamageSource)event.getSource()).getTrueSource() instanceof EntityZombie &&
						!(((EntityDamageSource)event.getSource()).getTrueSource() instanceof EntityPigZombie)))
			event.setAmount(event.getAmount() - 1);
		if(HarshenUtils.containsItem(event.getEntityLiving(), HarshenItems.soul_shield))
			for(int i = 0; i < HarshenUtils.getHandler((EntityPlayer) event.getEntityLiving()).getSlots(); i++)
				if(HarshenUtils.getHandler((EntityPlayer) event.getEntityLiving()).getStackInSlot(i).getItem() == HarshenItems.soul_shield)
				{
					HarshenUtils.damageFirstOccuringItem((EntityPlayer) event.getEntityLiving(), HarshenItems.soul_shield, (int) event.getAmount() * 2);
					event.setAmount(0);
				}
	}
	
	@SubscribeEvent
	public void onLivingDeath(LivingDropsEvent event)
	{
		if(event.getSource() instanceof EntityDamageSource && ((EntityDamageSource)event.getSource()).getTrueSource() instanceof EntityPlayer)
		{
			ArrayList<EntityItem> drops = new ArrayList<EntityItem>();
			EntityPlayer player = ((EntityPlayer)((EntityDamageSource)event.getSource()).getTrueSource());
			if(HarshenUtils.containsItem(player, HarshenItems.looting_earring))
				for(EntityItem e : event.getDrops())
					drops.add(new EntityItem(e.world, e.posX, e.posY, e.posZ, e.getItem()));
			event.getDrops().addAll(drops);
		}
	}
	
	public static void ringEvent(EntityPlayer player, int ringType)
	{
		ArrayList<Item> ringTypeItem = new ArrayList<Item>();
		ringTypeItem.add(HarshenItems.telering);
		ringTypeItem.add(HarshenItems.minering);
		if(HarshenUtils.containsItem(player, ringTypeItem.get(ringType)))
		{
			World world = player.world;
			Vec3d vec = new Vec3d(player.posX + (player.getLookVec().x * 4f),
					player.posY + (player.getLookVec().y * 4f), player.posZ + (player.getLookVec().z* 4f));
			BlockPos blockpos = ringType == 0? HarshenUtils.getTopBlock(world, vec) : HarshenUtils.getBottomBlockAir(world, vec);
			Vec3d vecPos = new Vec3d(blockpos).addVector(0.5, 0, 0.5);
			if(blockpos.getY() != -1 && player.getFoodStats().getFoodLevel() > 0)
			{
				((EntityPlayerMP)player).velocityChanged = true;
				((EntityPlayerMP)player).fallDistance = 0;
				HarshenNetwork.sendToPlayer(((EntityPlayerMP)player), new MessagePacketPlayerTeleportEffects(vecPos));
				((EntityPlayerMP)player).setPositionAndUpdate(vecPos.x, vecPos.y, vecPos.z);
				world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
                player.playSound(SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
                player.addExhaustion(0.5F);
                HarshenUtils.damageFirstOccuringItem(player, ringTypeItem.get(ringType));
			}
		}		
	}
}
