package kenijey.harshencastle.worldgenerators.pontus;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.fluids.HarshenFluids;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraftforge.fluids.BlockFluidBase;

public class PontusRavineGenerator extends MapGenRavine
{
	@Override
	protected void digBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ, boolean foundTop) {
		data.setBlockState(x, y, z, y - 1 < 10 ? HarshenFluids.harshing_water_block.getDefaultState() : AIR);
	}
}
