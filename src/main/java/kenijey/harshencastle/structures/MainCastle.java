package kenijey.harshencastle.structures;

import java.util.Random;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.HarshenLootTables;
import kenijey.harshencastle.base.HarshenStructure;
import kenijey.harshencastle.worldgenerators.castle.ChestGenerator;
import kenijey.harshencastle.worldgenerators.castle.MazeGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MainCastle extends HarshenStructure
{
	public MainCastle() {
		super("main","castle", new BlockPos(-36, -20, 1), 1f, false, 0);
	}
	
	@Override
	public boolean canLoadAt(int dimension, int chunkX, int chunkZ) {
		return false;
	}
	
	@Override
	public boolean addPositionOnGenerate() {
		return true;
	}
	
	@Override
	public void preAddition(World world, BlockPos pos, Random random) {
		BlockPos castleSize = getRelativeSize(world, pos);
		for(int i = 0; i < 4; i++)
			new MazeGenerator(new BlockPos(castleSize.getX(), 3, castleSize.getZ()), HarshenBlocks.harshen_dimensional_stone.getDefaultState(), 0.35f).generate(world, random, pos.add(1, 1 + (i * 4), 2));
	}
	
	@Override
	public void postAddition(World world, BlockPos pos, Random random) {
		BlockPos castleSize = getRelativeSize(world, pos);
		for(int i = 0; i < 3; i++)
			new ChestGenerator(castleSize, 0.015f, HarshenLootTables.harshen_castle, true).generate(world, random, pos.add(1, 1 + (i * 4), 2));
		BlockPos[] possOfFillableChests = {new BlockPos(9, 20, 36), new BlockPos(15, 20, 40), new BlockPos(15, 20, 40), new BlockPos(19, 20, 31)};
		for(BlockPos chestPos : possOfFillableChests)
			new ChestGenerator(BlockPos.ORIGIN, 1f, HarshenLootTables.harshen_castle, false).generate(world, random, pos.add(chestPos));
	}
	
	private BlockPos getRelativeSize(World world, BlockPos pos)
	{
		return size.add(-1, 0, -2);
	}
}
