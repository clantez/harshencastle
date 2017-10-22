package kenijey.harshencastle.handlers.server;

import java.util.ArrayList;
import java.util.HashMap;

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
			TileEntityCaulronBlock.testForCauldron(event.world, null);
		}
		
		if(!event.world.isRemote && !UPDATE_LIST.isEmpty())
			for(TileEntityCaulronBlock te : UPDATE_LIST)
				TileEntityCaulronBlock.testForCauldron(event.world, te.getPos());

		UPDATE_LIST.clear();
	}
	
	public final static ArrayList<TileEntityCaulronBlock> UPDATE_LIST = new ArrayList<>();
}
