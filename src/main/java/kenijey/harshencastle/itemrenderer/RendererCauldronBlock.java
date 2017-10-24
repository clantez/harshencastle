package kenijey.harshencastle.itemrenderer;

import java.util.HashMap;

import org.lwjgl.opengl.GL11;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.blocks.CauldronBlock;
import kenijey.harshencastle.enums.particle.EnumHarshenParticle;
import kenijey.harshencastle.particle.ParticleCauldronTop;
import kenijey.harshencastle.tileentity.TileEntityCaulronBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class RendererCauldronBlock extends TileEntitySpecialRenderer<TileEntityCaulronBlock>
{
	
	private HashMap<BlockPos, Particle> particleMap = new HashMap<>();
	private HashMap<BlockPos, Double> levelMove = new HashMap<>();
	private HashMap<BlockPos, Integer> serialMove = new HashMap<>();
	
	@Override
	public void render(TileEntityCaulronBlock te, double x, double y, double z, float partialTicks, int destroyStage,
			float alpha) {	
		if(te.isLeader())
		{
			if((ParticleCauldronTop)particleMap.get(te.getPos()) != null)
				((ParticleCauldronTop)particleMap.get(te.getPos())).kill();
			if(!levelMove.containsKey(te.getPos()))
				levelMove.put(te.getPos(), (double) te.getLevel());
			if(!serialMove.containsKey(te.getPos()))
				serialMove.put(te.getPos(), 0);
			serialMove.put(te.getPos(), serialMove.get(te.getPos()) + 1);
			if(levelMove.get(te.getPos()).floatValue() != te.getLevel())
			{
				float moveBy = 0.08f;
				levelMove.put(te.getPos(), levelMove.get(te.getPos()) + (Math.min(levelMove.get(te.getPos()), te.getLevel()) == te.getLevel()? - moveBy : moveBy));
			}
			if(te.getLevel() > 0 && te.getFluid() != null)
				particleMap.put(te.getPos(), HarshenCastle.proxy.spawnParticle(EnumHarshenParticle.CAULDRON_LIQUID, new Vec3d(te.getPos()).addVector(0.5D, MathHelper.clamp(Math.sin(serialMove.get(te.getPos()) / 1d) / 50d + 0.05D + levelMove.get(te.getPos()) / 4d, 0.2D, 1D), 0.5D), Vec3d.ZERO, 0.1f, true, te.getFluid().getStateOrLoc()));
		}
        GlStateManager.pushMatrix();
        {
        	GlStateManager.translate(x, y, z);
            GlStateManager.disableLighting();
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);
            this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            World world = getWorld();
    		int scaleSize = (te.getSize() - TileEntityCaulronBlock.MIN_LEVELS) + 2;
    		GlStateManager.scale(scaleSize, scaleSize, scaleSize);
            GlStateManager.translate(-te.getPos().getX(), -te.getPos().getY(), -te.getPos().getZ());
            Tessellator tessellator = Tessellator.getInstance();
            tessellator.getBuffer().begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
            if(te.isLeader() && world != null)
    	        Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelRenderer().renderModel(world, CauldronBlock.MODELS.get(te.getSize()),
    	        		world.getBlockState(te.getPos()), te.getPos(), Tessellator.getInstance().getBuffer(), false);
            tessellator.draw();
            GlStateManager.disableBlend();
            GlStateManager.enableLighting();
            GlStateManager.translate(te.getPos().getX(), te.getPos().getY(), te.getPos().getZ());
        	GlStateManager.translate(-x, -y, -z);
        }
    	GlStateManager.popMatrix();

	}
}
