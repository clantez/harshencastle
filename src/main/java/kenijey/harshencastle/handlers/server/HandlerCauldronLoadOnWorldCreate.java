package kenijey.harshencastle.handlers.server;

import java.util.ArrayList;

import kenijey.harshencastle.tileentity.TileEntityCaulronBlock;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;

public class HandlerCauldronLoadOnWorldCreate 
{
	private ArrayList<Integer> dimensionsLoaded = new ArrayList<>();
	
	private String worldName;
	
	@SubscribeEvent
	public void onWorldLoad(WorldTickEvent event)
	{
		if(!worldName.equals(event.world.getWorldInfo().getWorldName()))
			dimensionsLoaded.clear();
		if(!dimensionsLoaded.contains(event.world.provider.getDimension()))
		{
			worldName = event.world.getWorldInfo().getWorldName();
			dimensionsLoaded.add(event.world.provider.getDimension());
			TileEntityCaulronBlock.testForCauldron(event.world);
		}
	}
}
