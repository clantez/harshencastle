package kenijey.harshencastle.items;

import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import kenijey.harshencastle.interfaces.HarshenEvent;
import kenijey.harshencastle.interfaces.IHarshenProvider;
import kenijey.harshencastle.network.HarshenNetwork;
import kenijey.harshencastle.network.packets.MessagePacketSummonFirework;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickEmpty;

public class ElytraPendant extends Item implements IHarshenProvider
{

	public ElytraPendant() {
		setRegistryName("elytra_pendant");
		setUnlocalizedName("elytra_pendant");
		setMaxDamage(5000);
	}
	
	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.NECK;
	}
	
	@Override
	public Object getProvider(ItemStack stack) {
		return new HandlerElytraPendant();
	}
	
	public class HandlerElytraPendant
	{
		@HarshenEvent
		public void onLivingHurt(LivingHurtEvent event)
		{
			if(HarshenUtils.toArray(DamageSource.FLY_INTO_WALL, DamageSource.FALL).contains(event.getSource())
					&& event.getEntityLiving().getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == Items.ELYTRA)
				event.setCanceled(true);		
		}
		
		@HarshenEvent
		public void onRightClick(PlayerInteractEvent event)
		{
			if(event.getEntityPlayer().world.isRemote && HarshenUtils.toArray(RightClickBlock.class, RightClickEmpty.class).contains(event.getClass()) &&
					event.getEntityPlayer().getHeldItemMainhand().isEmpty() && event.getEntityPlayer().isElytraFlying())
			{
				HarshenNetwork.sendToServer(new MessagePacketSummonFirework());

				Vec3d vec3d = event.getEntityPlayer().getLookVec();
				event.getEntityPlayer().motionX += vec3d.x * 0.1D + (vec3d.x * 2.5D - event.getEntityPlayer().motionX) * 0.5D;
				event.getEntityPlayer().motionY += vec3d.y * 0.1D + (vec3d.y * 2.5D - event.getEntityPlayer().motionY) * 0.5D;
				event.getEntityPlayer().motionZ += vec3d.z * 0.1D + (vec3d.z * 2.5D - event.getEntityPlayer().motionZ) * 0.5D;
			}
		}
	}

}
