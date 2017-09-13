package kenijey.harshencastle.structures;

import kenijey.harshencastle.base.HarshenStructure;
import kenijey.harshencastle.dimensions.DimensionPontus;
import net.minecraft.util.math.BlockPos;

public class TreeDome extends HarshenStructure
{

	public TreeDome() {
		super("pontus/ambient", "inside_dome", new BlockPos(-8, 0, -12), 0.001f, true, DimensionPontus.DIMENSION_ID);
	}

}
