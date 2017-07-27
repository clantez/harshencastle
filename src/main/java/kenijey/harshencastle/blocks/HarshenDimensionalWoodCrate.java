package kenijey.harshencastle.blocks;

import kenijey.harshencastle.base.BaseHarshenBlockCastle;
import net.minecraft.block.material.Material;

public class HarshenDimensionalWoodCrate extends BaseHarshenBlockCastle
{
	public HarshenDimensionalWoodCrate()
	{
		super(Material.WOOD);
        setUnlocalizedName("harshen_dimensional_wood_crate");
        setRegistryName("harshen_dimensional_wood_crate");
        blockSoundType = blockSoundType.WOOD;
	}
}
