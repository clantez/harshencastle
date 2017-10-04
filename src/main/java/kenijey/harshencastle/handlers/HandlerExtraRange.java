package kenijey.harshencastle.handlers;

import kenijey.harshencastle.HarshenClientUtils;
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
			RayTraceResult ray = HarshenClientUtils.getMouseOver();
			if(ray != null)
			{
				HarshenNetwork.sendToServer(new MessagePacketHitWithRange(ray.entityHit.getEntityId()));

			}
		}
	}
	
}
