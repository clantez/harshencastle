package kenijey.harshencastle.items;

import java.util.List;

import kenijey.harshencastle.base.BaseHarshenBow;
import kenijey.harshencastle.enums.entities.EnumHarshenArrowTypes;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class EnderBow extends BaseHarshenBow
{
	public EnderBow()
	{
		super(EnumHarshenArrowTypes.ENDER);
		setRegistryName("ender_bow");
		setUnlocalizedName("ender_bow");
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("\u00A73" + new TextComponentTranslation("enderbow1").getFormattedText());
		tooltip.add("\u00A73" + new TextComponentTranslation("enderbow2").getFormattedText());
		tooltip.add("\u00A73" + new TextComponentTranslation("enderbow3").getFormattedText());
		tooltip.add("");
		tooltip.add("\u00A73" + new TextComponentTranslation("enderbow4").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	protected SoundEvent bowSound() {
		return SoundEvents.ENTITY_ENDERMEN_TELEPORT;
	}

	@Override
	public int getMaxDamage() {
		return 1938;
	}

	@Override
	public double additionDamage() {
		return 1.2D;
	}
	
	@Override
	protected boolean useDefaultArrow() {
		return true;
	}
}
