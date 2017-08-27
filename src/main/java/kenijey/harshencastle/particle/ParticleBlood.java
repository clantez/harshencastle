package kenijey.harshencastle.particle;

import kenijey.harshencastle.base.BaseHarshenParticle;
import kenijey.harshencastle.proxy.ClientProxy;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ParticleBlood extends BaseHarshenParticle
{

	public ParticleBlood(World world, double xCoordIn, double yCoordIn, double zCoordIn, double motionXIn,
			double motionYIn, double motionZIn, float par14, boolean disableMoving) {
		super(world, xCoordIn, yCoordIn, zCoordIn, motionXIn, motionYIn, motionZIn, par14, disableMoving);
	}

	@Override
	protected int getXIndex() {
		return 7;
	}

	@Override
	protected int getYIndex() {
		return 0;
	}

	@Override
	protected ResourceLocation getLocation() {
		return ClientProxy.particleTexturesLocation;
	}
	
	

}
