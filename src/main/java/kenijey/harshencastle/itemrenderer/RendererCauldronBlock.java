package kenijey.harshencastle.itemrenderer;

import kenijey.harshencastle.base.BaseItemRenderer;
import kenijey.harshencastle.blocks.CauldronBlock;
import kenijey.harshencastle.tileentity.TileEntityCaulronBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class RendererCauldronBlock extends TileEntitySpecialRenderer<TileEntityCaulronBlock>
{
	@Override
	public void render(TileEntityCaulronBlock te, double x, double y, double z, float partialTicks, int destroyStage,
			float alpha) {
		GlStateManager.pushMatrix();
		{	
			GlStateManager.translate(x, y, z);
			GlStateManager.translate(0.5f,1f,0.5f);
			EntityItem item = new EntityItem(getWorld(), 0, 0, 0, new ItemStack(te.isLeader() ? Items.GOLDEN_APPLE : Items.APPLE));
			item.hoverStart = 0;
			GlStateManager.disableLighting();
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);
            if(CauldronBlock.CAULDRON_POSITIONS.contains(te.getPos()))
            	Minecraft.getMinecraft().getRenderManager().renderEntity(item, 0f, 0f, 0f, 0f, 0f, false);
			GlStateManager.enableLighting();
		}
		GlStateManager.popMatrix();
		super.render(te, x, y, z, partialTicks, destroyStage, alpha);
	}
}
