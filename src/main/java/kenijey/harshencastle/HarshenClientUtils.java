package kenijey.harshencastle;

import java.awt.Color;

import javax.vecmath.Vector4f;

import kenijey.harshencastle.inventory.GuiHandler;
import kenijey.harshencastle.network.HarshenNetwork;
import kenijey.harshencastle.network.packets.MessagePacketOpenInv;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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
	
	public static void renderFaceAt(EnumFacing face, int x, int y, int z, float partialTicks, Vector4f color)
	{
		BufferBuilder bufferbuilder = prepNoDepthLineRender(partialTicks);
		switch (face) {
		case DOWN:
			bufferbuilder.pos(x,y,z).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x,y,z+1).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x+1,y,z+1).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x+1,y,z).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x,y,z).color(color.x,color.y,color.z,color.w).endVertex();
			break;
		case EAST:
			bufferbuilder.pos(x+1,y+1,z+1).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x+1,y,z+1).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x+1,y,z).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x+1,y+1,z).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x+1,y+1,z+1).color(color.x,color.y,color.z,color.w).endVertex();	
			break;
		case NORTH:
			bufferbuilder.pos(x,y,z).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x,y+1,z).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x+1,y+1,z).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x+1,y,z).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x,y,z).color(color.x,color.y,color.z,color.w).endVertex();
			break;
		case SOUTH:
			bufferbuilder.pos(x,y,z+1).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x,y+1,z+1).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x+1,y+1,z+1).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x+1,y,z+1).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x,y,z+1).color(color.x,color.y,color.z,color.w).endVertex();
			break;
		case UP:
			bufferbuilder.pos(x,y+1,z).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x,y+1,z+1).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x+1,y+1,z+1).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x+1,y+1,z).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x,y+1,z).color(color.x,color.y,color.z,color.w).endVertex();
			break;
		case WEST:
			bufferbuilder.pos(x,y+1,z+1).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x,y,z+1).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x,y,z).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x,y+1,z).color(color.x,color.y,color.z,color.w).endVertex();
			bufferbuilder.pos(x,y+1,z+1).color(color.x,color.y,color.z,color.w).endVertex();
			break;
		}
        postNoDepthLineRender();
	}
	
	public static void renderGhostBlock(IBlockState state, BlockPos position, Color color, boolean noDepth, float partialTicks)
	{
		BufferBuilder vb;
		if(noDepth)
		{
            GlStateManager.depthFunc(519);
			vb = prepRenderBlockDepth(partialTicks);
		}
		else
	        vb = prepRender(partialTicks);

        vb.begin(7, DefaultVertexFormats.BLOCK);
        World world = Minecraft.getMinecraft().world;
        BlockRendererDispatcher dispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
        dispatcher.renderBlock(state, position.add(0, + 300, 0), world, vb);
        for(int i = 0; i < vb.getVertexCount(); i++)
        	vb.putColorMultiplier(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, i);
        postRender();
        GlStateManager.depthFunc(515);
	}
	
	public static void renderGhostBlock(IBlockState state, BlockPos position, boolean noDepth, float partialTicks)
	{
		renderGhostBlock(state, position, new Color(1f, 1f, 1f), noDepth, partialTicks);
	}
	
	private final static Vector4f WHITE = new Vector4f(1, 1, 1, 1);
	
	public static void renderFaceAt(EnumFacing face, BlockPos pos, float partialTicks, Vector4f color, float line)
	{
		setLine(line);
		renderFaceAt(face, pos.getX(), pos.getY(), pos.getZ(), partialTicks, color);
	}
	
	public static void renderFullBoxAt(BlockPos pos, float partialTicks, Vector4f color, float line)
	{
		for(EnumFacing face : EnumFacing.values())
			renderFaceAt(face, pos, partialTicks, color, line);
	}
	
	public static void renderFaceAt(EnumFacing face, BlockPos pos, float partialTicks)
	{
		renderFaceAt(face, pos.getX(), pos.getY(), pos.getZ(), partialTicks, WHITE);
	}
	
	private static float line;
	
	public static void setLine(float line) {
		HarshenClientUtils.line = line;
	}
	
	public static void renderFullBoxAt(BlockPos pos, float partialTicks)
	{
		for(EnumFacing face : EnumFacing.values())
			renderFaceAt(face, pos, partialTicks);
	}
		
	public static BufferBuilder prepNoDepthLineRender(float partialTicks)
	{
        GlStateManager.depthFunc(519);
        return prepLineRender(partialTicks);

	}
	
	public static void postNoDepthLineRender()
	{
		postLineRender();
        GlStateManager.depthFunc(515);
	}
	
	public static BufferBuilder prepLineRender(float partialTicks)
	{
		GlStateManager.disableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.glLineWidth(line);
        BufferBuilder bufferbuilder = prepRender(partialTicks);
        bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        return bufferbuilder;
	}
	
	public static BufferBuilder prepRender(float partialTicks)
	{
		GlStateManager.pushMatrix();
		EntityPlayer entityplayer = Minecraft.getMinecraft().player;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        double d0 = entityplayer.lastTickPosX + (entityplayer.posX - entityplayer.lastTickPosX) * (double)partialTicks;
        double d1 = entityplayer.lastTickPosY + (entityplayer.posY - entityplayer.lastTickPosY) * (double)partialTicks;
        double d2 = entityplayer.lastTickPosZ + (entityplayer.posZ - entityplayer.lastTickPosZ) * (double)partialTicks;
        Tessellator.getInstance().getBuffer().setTranslation(-d0, -d1, -d2);
        return bufferbuilder;
	}
	
	public static BufferBuilder prepRenderBlockDepth(float partialTicks)
	{
		GlStateManager.pushMatrix();
		EntityPlayer entityplayer = Minecraft.getMinecraft().player;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        double d0 = entityplayer.lastTickPosX + (entityplayer.posX - entityplayer.lastTickPosX) * (double)partialTicks;
        double d1 = (entityplayer.lastTickPosY + (entityplayer.posY - entityplayer.lastTickPosY) * (double)partialTicks) + 300;
        double d2 = entityplayer.lastTickPosZ + (entityplayer.posZ - entityplayer.lastTickPosZ) * (double)partialTicks;
        Tessellator.getInstance().getBuffer().setTranslation(-d0, -d1, -d2);
        return bufferbuilder;
	}
	
	public static void postRender()
	{
		Tessellator.getInstance().draw();
        Tessellator.getInstance().getBuffer().setTranslation(0, 0, 0);
        GlStateManager.popMatrix();
	}
		
	public static void postLineRender()
	{
		postRender();
        GlStateManager.glLineWidth(1.0F);
        line = 0.5f;
        GlStateManager.enableBlend();
        GlStateManager.enableTexture2D();
	}
	
	public static void openInventory()
	{
		EntityPlayer player = Minecraft.getMinecraft().player;
		player.openGui(HarshenCastle.instance, GuiHandler.CUSTOMINVENTORY, player.world, (int)player.posX, (int)player.posY, (int)player.posZ);
		HarshenNetwork.sendToServer(new MessagePacketOpenInv());
	}
}
