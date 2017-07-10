package kenijey.harshencastle.biomes;

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
	public static final int pontusID = 170;
	
	public static void initBiomes()
	{
		pontus_dimensional_biome = new PontusDimensionBiome();
		Biome.registerBiome(pontusID, "Pontus", pontus_dimensional_biome);
		ForgeRegistries.BIOMES.register(pontus_dimensional_biome);
	}
	
//	public static void regBiome()
//	{
//		BiomeManager.addSpawnBiome(pontus_dimensional_biome);
//	}
	
}
