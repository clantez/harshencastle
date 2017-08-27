package kenijey.harshencastle.base;

import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.dimensions.PontusBiomeDecorator;
import kenijey.harshencastle.entity.EntitySoulPart;
import net.minecraft.block.Block;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

public abstract class BasePontusResourceBiome extends Biome
{
	public BasePontusResourceBiome(String biomeName) {
		super(new Biome.BiomeProperties(biomeName).setTemperature(5f).setRainDisabled().setBaseHeight(0.7f).setHeightVariation(2f));
		
		setRegistryName(HarshenCastle.MODID, biomeName);
		
		this.spawnableMonsterList.clear();
		this.spawnableMonsterList.add(new SpawnListEntry(EntitySoulPart.class, 2, 1, 1));
		
		this.decorator = new PontusBiomeDecorator();
	}
	
	public abstract int getLevel();
	
	public abstract Block[] getGroundBlocks();
		
	public abstract Block getMergerBlock();
	
	public abstract int distanceStartSpawn();
	
	public abstract BiomeDictionary.Type[] getTypes();
	
	public Block[] getNonHightBlocks()
	{
		return null;
	}
	
	public int getHeightForNonHeightBlocks()
	{
		return 90;
	}
}
