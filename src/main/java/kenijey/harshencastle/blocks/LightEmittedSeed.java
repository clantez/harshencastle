package kenijey.harshencastle.blocks;

import kenijey.harshencastle.HarshenItems;
import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;

public class LightEmittedSeed extends BlockCrops
{
	public LightEmittedSeed() 
	{
		setRegistryName("light_emitted_seed");
		setUnlocalizedName("light_emitted_seed");
	}
	
	@Override
	protected Item getSeed() {
		return HarshenItems.light_emitted_seed;
	}
	
	@Override
	protected Item getCrop() {
		return HarshenItems.light_emitted_essence;
	}
}
