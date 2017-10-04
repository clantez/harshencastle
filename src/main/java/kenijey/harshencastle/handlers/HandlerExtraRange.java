package kenijey.harshencastle.handlers;

import kenijey.harshencastle.HarshenClientUtils;
import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.base.BaseHarshenScythe;
import kenijey.harshencastle.config.AccessoryConfig;
import kenijey.harshencastle.network.HarshenNetwork;
import kenijey.harshencastle.network.packets.MessagePacketHitWithRange;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.MouseInputEvent;

public class HandlerExtraRange 
{
	
	@SubscribeEvent
	public void onClick(MouseInputEvent event)
	{
		if(Minecraft.getMinecraft().gameSettings.keyBindAttack.isPressed())
		{
			KeyBinding.onTick(Minecraft.getMinecraft().gameSettings.keyBindAttack.getKeyCode());
			RayTraceResult ray = HarshenClientUtils.getMouseOver(Minecraft.getMinecraft().player.getHeldItemMainhand().getItem() instanceof BaseHarshenScythe ?
					((BaseHarshenScythe)Minecraft.getMinecraft().player.getHeldItemMainhand().getItem()).getReach() : AccessoryConfig.reachPendantLength);
			if(Minecraft.getMinecraft().player.getHeldItemMainhand().getItem() instanceof BaseHarshenScythe && HarshenUtils.containsItem(Minecraft.getMinecraft().player, HarshenItems.REACH_PENDANT))
				ray = HarshenClientUtils.getMouseOver(((BaseHarshenScythe)Minecraft.getMinecraft().player.getHeldItemMainhand().getItem()).getReach() + AccessoryConfig.reachPendantLength);
			if(ray != null && (Minecraft.getMinecraft().player.getHeldItemMainhand().getItem() instanceof BaseHarshenScythe || HarshenUtils.containsItem(Minecraft.getMinecraft().player, HarshenItems.REACH_PENDANT)))
				HarshenNetwork.sendToServer(new MessagePacketHitWithRange(ray.entityHit.getEntityId()));
		}
	}
	
}
