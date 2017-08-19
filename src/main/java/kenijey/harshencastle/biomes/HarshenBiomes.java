package kenijey.harshencastle.biomes;

import java.util.ArrayList;

import kenijey.harshencastle.base.BasePontusResourceBiome;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class HarshenBiomes 
{
	public static void register()
	{
		initBiomes();
	}
	
	public static BasePontusResourceBiome pontus_dimensional_biome;
	public static BasePontusResourceBiome pontus_outer_biome;
		
	public static void initBiomes()
	{
		pontus_dimensional_biome = initAndRegBiome(new PontusBiome());
		pontus_outer_biome = initAndRegBiome(new PontusOuterBiome());
	}
	
	private static BasePontusResourceBiome initAndRegBiome(BasePontusResourceBiome biome)
	{
		ForgeRegistries.BIOMES.register(biome);
		return biome;
	}
	
//	public static void regBiome()
//	{
//		BiomeManager.addSpawnBiome(pontus_dimensional_biome);
//	}
	
}
