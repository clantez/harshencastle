package kenijey.harshencastle.worldgenerators.pontus;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class NullGenerator extends WorldGenerator
{

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		return false;
	}

}
