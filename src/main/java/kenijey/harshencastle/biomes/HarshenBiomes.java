package kenijey.harshencastle.biomes;

import kenijey.harshencastle.biomes.pontus.PontusBiome;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class HarshenBiomes 
{
	public static void register()
	{
		initBiomes();
	}
	
	public static Biome pontus_dimensional_biome;

	
	public static void initBiomes()
	{
		pontus_dimensional_biome = new PontusBiome();
		ForgeRegistries.BIOMES.register(pontus_dimensional_biome);
	}
	
//	public static void regBiome()
//	{
//		BiomeManager.addSpawnBiome(pontus_dimensional_biome);
//	}
	
}
