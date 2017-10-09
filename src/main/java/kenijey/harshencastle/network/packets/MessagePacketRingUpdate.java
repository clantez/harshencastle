package kenijey.harshencastle.network.packets;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.netty.buffer.ByteBuf;
import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.base.BaseMessagePacket;
import kenijey.harshencastle.handlers.HandlerHarshenInventoryEffects;
import kenijey.harshencastle.network.HarshenNetwork;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameType;
import net.minecraft.world.World;

public class MessagePacketRingUpdate extends BaseMessagePacket<MessagePacketRingUpdate>{

	public MessagePacketRingUpdate() {
	}
	
	private int ringType;
	
	public MessagePacketRingUpdate(int ringType)
	{
		this.ringType = ringType;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {		
		ringType = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {		
		buf.writeInt(ringType);
	}

	@Override
	public void onReceived(MessagePacketRingUpdate message, EntityPlayer player) {
		if(message.ringType < 2)
		{
			ArrayList<Item> ringTypeItem = new ArrayList<Item>();
			ringTypeItem.add(HarshenItems.TELERING);
			ringTypeItem.add(HarshenItems.MINERING);
			if(HarshenUtils.containsItem(player,ringTypeItem.get(message.ringType)))
			{
				World world = player.world;
				Vec3d vec = new Vec3d(player.posX + (player.getLookVec().x * 4f),
						player.posY + (player.getLookVec().y * 4f), player.posZ + (player.getLookVec().z* 4f));
				BlockPos blockpos = message.ringType == 0? HarshenUtils.getTopBlock(world, vec) : HarshenUtils.getBottomBlockAir(world, vec);
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
	                HarshenUtils.damageFirstOccuringItem(player, ringTypeItem.get(message.ringType));
				}
			}			
		}
		else if(message.ringType == 2 && HarshenUtils.containsItem(player, HarshenItems.COMBAT_PENDANT))
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
}
