package kenijey.harshencastle.items;

import kenijey.harshencastle.config.AccessoryConfig;
import kenijey.harshencastle.enums.inventory.EnumInventorySlots;
import kenijey.harshencastle.interfaces.HarshenEvent;
import kenijey.harshencastle.interfaces.IHarshenProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
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
