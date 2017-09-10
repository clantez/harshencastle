package kenijey.harshencastle.handlers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.network.HarshenNetwork;
import kenijey.harshencastle.network.packets.MessagePacketPlayerTeleportEffects;
import kenijey.harshencastle.network.packets.MessagePacketSummonFirework;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
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
				{event.setAmount(event.getAmount() + HarshenUtils.getItemCount(player, HarshenItems.punchy_ring) * 2);System.out.println(event.getAmount());}
		}
		if(HarshenUtils.containsItem(event.getEntityLiving(), HarshenItems.zombi_pendant) &&
				(event.getSource() instanceof EntityDamageSource && ((EntityDamageSource)event.getSource()).getTrueSource() instanceof EntityZombie &&
						!(((EntityDamageSource)event.getSource()).getTrueSource() instanceof EntityPigZombie)))
			event.setAmount(1);
		if(HarshenUtils.containsItem(event.getEntityLiving(), HarshenItems.soul_shield))
			for(int i = 0; i < HarshenUtils.getHandler((EntityPlayer) event.getEntityLiving()).getSlots(); i++)
				if(HarshenUtils.getHandler((EntityPlayer) event.getEntityLiving()).getStackInSlot(i).getItem() == HarshenItems.soul_shield)
				{
					HarshenUtils.damageFirstOccuringItem((EntityPlayer) event.getEntityLiving(), HarshenItems.soul_shield, (int) event.getAmount() * 2);
					event.setAmount(0);
				}
		if(HarshenUtils.containsItem(event.getEntityLiving(), HarshenItems.elytra_pendant)&& Arrays.asList(DamageSource.FLY_INTO_WALL, DamageSource.FALL).contains(event.getSource())
				&& event.getEntityLiving().getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == Items.ELYTRA)
			event.setCanceled(true);

			
	}
	
	@SubscribeEvent
	public void onBlockBroken(HarvestDropsEvent event)
	{
		if(HarshenUtils.containsItem(event.getHarvester(), HarshenItems.fiery_ring))
			HarshenUtils.cookAndReplaceStackList(event.getDrops());
	}
	
	ArrayList<Long> clicksClicked = new ArrayList<>();
	
	@SubscribeEvent
	public void playerClick(PlayerInteractEvent.RightClickBlock event)
	{
		if(!clicksClicked.contains(System.currentTimeMillis()))
			AttemptFirework(event.getEntityPlayer());
		clicksClicked.add(System.currentTimeMillis());	
	}
	
	@SubscribeEvent
	public void playerClicked(PlayerInteractEvent.RightClickEmpty event)
	{
		if(!clicksClicked.contains(System.currentTimeMillis()))
			AttemptFirework(event.getEntityPlayer());
		clicksClicked.add(System.currentTimeMillis());	
	}
	
	
	private void AttemptFirework(EntityPlayer player)
	{
		if(HarshenUtils.containsItem(player, HarshenItems.elytra_pendant) && player.getHeldItemMainhand().isEmpty() && player.isElytraFlying())
		{
			HarshenNetwork.sendToServer(new MessagePacketSummonFirework());

			Vec3d vec3d = player.getLookVec();
			player.motionX += vec3d.x * 0.1D + (vec3d.x * 2.5D - player.motionX) * 0.5D;
	        player.motionY += vec3d.y * 0.1D + (vec3d.y * 2.5D - player.motionY) * 0.5D;
	        player.motionZ += vec3d.z * 0.1D + (vec3d.z * 2.5D - player.motionZ) * 0.5D;
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
			if(HarshenUtils.containsItem(player, HarshenItems.fiery_ring))
				HarshenUtils.cookAndReplaceList(event.getDrops());
		}
	}
	
	public static void ringEvent(EntityPlayer player, int ringType)
	{
		if(ringType < 2)
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
					HarshenNetwork.sendToPlayer(player, new MessagePacketPlayerTeleportEffects(vecPos));
					((EntityPlayerMP)player).setPositionAndUpdate(vecPos.x, vecPos.y, vecPos.z);
					world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
	                player.playSound(SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
	                player.addExhaustion(0.5F);
	                HarshenUtils.damageFirstOccuringItem(player, ringTypeItem.get(ringType));
				}
			}			
		}
		else if(ringType == 2 && HarshenUtils.containsItem(player, HarshenItems.combat_pendant))
		{
			World world = player.world;
			int range = 5;
			Vec3d vec = player.getLookVec().normalize();
			EntityLiving entityToAttack = null;
			for(int i = 1; i < range; i++)
			{
			    AxisAlignedBB aabb = new AxisAlignedBB(player.posX + vec.x * i - 1, player.posY + vec.y * i - 1, player.posZ + vec.z * i - 1, player.posX + vec.x * i + 2, player.posY + vec.y * i + 2, player.posZ + vec.z * i + 2);
			    List<EntityLiving> list = world.getEntitiesWithinAABB(EntityLiving.class, aabb);
			    double box = 4;
			    if(list.isEmpty())
			    	list = world.getEntitiesWithinAABB(EntityLiving.class, new AxisAlignedBB(player.posX - box, player.posY - box, player.posZ - box, player.posX + box, player.posY + box, player.posZ + box));
			    if(!list.isEmpty())
			    {
			    	entityToAttack = list.get(0);
			    	break;
			    }
			}
			if(entityToAttack != null)
			{
				Vec3d position = entityToAttack.getPositionVector();
				Vec3d playerPosNoY = position.addVector(movePos(), 0, movePos());
				Vec3d pos = new Vec3d(playerPosNoY.x, HarshenUtils.getTopBlock(world, new BlockPos(playerPosNoY)).getY(), playerPosNoY.z);
				double d0 = position.x - pos.x;
	            double d1 = position.y - (pos.y + (double)player.getEyeHeight() - entityToAttack.height / 2f + 0.1f);
	            double d2 = position.z - pos.z;
	            double d3 = (double)MathHelper.sqrt(d0 * d0 + d2 * d2);
	            float yaw = (float)(MathHelper.atan2(d2, d0) * (180D / Math.PI)) - 90.0F;
	            float pitch = (float)(-(MathHelper.atan2(d1, d3) * (180D / Math.PI)));
				((EntityPlayerMP)player).velocityChanged = true;
				((EntityPlayerMP)player).connection.setPlayerLocation(pos.x, pos.y, pos.z, yaw, pitch);
			}
		}
	}
	
	private static double movePos()
	{
		return (new Random().nextDouble() - 0.5D) * 2D;
	}
	
	private static float updateRotation(float origin, float addAmount, float distanceTime)
    {
        float f = MathHelper.wrapDegrees(addAmount - origin);

        if (f > distanceTime)
        {
            f = distanceTime;
        }

        if (f < -distanceTime)
        {
            f = -distanceTime;
        }

        return origin + f;
    }
}
