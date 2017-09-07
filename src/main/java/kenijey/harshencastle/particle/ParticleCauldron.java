package kenijey.harshencastle.particle;

import kenijey.harshencastle.base.BaseHarshenParticle;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ParticleCauldron extends BaseHarshenParticle
{ 	
    public ParticleCauldron(World world, ResourceLocation location, double xCoordIn, double yCoordIn, double zCoordIn, double motionXIn, double motionYIn, double motionZIn, float par14, boolean disableMoving)
    {
        super(world, xCoordIn, yCoordIn, zCoordIn, motionXIn, motionYIn, motionZIn, par14, disableMoving,  location);
    }

	@Override
	protected int getXIndex() {
		return 0;
	}
	
	@Override
	protected int getYIndex() {
		return 0;
	}
}