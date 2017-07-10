package kenijey.harshencastle.biomes;

import java.util.Random;

import kenijey.harshencastle.HarshenBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenTrees;

public class PontusDimensionBiome extends Biome
{

	private static final IBlockState DIRT = HarshenBlocks.harshen_dimensional_dirt.getDefaultState();
	private static final IBlockState STONE = HarshenBlocks.harshen_dimensional_rock.getDefaultState();
	
	public PontusDimensionBiome() {
		super(new Biome.BiomeProperties("pontus").setTemperature(0.2F).setRainDisabled().setBaseHeight(0.7f).setHeightVariation(2f));
		this.topBlock = DIRT;
		this.fillerBlock = STONE;
		
		this.decorator.dirtGen = new WorldGenMinable(DIRT, 20);
		this.decorator.treesPerChunk = 3;
		
		this.spawnableCaveCreatureList.clear();
		this.spawnableCaveCreatureList.add(new SpawnListEntry(EntityEnderman.class, 25, 2, 5));
		
		this.spawnableCreatureList.clear();
		this.spawnableCreatureList.add(new SpawnListEntry(EntityEndermite.class, 50, 1, 10));
	}
	
	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		return new WorldGenTrees(false, 3, HarshenBlocks.pontus_dead_wood.getDefaultState(), HarshenBlocks.pontus_dead_leaves.getDefaultState(), false);
	}
	
	

}
