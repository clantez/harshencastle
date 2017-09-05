package kenijey.harshencastle.items;

import javax.annotation.Nullable;

import kenijey.harshencastle.HarshenItems;
import kenijey.harshencastle.base.BaseHarshenBow;
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
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class IronBow extends BaseHarshenBow
{
	public IronBow()
	{
		setUnlocalizedName("iron_bow");
		setRegistryName("iron_bow");
	}
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem() == Items.IRON_INGOT;
	}

	@Override
	public int getMaxDamage() {
		return 1388;
	}

	@Override
	public double additionDamage() {
		return 1.5D;
	}
}
