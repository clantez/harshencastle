package kenijey.harshencastle.fluids.blocks;

import java.util.ArrayList;

import kenijey.harshencastle.HarshenBlocks;
import kenijey.harshencastle.HarshenUtils;
import kenijey.harshencastle.base.BaseFluidBlock;
import kenijey.harshencastle.fluids.HarshenFluids;
import kenijey.harshencastle.potions.HarshenPotions;
import net.minecraft.block.Block;
import net.minecraft.potion.PotionEffect;

public class HarshingWaterBlock extends BaseFluidBlock
{

	public HarshingWaterBlock() {
		super(HarshenFluids.HARSHING_WATER);
	}

	@Override
	protected ArrayList<PotionEffect> getPotions() {
		return new ArrayList<PotionEffect>(HarshenUtils.toArray(new PotionEffect(HarshenPotions.potionHarshed, 250, 1)));
	}

	@Override
	protected Block getBlockWhenSourceHit() {
		return HarshenBlocks.HARSHEN_DIMENSIONAL_ROCK;
	}

	@Override
	protected Block getBlockWhenOtherHit() {
		return HarshenBlocks.HARSHEN_DIMENSIONAL_ROCK;
	}
	
}
