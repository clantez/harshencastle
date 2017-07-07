package kenijey.harshencastle.items;

import kenijey.harshencastle.fluids.HarshenFluids;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBucket;

public class HarshenBucketDimensionalFluid extends ItemBucket
{

	public HarshenBucketDimensionalFluid() {
		super(HarshenFluids.dimensional_fluid_block);
		setRegistryName("harshen_bucket_dimensional_fluid");
		setUnlocalizedName("harshen_bucket_dimensional_fluid");
	}
	
	

}
