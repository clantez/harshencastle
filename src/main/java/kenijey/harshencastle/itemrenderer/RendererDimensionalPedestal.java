package kenijey.harshencastle.itemrenderer;

import javax.vecmath.Vector3f;

import kenijey.harshencastle.base.BaseItemRendererActive;
import kenijey.harshencastle.tileentity.TileEntityHarshenDimensionalPedestal;

public class RendererDimensionalPedestal extends BaseItemRendererActive<TileEntityHarshenDimensionalPedestal>
{
	@Override
	protected Vector3f movePos() {
		return new Vector3f(0.5f,0.65f,0.5f);
	}
}
