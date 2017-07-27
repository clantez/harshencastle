package kenijey.harshencastle.entityrender;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.entity.EntitySoullessKnight;
import kenijey.harshencastle.models.ModelSoullessKnight;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;


public class RenderSoullessKnight extends RenderLiving
{
    private static final ResourceLocation TEXTURES = new ResourceLocation(HarshenCastle.MODID, "textures/entity/soulless_knight.png");

    public RenderSoullessKnight(RenderManager manager)
    {
        super(manager, new ModelSoullessKnight(), 0.3F);
        this.addLayer(new LayerHeldItem(this));
    }
    
    protected ResourceLocation getEntityTexture(EntitySoullessKnight entity)
    {
        return TEXTURES;
    }

    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return this.getEntityTexture((EntitySoullessKnight)entity);
    }
}