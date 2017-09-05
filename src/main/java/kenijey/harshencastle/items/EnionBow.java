package kenijey.harshencastle.items;

import java.util.List;

import javax.annotation.Nullable;

import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.base.BaseHarshenBow;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EnionBow extends BaseHarshenBow
{
	public EnionBow()
	{
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
		return 2.1D;
	}
}
