package kenijey.harshencastle.particle;

import java.util.Random;

import kenijey.harshencastle.base.BaseHarshenParticle;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ParticleCauldron extends BaseHarshenParticle
{ 
	private ResourceLocation location;
	
    public ParticleCauldron(World world, ResourceLocation location, double xCoordIn, double yCoordIn, double zCoordIn, double motionXIn, double motionYIn, double motionZIn, float par14, boolean disableMoving)
    {
        super(world, xCoordIn, yCoordIn, zCoordIn, motionXIn, motionYIn, motionZIn, par14, disableMoving);
        this.location = location;
    }

	@Override
	protected int getXIndex() {
		return new Random().nextInt(8);
	}

	@Override
	protected int getYIndex() {
		return new Random().nextInt(8);
	}

	@Override
	protected ResourceLocation getLocation() {
		return this.location;
	}
}