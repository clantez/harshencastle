package kenijey.harshencastle.items;

import java.util.List;

import kenijey.harshencastle.base.BaseHarshenBow;
import kenijey.harshencastle.enums.entities.EnumHarshenArrowTypes;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class EnionBow extends BaseHarshenBow
{
	public EnionBow()
	{
		super(EnumHarshenArrowTypes.NORMAL);
		setUnlocalizedName("enion_bow");
		setRegistryName("enion_bow");
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("\u00A7b" + new TextComponentTranslation("enionbow1").getFormattedText());
		tooltip.add("\u00A7b" + new TextComponentTranslation("enionbow2").getFormattedText());
		tooltip.add(new TextComponentTranslation(" ").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public int getMaxDamage() {
		return 1837;
	}

	@Override
	public double additionDamage() {
		return 2.0D;
	}
}
