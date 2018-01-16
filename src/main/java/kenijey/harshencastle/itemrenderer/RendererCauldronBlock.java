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
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
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
		super.render(te, x, y, z, partialTicks, destroyStage, alpha);
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
        }
		GlStateManager.pushMatrix();
        {
        	GlStateManager.translate(x, y, z);
    		GlStateManager.scale(te.getSize(), te.getSize(), te.getSize());
    		for(int i = 0; i < te.handler.getSlots();i ++)
            {
            	if(!te.handler.getStackInSlot(i).isEmpty())
            	{
            		double l_move = levelMove.get(te.getPos()) / te.getMaxLevels();
            		l_move *= 10 * 30;
            		l_move = (int) l_move;
            		l_move /= 10 * 30;
            		double y_pos = Math.sin(serialMove.get(te.getPos()) / 50d) / 50d + 0.2f + (l_move * te.getSize() * 0.75f) + (te.z_Positions[i] + te.x_Positions[i]) / 50;
            		if(te.getLevel() == 0)
            			y_pos = 0.02f * te.getSize();  		
            		y_pos /= te.getSize();
            		y_pos += 0.025;
            		float inter_size = 0.6f - (0.05f * te.getSize());
            		GlStateManager.translate((te.x_Positions[i] * (1 - inter_size)) + (inter_size / 2f), y_pos, (te.z_Positions[i] * (1 - inter_size)) + (inter_size / 2f));
                	EntityItem item = new EntityItem(getWorld(), 0, 0, 0, te.handler.getStackInSlot(i));
                	item.hoverStart = 0f;
                    GlStateManager.scale(1f / te.getSize(), 1f / te.getSize(), 1f / te.getSize());
                	GlStateManager.rotate(90, 1, 0, 0);
                	GlStateManager.rotate(te.y_Rotation[i], 0, 0, 1);
        			Minecraft.getMinecraft().getRenderManager().renderEntity(item, 0f, 0f, 0f, 0f, 0f, false);
                	GlStateManager.rotate(-te.y_Rotation[i], 0, 0, 1);
                    GlStateManager.scale(te.getSize(), te.getSize(), te.getSize());
                	GlStateManager.rotate(-90, 1, 0, 0);
        			GlStateManager.translate(-((te.x_Positions[i] * (1 - inter_size)) + (inter_size / 2f)), -y_pos, -((te.z_Positions[i] * (1 - inter_size)) + (inter_size / 2f)));
            	}
            }
            GlStateManager.disableLighting();
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);
            this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            World world = getWorld();
            GlStateManager.translate(-te.getPos().getX(), -te.getPos().getY(), -te.getPos().getZ());
            Tessellator tessellator = Tessellator.getInstance();
            tessellator.getBuffer().begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
            if(te.isLeader() && world != null)
    	        Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelRenderer().renderModel(world, CauldronBlock.MODELS.get(te.getSize()),
    	        		world.getBlockState(te.getPos()), te.getPos(), Tessellator.getInstance().getBuffer(), false);
            tessellator.draw();
            if(te.getFluid() != null && te.getFluid().getStateOrLoc() instanceof ResourceLocation)
            	this.bindTexture((ResourceLocation) te.getFluid().getStateOrLoc());
            GlStateManager.translate(te.getPos().getX(), te.getPos().getY(), te.getPos().getZ());
            GlStateManager.enableLighting();
        	GlStateManager.translate(-x, -y, -z);
        	if(te.isLeader())
			{
    			if(te.getLevel() > 0 && te.getFluid() != null)
    				particleMap.put(te.getPos(), HarshenCastle.proxy.spawnParticle(EnumHarshenParticle.CAULDRON_LIQUID, 
    						new Vec3d(te.getPos()).addVector(te.getSize() / 2f, 
    								Math.sin(serialMove.get(te.getPos()) / 50d) / 50d + 0.3f + ((levelMove.get(te.getPos()) / te.getMaxLevels()) * te.getSize() * 0.75f), te.getSize() / 2f), 
    						Vec3d.ZERO, te.getSize() / 2.5f, true, te.getFluid().getStateOrLoc()));
			}
        }
    	GlStateManager.popMatrix();

	}
}
