package kenijey.harshencastle.blocks;

import net.minecraft.block.BlockLadder;
import net.minecraft.block.material.Material;

public class HarshenDimensionalLadder extends BlockLadder
{
	public HarshenDimensionalLadder() {
		setTickRandomly(true);
        setUnlocalizedName("harshen_dimensional_ladder");
        setRegistryName("harshen_dimensional_ladder");
        setHarvestLevel("pickaxe", 3);
        setHardness(2500.0f);
        setResistance(2500.0f);
	}
}
