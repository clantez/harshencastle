package kenijey.harshencastle.items;

import java.util.List;

import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.HarshenSounds;
import kenijey.harshencastle.base.BaseHarshenBow;
import kenijey.harshencastle.enums.entities.EnumHarshenArrowTypes;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class SoulRipperBow extends BaseHarshenBow
{
	public SoulRipperBow()
	{
		super(EnumHarshenArrowTypes.RIPPER);
		setRegistryName("soul_ripper_bow");
		setUnlocalizedName("soul_ripper_bow");
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("\u00A73" + new TextComponentTranslation("soulripperbow1").getFormattedText());
		tooltip.add("\u00A74" + new TextComponentTranslation("soulripperbow2").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	protected SoundEvent bowSound() {
		return HarshenSounds.RIPPER_SHOOT;
	}

	@Override
	public int getMaxDamage() {
		return 1988;
	}

	@Override
	public double additionDamage() {
		return 1.7;
	}
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == HarshenItems.SOUL_INFUSED_INGOT;
	}
}
