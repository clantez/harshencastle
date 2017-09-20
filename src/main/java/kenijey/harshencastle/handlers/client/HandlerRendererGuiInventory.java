package kenijey.harshencastle.handlers.client;

import java.util.ArrayList;

import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.HarshenUtils;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HandlerRendererGuiInventory 
{
	
	String previousXrayBlock = "";
	
	@SubscribeEvent
	public void onGameOverlay(RenderGameOverlayEvent.Post event)
	{
		Minecraft minecraft = Minecraft.getMinecraft();
        EntityPlayer player = minecraft.player;

		if(event.getType() != RenderGameOverlayEvent.ElementType.ALL || minecraft.currentScreen != null)
			return;
		ArrayList<Block> blocks = new ArrayList<>();
		ItemStack xrayStack = ItemStack.EMPTY;
		for(int i = 0; i < player.inventory.getSizeInventory(); i++)
			if(player.inventory.getStackInSlot(i).getItem() == HarshenItems.xray_pendant)
			{
				xrayStack = player.inventory.getStackInSlot(i);
				if(player.inventory.getStackInSlot(i).hasTagCompound())
					blocks.addAll(HarshenUtils.getBlocksFromString(player.inventory.getStackInSlot(i).getTagCompound().getString("BlockToSearch")));
			}
		if(HarshenUtils.getFirstOccuringItem(player,  HarshenItems.xray_pendant).hasTagCompound())
			blocks.addAll(HarshenUtils.getBlocksFromString(HarshenUtils.getFirstOccuringItem(player,  HarshenItems.xray_pendant).getTagCompound().getString("BlockToSearch")));
		ItemStack testStack = HarshenUtils.getFirstOccuringItem(player,  HarshenItems.xray_pendant);
		xrayStack = testStack.isEmpty() ? xrayStack : testStack;
		boolean flag = false;
		if(xrayStack.isEmpty())
			flag = true;
		if(!flag)
		{
			String brokenBlock = "";
			if(xrayStack.hasTagCompound())
				brokenBlock = xrayStack.getTagCompound().getString("BlockToSearch");
			if(blocks.isEmpty() && !previousXrayBlock.equalsIgnoreCase(brokenBlock))
			{
				player.sendStatusMessage(new TextComponentString(TextFormatting.DARK_RED + new TextComponentTranslation("xray.blocknotfound", brokenBlock).getUnformattedText()), true); 
				previousXrayBlock = brokenBlock;
			}
		}
		
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
