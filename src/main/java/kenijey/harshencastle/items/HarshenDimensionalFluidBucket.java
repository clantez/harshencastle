package kenijey.harshencastle.items;

import kenijey.harshencastle.fluids.HarshenFluids;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBucket;

public class HarshenDimensionalFluidBucket extends ItemBucket
{

	public HarshenDimensionalFluidBucket() {
		super(HarshenFluids.harshen_dimensional_fluid_block);
		setRegistryName("harshen_dimensional_fluid_bucket");
		setUnlocalizedName("harshen_dimensional_fluid_bucket");
	}
	
}