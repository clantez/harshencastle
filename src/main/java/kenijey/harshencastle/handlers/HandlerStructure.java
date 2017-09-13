package kenijey.harshencastle.handlers;

import kenijey.harshencastle.base.HarshenStructure;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;

public class HandlerStructure 
{
	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent event)
	{
			HarshenStructure.load((WorldServer) event.player.world);
	}
}
