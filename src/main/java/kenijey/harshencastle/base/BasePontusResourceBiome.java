package kenijey.harshencastle.base;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;

public abstract class BasePontusResourceBiome extends Biome
{
	public BasePontusResourceBiome(BiomeProperties properties) {
		super(properties);
	}
	
	public abstract int getLevel();
}
