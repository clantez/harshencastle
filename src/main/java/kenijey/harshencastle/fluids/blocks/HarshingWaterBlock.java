package kenijey.harshencastle.fluids.blocks;

import java.util.ArrayList;
import java.util.Arrays;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.base.BaseFluidBlock;
import kenijey.harshencastle.fluids.HarshenFluids;
import kenijey.harshencastle.potions.HarshenPotions;
import net.minecraft.block.Block;
import net.minecraft.potion.PotionEffect;

public class HarshingWaterBlock extends BaseFluidBlock
{

	public HarshingWaterBlock() {
		super(HarshenFluids.harshing_water);
	}

	@Override
	protected ArrayList<PotionEffect> getPotions() {
		return new ArrayList<PotionEffect>(Arrays.asList(new PotionEffect(HarshenPotions.potionHarshed, 250, 1)));
	}

	@Override
	protected Block getBlockWhenSourceHit() {
		return HarshenBlocks.harshen_dimensional_rock;
	}

	@Override
	protected Block getBlockWhenOtherHit() {
		return HarshenBlocks.harshen_dimensional_rock;
	}
	
}
