package kenijey.harshencastle.base;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public abstract class BaseHarshenSword extends ItemSword
{
	private static ToolMaterial toolMaterial = EnumHelper.addToolMaterial("soul_sword", 3, 5000, 13.5f, 10f, 30);
	public BaseHarshenSword()
	{
		super(toolMaterial);
		setUnlocalizedName(getName());
		setRegistryName(getName());
		setNoRepair();
	}
	
	protected abstract String getSwordType();
	
	protected abstract String getName();
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TextComponentTranslation(" ").getFormattedText());
		String type = this.getSwordType();
		tooltip.add("\u00A73" + new TextComponentTranslation(type + "1").getFormattedText());
		tooltip.add("\u00a73" + new TextComponentTranslation(type + "2").getFormattedText());
		tooltip.add(new TextComponentTranslation(" ").getFormattedText());
		tooltip.add("\u00a74" + new TextComponentTranslation(type + "3").getFormattedText());
		tooltip.add("\u00a74" + new TextComponentTranslation(type + "4").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
}
