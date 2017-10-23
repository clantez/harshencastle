package kenijey.harshencastle.handlers.client;

import kenijey.harshencastle.tileentity.TileEntityCaulronBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraftforge.fml.relauncher.Side;

public class CauldronUpdate 
{
	
	int ticks = 0;
	
	@SubscribeEvent
	public void onTick(WorldTickEvent event)
	{
		if(ticks++%20==0)
			for(TileEntity te : event.world.loadedTileEntityList)
				if(te instanceof TileEntityCaulronBlock && ((TileEntityCaulronBlock)te).isLeader())
					Minecraft.getMinecraft().renderGlobal.markBlockRangeForRenderUpdate(te.getPos().getX(), te.getPos().getY(), te.getPos().getZ(), 
							te.getPos().getX() + ((TileEntityCaulronBlock)te).getSize(), te.getPos().getY() + ((TileEntityCaulronBlock)te).getSize(), te.getPos().getZ() + ((TileEntityCaulronBlock)te).getSize());
	}
}
