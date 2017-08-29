package kenijey.harshencastle.blocks;

import kenijey.harshencastle.HarshenCastle;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class KuanticOre extends Block
{
	public KuanticOre() 
	{
        super(Material.ROCK);
        setUnlocalizedName("kuantic_ore");
        setRegistryName("kuantic_ore");
        setHarvestLevel("pickaxe", 2);
		setHardness(31f);
		setResistance(100f);
		setCreativeTab(HarshenCastle.harshenTab);
    }

	
}
