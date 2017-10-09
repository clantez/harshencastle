package kenijey.harshencastle.items;

import javax.swing.text.html.parser.Entity;

import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.base.BaseItemInventory;
import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.GameType;
import net.minecraftforge.fml.relauncher.Side;

public class RingOfFlight extends BaseItemInventory
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
