package kenijey.harshencastle.items;

import java.util.List;

import kenijey.harshencastle.HarshenSounds;
import kenijey.harshencastle.base.BaseHarshenBow;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class SoulRipperBow extends BaseHarshenBow
{
	public SoulRipperBow()
	{
		setUnlocalizedName("soul_ripper_bow");
		setRegistryName("soul_ripper_bow");
	}
	
	@Override
	protected boolean isRipper() {
		return true;
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("\u00A74" + new TextComponentTranslation("soulripperbow1").getFormattedText());
		tooltip.add("\u00A74" + new TextComponentTranslation("soulripperbow2").getFormattedText());
		tooltip.add("\u00A73" + new TextComponentTranslation("soulripperbow3").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	protected SoundEvent bowSound() {
		return HarshenSounds.ripperShoot;
	}

	@Override
	public int getMaxDamage() {
		return 1988;
	}

	@Override
	public double additionDamage() {
		return 1.5;
	}
}
