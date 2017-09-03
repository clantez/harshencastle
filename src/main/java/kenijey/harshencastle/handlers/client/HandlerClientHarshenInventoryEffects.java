package kenijey.harshencastle.handlers.client;

import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.HarshenUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HandlerClientHarshenInventoryEffects 
{
	@SubscribeEvent
	public void RenderEventPre(RenderGameOverlayEvent.Pre event)
	{
		for(Entity e : Minecraft.getMinecraft().world.getLoadedEntityList())
			e.setGlowing(e instanceof EntityMob && HarshenUtils.containsItem(Minecraft.getMinecraft().player, HarshenItems.ender_pendant));
	}
}
