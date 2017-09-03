package kenijey.harshencastle.handlers.client;

import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.interfaces.IHudDisplay;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HandlerGameOverlay 
{
	@SubscribeEvent
	public void onGameOverlay(RenderGameOverlayEvent.Post event)
	{
		if(event.getType() == RenderGameOverlayEvent.ElementType.ALL && Minecraft.getMinecraft().currentScreen == null)
		{
            Minecraft minecraft = Minecraft.getMinecraft();
            EntityPlayer player = minecraft.player;
            RayTraceResult posHit = minecraft.objectMouseOver;
            if(posHit != null && posHit.getBlockPos() != null)
            {
                Block blockHit = minecraft.world.getBlockState(posHit.getBlockPos()).getBlock();
                if(blockHit instanceof IHudDisplay)
                    ((IHudDisplay)blockHit).displayHud(minecraft, player, posHit, event.getResolution());
                TileEntity tileHit = minecraft.world.getTileEntity(posHit.getBlockPos());
                if(tileHit instanceof IHudDisplay)
                	((IHudDisplay)tileHit).displayHud(minecraft, player, posHit, event.getResolution());
            }
		}
	}
}
