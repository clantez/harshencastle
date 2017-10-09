package kenijey.harshencastle.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.network.HarshenNetwork;
import kenijey.harshencastle.network.packets.MessagePacketPlayerTeleportEffects;
import kenijey.harshencastle.network.packets.MessagePacketReviveInventory;
import kenijey.harshencastle.network.packets.MessagePacketSummonFirework;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
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
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketCombatEvent;
import net.minecraft.potion.PotionEffect;
import net.minecraft.scoreboard.IScoreCriteria;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Team;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;

public class HandlerHarshenInventoryEffects 
{	
	
//	@SubscribeEvent
//	public void onLivingHurt(LivingHurtEvent event)
//	{
//		if(HarshenUtils.containsItem(event.getEntityLiving(), HarshenItems.FEARRING))
//			event.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 30));

//		if(event.getSource() instanceof EntityDamageSource && ((EntityDamageSource)event.getSource()).getTrueSource() instanceof EntityPlayer)
//		{
//			EntityPlayer player = ((EntityPlayer)((EntityDamageSource)event.getSource()).getTrueSource());
//			if(HarshenUtils.containsItem(player,  HarshenItems.FEARRING))
//				event.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 150));
//			if(HarshenUtils.containsItem(player, HarshenItems.PUNCHY_RING) && player.getHeldItemMainhand().getItem() == Items.AIR)
//				event.setAmount(event.getAmount() + HarshenUtils.getItemCount(player, HarshenItems.PUNCHY_RING) * 2);
//		}
//		if(HarshenUtils.containsItem(event.getEntityLiving(), HarshenItems.ZOMBI_PENDANT) &&
//				(event.getSource() instanceof EntityDamageSource && ((EntityDamageSource)event.getSource()).getTrueSource() instanceof EntityZombie &&
//						!(((EntityDamageSource)event.getSource()).getTrueSource() instanceof EntityPigZombie)))
//			event.setAmount(1);
//		if(HarshenUtils.containsItem(event.getEntityLiving(), HarshenItems.SOUL_SHIELD))
//			for(int i = 0; i < HarshenUtils.getHandler((EntityPlayer) event.getEntityLiving()).getSlots(); i++)
//				if(HarshenUtils.getHandler((EntityPlayer) event.getEntityLiving()).getStackInSlot(i).getItem() == HarshenItems.SOUL_SHIELD)
//				{
//					HarshenUtils.damageFirstOccuringItem((EntityPlayer) event.getEntityLiving(), HarshenItems.SOUL_SHIELD, (int) event.getAmount() * 2);
//					event.setAmount(0);
//				}
//		if(HarshenUtils.containsItem(event.getEntityLiving(), HarshenItems.ELYTRA_PENDANT)&& HarshenUtils.toArray(DamageSource.FLY_INTO_WALL, DamageSource.FALL).contains(event.getSource())
//				&& event.getEntityLiving().getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == Items.ELYTRA)
//			event.setCanceled(true);		
//	}
	
//	@SubscribeEvent
//	public void onPlayerTick(PlayerTickEvent event)
//	{
//		event.player.capabilities.allowFlying = HarshenUtils.toArray(GameType.ADVENTURE, GameType.SURVIVAL).contains(event.side == Side.SERVER ?
//				((EntityPlayerMP)event.player).interactionManager.getGameType() : net.minecraft.client.Minecraft.getMinecraft().playerController.getCurrentGameType()) 
//				? HarshenUtils.containsItem(event.player, HarshenItems.RING_OF_FLIGHT) : event.player.capabilities.allowFlying;
//		if(event.player.capabilities.isFlying && !event.player.capabilities.allowFlying)
//			event.player.capabilities.isFlying = false;
//	}
	
//	@SubscribeEvent
//	public void onBlockBroken(HarvestDropsEvent event)
//	{
//		if(HarshenUtils.containsItem(event.getHarvester(), HarshenItems.FIERY_RING))
//			HarshenUtils.cookAndReplaceStackList(event.getDrops());
//	}
	
//	ArrayList<Long> clicksClicked = new ArrayList<>();
	
//	@SubscribeEvent
//	public void playerClick(PlayerInteractEvent.RightClickBlock event)
//	{
//		if(!clicksClicked.contains(System.currentTimeMillis()))
//			AttemptFirework(event.getEntityPlayer());
//		clicksClicked.add(System.currentTimeMillis());	
//	}
//	
//	@SubscribeEvent
//	public void playerClicked(PlayerInteractEvent.RightClickEmpty event)
//	{
//		if(!clicksClicked.contains(System.currentTimeMillis()))
//			AttemptFirework(event.getEntityPlayer());
//		clicksClicked.add(System.currentTimeMillis());	
//	}
	
	
	
		
//	@SubscribeEvent
//	public void onLivingDrops(LivingDropsEvent event)
//	{
//		if(event.getSource() instanceof EntityDamageSource && ((EntityDamageSource)event.getSource()).getTrueSource() instanceof EntityPlayer)
//		{
//			ArrayList<EntityItem> drops = new ArrayList<EntityItem>();
//			EntityPlayer player = ((EntityPlayer)((EntityDamageSource)event.getSource()).getTrueSource());
//			if(HarshenUtils.containsItem(player, HarshenItems.LOOTING_EARRING))
//				for(EntityItem e : event.getDrops())
//					drops.add(new EntityItem(e.world, e.posX, e.posY, e.posZ, e.getItem()));
//			event.getDrops().addAll(drops);
//			if(HarshenUtils.containsItem(player, HarshenItems.FIERY_RING))
//				HarshenUtils.cookAndReplaceList(event.getDrops());
//		}
//	}
//	
//	@SubscribeEvent
//	public void onLivingDeath(LivingDeathEvent event)
//	{
//		if(!(event.getEntity() instanceof EntityPlayer))
//			return;
//		if(HarshenUtils.containsItem(event.getEntity(), HarshenItems.SOUL_BINDING_PENDANT))
//		{
//			event.setCanceled(true);
//		World world = event.getEntityLiving().world;
//		if(!world.isRemote)
//		{
//			boolean flag = world.getGameRules().getBoolean("showDeathMessages");
//			EntityPlayerMP player = (EntityPlayerMP) event.getEntity();
//			player.connection.sendPacket(new SPacketCombatEvent(player.getCombatTracker(), SPacketCombatEvent.Event.ENTITY_DIED, flag));
//	        if (flag)
//	        {
//	            Team team = player.getTeam();
//	            if (team != null && team.getDeathMessageVisibility() != Team.EnumVisible.ALWAYS)
//	                if (team.getDeathMessageVisibility() == Team.EnumVisible.HIDE_FOR_OTHER_TEAMS)
//	                	player.mcServer.getPlayerList().sendMessageToAllTeamMembers(player, player.getCombatTracker().getDeathMessage());
//	                else if (team.getDeathMessageVisibility() == Team.EnumVisible.HIDE_FOR_OWN_TEAM)
//	                	player.mcServer.getPlayerList().sendMessageToTeamOrAllPlayers(player, player.getCombatTracker().getDeathMessage());
//	            else
//	            	player.mcServer.getPlayerList().sendMessage(player.getCombatTracker().getDeathMessage());
//	        }
//
//	        for (ScoreObjective scoreobjective : world.getScoreboard().getObjectivesFromCriteria(IScoreCriteria.DEATH_COUNT))
//	            player.getWorldScoreboard().getOrCreateScore(player.getName(), scoreobjective).incrementScore();
//
//	        EntityLivingBase entitylivingbase = player.getAttackingEntity();
//
//	        if (entitylivingbase != null)
//	        {
//	            EntityList.EntityEggInfo entitylist$entityegginfo = EntityList.ENTITY_EGGS.get(EntityList.getKey(entitylivingbase));
//	            if (entitylist$entityegginfo != null)
//	            	player.addStat(entitylist$entityegginfo.entityKilledByStat);
//	        }
//	        player.addStat(StatList.DEATHS);
//	        player.takeStat(StatList.TIME_SINCE_DEATH);
//	        player.extinguish();
//	        setFlag(player, 0, false);
//	        player.getCombatTracker().reset();
//	        inventoryMap.put(event.getEntity().getUniqueID(), ((EntityPlayer)event.getEntity()).inventory.writeToNBT(new NBTTagList()));
//	        HarshenUtils.damageOccuringItemNoPacket(player, HarshenItems.SOUL_BINDING_PENDANT, 1);
//	        if(HarshenUtils.getFirstOccuringItem(player, HarshenItems.SOUL_BINDING_PENDANT).getItemDamage() == 
//	        		HarshenUtils.getFirstOccuringItem(player, HarshenItems.SOUL_BINDING_PENDANT).getMaxDamage())
//	        			HarshenUtils.damageOccuringItemNoPacket(player, HarshenItems.SOUL_BINDING_PENDANT, 1);
//	        	
//			}	
//		}
//		if(HarshenUtils.containsItem(event.getEntity(), Items.TOTEM_OF_UNDYING))
//		{
//			EntityPlayer player = (EntityPlayer) event.getEntity();
//			event.setCanceled(true);
//			if (player instanceof EntityPlayerMP)
//			{
//                EntityPlayerMP entityplayermp = (EntityPlayerMP)player;
//                entityplayermp.addStat(StatList.getObjectUseStats(Items.TOTEM_OF_UNDYING));
//                CriteriaTriggers.USED_TOTEM.trigger(entityplayermp, HarshenUtils.getFirstOccuringItem(player, Items.TOTEM_OF_UNDYING));
//            }
//			
//			HarshenUtils.setStackInSlot(player, Items.TOTEM_OF_UNDYING, ItemStack.EMPTY);
//			player.setHealth(1.0F);
//			player.clearActivePotions();
//			player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 900, 1));
//			player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 100, 1));
//			player.world.setEntityState(player, (byte)35);
//		}
//	}
//	
//	@SubscribeEvent
//	public void onPlayerRespawn(PlayerRespawnEvent event)
//	{
//		
//		if(HarshenUtils.containsItem(event.player, HarshenItems.SOUL_BINDING_PENDANT))
//		{
//			event.player.inventory.readFromNBT(inventoryMap.get(event.player.getUniqueID()));
//			HarshenNetwork.sendToPlayer(event.player, new MessagePacketReviveInventory(event.player));
//		}
//	}
//	
//	}
}
