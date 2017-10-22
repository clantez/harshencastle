package kenijey.harshencastle.itemrenderer;

import org.lwjgl.opengl.GL11;

import kenijey.harshencastle.HarshenClientUtils;
import kenijey.harshencastle.HarshenStructures;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.blocks.CauldronBlock;
import kenijey.harshencastle.template.HarshenTemplate;
import kenijey.harshencastle.template.HarshenTemplateRenderer;
import kenijey.harshencastle.tileentity.TileEntityCaulronBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.world.World;

public class RendererCauldronBlock extends TileEntitySpecialRenderer<TileEntityCaulronBlock>
{
	@Override
	public void render(TileEntityCaulronBlock te, double x, double y, double z, float partialTicks, int destroyStage,
			float alpha) {	
		GlStateManager.pushAttrib();
        GlStateManager.pushMatrix();
    	GlStateManager.translate(x, y, z);
    	GlStateManager.pushAttrib();
        GlStateManager.pushMatrix();
        GlStateManager.disableLighting();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);
        this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        World world = getWorld();
		int scaleSize = (te.getSize() - TileEntityCaulronBlock.MIN_LEVELS) + 2;
		GlStateManager.scale(scaleSize, scaleSize, scaleSize);
        GlStateManager.translate(-te.getPos().getX(), -te.getPos().getY(), -te.getPos().getZ());
        Tessellator tessellator = Tessellator.getInstance();
        tessellator.getBuffer().begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
        if(CauldronBlock.CAULDRON_POSITIONS.contains(te.getPos()) && te.isLeader() && world != null)
	        Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelRenderer().renderModel(world, CauldronBlock.MODELS.get(te.getSize()),
	        		world.getBlockState(te.getPos()), te.getPos(), Tessellator.getInstance().getBuffer(), false);
        tessellator.draw();
        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        GlStateManager.translate(te.getPos().getX(), te.getPos().getY(), te.getPos().getZ());
        GlStateManager.popMatrix();
        GlStateManager.popAttrib();
    	GlStateManager.translate(-x, -y, -z);
    	GlStateManager.popMatrix();
        GlStateManager.popAttrib();
	}
}
