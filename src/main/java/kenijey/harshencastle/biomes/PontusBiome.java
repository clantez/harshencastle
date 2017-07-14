package kenijey.harshencastle.biomes;

import java.util.Random;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.worldgenerators.pontus.NullGenerator;
import kenijey.harshencastle.worldgenerators.pontus.PontusWorldGeneratorDestroyedPlants;
import kenijey.harshencastle.worldgenerators.pontus.PontusWorldGeneratorStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class PontusBiome extends Biome
{

	private static final IBlockState DIRT = HarshenBlocks.harshen_dimensional_dirt.getDefaultState();
	private static final IBlockState STONE = HarshenBlocks.harshen_dimensional_rock.getDefaultState();
	
	public PontusBiome() {
		super(new Biome.BiomeProperties("Pontus").setTemperature(5f).setRainDisabled().setBaseHeight(0.7f).setHeightVariation(2f));
		
		setRegistryName(HarshenCastle.MODID, "Pontus");
		
		
		this.topBlock = DIRT;
		this.fillerBlock = STONE;
		
		this.spawnableCaveCreatureList.clear();
		this.spawnableCaveCreatureList.add(new SpawnListEntry(EntityEnderman.class, 25, 2, 5));
		
		this.spawnableCreatureList.clear();
		this.spawnableCreatureList.add(new SpawnListEntry(EntityEndermite.class, 50, 1, 10));
		
		this.decorator.flowersPerChunk = 0;
		this.decorator.mushroomsPerChunk = 0;
		this.decorator.reedsPerChunk = 0;
		this.decorator.reedGen = new NullGenerator();
		this.decorator.cactiPerChunk = 0;
		
		
	}
	
	
	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		return new WorldGenTrees(false, 3, HarshenBlocks.pontus_dead_wood.getDefaultState(), HarshenBlocks.pontus_dead_leaves.getDefaultState(), false);
	}
	
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random rand) {
		return new PontusWorldGeneratorDestroyedPlants();
	}
	
	@Override
	public void addDefaultFlowers() {
		
	}
	
	@Override
	public void plantFlower(World world, Random rand, BlockPos pos) {
	}
	
	@Override
	public void addFlower(IBlockState state, int weight) {
	}
	
	@Override
	public void decorate(World worldIn, Random rand, BlockPos pos) {
		super.decorate(worldIn, rand, pos);
		for (int i = 0; i < 13; ++i)
        {
            int j = rand.nextInt(16) + 8;
            int k = rand.nextInt(16) + 8;
            new PontusWorldGeneratorStone().generate(worldIn, rand, worldIn.getTopSolidOrLiquidBlock(pos.add(j, 0, k)));
        }
	}
	
	

}
