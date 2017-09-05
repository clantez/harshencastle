package kenijey.harshencastle.handlers.client;

import kenijey.harshencastle.HarshenUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HandlerRendererGuiInventory 
{
	@SubscribeEvent
	public void onGameOverlay(RenderGameOverlayEvent.Post event)
	{
		Minecraft minecraft = Minecraft.getMinecraft();
        EntityPlayer player = minecraft.player;
		if(event.getType() == RenderGameOverlayEvent.ElementType.ALL && Minecraft.getMinecraft().currentScreen == null)
            for(int i = 0; i < HarshenUtils.getHandler(player).getSlots(); i++)
            {
            	ItemStack stack  = HarshenUtils.getHandler(player).getStackInSlot(i);
                if(!stack.isEmpty())
                {
             	   float f = (float)stack.getAnimationsToGo() - event.getPartialTicks();
             	   int x = event.getResolution().getScaledWidth() - (20 * (5 - i));
                   int y = event.getResolution().getScaledHeight() - 19;
                    if (f > 0.0F)
                    {
                        GlStateManager.pushMatrix();
                        float f1 = 1.0F + f / 5.0F;
                        GlStateManager.translate((float)(x + 8), (float)(y + 12), 0.0F);
                        GlStateManager.scale(1.0F / f1, (f1 + 1.0F) / 2.0F, 1.0F);
                        GlStateManager.translate((float)(-(x + 8)), (float)(-(y + 12)), 0.0F);
                    }

                    minecraft.getRenderItem().renderItemAndEffectIntoGUI(player, stack, x, y);
                    
                    if (f > 0.0F)
                        GlStateManager.popMatrix();
                    
                    minecraft.getRenderItem().renderItemOverlays(minecraft.fontRenderer, stack, x, y);
                }
            }
	}
}
