package kenijey.harshencastle.itemrenderer;

import javax.vecmath.Vector3f;

import kenijey.harshencastle.base.BaseItemRenderer;
import kenijey.harshencastle.tileentity.TileEntityHarshenDisplayBlock;

public class RendererHarshenDisplayBlock extends BaseItemRenderer<TileEntityHarshenDisplayBlock>
{

	@Override
	protected float getMovementSpeed(TileEntityHarshenDisplayBlock te) {
		return 1.5f;
	}

	@Override
	protected Vector3f movePos() {
		return new Vector3f(0.5f, 1f, 0.5f);
	}
	
}
