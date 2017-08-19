package kenijey.harshencastle.biomes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.Nullable;

import kenijey.harshencastle.base.BasePontusResourceBiome;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;

public class PontusBiomeProvider extends BiomeProvider
{
	
	public static HashMap<BasePontusResourceBiome, Integer> distanceWhenStart = new HashMap<>();
	
	public PontusBiomeProvider() {
		distanceWhenStart.put(HarshenBiomes.pontus_dimensional_biome, -1);
		distanceWhenStart.put(HarshenBiomes.pontus_outer_biome, 7500);
	}
		
	@Override
	public Biome getBiome(BlockPos pos) {
		return biomeFromPosition(pos);
	}
	
	@Override
	public Biome[] getBiomesForGeneration(Biome[] biomes, int x, int z, int width, int height) {
		if (biomes == null || biomes.length < width * height)
        {
            biomes = new Biome[width * height];
        }

        Arrays.fill(biomes, 0, width * height, biomeFromPosition(new BlockPos(x, 0, z)));
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
	
	public static Biome biomeFromPosition(BlockPos pos)
	{
		double distance = new BlockPos(pos).getDistance(0, pos.getY(), 0);
		for(int i = 0; i < distanceWhenStart.size(); i ++)
		{
			BasePontusResourceBiome biomeToCheck = biomeList(distanceWhenStart.keySet()).get(i);
			if(i + 1 != distanceWhenStart.size())
			{
				if(distanceWhenStart.get(biomeToCheck) <= distance && distanceWhenStart.get(biomeList(distanceWhenStart.keySet()).get(i + 1)) > distance)
					return biomeToCheck;
				else;
			}
			else
				return biomeList(distanceWhenStart.keySet()).get(biomeList(distanceWhenStart.keySet()).size() - 1);
		}
	
		return HarshenBiomes.pontus_dimensional_biome;
	}
	
	public static double getDistance(BlockPos pos)
	{
		return new BlockPos(pos).getDistance(0, pos.getY(), 0);
	}
	
	public static ArrayList<BasePontusResourceBiome> biomeList(Set<BasePontusResourceBiome> set)
	{
		ArrayList<BasePontusResourceBiome> list = new ArrayList<>();
		ArrayList<BasePontusResourceBiome> finalList = new ArrayList<>();
		ArrayList<Integer> listOrder = new ArrayList<>();
		for(BasePontusResourceBiome biome : set)
		{
			list.add(biome);
			listOrder.add(distanceWhenStart.get(biome));
		}
		Collections.sort(listOrder);
		for(int i : listOrder)
			for(BasePontusResourceBiome biome : list)
				if(distanceWhenStart.get(biome) == i)
					finalList.add(biome);
		return finalList;
	}
	
	public static Biome biomeFromPosition(int chunk_X, int chunk_Z)
	{
		return biomeFromPosition(new BlockPos(chunk_X * 16, 0, chunk_Z * 16));
	}
	
	public static Biome biomeFromPosition(int x, int z, Object n)
	{
		return biomeFromPosition(new BlockPos(x, 0, z));
	}
}
