package kenijey.harshencastle.structures;

import kenijey.harshencastle.base.HarshenStructure;
import kenijey.harshencastle.dimensions.DimensionPontus;

public class TreeDome extends HarshenStructure
{

	public TreeDome() {
		super("pontus/ambient", "inside_dome", 0.001f, true, DimensionPontus.DIMENSION_ID);
	}

}
