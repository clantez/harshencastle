package kenijey.harshencastle.entityrender;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.base.BaseRenderBiped;
import kenijey.harshencastle.entity.EntityHarshenSoul;
import kenijey.harshencastle.models.ModelHarshenSoul;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderHarshenSoul extends BaseRenderBiped<EntityHarshenSoul> {

	public RenderHarshenSoul(RenderManager manager) {
		super(manager, new ModelHarshenSoul());
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityHarshenSoul entity) {
		return new ResourceLocation(HarshenCastle.MODID, "textures/entity/harshen_soul.png");
	}

}
