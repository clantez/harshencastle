package kenijey.harshencastle.items;

import java.util.List;

import kenijey.harshencastle.HarshenBlocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class LightEmittedSeed extends ItemSeeds
{

	public LightEmittedSeed() {
		super(HarshenBlocks.CROP_OF_GLEAM, HarshenBlocks.HARSHEN_DIMENSIONAL_DIRT);
		setUnlocalizedName("light_emitted_seed");
		setRegistryName("light_emitted_seed");	
		}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("\u00A73" + new TextComponentTranslation("seed1").getFormattedText());
		tooltip.add("\u00a73" + new TextComponentTranslation("seed2").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

}
