package kenijey.harshencastle.items;

import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public class SoulHarsherPickaxe extends ItemPickaxe
{
	public SoulHarsherPickaxe()
	{
		super(EnumHelper.addToolMaterial("soul_harsher_pickaxe", 3, 2000, 13.5f, 2.5f, 30));
		setUnlocalizedName("soul_harsher_pickaxe");
		setRegistryName("soul_harsher_pickaxe");
		setNoRepair();
	}
	
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("\u00A73" + new TextComponentTranslation("pickaxe1").getFormattedText());
		tooltip.add("\u00a73" + new TextComponentTranslation("pickaxe2").getFormattedText());
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

}
