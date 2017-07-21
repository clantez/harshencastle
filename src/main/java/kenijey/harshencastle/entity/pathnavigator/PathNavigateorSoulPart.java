package kenijey.harshencastle.entity.pathnavigator;

import net.minecraft.entity.EntityLiving;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.world.World;

public class PathNavigateorSoulPart extends PathNavigateFlying
{

	public PathNavigateorSoulPart(EntityLiving p_i47412_1_, World p_i47412_2_) {
		super(p_i47412_1_, p_i47412_2_);
	}
	
	@Override
	protected boolean canNavigate() {
		return true;
	}

}
