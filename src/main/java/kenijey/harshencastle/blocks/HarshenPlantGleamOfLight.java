package kenijey.harshencastle.blocks;

import kenijey.harshencastle.HarshenItems;
import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;

public class HarshenPlantGleamOfLight extends BlockCrops
{
	public HarshenPlantGleamOfLight() 
	{
		setRegistryName("harshen_gleam_light");
		setUnlocalizedName("harshen_gleam_light");
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
