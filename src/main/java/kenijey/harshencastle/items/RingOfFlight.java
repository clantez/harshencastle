package kenijey.harshencastle.items;

import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import kenijey.harshencastle.interfaces.IHarshenProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.world.GameType;

public class RingOfFlight extends Item implements IHarshenProvider
{
	
	public RingOfFlight() {
		setRegistryName("ring_of_flight");
		setUnlocalizedName("ring_of_flight");
	}
	
	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.RING1;
	}
	
	@Override
	public void onTick(EntityPlayer player, int tick) {
		player.capabilities.allowFlying = shouldBeOn(player, true);
	}
	
	@Override
	public void onRemove(EntityPlayer player) {
		player.capabilities.allowFlying = shouldBeOn(player, false);
		
		if(player.capabilities.isFlying && !player.capabilities.allowFlying)
			player.capabilities.isFlying = false;
	}
	
	private boolean shouldBeOn(EntityPlayer player, boolean _default)
	{
		return HarshenUtils.toArray(GameType.ADVENTURE, GameType.SURVIVAL).contains(!player.world.isRemote ?
				((EntityPlayerMP)player).interactionManager.getGameType() : net.minecraft.client.Minecraft.getMinecraft().playerController.getCurrentGameType()) 
				? _default : player.capabilities.allowFlying;
	}

}
