package kenijey.harshencastle.blocks;

import kenijey.harshencastle.HarshenItems;
import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;

public class CropOfGleam extends BlockCrops
{
	public CropOfGleam() 
	{
		setRegistryName("crop_of_gleam");
		setUnlocalizedName("crop_of_gleam");
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
