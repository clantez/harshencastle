package kenijey.harshencastle.itemrenderer;

import javax.vecmath.Vector3f;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.base.BaseItemRendererActive;
import kenijey.harshencastle.enums.particle.EnumHarshenParticle;
import kenijey.harshencastle.tileentity.TileEntityHarshenDimensionalPedestal;
import net.minecraft.init.Items;
import net.minecraft.util.math.Vec3d;

public class RendererDimensionalPedestal extends BaseItemRendererActive<TileEntityHarshenDimensionalPedestal>
{
	@Override
	protected void moveMore(TileEntityHarshenDimensionalPedestal te) {
		if(te.getItem().getItem() != Items.AIR)
			for(int i = 0; i < 15; i++)
				HarshenCastle.proxy.spawnParticle(EnumHarshenParticle.ITEM, new Vec3d(te.getPos()).addVector(0.5, 0.8, 0.5), Vec3d.ZERO, 0.85f, false, te.getItem());
	}

	@Override
	protected Vector3f movePos() {
		return new Vector3f(0.5f,0.65f,0.5f);
	}
}
