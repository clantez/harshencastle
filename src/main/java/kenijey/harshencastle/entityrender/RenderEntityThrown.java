package kenijey.harshencastle.entityrender;

import java.util.ArrayList;

import kenijey.harshencastle.HarshenClientUtils;
import kenijey.harshencastle.entity.EntityThrown;
import kenijey.harshencastle.objecthandlers.EntityThrownRendererHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class RenderEntityThrown extends Render<EntityThrown>
{
    private static final ResourceLocation EXPERIENCE_ORB_TEXTURES = new ResourceLocation("textures/entity/experience_orb.png");

    public RenderEntityThrown(RenderManager renderManagerIn)
    {
        super(renderManagerIn);
        this.shadowSize = 0.15F;
        this.shadowOpaque = 0.75F;
    }

    /**
     * Renders the desired {@code T} type Entity.
     */
    public void doRender(EntityThrown entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
    	ArrayList<EntityThrownRendererHelper> helpList = new ArrayList<>();
    	for(EntityThrownRendererHelper helper : pushMap)
    		if(entity.world.getEntityByID(helper.id) != null)
    			entity.world.getEntityByID(helper.id).readFromNBT(helper.compound);
    		else
    			helpList.add(helper);
    	pushMap = helpList;
    	
    	
    	if(entity.isLocation())
    	{
    		GlStateManager.pushMatrix();
            GlStateManager.translate((float)x, (float)y, (float)z);
            this.bindEntityTexture(entity);
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder bufferbuilder = tessellator.getBuffer();
            bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);
            bufferbuilder.pos(-0.5D, -0.25D, 0.0D).tex((double)f, (double)f3).color(255, 255, 255, 255).normal(0.0F, 1.0F, 0.0F).endVertex();
            bufferbuilder.pos(0.5D, -0.25D, 0.0D).tex((double)f1, (double)f3).color(255, 255, 255, 255).normal(0.0F, 1.0F, 0.0F).endVertex();
            bufferbuilder.pos(0.5D, 0.75D, 0.0D).tex((double)f1, (double)f2).color(255, 255, 255, 255).normal(0.0F, 1.0F, 0.0F).endVertex();
            bufferbuilder.pos(-0.5D, 0.75D, 0.0D).tex((double)f, (double)f2).color(255, 255, 255, 255).normal(0.0F, 1.0F, 0.0F).endVertex();
            tessellator.draw();
        	GlStateManager.depthFunc(515);
            super.doRender(entity, x, y, z, entityYaw, partialTicks);
    	}
    	else
    	{
    		GlStateManager.pushMatrix();
            GlStateManager.translate((float)x, (float)y, (float)z);
            GlStateManager.enableRescaleNormal();
            GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate((float)(this.renderManager.options.thirdPersonView == 2 ? -1 : 1) * this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
            this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            if (this.renderOutlines)
            {
                GlStateManager.enableColorMaterial();
                GlStateManager.enableOutlineMode(this.getTeamColor(entity));
            }
            
            Minecraft.getMinecraft().getRenderItem().renderItem(entity.getStack(), ItemCameraTransforms.TransformType.GROUND);

            if (this.renderOutlines)
            {
                GlStateManager.disableOutlineMode();
                GlStateManager.disableColorMaterial();
            }

            GlStateManager.disableRescaleNormal();
            GlStateManager.popMatrix();
    	}
    }
    
    public static ArrayList<EntityThrownRendererHelper> pushMap = new ArrayList<>();

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntityThrown entity)
    {
        return entity.isLocation() ? entity.getLocation() : TextureMap.LOCATION_BLOCKS_TEXTURE;
    }
}