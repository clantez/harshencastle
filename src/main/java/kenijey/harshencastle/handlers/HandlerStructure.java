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
		if(event.side == Side.SERVER && !HarshenStructure.hasLoaded() && event.player.world != null)
			HarshenStructure.load((WorldServer) event.player.world);
	}
}
