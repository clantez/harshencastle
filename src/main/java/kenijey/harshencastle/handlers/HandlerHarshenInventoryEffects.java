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

public class HandlerHarshenInventoryEffects 
{	
	
	@SubscribeEvent
	public void onLivingHurt(LivingHurtEvent event)
	{
		if(HarshenUtils.containsItem(event.getEntityLiving(), HarshenItems.FEARRING))
			event.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 30));

		if(event.getSource() instanceof EntityDamageSource && ((EntityDamageSource)event.getSource()).getTrueSource() instanceof EntityPlayer)
		{
			EntityPlayer player = ((EntityPlayer)((EntityDamageSource)event.getSource()).getTrueSource());
			if(HarshenUtils.containsItem(player,  HarshenItems.FEARRING))
				event.getEntityLiving().addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 150));
			if(HarshenUtils.containsItem(player, HarshenItems.PUNCHY_RING) && player.getHeldItemMainhand().getItem() == Items.AIR)
				event.setAmount(event.getAmount() + HarshenUtils.getItemCount(player, HarshenItems.PUNCHY_RING) * 2);
		}
		if(HarshenUtils.containsItem(event.getEntityLiving(), HarshenItems.ZOMBI_PENDANT) &&
				(event.getSource() instanceof EntityDamageSource && ((EntityDamageSource)event.getSource()).getTrueSource() instanceof EntityZombie &&
						!(((EntityDamageSource)event.getSource()).getTrueSource() instanceof EntityPigZombie)))
			event.setAmount(1);
		if(HarshenUtils.containsItem(event.getEntityLiving(), HarshenItems.SOUL_SHIELD))
			for(int i = 0; i < HarshenUtils.getHandler((EntityPlayer) event.getEntityLiving()).getSlots(); i++)
				if(HarshenUtils.getHandler((EntityPlayer) event.getEntityLiving()).getStackInSlot(i).getItem() == HarshenItems.SOUL_SHIELD)
				{
					HarshenUtils.damageFirstOccuringItem((EntityPlayer) event.getEntityLiving(), HarshenItems.SOUL_SHIELD, (int) event.getAmount() * 2);
					event.setAmount(0);
				}
		if(HarshenUtils.containsItem(event.getEntityLiving(), HarshenItems.ELYTRA_PENDANT)&& HarshenUtils.toArray(DamageSource.FLY_INTO_WALL, DamageSource.FALL).contains(event.getSource())
				&& event.getEntityLiving().getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == Items.ELYTRA)
			event.setCanceled(true);		
	}
	
	@SubscribeEvent
	public void onBlockBroken(HarvestDropsEvent event)
	{
		if(HarshenUtils.containsItem(event.getHarvester(), HarshenItems.FIERY_RING))
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
		if(HarshenUtils.containsItem(player, HarshenItems.ELYTRA_PENDANT) && player.getHeldItemMainhand().isEmpty() && player.isElytraFlying() && player.world.isRemote)
		{
			HarshenNetwork.sendToServer(new MessagePacketSummonFirework());

			Vec3d vec3d = player.getLookVec();
			player.motionX += vec3d.x * 0.1D + (vec3d.x * 2.5D - player.motionX) * 0.5D;
	        player.motionY += vec3d.y * 0.1D + (vec3d.y * 2.5D - player.motionY) * 0.5D;
	        player.motionZ += vec3d.z * 0.1D + (vec3d.z * 2.5D - player.motionZ) * 0.5D;
		}
	}
		
	@SubscribeEvent
	public void onLivingDrops(LivingDropsEvent event)
	{
		if(event.getSource() instanceof EntityDamageSource && ((EntityDamageSource)event.getSource()).getTrueSource() instanceof EntityPlayer)
		{
			ArrayList<EntityItem> drops = new ArrayList<EntityItem>();
			EntityPlayer player = ((EntityPlayer)((EntityDamageSource)event.getSource()).getTrueSource());
			if(HarshenUtils.containsItem(player, HarshenItems.LOOTING_EARRING))
				for(EntityItem e : event.getDrops())
					drops.add(new EntityItem(e.world, e.posX, e.posY, e.posZ, e.getItem()));
			event.getDrops().addAll(drops);
			if(HarshenUtils.containsItem(player, HarshenItems.FIERY_RING))
				HarshenUtils.cookAndReplaceList(event.getDrops());
		}
	}
	
	@SubscribeEvent
	public void onLivingDeath(LivingDeathEvent event)
	{
		if(!(event.getEntity() instanceof EntityPlayer) || !HarshenUtils.containsItem(event.getEntity(), HarshenItems.SOUL_BINDING_PENDANT))
			return;
		event.setCanceled(true);
		World world = event.getEntityLiving().world;
		if(!world.isRemote)
		{
			boolean flag = world.getGameRules().getBoolean("showDeathMessages");
			EntityPlayerMP player = (EntityPlayerMP) event.getEntity();
			player.connection.sendPacket(new SPacketCombatEvent(player.getCombatTracker(), SPacketCombatEvent.Event.ENTITY_DIED, flag));
	        if (flag)
	        {
	            Team team = player.getTeam();
	            if (team != null && team.getDeathMessageVisibility() != Team.EnumVisible.ALWAYS)
	                if (team.getDeathMessageVisibility() == Team.EnumVisible.HIDE_FOR_OTHER_TEAMS)
	                	player.mcServer.getPlayerList().sendMessageToAllTeamMembers(player, player.getCombatTracker().getDeathMessage());
	                else if (team.getDeathMessageVisibility() == Team.EnumVisible.HIDE_FOR_OWN_TEAM)
	                	player.mcServer.getPlayerList().sendMessageToTeamOrAllPlayers(player, player.getCombatTracker().getDeathMessage());
	            else
	            	player.mcServer.getPlayerList().sendMessage(player.getCombatTracker().getDeathMessage());
	        }

	        for (ScoreObjective scoreobjective : world.getScoreboard().getObjectivesFromCriteria(IScoreCriteria.DEATH_COUNT))
	            player.getWorldScoreboard().getOrCreateScore(player.getName(), scoreobjective).incrementScore();

	        EntityLivingBase entitylivingbase = player.getAttackingEntity();

	        if (entitylivingbase != null)
	        {
	            EntityList.EntityEggInfo entitylist$entityegginfo = EntityList.ENTITY_EGGS.get(EntityList.getKey(entitylivingbase));
	            if (entitylist$entityegginfo != null)
	            	player.addStat(entitylist$entityegginfo.entityKilledByStat);
	        }
	        player.addStat(StatList.DEATHS);
	        player.takeStat(StatList.TIME_SINCE_DEATH);
	        player.extinguish();
	        setFlag(player, 0, false);
	        player.getCombatTracker().reset();
	        inventoryMap.put(event.getEntity().getUniqueID(), ((EntityPlayer)event.getEntity()).inventory.writeToNBT(new NBTTagList()));
	        HarshenUtils.damageOccuringItemNoPacket(player, HarshenItems.SOUL_BINDING_PENDANT, 1);
	        if(HarshenUtils.getFirstOccuringItem(player, HarshenItems.SOUL_BINDING_PENDANT).getItemDamage() == 
	        		HarshenUtils.getFirstOccuringItem(player, HarshenItems.SOUL_BINDING_PENDANT).getMaxDamage())
	        			HarshenUtils.damageOccuringItemNoPacket(player, HarshenItems.SOUL_BINDING_PENDANT, 1);
	        	
		}	
		HandlerPlayerInventoryOverDeath.onPlayerDeath(event);
	}
	
	@SubscribeEvent
	public void onPlayerRespawn(PlayerRespawnEvent event)
	{
		HandlerPlayerInventoryOverDeath.onPlayerRespawn(event);
		
		if(HarshenUtils.containsItem(event.player, HarshenItems.SOUL_BINDING_PENDANT))
		{
			event.player.inventory.readFromNBT(inventoryMap.get(event.player.getUniqueID()));
			HarshenNetwork.sendToPlayer(event.player, new MessagePacketReviveInventory(event.player));
		}
	}
	
		
	private static HashMap<UUID, NBTTagList> inventoryMap = new HashMap<>();
	
    private static final DataParameter<Byte> FLAGS = EntityDataManager.<Byte>createKey(Entity.class, DataSerializers.BYTE);
	
	private static void setFlag(Entity entity, int flag, boolean set)
	{
		byte b0 = ((Byte)entity.getDataManager().get(FLAGS)).byteValue();

        if (set)
        	entity.getDataManager().set(FLAGS, Byte.valueOf((byte)(b0 | 1 << flag)));
        else
        	entity.getDataManager().set(FLAGS, Byte.valueOf((byte)(b0 & ~(1 << flag))));
	}
	
	public static void ringEvent(EntityPlayer player, int ringType)
	{
		if(ringType < 2)
		{
			ArrayList<Item> ringTypeItem = new ArrayList<Item>();
			ringTypeItem.add(HarshenItems.TELERING);
			ringTypeItem.add(HarshenItems.MINERING);
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
		else if(ringType == 2 && HarshenUtils.containsItem(player, HarshenItems.COMBAT_PENDANT))
		{
			EntityLivingBase entityToAttack = HarshenUtils.getFirstEntityInDirection(player.world, player.getPositionVector(), player.getLookVec().normalize(), 5, EntityLivingBase.class);
			if(entityToAttack == null)
			{
				List<EntityLivingBase> list = player.world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(player.posX - 4d, player.posY - 4d, player.posZ - 4d, player.posX + 4d, player.posY + 4d, player.posZ + 4d));
				if(!list.isEmpty())
					entityToAttack = list.get(0);
			}
			if(!player.equals(entityToAttack) && (entityToAttack != null || (entityToAttack instanceof EntityPlayerMP && player.canAttackPlayer((EntityPlayerMP)entityToAttack)
					&& HarshenUtils.toArray(GameType.SURVIVAL, GameType.ADVENTURE).contains(((EntityPlayerMP)entityToAttack).interactionManager.getGameType()))))
			{
				Vec3d position = entityToAttack.getPositionVector();
				Vec3d playerPosNoY = position.addVector(movePos(), 0, movePos());
				Vec3d pos = new Vec3d(playerPosNoY.x, HarshenUtils.getTopBlock(player.world, new BlockPos(playerPosNoY)).getY(), playerPosNoY.z);
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
