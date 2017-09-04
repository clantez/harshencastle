package kenijey.harshencastle.handlers.client;

import java.util.ArrayList;

import kenijey.harshencastle.blocks.HarshenDimensionalFlatPlate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HandlerFlatPlateRenderer 
{
	
	public static ArrayList<BlockPos> platePositions = new ArrayList<BlockPos>();
	
	@SubscribeEvent
	public void lastRender(RenderWorldLastEvent event)
	{
		if(!Minecraft.getMinecraft().player.capabilities.isCreativeMode)
			return;
		ArrayList<BlockPos> nonRemovedPlates = new ArrayList<>();
		for(BlockPos pos : platePositions)
			if(Minecraft.getMinecraft().world.getBlockState(pos).getBlock() instanceof HarshenDimensionalFlatPlate)
				nonRemovedPlates.add(renderAt(pos, event.getPartialTicks()));
		platePositions = nonRemovedPlates;
	}
	
	private BlockPos renderAt(BlockPos pos, float partialTicks)
	{
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		EntityPlayer entityplayer = Minecraft.getMinecraft().player;
		entityplayer.isGlowing();
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
        bufferbuilder.pos(x, y, z).color(0f, 0f, 1f, 1f).endVertex();
        bufferbuilder.pos(x, y, z + 1).color(0f, 0f, 1f, 1f).endVertex();
        bufferbuilder.pos(x + 1, y, z + 1).color(0f, 0f, 1f, 1f).endVertex();
        bufferbuilder.pos(x + 1, y, z).color(0f, 0f, 1f, 1f).endVertex();
        bufferbuilder.pos(x, y, z).color(0f, 0f, 1f, 1f).endVertex();
        tessellator.draw();
        Tessellator.getInstance().getBuffer().setTranslation(0, 0, 0);
        GlStateManager.glLineWidth(1.0F);
        GlStateManager.enableBlend();
        GlStateManager.enableTexture2D();
        GlStateManager.depthFunc(515);
        return pos;
	}
}
