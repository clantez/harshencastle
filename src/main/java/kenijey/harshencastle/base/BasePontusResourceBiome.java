package kenijey.harshencastle.base;

import net.minecraft.block.Block;
import net.minecraft.world.biome.Biome;

public abstract class BasePontusResourceBiome extends Biome
{
	public BasePontusResourceBiome(BiomeProperties properties) {
		super(properties);
	}
	
	public abstract int getLevel();
	
	public abstract Block[] getGroundBlocks();
	
	public abstract Block getMergerBlock();
	
	public abstract int distanceStartSpawn();
}
