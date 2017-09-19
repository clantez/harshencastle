package kenijey.harshencastle.itemrenderer;

import javax.vecmath.Vector3f;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.base.BaseItemRendererActive;
import kenijey.harshencastle.enums.particle.EnumHarshenParticle;
import kenijey.harshencastle.particle.ParticleItem;
import kenijey.harshencastle.tileentity.TileEntityHarshenDimensionalPedestal;
import net.minecraft.init.Items;
import net.minecraft.util.math.Vec3d;

public class RendererDimensionalPedestal extends BaseItemRendererActive<TileEntityHarshenDimensionalPedestal>
{
	@Override
	protected void moveMore(TileEntityHarshenDimensionalPedestal te) {

	}

	@Override
	protected Vector3f movePos() {
		return new Vector3f(0.5f,0.65f,0.5f);
	}
}
