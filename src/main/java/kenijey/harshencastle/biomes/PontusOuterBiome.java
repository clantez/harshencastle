package kenijey.harshencastle.biomes;

import java.util.Random;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.HarshenCastle;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.base.BaseLargeTreeGenerator;
import kenijey.harshencastle.base.BasePontusResourceBiome;
import kenijey.harshencastle.dimensions.PontusBiomeDecorator;
import kenijey.harshencastle.worldgenerators.pontus.PontusWorldGeneratorDestroyedPlants;
import kenijey.harshencastle.worldgenerators.pontus.PontusWorldGeneratorStone;
import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.BiomeDictionary.Type;

public class PontusOuterBiome extends BasePontusResourceBiome {
		
	public PontusOuterBiome() {
		super("Pontus_Chaotic");
		
		this.spawnableCaveCreatureList.clear();
		this.spawnableCaveCreatureList.add(new SpawnListEntry(EntityEnderman.class, 10, 2, 7));
		
		this.spawnableCreatureList.clear();
		this.spawnableCreatureList.add(new SpawnListEntry(EntityEndermite.class, 100, 5, 17));

		this.decorator.extraTreeChance = 0.5f;		
		
	}
	
	@Override
	public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
		return new BaseLargeTreeGenerator(false, HarshenBlocks.pontus_chaotic_leaves.getDefaultState(), HarshenBlocks.pontus_chaotic_wood);
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
	public int getLevel() {
		return 1;
	}
	
	@Override
	public int distanceStartSpawn() {
		return 7500;
	}


	@Override
	public Block[] getGroundBlocks() {
		return HarshenUtils.blockList(HarshenBlocks.harshen_dimensional_rock, HarshenBlocks.harshen_chaotic_rock);
	}


	@Override
	public Block getMergerBlock() {
		return HarshenBlocks.harshen_chaotic_rock;
	}	
	
	@Override
	public Type[] getTypes() {
		return new Type[]{Type.DRY, Type.MOUNTAIN};
	}	

}
