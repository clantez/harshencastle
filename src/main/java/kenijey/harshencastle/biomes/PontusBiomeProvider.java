package kenijey.harshencastle.biomes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;

public class PontusBiomeProvider extends BiomeProvider
{
	
	@Override
	public Biome getBiome(BlockPos pos) {
		return getbiomeFromBlockPos(pos);
	}
	
	@Override
	public Biome[] getBiomesForGeneration(Biome[] biomes, int x, int z, int width, int height) {
		if (biomes == null || biomes.length < width * height)
        {
            biomes = new Biome[width * height];
        }

        Arrays.fill(biomes, 0, width * height, getbiomeFromBlockPos(new BlockPos(x, 100, z)));
        return biomes;
	}
	
	@Override
	public Biome[] getBiomes(Biome[] oldBiomeList, int x, int z, int width, int depth) {
		return getBiomesForGeneration(oldBiomeList, x, z, width, depth);
	}
	
	@Override
	public BlockPos findBiomePosition(int x, int z, int range, List<Biome> biomes, Random random) {
		return null;
	}
	
	@Override
	public Biome[] getBiomes(@Nullable Biome[] listToReuse, int x, int z, int width, int length, boolean cacheFlag)
    {
        return this.getBiomes(listToReuse, x, z, width, length);
    }
	
	private Biome getbiomeFromBlockPos(BlockPos pos)
	{
		return pos.add(0, pos.getX() + 100, 0).getDistance(0, 100, 0) > 7500 ? HarshenBiomes.pontus_outer_biome : HarshenBiomes.pontus_dimensional_biome;
	}
}
