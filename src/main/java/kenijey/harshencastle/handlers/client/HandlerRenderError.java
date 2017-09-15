package kenijey.harshencastle.handlers.client;

import java.util.ArrayList;

import javax.vecmath.Vector4f;

import org.lwjgl.opengl.GL11;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.HarshenClientUtils;
import kenijey.harshencastle.objecthandlers.FaceRenderer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RegionRenderCacheBuilder;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HandlerRenderError 
{
	public static ArrayList<FaceRenderer> erroredPositions = new ArrayList<>();
	private static RegionRenderCacheBuilder cache = new RegionRenderCacheBuilder();
	
	@SubscribeEvent
	public void playerOverLay(RenderWorldLastEvent event)
	{

		for(FaceRenderer render: erroredPositions)
			if(render.getPosition().distanceSq(Minecraft.getMinecraft().player.posX, Minecraft.getMinecraft().player.posY, Minecraft.getMinecraft().player.posZ) < 400)
				if(render.getFace() == null)
					HarshenClientUtils.renderFullBoxAt(render.getPosition(), event.getPartialTicks(), new Vector4f(1, 0, 0, 1), 5);
				else
					HarshenClientUtils.renderFaceAt(render.getFace(), render.getPosition(), event.getPartialTicks(), new Vector4f(1, 0, 0, 1), 5);
		
		IBlockState state = Blocks.STONE.getDefaultState();
		GlStateManager.pushMatrix();
        BlockPos pos = new BlockPos(0, 100, 0);
        EntityPlayer entityplayer = Minecraft.getMinecraft().player;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        double d0 = entityplayer.lastTickPosX + (entityplayer.posX - entityplayer.lastTickPosX) * (double)event.getPartialTicks();
        double d1 = entityplayer.lastTickPosY + (entityplayer.posY - entityplayer.lastTickPosY) * (double)event.getPartialTicks();
        double d2 = entityplayer.lastTickPosZ + (entityplayer.posZ - entityplayer.lastTickPosZ) * (double)event.getPartialTicks();
        Tessellator tess = Tessellator.getInstance();
        BufferBuilder vb = tess.getBuffer();
        vb.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
        vb.setTranslation(-d0, -d1, -d2);
        World world = Minecraft.getMinecraft().world;
        BlockRendererDispatcher dispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
        dispatcher.renderBlock(state, pos, world, vb);
        float j = world.getLight(pos);
        System.out.println(j);
        for(int i = 0; i < vb.getVertexCount(); i++)
        	vb.putColorMultiplier(j, j, j * (world.getLightFromNeighborsFor(EnumSkyBlock.SKY, pos) == j ? 1.5f : 1f), i);
        tess.draw();
        Tessellator.getInstance().getBuffer().setTranslation(0, 0, 0);
        GlStateManager.popMatrix();
		
		
	}
}
