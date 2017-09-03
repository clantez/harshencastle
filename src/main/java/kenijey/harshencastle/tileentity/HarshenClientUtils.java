package kenijey.harshencastle.tileentity;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

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
}
