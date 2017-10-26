package kenijey.harshencastle.handlers.client;

import kenijey.harshencastle.tileentity.TileEntityCaulronBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraftforge.fml.relauncher.Side;

public class CauldronUpdate 
{
	
	int ticks = 0;
	
	@SubscribeEvent
	public void onTick(WorldTickEvent event)
	{
		if(Minecraft.getMinecraft().world != null && ticks++%40==0)
			for(TileEntity te : Minecraft.getMinecraft().world.loadedTileEntityList)
				if(te instanceof TileEntityCaulronBlock && ((TileEntityCaulronBlock)te).isLeader())
					Minecraft.getMinecraft().renderGlobal.markBlockRangeForRenderUpdate(te.getPos().getX(), te.getPos().getY(), te.getPos().getZ(), 
							te.getPos().getX() + ((TileEntityCaulronBlock)te).getSize(), te.getPos().getY() + ((TileEntityCaulronBlock)te).getSize(), te.getPos().getZ() + ((TileEntityCaulronBlock)te).getSize());
	}
}
