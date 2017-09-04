package kenijey.harshencastle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;

public class HarshenClientUtils 
{
	public static void drawTexture(int x, int y, float u, float v, float uWidth, float vHeight, int width, int height, float tileWidth, float tileHeight)
	{
		float f = 1.0F / tileWidth;
        float f1 = 1.0F / tileHeight;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos((double)x, (double)(y + height), 0.0D).tex((double)(u * f), (double)((v + vHeight) * f1)).endVertex();
        bufferbuilder.pos((double)(x + width), (double)(y + height), 0.0D).tex((double)((u + uWidth) * f), (double)((v + vHeight) * f1)).endVertex();
        bufferbuilder.pos((double)(x + width), (double)y, 0.0D).tex((double)((u + uWidth) * f), (double)(v * f1)).endVertex();
        bufferbuilder.pos((double)x, (double)y, 0.0D).tex((double)(u * f), (double)(v * f1)).endVertex();
        tessellator.draw();
	}
	
	public static void renderBoxAt(int x, int y, int z, float partialTicks)
	{
		EntityPlayer entityplayer = Minecraft.getMinecraft().player;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        double d0 = entityplayer.lastTickPosX + (entityplayer.posX - entityplayer.lastTickPosX) * (double)partialTicks;
        double d1 = entityplayer.lastTickPosY + (entityplayer.posY - entityplayer.lastTickPosY) * (double)partialTicks;
        double d2 = entityplayer.lastTickPosZ + (entityplayer.posZ - entityplayer.lastTickPosZ) * (double)partialTicks;
        GlStateManager.depthFunc(519);
        GlStateManager.disableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.glLineWidth(3.0F);
        bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        Tessellator.getInstance().getBuffer().setTranslation(-d0, -d1, -d2);
        bufferbuilder.pos(x, y, z).color(1f, 1f, 1f, 1f).endVertex();
        bufferbuilder.pos(x, y, z + 1).color(1f, 1f, 1f, 1f).endVertex();
        bufferbuilder.pos(x + 1, y, z + 1).color(1f, 1f, 1f, 1f).endVertex();
        bufferbuilder.pos(x + 1, y, z).color(1f, 1f, 1f, 1f).endVertex();
        bufferbuilder.pos(x, y, z).color(1f, 1f, 1f, 1f).endVertex();
        bufferbuilder.pos(x, y + 1, z).color(1f, 1f, 1f, 1f).endVertex();
        bufferbuilder.pos(x, y + 1, z + 1).color(1f, 1f, 1f, 1f).endVertex();
        bufferbuilder.pos(x + 1, y + 1, z + 1).color(1f, 1f, 1f, 1f).endVertex();
        bufferbuilder.pos(x + 1, y + 1, z).color(1f, 1f, 1f, 1f).endVertex();
        bufferbuilder.pos(x, y + 1, z).color(1f, 1f, 1f, 1f).endVertex();
        bufferbuilder.pos(x, y + 1, z + 1).color(1f, 1f, 1f, 1f).endVertex();
        bufferbuilder.pos(x, y, z + 1).color(1f, 1f, 1f, 1f).endVertex();
        bufferbuilder.pos(x, y + 1, z + 1).color(1f, 1f, 1f, 1f).endVertex();
        bufferbuilder.pos(x + 1, y + 1, z + 1).color(1f, 1f, 1f, 1f).endVertex();
        bufferbuilder.pos(x + 1, y, z + 1).color(1f, 1f, 1f, 1f).endVertex();
        bufferbuilder.pos(x + 1, y + 1, z + 1).color(1f, 1f, 1f, 1f).endVertex();
        bufferbuilder.pos(x + 1, y + 1, z).color(1f, 1f, 1f, 1f).endVertex();
        bufferbuilder.pos(x + 1, y, z).color(1f, 1f, 1f, 1f).endVertex();
        bufferbuilder.pos(x + 1, y + 1, z).color(1f, 1f, 1f, 1f).endVertex();
        tessellator.draw();
        Tessellator.getInstance().getBuffer().setTranslation(0, 0, 0);
        GlStateManager.glLineWidth(1.0F);
        GlStateManager.enableBlend();
        GlStateManager.enableTexture2D();
        GlStateManager.depthFunc(515);
	}
	
	public static void renderBoxAt(BlockPos pos, float partialTicks)
	{
		renderBoxAt(pos.getX(), pos.getY(), pos.getZ(), partialTicks);
	}
}
