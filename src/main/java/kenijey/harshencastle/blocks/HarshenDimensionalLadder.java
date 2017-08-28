package kenijey.harshencastle.blocks;

import net.minecraft.block.BlockLadder;

public class HarshenDimensionalLadder extends BlockLadder
{
	public HarshenDimensionalLadder() {
		setTickRandomly(true);
        setUnlocalizedName("harshen_dimensional_ladder");
        setRegistryName("harshen_dimensional_ladder");
        setHarvestLevel("pickaxe", 3);
        setHardness(3000.0f);
        setResistance(3000.0f);
        blockSoundType = blockSoundType.LADDER;
	}
}
