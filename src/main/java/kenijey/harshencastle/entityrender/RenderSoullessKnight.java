package kenijey.harshencastle.entityrender;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.base.BaseRenderBiped;
import kenijey.harshencastle.entity.EntitySoullessKnight;
import kenijey.harshencastle.models.ModelSoullessKnight;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;


public class RenderSoullessKnight extends BaseRenderBiped<EntitySoullessKnight>
{
    public RenderSoullessKnight(RenderManager manager)
    {
        super(manager, new ModelSoullessKnight());
    }

	@Override
	protected ResourceLocation getEntityTexture(EntitySoullessKnight entity) {
		return new ResourceLocation(HarshenCastle.MODID, "textures/entity/soulless_knight.png");
	}
    
}