package kenijey.harshencastle.items;

import kenijey.harshencastle.HarshenBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;

public class LightEmittedSeed extends ItemSeeds
{

	public LightEmittedSeed() {
		super(HarshenBlocks.crop_of_gleam, HarshenBlocks.harshen_dimensional_dirt);
		setUnlocalizedName("light_emitted_seed");
		setRegistryName("light_emitted_seed");	
		}


}
