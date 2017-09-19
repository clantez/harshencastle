package kenijey.harshencastle.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class JewelDirt extends Block
{
	public JewelDirt()
	{
		super(Material.GRASS);
		setUnlocalizedName("jewel_dirt");
        setRegistryName("jewel_dirt");
        setHarvestLevel("shovel", 2);
        blockSoundType = blockSoundType.GROUND;
	}

}
