package kenijey.harshencastle.items;

import kenijey.harshencastle.api.EnumInventorySlots;
import kenijey.harshencastle.api.HarshenEvent;
import kenijey.harshencastle.api.IHarshenProvider;
import kenijey.harshencastle.config.AccessoryConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class EnderPendant extends Item implements IHarshenProvider
{
	
	public EnderPendant() {
		setRegistryName("ender_pendant");
		setUnlocalizedName("ender_pendant");
	}
	
	@Override
	public EnumInventorySlots getSlot() {
		return EnumInventorySlots.NECK;
	}
	
	@Override
	public Object getProvider(ItemStack stack) {
		return new EnderPendantHandler();
	}
	
	@Override
	public void onRemove(EntityPlayer player, int slot) {
		if(player.world.isRemote)
			for(Entity e : player.world.getLoadedEntityList())
				e.setGlowing(false);
	}
	
	public class EnderPendantHandler
	{
		@HarshenEvent
		public void renderGame(RenderGameOverlayEvent.Pre event)
		{
			for(Entity e : net.minecraft.client.Minecraft.getMinecraft().world.getLoadedEntityList())
				e.setGlowing(e instanceof EntityLivingBase &&
						net.minecraft.client.Minecraft.getMinecraft().player.getDistanceToEntity(e) < AccessoryConfig.enderPendantDistance);
		}
	}

}
