package kenijey.harshencastle.biomes;

import java.util.Random;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.base.BasePontusResourceBiome;
import kenijey.harshencastle.worldgenerators.pontus.PontusWorldGeneratorDestroyedPlants;
import kenijey.harshencastle.worldgenerators.pontus.PontusWorldGeneratorStone;
import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.BiomeDictionary.Type;

public class PontusBiome extends BasePontusResourceBiome
{
	
	public PontusBiome() {
		super("Pontus");
		
		this.spawnableCaveCreatureList.clear();
		this.spawnableCaveCreatureList.add(new SpawnListEntry(EntityEnderman.class, 25, 2, 5));
		
		this.spawnableCreatureList.clear();
		this.spawnableCreatureList.add(new SpawnListEntry(EntityEndermite.class, 50, 1, 10));	
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
	public void decorate(World worldIn, Random rand, BlockPos pos) {
		super.decorate(worldIn, rand, pos);
		for (int i = 0; i < 13; ++i)
        {
            int j = rand.nextInt(16) + 8;
            int k = rand.nextInt(16) + 8;
            new PontusWorldGeneratorStone().generate(worldIn, rand, worldIn.getTopSolidOrLiquidBlock(pos.add(j, 0, k)));
        }
	}
	
	@Override
	public Block[] getNonHightBlocks() {
		return HarshenUtils.blockList(HarshenBlocks.harshen_dimensional_dirt);
	}

	@Override
	public int getLevel() {
		return 0;
	}

	@Override
	public int distanceStartSpawn() {
		return -1;
	}


	@Override
	public Block[] getGroundBlocks() {
		return HarshenUtils.blockList(HarshenBlocks.harshen_dimensional_rock, HarshenBlocks.harshen_dimensional_dirt);
	}


	@Override
	public Block getMergerBlock() {
		return HarshenBlocks.harshen_dimensional_dirt;
	}


	@Override
	public Type[] getTypes() {
		return new Type[]{Type.DRY, Type.HILLS};
	}	

}
